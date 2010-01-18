package edacc.experiment;

import edacc.EDACCExperimentMode;
import edacc.EDACCSolverConfigEntry;
import edacc.EDACCSolverConfigPanel;
import edacc.model.Experiment;
import edacc.model.ExperimentDAO;
import edacc.model.ExperimentHasInstance;
import edacc.model.ExperimentHasInstanceDAO;
import edacc.model.ExperimentResultDAO;
import edacc.model.Instance;
import edacc.model.InstanceDAO;
import edacc.model.Solver;
import edacc.model.SolverConfiguration;
import edacc.model.SolverConfigurationDAO;
import edacc.model.SolverDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author daniel
 */
public class ExperimentController {

    EDACCExperimentMode main;
    EDACCSolverConfigPanel solverConfigPanel;
    private Experiment activeExperiment;
    private Vector<Experiment> experiments;
    private Vector<Instance> instances;
    private Vector<Solver> solvers;

    /**
     * Creates a new experiment Controller
     * @param experimentMode
     * @param solverConfigPanel
     */
    public ExperimentController(EDACCExperimentMode experimentMode, EDACCSolverConfigPanel solverConfigPanel) {
        this.main = experimentMode;
        this.solverConfigPanel = solverConfigPanel;
    }

    /**
     * Initializes the experiment controller. Loads the experiments and the instances.
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        Vector<Experiment> v = new Vector<Experiment>();
        v.addAll(ExperimentDAO.getAll());
        experiments = v;
        main.expTableModel.setExperiments(experiments);

        Vector<Instance> vi = new Vector<Instance>();
        vi.addAll(InstanceDAO.getAll());
        instances = vi;
        main.insTableModel.setInstances(instances);
    }

    /**
     * Loads an experiment, the solvers and the solver configurations.
     * @param id
     * @throws SQLException
     */
    public void loadExperiment(int id) throws SQLException {
        if (activeExperiment != null) {
            // TODO! messagedlg,..
            solverConfigPanel.removeAll();
        }
        activeExperiment = ExperimentDAO.getById(id);
        Vector<Solver> vs = new Vector<Solver>();
        vs.addAll(SolverDAO.getAll());
        solvers = vs;
        main.solTableModel.setSolvers(solvers);

        Vector<SolverConfiguration> vss = SolverConfigurationDAO.getSolverConfigurationByExperimentId(id);
        main.solverConfigPanel.beginUpdate();
        for (int i = 0; i < vss.size(); i++) {
            main.solverConfigPanel.addSolverConfiguration(vss.get(i));
            for (int k = 0; k < main.solTableModel.getRowCount(); k++) {
                if (((Solver) main.solTableModel.getValueAt(k, 5)).getId() == vss.get(i).getSolver_id()) {
                    main.solTableModel.setValueAt(true, k, 4);
                }
            }
        }
        main.solverConfigPanel.endUpdate();

        main.insTableModel.setExperimentHasInstances(ExperimentHasInstanceDAO.getExperimentHasInstanceByExperimentId(activeExperiment.getId()));
        main.afterExperimentLoaded();
    }

    /**
     * Removes an experiment form the db.
     * @param id
     * @return
     * @throws SQLException
     */
    public void removeExperiment(int id) throws SQLException {
        Experiment e = ExperimentDAO.getById(id);
        if (e.equals(activeExperiment)) {
            unloadExperiment();
        }
        ExperimentDAO.removeExperiment(e);
        initialize();
    }

    public Experiment getActiveExperiment() {
        return activeExperiment;
    }

    public void unloadExperiment() {
        activeExperiment = null;
        main.afterExperimentUnloaded();
    }

    public void createExperiment(String name, Date date, String description) throws SQLException {
        ExperimentDAO.createExperiment(name, date, description);
        initialize();
    }

    /**
     * Saves all solver configurations with parameter instances in the solver
     * config panel.
     * @throws SQLException
     */
    public void saveSolverConfigurations() throws SQLException {
        for (int i = 0; i < solverConfigPanel.getComponentCount(); i++) {
            EDACCSolverConfigEntry entry = (EDACCSolverConfigEntry) solverConfigPanel.getComponent(i);
            if (entry.getSolverConfiguration() == null) {
                entry.setSolverConfiguration(SolverConfigurationDAO.createSolverConfiguration(entry.getSolverId(), activeExperiment.getId()));
            }
            entry.saveParameterInstances();
        }
        SolverConfigurationDAO.saveAll();
    }

    public void saveExperimentHasInstances() throws SQLException {
        for (int i = 0; i < main.insTableModel.getRowCount(); i++) {
            if ((Boolean) main.insTableModel.getValueAt(i, 5)) {
                if ((ExperimentHasInstance) main.insTableModel.getValueAt(i, 6) == null) {
                    main.insTableModel.setExperimentHasInstance(ExperimentHasInstanceDAO.createExperimentHasInstance(activeExperiment.getId(), ((Instance) main.insTableModel.getValueAt(i, 7)).getId()), i);
                }
            } else {
                ExperimentHasInstance ei = (ExperimentHasInstance) main.insTableModel.getValueAt(i, 6);
                if (ei != null) {
                    ExperimentHasInstanceDAO.removeExperimentHasInstance(ei);
                    main.insTableModel.setExperimentHasInstance(null, i);
                }
            }
        }
    }


    /**
     * method used for auto seed generation
     * @return
     */
    private int generateSeed() {
        return 42;
    }

    /**
     * generates the ExperimentResults (jobs) in the database for the currently active experiment
     * This is the cartesian product of the set of solver configs and the set of the selected instances
     * @throws SQLException
     */
    public void generateJobs(int numRuns, int timeout, boolean generateSeeds) throws SQLException {
        activeExperiment.setAutoGeneratedSeeds(generateSeeds);
        activeExperiment.setNumRuns(numRuns);
        activeExperiment.setTimeOut(timeout);
        ExperimentDAO.save(activeExperiment);
        
        // get instances of this experiment
        LinkedList<Instance> listInstances = InstanceDAO.getAllByExperimentId(activeExperiment.getId());

        // get solver configurations of this experiment
        Vector<SolverConfiguration> vsc = new Vector<SolverConfiguration>();
        for (int i = 0; i < solverConfigPanel.getComponentCount(); i++) {
            EDACCSolverConfigEntry entry = (EDACCSolverConfigEntry) solverConfigPanel.getComponent(i);
            if (entry.getSolverConfiguration() != null) {
                vsc.add(entry.getSolverConfiguration());
            }
        }

        int seed = 0;
        if (generateSeeds) seed = generateSeed();

        // cartesian product
        for (Instance i: listInstances) {
            for (SolverConfiguration c: vsc) {
                ExperimentResultDAO.createExperimentResult(0, 0, seed, "", 0, 0, c.getId(), activeExperiment.getId(), i.getId());
            }
        }
        ExperimentResultDAO.clearCache();
    }
}