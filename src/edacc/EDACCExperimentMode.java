/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCExperimentMode.java
 *
 * Created on 02.01.2010, 00:25:47
 */
package edacc;

import edacc.experiment.ExperimentController;
import edacc.experiment.ExperimentTableModel;
import edacc.experiment.InstanceTableModel;
import edacc.experiment.SolverTableModel;
import edacc.model.Solver;
import java.sql.SQLException;
import org.jdesktop.application.Action;

/**
 *
 * @author simon
 */
public class EDACCExperimentMode extends javax.swing.JPanel {

    public ExperimentController expController;
    public ExperimentTableModel expTableModel;
    public InstanceTableModel insTableModel;
    public SolverTableModel solTableModel;
    public EDACCSolverConfigPanel solverConfigPanel;

    /** Creates new form EDACCExperimentMode */
    public EDACCExperimentMode() {
        initComponents();
        manageExperimentPane.setEnabledAt(1, false);
        manageExperimentPane.setEnabledAt(2, false);
        manageExperimentPane.setEnabledAt(3, false);

        expController = new ExperimentController(this, solverConfigPanel);
        expTableModel = new ExperimentTableModel();
        insTableModel = new InstanceTableModel();
        solTableModel = new SolverTableModel();
        tableExperiments.setModel(expTableModel);
        tableInstances.setModel(insTableModel);
        tableSolvers.setModel(solTableModel);
    }

    public void initialize() {
        expController.initialize();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageExperimentPane = new javax.swing.JTabbedPane();
        panelManageExperiment = new javax.swing.JPanel();
        scrollPaneExperimentsTable = new javax.swing.JScrollPane();
        tableExperiments = new javax.swing.JTable();
        btnRemoveExperiment = new javax.swing.JButton();
        btnLoadExperiment = new javax.swing.JButton();
        pnlNewExperiment = new javax.swing.JPanel();
        lblExperimentName = new javax.swing.JLabel();
        lblExperimentDate = new javax.swing.JLabel();
        lblExperimentDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExperimentDescription = new javax.swing.JTextArea();
        txtExperimentName = new javax.swing.JTextField();
        txtExperimentDate = new javax.swing.JTextField();
        btnCreateExperiment = new javax.swing.JButton();
        panelChooseSolver = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSolvers = new javax.swing.JTable();
        btnDeselectAllSolvers = new javax.swing.JButton();
        btnChooseSolvers = new javax.swing.JButton();
        btnSelectAllSolvers = new javax.swing.JButton();
        btnReverseSolverSelection = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        btnSaveSolverConfigurations = new javax.swing.JButton();
        panelChooseInstances = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableInstances = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        panelExperimentParams = new javax.swing.JPanel();

        setName("Form"); // NOI18N

        manageExperimentPane.setName("manageExperimentPane"); // NOI18N

        panelManageExperiment.setName("panelManageExperiment"); // NOI18N

        scrollPaneExperimentsTable.setName("scrollPaneExperimentsTable"); // NOI18N

        tableExperiments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableExperiments.setName("tableExperiments"); // NOI18N
        scrollPaneExperimentsTable.setViewportView(tableExperiments);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCExperimentMode.class, this);
        btnRemoveExperiment.setAction(actionMap.get("btnRemoveExperiment")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCExperimentMode.class);
        btnRemoveExperiment.setText(resourceMap.getString("btnRemoveExperiment.text")); // NOI18N
        btnRemoveExperiment.setName("btnRemoveExperiment"); // NOI18N

        btnLoadExperiment.setAction(actionMap.get("btnLoadExperiment")); // NOI18N
        btnLoadExperiment.setText(resourceMap.getString("btnLoadExperiment.text")); // NOI18N
        btnLoadExperiment.setName("btnLoadExperiment"); // NOI18N

        pnlNewExperiment.setBorder(javax.swing.BorderFactory.createTitledBorder("New Experiment"));
        pnlNewExperiment.setName("pnlNewExperiment"); // NOI18N

        lblExperimentName.setName("lblExperimentName"); // NOI18N

        lblExperimentDate.setName("lblExperimentDate"); // NOI18N

        lblExperimentDescription.setName("lblExperimentDescription"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtExperimentDescription.setColumns(20);
        txtExperimentDescription.setRows(5);
        txtExperimentDescription.setName("txtExperimentDescription"); // NOI18N
        jScrollPane1.setViewportView(txtExperimentDescription);

        txtExperimentName.setName("txtExperimentName"); // NOI18N

        txtExperimentDate.setName("txtExperimentDate"); // NOI18N

        btnCreateExperiment.setAction(actionMap.get("btnCreateExperiment")); // NOI18N
        btnCreateExperiment.setText(resourceMap.getString("btnCreateExperiment.text")); // NOI18N
        btnCreateExperiment.setName("btnCreateExperiment"); // NOI18N

        javax.swing.GroupLayout pnlNewExperimentLayout = new javax.swing.GroupLayout(pnlNewExperiment);
        pnlNewExperiment.setLayout(pnlNewExperimentLayout);
        pnlNewExperimentLayout.setHorizontalGroup(
            pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewExperimentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExperimentDate)
                    .addComponent(lblExperimentName)
                    .addComponent(lblExperimentDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtExperimentName)
                    .addComponent(txtExperimentDate)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(btnCreateExperiment, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNewExperimentLayout.setVerticalGroup(
            pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewExperimentLayout.createSequentialGroup()
                .addGroup(pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlNewExperimentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCreateExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNewExperimentLayout.createSequentialGroup()
                        .addGroup(pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExperimentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExperimentName))
                        .addGap(8, 8, 8)
                        .addGroup(pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExperimentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExperimentDate))
                        .addGap(10, 10, 10)
                        .addGroup(pnlNewExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblExperimentDescription)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelManageExperimentLayout = new javax.swing.GroupLayout(panelManageExperiment);
        panelManageExperiment.setLayout(panelManageExperimentLayout);
        panelManageExperimentLayout.setHorizontalGroup(
            panelManageExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageExperimentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneExperimentsTable, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                    .addGroup(panelManageExperimentLayout.createSequentialGroup()
                        .addComponent(pnlNewExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(btnRemoveExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelManageExperimentLayout.setVerticalGroup(
            panelManageExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageExperimentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneExperimentsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelManageExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNewExperiment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelManageExperimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLoadExperiment)
                        .addComponent(btnRemoveExperiment)))
                .addContainerGap())
        );

        manageExperimentPane.addTab("Experiments", panelManageExperiment);

        panelChooseSolver.setName("panelChooseSolver"); // NOI18N

        jSplitPane1.setDividerLocation(500);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tableSolvers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSolvers.setName("tableSolvers"); // NOI18N
        jScrollPane3.setViewportView(tableSolvers);

        btnDeselectAllSolvers.setAction(actionMap.get("btnDeselectAll")); // NOI18N
        btnDeselectAllSolvers.setText(resourceMap.getString("btnDeselectAllSolvers.text")); // NOI18N
        btnDeselectAllSolvers.setName("btnDeselectAllSolvers"); // NOI18N

        btnChooseSolvers.setAction(actionMap.get("btnChooseSolvers")); // NOI18N
        btnChooseSolvers.setText(resourceMap.getString("btnChooseSolvers.text")); // NOI18N
        btnChooseSolvers.setName("btnChooseSolvers"); // NOI18N

        btnSelectAllSolvers.setAction(actionMap.get("btnSelectAllSolvers")); // NOI18N
        btnSelectAllSolvers.setText(resourceMap.getString("btnSelectAllSolvers.text")); // NOI18N
        btnSelectAllSolvers.setName("btnSelectAllSolvers"); // NOI18N

        btnReverseSolverSelection.setAction(actionMap.get("btnReverseSolverSelection")); // NOI18N
        btnReverseSolverSelection.setText(resourceMap.getString("btnReverseSolverSelection.text")); // NOI18N
        btnReverseSolverSelection.setName("btnReverseSolverSelection"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnSelectAllSolvers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeselectAllSolvers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReverseSolverSelection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addComponent(btnChooseSolvers)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChooseSolvers)
                    .addComponent(btnDeselectAllSolvers)
                    .addComponent(btnSelectAllSolvers)
                    .addComponent(btnReverseSolverSelection))
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setName("jScrollPane4"); // NOI18N
        solverConfigPanel = new EDACCSolverConfigPanel();
        jScrollPane4.setViewportView(solverConfigPanel);
        jSplitPane1.setRightComponent(jScrollPane4);

        btnSaveSolverConfigurations.setAction(actionMap.get("btnSaveSolverConfigurations")); // NOI18N
        btnSaveSolverConfigurations.setText(resourceMap.getString("btnSaveSolverConfigurations.text")); // NOI18N
        btnSaveSolverConfigurations.setName("btnSaveSolverConfigurations"); // NOI18N

        javax.swing.GroupLayout panelChooseSolverLayout = new javax.swing.GroupLayout(panelChooseSolver);
        panelChooseSolver.setLayout(panelChooseSolverLayout);
        panelChooseSolverLayout.setHorizontalGroup(
            panelChooseSolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChooseSolverLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChooseSolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                    .addComponent(btnSaveSolverConfigurations, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panelChooseSolverLayout.setVerticalGroup(
            panelChooseSolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChooseSolverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveSolverConfigurations)
                .addGap(81, 81, 81))
        );

        manageExperimentPane.addTab("Solvers", panelChooseSolver);

        panelChooseInstances.setName("panelChooseInstances"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tableInstances.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableInstances.setName("tableInstances"); // NOI18N
        jScrollPane2.setViewportView(tableInstances);

        jButton3.setName("jButton3"); // NOI18N

        jButton4.setName("jButton4"); // NOI18N

        jButton5.setName("jButton5"); // NOI18N

        jButton6.setName("jButton6"); // NOI18N

        jButton7.setName("jButton7"); // NOI18N

        javax.swing.GroupLayout panelChooseInstancesLayout = new javax.swing.GroupLayout(panelChooseInstances);
        panelChooseInstances.setLayout(panelChooseInstancesLayout);
        panelChooseInstancesLayout.setHorizontalGroup(
            panelChooseInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChooseInstancesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChooseInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                    .addGroup(panelChooseInstancesLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 670, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelChooseInstancesLayout.setVerticalGroup(
            panelChooseInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChooseInstancesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelChooseInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        manageExperimentPane.addTab("Instances", panelChooseInstances);

        panelExperimentParams.setName("panelExperimentParams"); // NOI18N

        javax.swing.GroupLayout panelExperimentParamsLayout = new javax.swing.GroupLayout(panelExperimentParams);
        panelExperimentParams.setLayout(panelExperimentParamsLayout);
        panelExperimentParamsLayout.setHorizontalGroup(
            panelExperimentParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        panelExperimentParamsLayout.setVerticalGroup(
            panelExperimentParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );

        manageExperimentPane.addTab("Experiment Parameters", panelExperimentParams);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(manageExperimentPane, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(manageExperimentPane, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Method to be called after an experiment is loaded.
     */
    public void afterExperimentLoaded() {
        manageExperimentPane.setEnabledAt(1, true);
        manageExperimentPane.setEnabledAt(2, true);
        manageExperimentPane.setEnabledAt(3, true);
    }

    /**
     * Method to be call after an experiment ist unloaded.
     */
    public void afterExperimentUnloaded() {
        manageExperimentPane.setEnabledAt(1, false);
        manageExperimentPane.setEnabledAt(2, false);
        manageExperimentPane.setEnabledAt(3, false);
    }

    private void createDatabaseErrorMessage(SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "There was an error while communicating with the database: " +e, "Connection error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    @Action
    public void btnLoadExperiment() {
        if (tableExperiments.getSelectedRow() != -1) {
            try {
                Integer i = (Integer) expTableModel.getValueAt(tableExperiments.getSelectedRow(), 5);
                expController.loadExperiment(i.intValue());
            } catch (SQLException ex) {
                createDatabaseErrorMessage(ex);
            }

        }
    }

    @Action
    public void btnCreateExperiment() {
        try {
            expController.createExperiment(txtExperimentName.getText(), java.sql.Date.valueOf(txtExperimentDate.getText()), txtExperimentDescription.getText());
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "The date format you entered could not be recognized. Expected: yyyy-mm-dd", "Incorrect Date", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action
    public void btnRemoveExperiment() {
        if (tableExperiments.getSelectedRow() != -1) {
            try {
                Integer i = (Integer) expTableModel.getValueAt(tableExperiments.getSelectedRow(), 5);
                expController.removeExperiment(i);
            } catch (SQLException ex) {
                createDatabaseErrorMessage(ex);
            }
        }
    }

    @Action
    public void btnChooseSolvers() {
        for (int i = 0; i < solTableModel.getRowCount(); i++) {
            if ((Boolean) solTableModel.getValueAt(i, 4) && !solverConfigPanel.solverExists(((Solver)solTableModel.getValueAt(i, 5)).getId())) {
                solverConfigPanel.addSolver(solTableModel.getValueAt(i, 5));
            }
        }
    }

    @Action
    public void btnSaveSolverConfigurations() {
        try {
            expController.saveSolverConfigurations();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action
    public void btnSelectAllSolvers() {
        for (int i = 0; i < solTableModel.getRowCount(); i++) {
            solTableModel.setValueAt(true, i, 4);
        }
    }

    @Action
    public void btnDeselectAll() {
        for (int i = 0; i < solTableModel.getRowCount(); i++) {
            solTableModel.setValueAt(false, i, 4);
        }
    }

    @Action
    public void btnReverseSolverSelection() {
        for (int i = 0; i < solTableModel.getRowCount(); i++) {
            solTableModel.setValueAt(!((Boolean) solTableModel.getValueAt(i, 4)), i, 4);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseSolvers;
    private javax.swing.JButton btnCreateExperiment;
    private javax.swing.JButton btnDeselectAllSolvers;
    private javax.swing.JButton btnLoadExperiment;
    private javax.swing.JButton btnRemoveExperiment;
    private javax.swing.JButton btnReverseSolverSelection;
    private javax.swing.JButton btnSaveSolverConfigurations;
    private javax.swing.JButton btnSelectAllSolvers;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblExperimentDate;
    private javax.swing.JLabel lblExperimentDescription;
    private javax.swing.JLabel lblExperimentName;
    private javax.swing.JTabbedPane manageExperimentPane;
    private javax.swing.JPanel panelChooseInstances;
    private javax.swing.JPanel panelChooseSolver;
    private javax.swing.JPanel panelExperimentParams;
    private javax.swing.JPanel panelManageExperiment;
    private javax.swing.JPanel pnlNewExperiment;
    private javax.swing.JScrollPane scrollPaneExperimentsTable;
    private javax.swing.JTable tableExperiments;
    private javax.swing.JTable tableInstances;
    private javax.swing.JTable tableSolvers;
    private javax.swing.JTextField txtExperimentDate;
    private javax.swing.JTextArea txtExperimentDescription;
    private javax.swing.JTextField txtExperimentName;
    // End of variables declaration//GEN-END:variables
}
