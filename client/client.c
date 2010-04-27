#include "global.h"
#include "log.h"
#include "signals.h"
#include "database.h"
#include "configuration.h"
#include "safeio.h"
#include "mutex.h"
#include "md5sum.h"

#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <stdio.h>
#include <stdarg.h>
#include <time.h>


//This array stores the arguments for a solver run we execute via execve
static char* jobArgs[4]={NULL, NULL, NULL, NULL};

//An array for keeping track of the jobs we're currently processing
static job* jobs;
static int jobsLen;

//The path where we want to create solvers, instances and temporary files
static char* basename="./";

//The length of basename without the terminating '\0' character
static int basenameLen;

//The timeout in seconds for each solver run
int timeOut;


//Returns a unique file name based on pid. The string is located in static memory
//and must not be freed.
char* pidToFileName(pid_t pid) {
	static char fileName[20];
	snprintf(fileName, 20, "%ld.tmp", (long)pid);
	return fileName;
}

//Prepend fileName by basename. On success, the function returns a pointer
//to the string in newly allocated memory that needs to be freed.
//If the memory allocation fails, the return value is NULL.
char* prependBasename(const char* fileName) {
	char* str;
	int fileNameLen=strlen(fileName);

	str=malloc(basenameLen+fileNameLen+1);
	if(str==NULL)
		return NULL;
	strcpy(str, basename);
	strcpy(str+basenameLen, fileName);
	str[basenameLen+fileNameLen]='\0';

	return str;
}

//Test if a file exists
int fileExists(const char* fileName) {
	struct stat buf;
	if(stat(fileName, &buf)==-1 && errno==ENOENT)
		return 0;
	return 1;
}

//Create a file and verify the md5 sum
status createFile(const char* fileName, const char* content, size_t contentLen, const char* md5sum, mode_t mode) {
	FILE* dst;
	unsigned char md5Buffer[16];
	char md5String[33];
	int i;
	char* md5StringPtr;

	//Create the file
	dst=fopen(fileName, "w+");
	if(dst==NULL) {
		logError("(1) Unable to open %s: %s\n", fileName, strerror(errno));
		return sysError;
	}

	//Write the file content. There's no need to check for errors in fwrite here;
	//if something goes wrong, we'll get an error in the md5 check anyway.
	fwrite(content, sizeof(char), contentLen, dst);

	//Verify the md5sum
	rewind(dst);
	if(md5_stream(dst, &md5Buffer)!=0) {
		logError("Error in md5_stream()\n");
		fclose(dst);
		return sysError;
	}
	for(i=0, md5StringPtr=md5String; i<16; ++i, md5StringPtr+=2)
		sprintf(md5StringPtr, "%02x", md5Buffer[i]);
	md5String[32]='\0';
	if(strcasecmp(md5String, md5sum)!=0) {
		logError("The md5 sum of %s doesn't match\n", fileName);
		fclose(dst);
		return sysError;
	}

	fclose(dst);

	//Set the file permissions
	if(chmod(fileName, mode)==-1) {
		logError("Unable to change permissions for %s: %s\n", fileName, strerror(errno));
		return sysError;
	}

	return success;
}

status init(int argc, char *argv[]) {
	experiment exp;
	char* fileName;
	status s;
	int i;

	if(argc>1)
		basename=argv[1];
	basenameLen=strlen(basename);

	s=dbFetchExperimentData(&exp);
	if(s!=success)
		return s;
	jobsLen=exp.numCPUs;
	timeOut=exp.timeOut;
    printf("experimet data fetched\n");


    printf("going trough %i solvers\n", exp.numSolvers);
	for(i=0; i<exp.numSolvers; ++i) {
		//Prepend the solver name with pathname
		fileName=prependBasename(exp.solverNames[i]);
        printf("test filename is NULL\n");
		if(fileName==NULL) {
			logError("Error: Out of memory\n");
			freeExperimentData(&exp);
			return sysError;
		}
		//Start a mutual execution lock between several application instances
        printf("try lock mutex\n");
		if(lockMutex()!=success) {
			free(fileName);
			freeExperimentData(&exp);
			return sysError;
		}
		//Create the solver binary if it doesn't exist yet
        printf("create solver binary\n");
		if(fileExists(fileName)) {
			free(fileName);
			unlockMutex();
			continue;
		}
		if(createFile(fileName, exp.solvers[i], exp.lengthSolver[i],
		   exp.md5Solvers[i], 0111)!=success                                  ) {
			free(fileName);
			unlockMutex();
			freeExperimentData(&exp);
			return sysError;
		}
		free(fileName);
		//End the mutual execution lock
		if(unlockMutex()!=success) {
			freeExperimentData(&exp);
			return sysError;
		}
	}
    printf("solvers created\n");

	for(i=0; i<exp.numInstances; ++i) {
		//Prepend the instance name with pathname
		fileName=prependBasename(exp.instanceNames[i]);
		if(fileName==NULL) {
			logError("Error: Out of memory\n");
			freeExperimentData(&exp);
			return sysError;
		}
		//Start a mutual execution lock between several application instances
		if(lockMutex()!=success) {
			free(fileName);
			freeExperimentData(&exp);
			return sysError;
		}
		//Create the instance file if it doesn't exist yet
		if(fileExists(fileName)) {
			free(fileName);
			unlockMutex();
			continue;
		}
		if(createFile(fileName, exp.instances[i], strlen(exp.instances[i]),
		   exp.md5Instances[i], 0444)!=success                                         ) {
			free(fileName);
			unlockMutex();
			freeExperimentData(&exp);
			return sysError;
		}
		free(fileName);
		//End the mutual execution lock
		if(unlockMutex()!=success) {
			freeExperimentData(&exp);
			return sysError;
		}
	}
    printf("instances created\n");

	freeExperimentData(&exp);

	jobs=calloc(jobsLen, sizeof(job));
	if(jobs==NULL) {
		return sysError;
	}

	return success;
}

inline int fetchJob(job* j, status* s) {
	int retval;

	deferSignals();
	retval=dbFetchJob(j, s);
	resetSignalHandler();

	return retval;
}

inline status update(const job* j) {
	status retval;

	deferSignals();
	retval=dbUpdate(j);
	resetSignalHandler();

	return retval;
}

//Abort all running process and terminate the application with exit code retval
void exitClient(status retval) {
	char* fileName;
	int i;

	deferSignals();
	unrefMutex();

	//Massacre all other processes in the group including any child they might have
	kill(0, SIGTERM);

	//Update the database entries of the processes that were still running
	for(i=0; i<jobsLen; ++i) {
		if(jobs[i].pid!=0) {
			jobs[i].status=-2;
			update(&(jobs[i]));
			fileName=prependBasename(pidToFileName(jobs[i].pid));
			if(fileName!=NULL) {
				remove(fileName);
				free(fileName);
			}
		}
	}

	//Try to give the child processes time to shut down cleanly
	for(i=0; i<jobsLen; ++i) {
		if(jobs[i].pid!=0) {
			waitpid(jobs[i].pid, NULL, 0);
			freeJob(&(jobs[i]));
		}
	}

	free(jobs);
	exit(retval);
}

void signalHandler(int signum) {
	if(signum==SIGUSR1)
		exitClient(sysError);
	else if(signum==SIGUSR2)
		exitClient(dbError);
	else
		exitClient(success);
}

//Process the results of a normally terminated job j
status processResults(job* j) {
	char* fileName;
	char* resultFilePtr;
	FILE* filePtr;
	FILE* resultFile;
	int signum, c;
	long outputLen;

	//Set the status to -2 in case anything goes wrong in this function
	j->status=-2;

	fileName=prependBasename(pidToFileName(j->pid));
	if(fileName==NULL) {
		logError("Error: Out of memory\n");
		return sysError;
	}

	//Parse the temporary result file of j
	filePtr=fopen(fileName, "r");
	if(filePtr==NULL) {
		logError("Unable to open %s: %s\n", fileName, strerror(errno));
		free(fileName);
		return sysError;
	}

	if(fscanf(filePtr, "Command terminated by signal %d", &signum)==1) {
		//The solver was terminated by signal signum
		if(signum==SIGXCPU)
			j->status=2;
		else
			j->status=3;
	} else {
		//Extract the solver output
		do {
			c=getc(filePtr);
			if(c==EOF) {
				logError("Error parsing %s: Unexpected format\n", fileName);
				free(fileName);
				return sysError;
			}
		} while(c!=(int)'|');
		outputLen=ftell(filePtr);
		if(outputLen==-1) {
			logError("Error in ftell(): %s\n", strerror(errno));
			free(fileName);
			return sysError;
		}
		j->resultFile=malloc(outputLen);
		if(j->resultFile==NULL) {
			logError("Error: Out of memory\n");
			free(fileName);
			return sysError;
		}
		rewind(filePtr);
		resultFilePtr=j->resultFile;
		do {
			c=getc(filePtr);
			if(c==EOF) {
				logError("Error parsing %s: Unexpected format\n", fileName);
				free(fileName);
				return sysError;
			}
			*resultFilePtr=(char)c;
		} while(c!=(int)'|');
		*resultFilePtr='\0';
		//Skip the file content until the next '|' character
		do {
			c=getc(filePtr);
			if(c==EOF) {
				logError("Error parsing %s: Unexpected format\n", fileName);
				free(fileName);
				return sysError;
			}
		} while(c!=(int)'|');
		//Extract the runtime and return value of the solver
		if(fscanf(filePtr, "%f|%d", &(j->time), &(j->statusCode))!=2){
			logError("Error parsing %s: Unexpected format\n", fileName);
			free(fileName);
			return sysError;
		}
		j->resultFile[outputLen-1]='\0';
		j->status=1;
		//Append the content of fileName to j->resultFileName
		rewind(filePtr);
		resultFile=fopen(j->resultFileName, "a");
		if(resultFile==NULL) {
			logError("Unable to open %s: %s\n", j->resultFileName, strerror(errno));
			free(fileName);
			return sysError;
		}
		while((c=safeGetc(filePtr))!=EOF) {
			if(safeFputc(c, resultFile)==EOF) {
				logError("An error occured while copying a character from %s to %s\n", fileName, j->resultFileName);
				free(fileName);
				return sysError;
			}
		}
		if(safeFputc((int)'\n', resultFile)==EOF) {
			logError("An error occured while writing a character to %s\n", j->resultFileName);
			free(fileName);
			return sysError;
		}
		fclose(resultFile);
	}

	fclose(filePtr);
	remove(fileName);
	free(fileName);

	return success;
}

//Wait for cnt child processes to terminate and handle the results
status handleChildren(int cnt) {
	int i, retval;
	status s;
	job* j;
	pid_t pid;
	char *fileName;

	for(i=0; i<cnt; ++i) {
		//Wait until a child process terminates
		pid=wait(&retval);
		if(pid==-1){
			logError("Error in wait(): %s\n", strerror(errno));
			return sysError;
		}

		//Point j to the entry in jobs corresponding to the terminated child process
		for(j=jobs; j->pid!=pid; ++j);

		if(WIFEXITED(retval) && (WEXITSTATUS(retval)==0)) {
			//The process terminated normally.
			//Start a mutual execution lock between several application instances.
			if(lockMutex()!=success) {
				freeJob(j);
				j->pid=0;
				return sysError;
			}
			s=processResults(j);
			j->pid=0;
			//End the mutual execution lock
			if(unlockMutex()!=success) {
				freeJob(j);
				return sysError;
			}
			if(s!=success) {
				j->status=-2;
				update(j);
				freeJob(j);
				return s;
			}
			s=update(j);
			freeJob(j);
			if(s!=success) {
				return s;
			}
		} else {
			//The process terminated abnormally
			fileName=prependBasename(pidToFileName(j->pid));
			j->pid=0;
			j->status=-2;
			s=update(j);
			freeJob(j);
			if(fileName==NULL) {
				logError("Error: Out of memory\n");
				if(s==success)
					s=sysError;
				return s;
			}
			remove(fileName);
			free(fileName);
			if(s!=success) {
				return s;
			}
		}
	}

	return success;
}

//Fill jobArgs with the information in j. If the function succeeds, some parts of
//jobArgs might be set to allocated memory that can be freed with freeJobArg().
status setJobArgs(const job* j) {
	char* fileName;
	char* command;
	char* solverName;
	char* instanceName;

	fileName=prependBasename(pidToFileName(getpid()));
	if(fileName==NULL) {
		logError("Error: Out of memory\n");
		return sysError;
	}
	solverName=prependBasename(j->solverName);
	if(solverName==NULL) {
		logError("Error: Out of memory\n");
		free(fileName);
		return sysError;
	}
	instanceName=prependBasename(j->instanceName);
	if(instanceName==NULL) {
		logError("Error: Out of memory\n");
		free(fileName);
		free(solverName);
		return sysError;
	}
	if(sprintfAlloc(&command,
	                "ulimit -S -t %d && /usr/bin/time -a -o %s -f \"|%%C|%%U|%%x\" %s %s %s >> %s",
	                timeOut, fileName, solverName, j->params, instanceName, fileName          ) < 0) {
		logError("Error in sprintfAlloc()\n");
		free(fileName);
		free(solverName);
		free(instanceName);
		return sysError;
	}
    printf("command: %s\n", command);

	jobArgs[0]="/bin/bash";
	jobArgs[1]="-c";
	jobArgs[2]=command;
	jobArgs[3]=NULL;

	return success;
}

void freeJobArgs() {
	free(jobArgs[2]);
}

//Set the startTime field in j to the current local time
status setStartTime(job *j) {
	time_t t;
	struct tm *tmp;

	t=time(NULL);
	if(t==((time_t) -1)) {
		logError("Error in time(): %s\n", strerror(errno));
		return sysError;
	}
	tmp=localtime(&t);
	if(tmp==NULL) {
		logError("Error in localtime(): %s\n", strerror(errno));
		return sysError;
	}
	if (strftime(j->startTime, sizeof(j->startTime), "%H%M%S", tmp) != sizeof(j->startTime)-1) {
		logError("Error in strftime()\n");
		return sysError;
	}

	return success;
}

int main(int argc, char *argv[]) {
	int numJobs;
	status s;
	job* j;
	pid_t pid;
	solver solv;
	instance inst;
	char* fileName;

	s=read_config();
	if(s!=success) {
        logError("Couldn't read config successfully.");
		exit(s);
	}

	s=init(argc, argv);
	if(s!=success) {
        logError("Couldn't init successfully, for experiment %i.\n", experimentId);
		exit(s);
	}
    printf("initialized\n");


	setSignalHandler(signalHandler);

	for(numJobs=0; ;--numJobs) {
		//Run jobsLen child processes, each of them processing one job
		for(; numJobs<jobsLen; ++numJobs) {
			//Point j to a free slot in the jobs array
			for(j=jobs; j->pid!=0; ++j);

			//Start a mutual execution lock between several application instances
			if(lockMutex()!=success) {
				exitClient(sysError);
			}

			//Try to load a job from the database and write it to j
			if(fetchJob(j, &s)!=0) {
				//Unable to retrieve a job from the database
				unlockMutex();
				if(s==success) {
					//No error occured, but there's no job left in the database.
					//Wait until all child processes have terminated.
					s=handleChildren(numJobs);
				}
				exitClient(s);
			}

			//Set j->startTime to the current local time
			s=setStartTime(j);
			if(s!=success) {
				unlockMutex();
				exitClient(s);
			}

			//Set the job state to running in the database
			j->status=0;
			s=update(j);
			if(s!=success) {
				unlockMutex();
				exitClient(s);
			}

			//Create the solver binary if it doesn't exist yet
            //printf("creating solver binary %s ...\n", j->solverName);
			fileName=prependBasename(j->solverName);
			if(fileName==NULL) {
				logError("Error: Out of memory\n");
				unlockMutex();
				j->status=-2;
				s=update(j);
				exitClient(sysError);
			}
			if(!fileExists(fileName)) {
                //printf("file doesn't exist\n");
				s=dbFetchSolver(j->solverName, &solv);
				if(s!=success) {
					unlockMutex();
					free(fileName);
					j->status=-2;
					s=update(j);
					exitClient(s);
				}
				if(createFile(fileName, solv.solver, solv.length, solv.md5, 0111)!=success) {
					unlockMutex();
					free(fileName);
					freeSolver(&solv);
					j->status=-2;
					s=update(j);
					exitClient(sysError);
				}
				freeSolver(&solv);
			}
			free(fileName);

			//Create the instance file if it doesn't exist yet
			fileName=prependBasename(j->instanceName);
			if(fileName==NULL) {
				logError("Error: Out of memory\n");
				unlockMutex();
				j->status=-2;
				s=update(j);
				exitClient(sysError);
			}
			if(!fileExists(fileName)) {
				s=dbFetchInstance(j->instanceName, &inst);
				if(s!=success) {
					unlockMutex();
					free(fileName);
					j->status=-2;
					s=update(j);
					exitClient(s);
				}
				if(createFile(fileName, inst.instance, strlen(inst.instance), inst.md5, 0444)!=success) {
					unlockMutex();
					free(fileName);
					freeInstance(&inst);
					j->status=-2;
					s=update(j);
					exitClient(sysError);
				}
				freeInstance(&inst);
			}
			free(fileName);

			//End the mutual execution lock
			if(unlockMutex()!=success) {
				j->status=-2;
				s=update(j);
				exitClient(sysError);
			}

			//Create a process for processing the job
			pid=fork();
			if(pid==-1) {
				logError("Error in fork(): %s\n", strerror(errno));
				j->status=-2;
				s=update(j);
				exitClient(sysError);
			} else if(pid==0) {
				//This is the child process. Disable the inherited signal handler.
				deferSignals();
				//Set up jobArgs and run the command
				if(s==success && (s=setJobArgs(j))==success) {
/*                     for(t = jobArgs; t!=NULL; ++t) {
 *                         printf("jobArgs: %s\n", t);
 *                     }
 */
					if(execve("/bin/bash", jobArgs, NULL)==-1) {
						logError("Error in execve(): %s\n", strerror(errno));
						freeJobArgs();
						s=sysError;
					}
				}
				//Something is seriously wrong. Send the father process a signal indicating the error.
				j->status=-2;
				update(j);
				if(s==sysError) {
					if(kill(getppid(), SIGUSR1)!=0) {
						logError("Error in kill(): %s\n", strerror(errno));
					}
				} else {
					if(kill(getppid(), SIGUSR2)!=0) {
						logError("Error in kill(): %s\n", strerror(errno));
					}
				}
				//Now that was a shitty short life :-/
				exit(sysError);
			}
			j->pid=pid;
		}

		//Wait until one child process terminates and handle the result
		s=handleChildren(1);
		if(s!=success) {
			exitClient(s);
		}
	}

	//Avoid compiler warnings
	return success;
}

/* int main_test(int argc, char *argv[]) {
 * 	int numJobs;
 * 	status s;
 * 	job* j;
 * 	pid_t pid;
 * 	solver solv;
 * 	instance inst;
 * 	char* fileName;
 *     experiment exp;
 * 
 * 	read_config();
 * 
 *     s=dbFetchExperimentData(&exp);
 * 	s=init(argc, argv);
 * 	if(s!=success) {
 * 		exit(s);
 * 	}
 * 
 * 	setSignalHandler(signalHandler);
 * 
 * 
 *     printf("id: %i, numcpus: %i, timeout: %i, numinstances: %i,"\
 *             "instancename 1: %s, numsolvers: %i, solvername 1: %s\n",
 *             exp.id, exp.numCPUs, exp.timeOut, exp.numInstances, 
 *             exp.instanceNames[0], exp.numSolvers, exp.solverNames[0]);
 * 
 * 
 *     return 1;
 * }
 */

