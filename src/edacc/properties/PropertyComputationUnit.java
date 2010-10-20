/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.properties;

import edacc.model.ComputationMethodDAO;
import edacc.model.ComputationMethodDoesNotExistException;
import edacc.model.DatabaseConnector;
import edacc.model.ExperimentResultDAO;
import edacc.model.ExperimentResultHasProperty;
import edacc.model.ExperimentResultHasPropertyDAO;
import edacc.model.InstanceDAO;
import edacc.model.InstanceHasProperty;
import edacc.model.InstanceHasPropertyDAO;
import edacc.model.InstanceHasPropertyNotInDBException;
import edacc.model.InstanceNotInDBException;
import edacc.model.NoConnectionToDBException;
import edacc.model.Property;
import edacc.model.PropertyDAO;
import edacc.model.PropertyNotInDBException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rretz
 */
public class PropertyComputationUnit implements Runnable {
    ExperimentResultHasProperty erhp;
    InstanceHasProperty ihp;
    PropertyComputationController callback;
    Property property;

    PropertyComputationUnit(ExperimentResultHasProperty erhp, PropertyComputationController callback) {
        this.erhp = erhp;
        this.callback = callback;
        this.property = erhp.getProperty();
    }

    PropertyComputationUnit(InstanceHasProperty ihp, PropertyComputationController callback) {
        this.ihp = ihp;
        this.callback = callback;
        this.property = ihp.getProperty();
    }

    @Override
    public void run() {
        if(erhp != null){
            try {
                Property property = erhp.getProperty();
                switch (property.getPropertySource()) {
                    case LauncherOutput:
                        compute(ExperimentResultDAO.getLauncherOutputFile(erhp.getExpResult()));
                        break;
                    case SolverOutput:
                        compute(ExperimentResultDAO.getSolverOutputFile(erhp.getExpResult()));
                        break;
                    case VerifierOutput:
                        compute(ExperimentResultDAO.getVerifierOutputFile(erhp.getExpResult()));
                        break;
                    case WatcherOutput:
                        compute(ExperimentResultDAO.getWatcherOutputFile(erhp.getExpResult()));
                        break;
                }
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) { e.printStackTrace(); }
        }else if(ihp != null){
            try {
                switch (property.getPropertySource()) {
                    case Instance:
                        try {
                            compute(InstanceDAO.getBinaryFileOfInstance(ihp.getInstance()));
                        } catch (InstanceNotInDBException ex) {
                            Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        break;
                    case InstanceName:
                        parseInstanceName();
                        break;
                }
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) { e.printStackTrace(); }
        }
        callback.callback();  
    }

    private void compute(File f) throws FileNotFoundException, IOException, SQLException, NoConnectionToDBException, InstanceNotInDBException, ComputationMethodDoesNotExistException {
        if(property.getComputationMethod() != null){
            // parse instance file (external program call)
            if (ihp != null) {
                File bin = ComputationMethodDAO.getBinaryOfComputationMethod(property.getComputationMethod());
                bin.setExecutable(true);
                Process p = Runtime.getRuntime().exec(bin.getAbsolutePath());
                Blob instance = InstanceDAO.getBinary(ihp.getInstance().getId());
                long instanceFileSize = instance.length();
                //FileReader instanceReader = new FileReader(new InputStreamReader(instanceStream));
                // The std input stream of the external program. We pipe the content of the instance file into that stream
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                // The std output stream of the external program (-> output of the program). We read the calculated value from this stream.
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

                // pipe the content of the instance file to the input of the external program
                try {
                    for (int i = 1; i < instance.length(); i += 1000) {
                        p.getOutputStream().write(instance.getBytes(1, 1000));
                    }
                } catch (IOException e) {
                    if (!e.getMessage().contains("Broken pipe")) {
                        throw e;
                    }
                }
                // Read first line of program output
                String value = in.readLine();
                ihp.setValue(value);
            }
        }else if(!property.getRegularExpression().equals("") || property.getRegularExpression() != null){
            Vector<String> res = new Vector<String>();
            BufferedReader buf = new BufferedReader(new FileReader(f));
            String tmp;
            Vector<String> toAdd = new Vector<String>();
            while((tmp = buf.readLine()) != null){
                if(!(toAdd = parse(tmp)).isEmpty()){
                    res.addAll(res);
                    if(!property.isMultiple() || ihp != null)
                        break;
                }
            }
            if(ihp  != null){
                ihp.setValue(res.firstElement());
                InstanceHasPropertyDAO.save(ihp);
            }
            else if(erhp != null){
                erhp.setValue(res);
                ExperimentResultHasPropertyDAO.save(erhp);
            }
        }
    }

    private Vector<String> parse(String toParse) {
        Vector<String> res = new Vector<String>();
        Pattern pat = Pattern.compile(property.getRegularExpression());
        Matcher m = pat.matcher(toParse);
        while (m.find()) {
            res.add(m.group(1));
            if(!property.isMultiple())
                break;
        }
        return res;
    }

    private void parseInstanceName() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        try {
            DatabaseConnector.getInstance().connect("edacc.informatik.uni-ulm.de", 3306, "edacc", "EDACC2", "edaccteam");

            PropertyComputationUnit unit = new PropertyComputationUnit(InstanceHasPropertyDAO.createInstanceHasInstanceProperty(InstanceDAO.getById(1), PropertyDAO.getById(1)), null);
            unit.compute(null);
        } catch (NoConnectionToDBException ex) {
            Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyNotInDBException ex) {
            Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyTypeNotExistException ex) {
            Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ComputationMethodDoesNotExistException ex) {
            Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
