package edacc.model;

import edacc.properties.PropertyTypeNotExistException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExperimentResultDAO {

    protected static PreparedStatement curSt = null;
    protected static final String table = "ExperimentResults";
    protected static final String insertQuery = "INSERT INTO " + table + " (SolverConfig_idSolverConfig, Experiment_idExperiment,"
            + "Instances_idInstance, run, status, seed, solverOutputFN, launcherOutputFN, watcherOutputFN, verifierOutputFN, "
            + "solverOutput, launcherOutput, watcherOutput, verifierOutput, startTime, priority, resultTime, computeQueue, resultCode) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    protected static final String deleteQuery = "DELETE FROM " + table + " WHERE idJob=?";
    protected static final String selectQuery = "SELECT SolverConfig_idSolverConfig, Experiment_idExperiment, Instances_idInstance, "
            + "idJob, run, seed, status, resultTime, resultCode, solverOutputFN, launcherOutputFN, watcherOutputFN, verifierOutputFN, "
            + "solverExitCode, watcherExitCode, verifierExitCode, computeQueue, TIMESTAMPDIFF(SECOND, startTime, NOW()) AS runningTime, "
            + "IF(status = " + ExperimentResultStatus.RUNNING.getValue() + ", TIMESTAMPADD(SECOND, -1, CURRENT_TIMESTAMP), date_modified) AS date_modified,"
            + "priority, startTime "
            + "FROM " + table + " ";
    protected static final String copyOutputQuery = "UPDATE ExperimentResults as dest, ExperimentResults as src "
            + "SET "
            + "dest.solverOutput = src.solverOutput, "
            + "dest.watcherOutput = src.watcherOutput, "
            + "dest.launcherOutput = src.launcherOutput, "
            + "dest.verifierOutput = src.verifierOutput "
            + "WHERE src.idJob = ? AND dest.idJob = ?";

    public static ExperimentResult createExperimentResult(int run, int priority, int computeQueue, int status, int seed, ExperimentResultResultCode resultCode, float time, int SolverConfigId, int ExperimentId, int InstanceId, Timestamp startTime) throws SQLException {
        ExperimentResult r = new ExperimentResult(run, priority, computeQueue, status, seed, resultCode, time, SolverConfigId, ExperimentId, InstanceId, startTime);
        r.setNew();
        return r;
    }

    public static ExperimentResultEx createExperimentResult(int run, int priority, int computeQueue, int status, ExperimentResultResultCode resultCode, int seed, float time, int SolverConfigId, int ExperimentId, int InstanceId, Timestamp startTime, byte[] solverOutput, byte[] launcherOutput, byte[] watcherOutput, byte[] verifierOutput) {
        ExperimentResultEx r = new ExperimentResultEx(run, priority, computeQueue, status, resultCode, seed, time, SolverConfigId, ExperimentId, InstanceId, startTime, solverOutput, launcherOutput, watcherOutput, verifierOutput);
        r.setNew();
        return r;
    }

    /**
     * Saves the experiment results at once (batch).
     * @param v
     * @throws SQLException
     */
    public static void batchSave(ArrayList<ExperimentResult> v) throws SQLException {
        boolean autoCommit = DatabaseConnector.getInstance().getConn().getAutoCommit();
        try {
            DatabaseConnector.getInstance().getConn().setAutoCommit(false);
            PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            curSt = st;
            for (ExperimentResult r : v) {
                st.setInt(1, r.getSolverConfigId());
                st.setInt(2, r.getExperimentId());
                st.setInt(3, r.getInstanceId());
                st.setInt(4, r.getRun());
                st.setInt(5, r.getStatus().getValue());
                st.setInt(6, r.getSeed());
                st.setString(7, r.getSolverOutputFilename());
                st.setString(8, r.getLauncherOutputFilename());
                st.setString(9, r.getWatcherOutputFilename());
                st.setString(10, r.getVerifierOutputFilename());
                if (r instanceof ExperimentResultEx) {
                    ExperimentResultEx rx = (ExperimentResultEx) r;
                    st.setBytes(11, rx.getSolverOutput());
                    st.setBytes(12, rx.getLauncherOutput());
                    st.setBytes(13, rx.getWatcherOutput());
                    st.setBytes(14, rx.getVerifierOutput());
                } else {
                    st.setNull(11, java.sql.Types.BLOB);
                    st.setNull(12, java.sql.Types.BLOB);
                    st.setNull(13, java.sql.Types.BLOB);
                    st.setNull(14, java.sql.Types.BLOB);
                }
                st.setTimestamp(15, r.getStartTime());
                st.setInt(16, r.getPriority());
                st.setFloat(17, r.getResultTime());
                st.setInt(18, r.getComputeQueue());
                st.setInt(19, (r.getResultCode() == null) ? 0 : r.getResultCode().getValue());
                st.addBatch();
            }
            st.executeBatch();
            ResultSet rs = st.getGeneratedKeys();
            int i = 0;
            while (rs.next()) {
                v.get(i).setSaved();
                v.get(i).setId(rs.getInt(1));
                i++;
            }
            st.close();
        } catch (SQLException e) {
            DatabaseConnector.getInstance().getConn().rollback();
            throw e;
        } finally {
            curSt = null;
            DatabaseConnector.getInstance().getConn().setAutoCommit(autoCommit);
        }

    }

    /**
     * Updates the run property of the ExperimentResults at once (batch).
     * @param v vector of ExperimentResults to be updated
     * @throws SQLException
     */
    public static void batchUpdateRun(ArrayList<ExperimentResult> v) throws SQLException {
        boolean autoCommit = DatabaseConnector.getInstance().getConn().getAutoCommit();
        try {
            DatabaseConnector.getInstance().getConn().setAutoCommit(false);
            final String query = "UPDATE " + table + " SET run=? WHERE idJob=?";
            PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(query);
            curSt = st;
            for (ExperimentResult r : v) {
                st.setInt(1, r.getRun());
                st.setInt(2, r.getId());
                st.addBatch();
                r.setSaved();
            }
            st.executeBatch();
            st.close();
        } catch (SQLException e) {
            DatabaseConnector.getInstance().getConn().rollback();
            throw e;
        } finally {
            curSt = null;
            DatabaseConnector.getInstance().getConn().setAutoCommit(autoCommit);
        }
    }

    /**
     * This will copy the output from the experiment results specified in the <code>from</code>-ArrayList to the experiment results
     * specified in the <code>to</code>-ArrayList.
     * @param from
     * @param to
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws IllegalArgumentException if <code>from.size() != to.size()</code> or <code>from == null</code> or <code>to == null</code>
     */
    public static void batchCopyOutputs(ArrayList<ExperimentResult> from, ArrayList<ExperimentResult> to) throws NoConnectionToDBException, SQLException {
        if (from.size() != to.size() || from == null || to == null) {
            throw new IllegalArgumentException("from.size() != to.size()");
        }
        boolean autoCommit = DatabaseConnector.getInstance().getConn().getAutoCommit();
        try {
            DatabaseConnector.getInstance().getConn().setAutoCommit(false);
            final String query = copyOutputQuery;
            PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(query);
            curSt = st;
            for (int i = 0; i < from.size(); i++) {
                st.setInt(1, from.get(i).getId());
                st.setInt(2, to.get(i).getId());
                st.addBatch();
            }

            st.executeBatch();
            st.close();
        } catch (Throwable e) {
            DatabaseConnector.getInstance().getConn().rollback();
            if (e instanceof SQLException) {
                throw (SQLException) e;
            }
        } finally {
            curSt = null;
            DatabaseConnector.getInstance().getConn().setAutoCommit(autoCommit);
        }
    }

    /**
     * Updates the priority of the ExperimentResults at once (batch).
     * @param v vector of ExperimentResults to be updated
     * @throws SQLException
     */
    public static void batchUpdatePriority(ArrayList<ExperimentResult> v) throws SQLException {
        boolean autoCommit = DatabaseConnector.getInstance().getConn().getAutoCommit();
        try {
            DatabaseConnector.getInstance().getConn().setAutoCommit(false);
            final String query = "UPDATE " + table + " SET priority=? WHERE idJob=?";
            PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(query);
            curSt = st;
            for (ExperimentResult r : v) {
                st.setInt(1, r.getPriority());
                st.setInt(2, r.getId());
                st.addBatch();
                r.setSaved();
            }
            st.executeBatch();
            st.close();
        } catch (Throwable e) {
            DatabaseConnector.getInstance().getConn().rollback();
            if (e instanceof SQLException) {
                throw (SQLException) e;
            }
        } finally {
            curSt = null;
            DatabaseConnector.getInstance().getConn().setAutoCommit(autoCommit);
        }
    }

    /**
     * Updates the status of the experiment results to <code>status</code>.
     * If <code>status</code> equals <code>ExperimentResultStatus.NOTSTARTED</code> then all fields which are set when saving results (client)
     * are set to <code>NULL</code>.<br/><br/>
     * <b>Note</b>: After that operation the local cached experiment results should be reloaded to prevent inconsistency.
     * @param v
     * @param status
     * @throws SQLException
     */
    public static void batchUpdateStatus(ArrayList<ExperimentResult> v, ExperimentResultStatus status) throws SQLException {
        boolean autoCommit = DatabaseConnector.getInstance().getConn().getAutoCommit();
        try {
            DatabaseConnector.getInstance().getConn().setAutoCommit(false);

            String updateString = "status = " + status.getValue();
            if (status == ExperimentResultStatus.NOTSTARTED) {
                updateString += ",startTime=NULL, resultTime = NULL, resultCode = NULL, solverOutput = NULL, launcherOutput = NULL, "
                        + "verifierOutput = NULL, watcherOutput = NULL, solverExitCode = NULL, watcherExitCode = NULL, "
                        + "verifierExitCode = NULL, computeQueue = NULL";
            }
            final String query = "UPDATE " + table + " SET " + updateString + " WHERE idJob=?";
            PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(query);
            curSt = st;
            for (ExperimentResult r : v) {
                st.setInt(1, r.getId());
                st.addBatch();
                r.setSaved();
            }
            st.executeBatch();
            st.close();
        } catch (Throwable e) {
            DatabaseConnector.getInstance().getConn().rollback();
            if (e instanceof SQLException) {
                throw (SQLException) e;
            }
        } finally {
            curSt = null;
            DatabaseConnector.getInstance().getConn().setAutoCommit(autoCommit);
        }
    }

    /**
     * Deletes all experiment results at once (batch).
     * @param experimentResults the experiment results to be deleted
     * @throws SQLException
     */
    public static void deleteExperimentResults(ArrayList<ExperimentResult> experimentResults) throws SQLException {
        boolean autoCommit = DatabaseConnector.getInstance().getConn().getAutoCommit();
        try {
            DatabaseConnector.getInstance().getConn().setAutoCommit(false);
            PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(deleteQuery);
            curSt = st;
            for (ExperimentResult r : experimentResults) {
                st.setInt(1, r.getId());
                st.addBatch();
                r.setDeleted();
                /* this should only be done if the batch delete actually
                 * gets commited, right now this might not be the case
                 * if there's an DB exception or the executeBatch() is
                 * cancelled (see cancelStatement()).
                 * Without caching this might not be a problem.
                 */
            }
            st.executeBatch();
            st.close();
        } catch (SQLException e) {
            DatabaseConnector.getInstance().getConn().rollback();
            throw e;
        } finally {
            curSt = null;
            DatabaseConnector.getInstance().getConn().setAutoCommit(autoCommit);
        }
    }

    private static ExperimentResult getExperimentResultFromResultSet(ResultSet rs) throws SQLException {
        ExperimentResult r = new ExperimentResult();
        r.setSolverConfigId(rs.getInt("SolverConfig_idSolverConfig"));
        r.setInstanceId(rs.getInt("Instances_idInstance"));
        r.setExperimentId(rs.getInt("Experiment_idExperiment"));
        r.setId(rs.getInt("idJob"));
        r.setRun(rs.getInt("run"));
        r.setSeed(rs.getInt("seed"));
        r.setStatus(rs.getInt("status"));
        r.setResultTime(rs.getFloat("resultTime"));
        Integer resultCode = rs.getInt("resultCode");
        if (resultCode == null) {
            resultCode = -1;
        }
        r.setResultCode(resultCode);
        r.setSolverOutputFilename(rs.getString("solverOutputFN"));
        r.setLauncherOutputFilename(rs.getString("launcherOutputFN"));
        r.setWatcherOutputFilename(rs.getString("watcherOutputFN"));
        r.setVerifierOutputFilename(rs.getString("verifierOutputFN"));
        r.setSolverExitCode(rs.getInt("solverExitCode"));
        r.setWatcherExitCode(rs.getInt("watcherExitCode"));
        r.setVerifierExitCode(rs.getInt("verifierExitCode"));
        r.setComputeQueue(rs.getInt("computeQueue"));
        r.setPriority(rs.getInt("priority"));
        r.setStartTime(rs.getTimestamp("startTime"));

        r.setDatemodified(rs.getTimestamp("date_modified"));
        if (r.getStatus() == ExperimentResultStatus.RUNNING) {
            r.setRunningTime(rs.getInt("runningTime"));
        } else {
            r.setRunningTime(0);
        }
        return r;
    }

    /**
     * returns the number of jobs in the database for the given experiment
     * @param id experiment id
     * @return
     * @throws SQLException
     */
    public static int getCountByExperimentId(int id) throws SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT COUNT(*) as count FROM " + table + " WHERE Experiment_idExperiment=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        rs.next(); // there will always be a count
        int count = rs.getInt("count");
        rs.close();
        return count;
    }

    public static Timestamp getLastModifiedByExperimentId(int id) throws NoConnectionToDBException, SQLException {
        /*PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT IF(running > 0, TIMESTAMPADD(SECOND, -1, CURRENT_TIMESTAMP),ermodified) FROM (select MAX(date_modified) AS ermodified FROM " + table + " WHERE Experiment_idExperiment = ?) AS tbl1 JOIN (select COUNT(*) AS running FROM " + table + " WHERE Experiment_idExperiment = ? AND status = ?) AS tbl2");
        st.setInt(1, id);
        st.setInt(2, id);
        st.setInt(3, ExperimentResultStatus.RUNNING.getValue());*/
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("select MAX(TIMESTAMPADD(SECOND, 1, date_modified)) AS ermodified FROM " + table + " WHERE Experiment_idExperiment = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        rs.next(); // there will always be a timestamp
        Timestamp res = rs.getTimestamp(1);
        rs.close();
        return res;
    }

    /**
     * checks the database if a job with the given parameters already exists
     * @param run
     * @param solverConfigId
     * @param InstanceId
     * @param ExperimentId
     * @return bool
     * @throws SQLException
     */
    public static boolean jobExists(int run, int solverConfigId, int InstanceId, int ExperimentId) throws SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT COUNT(*) as count FROM " + table + " "
                + "WHERE run=? AND SolverConfig_idSolverConfig=? AND Instances_idInstance=? AND Experiment_idExperiment=? ;");
        st.setInt(1, run);
        st.setInt(2, solverConfigId);
        st.setInt(3, InstanceId);
        st.setInt(4, ExperimentId);
        ResultSet rs = st.executeQuery();
        rs.next();
        int count = rs.getInt("count");
        rs.close();
        return count > 0;
    }

    /**
     * returns the seed value of the job specified by the given parameters
     * @param run
     * @param solverConfigId
     * @param InstanceId
     * @param ExperimentId
     * @return bool
     * @throws SQLException
     */
    public static int getSeedValue(int run, int solverConfigId, int InstanceId, int ExperimentId) throws SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT seed FROM " + table + " "
                + "WHERE run=? AND SolverConfig_idSolverConfig=? AND Instances_idInstance=? AND Experiment_idExperiment=? ;");
        st.setInt(1, run);
        st.setInt(2, solverConfigId);
        st.setInt(3, InstanceId);
        st.setInt(4, ExperimentId);
        ResultSet rs = st.executeQuery();
        rs.next();
        int seed = rs.getInt("seed");
        rs.close();
        return seed;
    }

    /**
     * Returns all experiment results which were modified after the modified timestamp for a given experiment id
     * @param id the experiment id for the experiment results
     * @param modified the modified timestamp
     * @return
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws IOException
     * @throws PropertyNotInDBException
     * @throws PropertyTypeNotExistException
     */
    public static ArrayList<ExperimentResult> getAllModifiedByExperimentId(int id, Timestamp modified) throws NoConnectionToDBException, SQLException, IOException, PropertyNotInDBException, PropertyTypeNotExistException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException, ExperimentResultNotInDBException {
        ArrayList<ExperimentResult> v = new ArrayList<ExperimentResult>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery
                + "WHERE Experiment_idExperiment=? AND IF(status = " + ExperimentResultStatus.RUNNING.getValue() + ", TIMESTAMPADD(SECOND, -1, CURRENT_TIMESTAMP), date_modified) >= ?;");
        st.setInt(1, id);
        st.setTimestamp(2, modified);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ExperimentResult er = getExperimentResultFromResultSet(rs);
            v.add(er);
            er.setSaved();
        }
        ExperimentResultHasPropertyDAO.assign(v, id);
        rs.close();
        st.close();
        return v;
    }

    /**
     * returns all jobs of the given Experiment
     * @param id
     * @return ExperimentResults vector
     * @throws SQLException
     */
    public static ArrayList<ExperimentResult> getAllByExperimentId(int id) throws SQLException, IOException, PropertyTypeNotExistException, PropertyNotInDBException, NoConnectionToDBException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException, ExperimentResultNotInDBException {
        ArrayList<ExperimentResult> v = new ArrayList<ExperimentResult>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery
                + "WHERE Experiment_idExperiment=?;");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ExperimentResult er = getExperimentResultFromResultSet(rs);
            v.add(er);
            er.setSaved();
        }
        ExperimentResultHasPropertyDAO.assign(v, id);
        rs.close();
        st.close();
        return v;
    }

    public static ArrayList<ExperimentResultEx> getExtendedExperimentResultsFromExperimentResults(List<ExperimentResult> results) throws NoConnectionToDBException, SQLException {
        ArrayList<ExperimentResultEx> resx = new ArrayList<ExperimentResultEx>();
        if (results.isEmpty()) {
            return resx;
        }
        HashMap<Integer, ExperimentResult> resultMap = new HashMap<Integer, ExperimentResult>();
        String constTable = "(";
        for (ExperimentResult er : results) {
            resultMap.put(er.getId(), er);
            constTable += "" + er.getId() + ",";
        }
        constTable = constTable.substring(0, constTable.length() - 1) + ")";
        Statement st = DatabaseConnector.getInstance().getConn().createStatement();
        ResultSet rs = st.executeQuery("SELECT idJob, startTime, solverOutput, launcherOutput, watcherOutput, verifierOutput FROM " + table + " WHERE idJob IN " + constTable);
        while (rs.next()) {
            Timestamp startTime = null;
            try {
                startTime = rs.getTimestamp(2);
            } catch (Exception e) {
                // fails if the db DateTime objekt could not be converted to a Timestamp (illegal date.)
            }
            ExperimentResult er = resultMap.get(rs.getInt(1));
            ExperimentResultEx erx = createExperimentResult(er.getRun(), er.getPriority(), er.getComputeQueue(), er.getStatus().getValue(),
                    er.getResultCode(), er.getSeed(), er.getResultTime(), er.getSolverConfigId(), er.getExperimentId(),
                    er.getInstanceId(),
                    startTime,
                    rs.getBytes(3),
                    rs.getBytes(4),
                    rs.getBytes(5),
                    rs.getBytes(6));
            // set other fields
            erx.setResultTime(er.getResultTime());
            erx.setSolverExitCode(er.getSolverExitCode());
            erx.setVerifierExitCode(er.getVerifierExitCode());
            erx.setWatcherExitCode(er.getWatcherExitCode());
            resx.add(erx);
        }
        rs.close();
        st.close();
        return resx;
    }

    /**
     * Returns all runs for an experiment specified by id
     * @param id the experiment id
     * @return vector of run numbers
     * @throws SQLException
     */
    public static ArrayList<Integer> getAllRunsByExperimentId(int id) throws SQLException {
        ArrayList<Integer> res = new ArrayList<Integer>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT run "
                + "FROM " + table + " "
                + "WHERE Experiment_idExperiment=? GROUP BY run ORDER BY run;");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            res.add(rs.getInt(1));
        }

        return res;
    }

    /**
     * Returns all instance ids which have jobs in the database and are associated
     * with the experiment specified by id
     * @param id the experiment id
     * @return vector of solver config ids
     * @throws SQLException
     */
    public static ArrayList<Integer> getAllInstanceIdsByExperimentId(int id) throws SQLException {
        ArrayList<Integer> res = new ArrayList<Integer>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT Instances_idInstance "
                + "FROM " + table + " "
                + "WHERE Experiment_idExperiment=? GROUP BY Instances_idInstance ORDER BY Instances_idInstance;");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            res.add(rs.getInt(1));
        }

        return res;
    }

    /**
     * Returns all solver config ids which have jobs in the database and are associated
     * with the experiment specified by id.
     * @param id the experiment id
     * @return vector of solver config ids
     * @throws SQLException
     */
    public static ArrayList<Integer> getAllSolverConfigIdsByExperimentId(int id) throws SQLException {
        ArrayList<Integer> res = new ArrayList<Integer>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT SolverConfig_idSolverConfig "
                + "FROM " + table + " "
                + "WHERE Experiment_idExperiment=? GROUP BY SolverConfig_idSolverConfig ORDER BY SolverConfig_idSolverConfig;");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            res.add(rs.getInt(1));
        }

        return res;
    }

    /**
     * Returns all experiment results for the given parameters
     * @param eid the experiment id for the experiment results
     * @param run the run for the experiment results
     * @return
     * @throws SQLException
     * @throws PropertyNotInDBException
     * @throws PropertyTypeNotExistException
     * @throws IOException
     */
    public static ArrayList<ExperimentResult> getAllByExperimentIdAndRun(int eid, int run) throws SQLException, PropertyNotInDBException, PropertyTypeNotExistException, IOException, NoConnectionToDBException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException, ExperimentResultNotInDBException {
        ArrayList<ExperimentResult> res = new ArrayList<ExperimentResult>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery + "WHERE Experiment_idExperiment=? AND run=?;");
        st.setInt(1, eid);
        st.setInt(2, run);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ExperimentResult er = getExperimentResultFromResultSet(rs);
            res.add(er);
            er.setSaved();
        }
        ExperimentResultHasPropertyDAO.assign(res, eid);
        return res;
    }

    /**
     * Returns all experiment results for the given solver configuration
     * @param sc
     * @return
     * @throws SQLException
     * @throws PropertyNotInDBException
     * @throws PropertyTypeNotExistException
     * @throws IOException
     */
    public static ArrayList<ExperimentResult> getAllBySolverConfiguration(SolverConfiguration sc) throws SQLException, PropertyNotInDBException, PropertyTypeNotExistException, IOException, NoConnectionToDBException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException, ExperimentResultNotInDBException {
        ArrayList<ExperimentResult> res = new ArrayList<ExperimentResult>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery + "WHERE Experiment_idExperiment=? AND SolverConfig_idSolverConfig=?;");
        st.setInt(1, sc.getExperiment_id());
        st.setInt(2, sc.getId());
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ExperimentResult er = getExperimentResultFromResultSet(rs);
            res.add(er);
            er.setSaved();
        }
        ExperimentResultHasPropertyDAO.assign(res, sc.getExperiment_id());
        return res;
    }

    /**
     * Returns all experiment results for the given ExperimentHasInstance object.
     * @param ehi
     * @return
     * @throws SQLException
     * @throws PropertyNotInDBException
     * @throws PropertyTypeNotExistException
     * @throws IOException
     */
    public static ArrayList<ExperimentResult> getAllByExperimentHasInstance(ExperimentHasInstance ehi) throws SQLException, PropertyNotInDBException, PropertyTypeNotExistException, IOException, NoConnectionToDBException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException, ExperimentResultNotInDBException {
        ArrayList<ExperimentResult> res = new ArrayList<ExperimentResult>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery + "WHERE Experiment_idExperiment=? AND Instances_idInstance=?;");
        st.setInt(1, ehi.getExperiment_id());
        st.setInt(2, ehi.getInstances_id());
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ExperimentResult er = getExperimentResultFromResultSet(rs);
            res.add(er);
            er.setSaved();
        }
        ExperimentResultHasPropertyDAO.assign(res, ehi.getExperiment_id());
        return res;
    }

    /**
     * Sets the auto commit of the underlying connection.
     * @param commit
     * @throws SQLException
     */
    public static void setAutoCommit(boolean commit) throws SQLException {
        DatabaseConnector.getInstance().getConn().setAutoCommit(commit);
    }

    public static void cancelStatement() throws SQLException {
        if (curSt != null) {
            try {
                curSt.cancel();
            } catch (Exception _) {
            }
        }
    }

    /**
     *
     * @param id of the requested ExperimentResult
     * @return the ExperimentResult object with the given id
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws ExperimentResultNotInDBException
     * @author rretz
     */
    public static ExperimentResult getById(int id) throws NoConnectionToDBException, SQLException, ExperimentResultNotInDBException, PropertyTypeNotExistException, IOException, PropertyNotInDBException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery
                + "WHERE idJob=?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new ExperimentResultNotInDBException();
        }
        ExperimentResult er = getExperimentResultFromResultSet(rs);
        ps.close();
        ExperimentResultHasPropertyDAO.assign(er);
        return er;
    }

    /**
     *
     * @param id of the requested ExperimentResult
     * @return the ExperimentResult object with the given id
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws ExperimentResultNotInDBException
     * @author rretz
     */
    public static ExperimentResult getByIdWithoutAssign(int id) throws NoConnectionToDBException, SQLException, ExperimentResultNotInDBException, PropertyTypeNotExistException, IOException, PropertyNotInDBException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery
                + "WHERE idJob=?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new ExperimentResultNotInDBException();
        }
        ExperimentResult er = getExperimentResultFromResultSet(rs);
        ArrayList<ExperimentResult> tmp = new ArrayList<ExperimentResult>();
        tmp.add(er);
        //ExperimentResultHasPropertyDAO.assign(tmp, er.getExperimentId());
        return er;
    }

    /**
     * Copies the binary file of the client output of a ExperimentResult to a temporary location on the file system and retuns a File
     * reference on it.
     * @param expRes ExperimentResult from which the binary file is copied
     * @return reference of the binary file of the clinet output of the given ExperimentResult
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File getSolverOutputFile(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        File f = new File("tmp" + System.getProperty("file.separator") + expRes.getId() + "_" + expRes.getSolverOutputFilename());
        // create missing directories
        f.getParentFile().mkdirs();
        getSolverOutput(expRes.getId(), f);
        return f;
    }

    /**
     * Copies the binary file of the launcher file of a ExperimentResult to a temporary location on the file system and retuns a File
     * reference on it.
     * @param expRes expRes ExperimentResult from which the binary file is copied
     * @return reference of the binary file of the result file of the given ExperimentResult
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File getLauncherOutputFile(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        File f = new File("tmp" + System.getProperty("file.separator") + expRes.getId() + "_" + expRes.getLauncherOutputFilename());
        // create missing directories
        f.getParentFile().mkdirs();
        getLauncherOutput(expRes.getId(), f);
        return f;
    }

    public static Blob getLauncherOutput(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException, ExperimentResultNotInDBException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT lancherOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, expRes.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            try {
                return rs.getBlob(1);
            } finally {
                ps.close();
            }
        } else {
            throw new ExperimentResultNotInDBException();
        }
    }

    public static Blob getSolverOutput(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException, ExperimentResultNotInDBException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT solverOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, expRes.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            try {
                return rs.getBlob(1);
            } finally {
                ps.close();
            }
        } else {
            throw new ExperimentResultNotInDBException();
        }
    }

    public static Blob getVerifierOutput(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException, ExperimentResultNotInDBException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT verifierOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, expRes.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            try {
            return rs.getBlob(1);
            } finally {
                ps.close();
            }
        } else {
            throw new ExperimentResultNotInDBException();
        }
    }

    public static Blob getWatcherOutput(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException, ExperimentResultNotInDBException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT watcherOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, expRes.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob(1);
            ps.close();
            return blob;
        } else {
            throw new ExperimentResultNotInDBException();
        }
    }

    /**
     * Copies the binary file of the verifier file of a ExperimentResult to a temporary location on the file system and retuns a File
     * reference on it.
     * @param expRes expRes ExperimentResult from which the binary file is copied
     * @return reference of the binary file of the result file of the given ExperimentResult
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File getVerifierOutputFile(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        File f = new File("tmp" + System.getProperty("file.separator") + expRes.getId() + "_" + expRes.getVerifierOutputFilename());
        // create missing directories
        f.getParentFile().mkdirs();
        getVerifierOutput(expRes.getId(), f);
        return f;
    }

    /**
     * Copies the binary file of the watcher file of a ExperimentResult to a temporary location on the file system and retuns a File
     * reference on it.
     * @param expRes expRes ExperimentResult from which the binary file is copied
     * @return reference of the binary file of the result file of the given ExperimentResult
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File getWatcherOutputFile(ExperimentResult expRes) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        File f = new File("tmp" + System.getProperty("file.separator") + expRes.getId() + "_" + expRes.getWatcherOutputFilename());
        // create missing directories
        f.getParentFile().mkdirs();
        getWatcherOutput(expRes.getId(), f);
        return f;
    }

    /**
     * Copies the binary file of a result file of an ExperimentResult to a specified location on the filesystem.
     * @param id the id of the ExperimentResult
     * @param f the file in which the binary file is copied
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void getSolverOutput(int id, File f) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT solverOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            FileOutputStream out = new FileOutputStream(f);
            InputStream in = rs.getBinaryStream("solverOutput");
            int len;
            byte[] buf = new byte[256 * 1024];
            while ((len = in.read(buf)) > -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
    }

    /**
     * Copies the binary file of the launcher output of an ExperimentResult to a specified location on the filesystem.
     * @param id th id of the ExperimentResult
     * @param f the file in which the binary file is copied
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void getLauncherOutput(int id, File f) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT lancherOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            FileOutputStream out = new FileOutputStream(f);
            InputStream in = rs.getBinaryStream("lancherOutput");
            int len;
            byte[] buf = new byte[256 * 1024];
            while ((len = in.read(buf)) > -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
    }

    /**
     * Copies the binary file of the watcher output of an ExperimentResult to a specified location on the filesystem.
     * @param id th id of the ExperimentResult
     * @param f the file in which the binary file is copied
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void getWatcherOutput(int id, File f) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT watcherOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            FileOutputStream out = new FileOutputStream(f);
            InputStream in = rs.getBinaryStream("watcherOutput");
            int len;
            byte[] buf = new byte[256 * 1024];
            while ((len = in.read(buf)) > -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
    }

    /**
     * Copies the binary file of the verifier output of an ExperimentResult to a specified location on the filesystem.
     * @param id th id of the ExperimentResult
     * @param f the file in which the binary file is copied
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void getVerifierOutput(int id, File f) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT verifierOutput "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            FileOutputStream out = new FileOutputStream(f);
            InputStream in = rs.getBinaryStream("verifierOutput");
            int len;
            byte[] buf = new byte[256 * 1024];
            while ((len = in.read(buf)) > -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
    }

    /**
     * Returns a string representing the output of the given type.
     * @param type one of ExperimentResult.SOLVER_OUTPUT/LAUNCHER_OUTPUT/VERIFIER_OUTPUT/WATCHER_OUTPUT
     * @param er the experiment result for which the output string should be generated
     * @return null if there is no output, the string representing the output otherwise
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws IOException
     */
    public static String getOutputText(int type, ExperimentResult er) throws NoConnectionToDBException, SQLException, IOException {
        String col = null;
        switch (type) {
            case ExperimentResult.SOLVER_OUTPUT:
                col = "solverOutput";
                break;
            case ExperimentResult.LAUNCHER_OUTPUT:
                col = "launcherOutput";
                break;
            case ExperimentResult.VERIFIER_OUTPUT:
                col = "verifierOutput";
                break;
            case ExperimentResult.WATCHER_OUTPUT:
                col = "watcherOutput";
        }
        if (col == null) {
            return null;
        }

        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT " + col + " "
                + "FROM " + table + " "
                + "WHERE idJob=?;");
        ps.setInt(1, er.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            StringBuilder sb = new StringBuilder();
            InputStream in = rs.getBinaryStream(1);
            if (in == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            in.close();
            return sb.toString();
        }
        return null;
    }

    /**
     * Returns CURRENT_TIMESTAMP - 1 second
     * @return
     * @throws NoConnectionToDBException
     * @throws SQLException
     */
    public static Timestamp getCurrentTimestamp() throws NoConnectionToDBException, SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT TIMESTAMPADD(SECOND, -1, CURRENT_TIMESTAMP);");
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getTimestamp(1);
        } else {
            return null;
        }
    }

    public static ArrayList<ExperimentResult> getAllByInstanceId(int id) throws NoConnectionToDBException, SQLException, PropertyNotInDBException, PropertyTypeNotExistException, IOException, ComputationMethodDoesNotExistException, ExpResultHasSolvPropertyNotInDBException, ExperimentResultNotInDBException {
        ArrayList<ExperimentResult> v = new ArrayList<ExperimentResult>();
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                selectQuery
                + "WHERE Instances_idInstance=?;");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ExperimentResult er = getExperimentResultFromResultSet(rs);
            v.add(er);
            er.setSaved();
        }
        ExperimentResultHasPropertyDAO.assign(v, id);
        rs.close();
        st.close();
        return v;
    }
}
