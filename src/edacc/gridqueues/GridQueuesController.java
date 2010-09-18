/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edacc.gridqueues;

import edacc.model.Experiment;
import edacc.model.ExperimentHasGridQueue;
import edacc.model.ExperimentHasGridQueueDAO;
import edacc.model.GridQueue;
import edacc.model.GridQueueDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Vector;

/**
 * @author dgall
 */
public class GridQueuesController extends Observable {

    private static GridQueuesController instance;
    private File tmpPBSScript;
    private GridQueue chosenQueue;

    private GridQueuesController() {
    }

    public static GridQueuesController getInstance() {
        if (instance == null) {
            instance = new GridQueuesController();
        }
        return instance;
    }

    /**
     * Saves a given new GridQueue to the Database and cache.
     * It throws an exception, if no pbs script is specified (see addPBSScript).
     * @param q
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void createNewGridQueue(GridQueue q) throws SQLException, FileNotFoundException, Exception {
        if (tmpPBSScript == null) {
            throw new Exception("You must specify a generic PBS script!");
        } else {
            q.setGenericPBSScript(tmpPBSScript);
        }
        GridQueueDAO.save(q);
        tmpPBSScript = null;
    }

    /**
     * Saves a given modified GridQueue to the Database and cache.
     * In contrast to createNewGridQueue it throws no exception, if no pbs
     * script is specified (see addPBSScript).
     * @param q
     * @throws SQLException
     * @throws FileNotFoundException
     */
    public void saveEditedGridQueue(GridQueue q) throws SQLException, FileNotFoundException {
        GridQueueDAO.save(q);
        tmpPBSScript = null;
    }

    public void addPBSScript(File f) {
        tmpPBSScript = f;
    }

    public boolean hasTmpPBSScript() {
        return tmpPBSScript != null;
    }

    public void clearPBSScript() {
        tmpPBSScript = null;
    }

    public Vector<GridQueue> getChosenQueuesByExperiment(Experiment exp) throws SQLException {
        Vector<GridQueue> res = new Vector<GridQueue>();
        Vector<ExperimentHasGridQueue> ehgqs = ExperimentHasGridQueueDAO.getExperimentHasGridQueueByExperiment(exp);
        for (ExperimentHasGridQueue ehgq : ehgqs) {
            res.add(GridQueueDAO.getById(ehgq.getIdGridQueue()));
        }
        return res;
    }

    public void gridQueueSelectionChanged() {
        setChanged();
        notifyObservers();
    }
}
