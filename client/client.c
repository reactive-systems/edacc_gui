#include "global.h"
#include "log.h"
#include "signals.h"
#include "database.h"
#include "configuration.h"
#include "safeio.h"
#include "mutex.h"

#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <stdio.h>


//The path of the shellscript we're using to execute a solver run
//and an array for storing it's arguments
const char* const jobScript="jobScript.sh";
char* jobScriptArgs[7]={NULL, NULL, NULL, NULL, NULL, NULL, NULL};

//An array for keeping track of the jobs we're currently processing
job* jobs;
int jobsLen;

//The timeout in seconds for each solver run in string form
char timeOutStr[20];


char* pidToFileName(pid_t pid) {
	static char fileName[20];
	snprintf(fileName, 20, "%ld.tmp", (long)pid);
	return fileName;
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
	FILE* md5File;
	char* const md5FileName="md5sums.txt";
	char* const md5CheckScript="md5check.sh";
	char* const md5CheckScriptArgs[3]={md5CheckScript, md5FileName, NULL};
	pid_t pid;
	int retval;

	//Create a file for saving the md5sum and fileName
	md5File=fopen(md5FileName, "w+");
	if(md5File==NULL) {
		logError("Unable to open %s: %s\n", md5FileName, strerror(errno));
		return sysError;
	}

	//Create the file
	dst=fopen(fileName, "w+");
	if(dst==NULL) {
		logError("Unable to open %s: %s\n", fileName, strerror(errno));
		fclose(md5File);
		return sysError;
	}

	//Write the file content. There's no need to check for errors in safeFwrite here;
	//if something goes wrong, we'll get an error in the md5 check anyway.
	safeFwrite(content, sizeof(char), contentLen, dst);
	fclose(dst);

	//Set the file permissions
	if(chmod(fileName, mode)==-1) {
		logError("Unable to change permissions for %s: %s\n", fileName, strerror(errno));
		fclose(md5File);
		return sysError;
	}

	//Write md5sum and fileName to md5file
	if(safeFprintf(md5File, "%s  %s\n", md5sum, fileName)<0) {
		logError("Unable to write to %s\n", md5FileName);
		fclose(md5File);
		return sysError;
	}
	fclose(md5File);

	//Verify the md5sums of the file against the content of md5file
	pid=fork();
	if(pid==-1) {
		logError("Error in fork(): %s\n", strerror(errno));
		return sysError;
	} else if(pid==0) {
		//This is the child process. Run the local md5sum program to verify the md5 sums.
		if(execve(md5CheckScript, md5CheckScriptArgs, NULL)==-1) {
			logError("Error in execve(): %s\n", strerror(errno));
			return sysError;
		}
	}
	//This is the parent process. Wait for the md5sum check and interpret the return value.
	pid=wait(&retval);
	if(pid==-1){
		logError("Error in wait(): %s\n", strerror(errno));
		return sysError;
	}
	if(retval!=0) {
		logError("The md5 sum of %s doesn't match\n", fileName);
		return sysError;
	}
	remove(md5FileName);

	return success;
}

status init() {
	experiment exp;
	status s;
	int i;

	s=dbFetchExperimentData(&exp);
	if(s!=success)
		return s;
	jobsLen=exp.numNodes;
	snprintf(timeOutStr, 20, "%d", exp.timeOut);

	for(i=0; i<exp.numSolvers; ++i) {
		//Start a mutual execution lock between several application instances
		if(lockMutex()!=success)
			return sysError;
		//Create the solver binary if it doesn't exist yet
		if(fileExists(exp.solverNames[i])) {
			unlockMutex();
			continue;
		}
		if(createFile(exp.solverNames[i], exp.solvers[i], exp.lengthSolver[i],
		   exp.md5Solvers[i], 0111)!=success                                  ) {
			unlockMutex();
			return sysError;
		}
		//End the mutual execution lock
		if(unlockMutex()!=success)
			return sysError;
	}

	for(i=0; i<exp.numInstances; ++i) {
		//Start a mutual execution lock between several application instances
		if(lockMutex()!=success)
			return sysError;
		//Create the instance file if it doesn't exist yet
		if(fileExists(exp.instanceNames[i])) {
			unlockMutex();
			continue;
		}
		if(createFile(exp.instanceNames[i], exp.instances[i], strlen(exp.instances[i]),
		   exp.md5Instances[i], 0444)!=success                                         ) {
			unlockMutex();
			return sysError;
		}
		//End the mutual execution lock
		if(unlockMutex()!=success)
			return sysError;
	}

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
void shutdown(status retval) {
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
			remove(pidToFileName(jobs[i].pid));
		}
	}

	//Try to give the child processes time to shut down cleanly
	for(i=0; i<jobsLen; ++i) {
		if(jobs[i].pid!=0) {
			waitpid(jobs[i].pid, NULL, 0);
		}
	}

	free(jobs);
	exit(retval);
}

void signalHandler(int signum) {
	if(signum==SIGUSR1)
		shutdown(sysError);
	else if(signum==SIGUSR2)
		shutdown(dbError);
	else
		shutdown(success);
}

//Process the results of a normally terminated job j
status processResults(job* j) {
	char* fileName=pidToFileName(j->pid);
	FILE* filePtr;
	FILE* resultFile;
	int signum, semicolonsToSkip=3, c, secFraction;
	char buf[20];

	//Parse the temporary result file of j
	filePtr=fopen(fileName, "r");
	if(filePtr==NULL) {
		logError("Unable to open %s: %s\n", fileName, strerror(errno));
		return sysError;
	}

	if(safeFscanf(filePtr, "Command terminated by signal %d", &signum)==1) {
		//The solver was terminated by signal signum
		if(signum==SIGXCPU)
			j->status=2;
		else
			j->status=3;
	} else {
		//Skip semicolonsToSkip semicolons.
		while(semicolonsToSkip>0) {
			c=safeGetc(filePtr);
			if(c==(int)';') {
				--semicolonsToSkip;
			} else if(c==EOF) {
				logError("Error parsing %s: Unexpected format\n", fileName);
				return sysError;
			}
		}
		//Extract the runtime and return value of the solver
		if(safeFscanf(filePtr, "%d.%d;%d", &(j->time), &secFraction, &(j->statusCode))!=3){
			logError("Error parsing %s: Unexpected format\n", fileName);
			return sysError;
		}
		//Round j->time to the nearest second
		snprintf(buf, 20, "%d", secFraction);
		if(buf[0]>='5')
			++(j->time);
		j->status=1;
		//Append the first line of fileName to j->resultFileName
		rewind(filePtr);
		resultFile=fopen(j->resultFileName, "a");
		if(resultFile==NULL) {
			logError("Unable to open %s: %s\n", j->resultFileName, strerror(errno));
			return sysError;
		}
		do {
			if((c=safeGetc(filePtr))==EOF || safeFputc(c, resultFile)==EOF) {
				logError("An error occured while copying a character from %s to %s\n", fileName, j->resultFileName);
				return sysError;
			}
		} while(c!='\n');
		fclose(resultFile);
	}

	fclose(filePtr);
	remove(fileName);

	return success;
}

//Wait for cnt child processes to terminate and handle the results
status handleChildren(int cnt) {
	int i, retval;
	status s;
	job* j;
	pid_t pid;

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
			//The process terminated normally
			s=processResults(j);
			j->pid=0;
			if(s!=success) {
				return s;
			}
			s=update(j);
			if(s!=success) {
				return s;
			}
		} else {
			//The process terminated abnormally
			j->status=-2;
			j->pid=0;
			s=update(j);
			if(s!=success) {
				return s;
			}
		}
	}

	return success;
}

int main() {
	int numJobs;
	status s;
	job* j;
	pid_t pid;
	solver solv;
	instance inst;

	read_config();

	s=init();
	if(s!=success) {
		exit(s);
	}

	setSignalHandler(signalHandler);

	for(numJobs=0; ;--numJobs) {
		//Run jobsLen child processes, each of them processing one job
		for(; numJobs<jobsLen; ++numJobs) {
			//Point j to a free slot in the jobs array
			for(j=jobs; j->pid!=0; ++j);

			//Try to load a job from the database and write it to j
			if(fetchJob(j, &s)!=0) {
				//Unable to retrieve a job from the database
				if(s==success) {
					//No error occured, but there's no job left in the database.
					//Wait until all child processes have terminated.
					s=handleChildren(numJobs);
				}
				shutdown(s);
			}

			//Start a mutual execution lock between several application instances
			if(lockMutex()!=success)
				shutdown(sysError);

			//Create the solver binary if it doesn't exist yet
			if(!fileExists(j->solverName)) {
				s=dbFetchSolver(j->solverName, &solv);
				if(s!=success) {
					unlockMutex();
					shutdown(s);
				}
				if(createFile(j->solverName, solv.solver, solv.length, solv.md5, 0111)!=success) {
					unlockMutex();
					shutdown(sysError);
				}
			}

			//Create the instance file if it doesn't exist yet
			if(!fileExists(j->instanceName)) {
				s=dbFetchInstance(j->instanceName, &inst);
				if(s!=success) {
					unlockMutex();
					shutdown(s);
				}
				if(createFile(j->instanceName, inst.instance, strlen(inst.instance), inst.md5, 0444)!=success) {
					unlockMutex();
					shutdown(sysError);
				}
			}

			//End the mutual execution lock
			if(unlockMutex()!=success)
				shutdown(sysError);

			//Create a process for processing the job
			pid=fork();
			if(pid==-1) {
				logError("Error in fork(): %s\n", strerror(errno));
				shutdown(sysError);
			} else if(pid==0) {
				//This is the child process.
				deferSignals(); //Disable the inherited signal handler.
				//Set the job state to running in the database
				j->status=0;
				s=update(j);
				//Set up the jobScriptArgs array
				jobScriptArgs[0]=(char*)jobScript;
				jobScriptArgs[1]=j->solverName;
				jobScriptArgs[2]=j->params;
				jobScriptArgs[3]=j->instanceName;
				jobScriptArgs[4]=pidToFileName(getpid());
				jobScriptArgs[5]=timeOutStr;
				if(s==success) {
					if(execve(jobScript, jobScriptArgs, NULL)==-1) {
						logError("Error in execve(): %s\n", strerror(errno));
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
			shutdown(s);
		}
	}

	//Avoid compiler warnings
	return success;
}

