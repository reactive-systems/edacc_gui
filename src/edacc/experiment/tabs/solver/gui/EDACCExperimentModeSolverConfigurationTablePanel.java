/*
 * EDACCExperimentModeSolverConfigurationTablePanel.java
 *
 * Created on 17.03.2011, 19:44:10
 */
package edacc.experiment.tabs.solver.gui;

import edacc.EDACCApp;
import edacc.JTableTooltipInformation;
import edacc.experiment.ExperimentController;
import edacc.experiment.tabs.solver.EDACCSolverConfigEntryAdapter;
import edacc.experiment.tabs.solver.SolverConfigurationEntry;
import edacc.experiment.tabs.solver.SolverConfigurationEntryModel;
import edacc.experiment.tabs.solver.SolverConfigurationEntryModelListener;
import edacc.model.ParameterInstance;
import edacc.model.Solver;
import edacc.model.SolverConfiguration;
import edacc.model.TaskRunnable;
import edacc.model.Tasks;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author simon
 */
public class EDACCExperimentModeSolverConfigurationTablePanel extends javax.swing.JPanel implements SolverConfigurationEntryModelListener {

    private TableModel tableModel;
    private ExperimentController expController;
    private SolverConfigurationEntryModel model;

    /** Creates new form EDACCExperimentModeSolverConfigurationTablePanel */
    public EDACCExperimentModeSolverConfigurationTablePanel(ExperimentController expController) {
        initComponents();
        this.expController = expController;
        this.model = expController.getSolverConfigurationEntryModel();
        model.addSolverConfigurationEntryModelListener(this);
        tableModel = new TableModel();
        table.setModel(tableModel);

        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = table.getInputMap(condition);
        ActionMap actionMap = table.getActionMap();

        itemRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Delete");
        actionMap.put("Delete", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                itemRemove.doClick();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        itemEdit = new javax.swing.JMenuItem();
        itemCalculateCosts = new javax.swing.JMenuItem();
        itemRemove = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = table = new JTableTooltipInformation();
        lblFilterStatus = new javax.swing.JLabel();

        popupMenu.setName("popupMenu"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCExperimentModeSolverConfigurationTablePanel.class);
        itemEdit.setText(resourceMap.getString("itemEdit.text")); // NOI18N
        itemEdit.setName("itemEdit"); // NOI18N
        itemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditActionPerformed(evt);
            }
        });
        popupMenu.add(itemEdit);

        itemCalculateCosts.setText(resourceMap.getString("itemCalculateCosts.text")); // NOI18N
        itemCalculateCosts.setName("itemCalculateCosts"); // NOI18N
        itemCalculateCosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCalculateCostsActionPerformed(evt);
            }
        });
        popupMenu.add(itemCalculateCosts);

        itemRemove.setText(resourceMap.getString("itemRemove.text")); // NOI18N
        itemRemove.setName("itemRemove"); // NOI18N
        itemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRemoveActionPerformed(evt);
            }
        });
        popupMenu.add(itemRemove);

        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setComponentPopupMenu(popupMenu);
        table.setName("table"); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        lblFilterStatus.setText(resourceMap.getString("lblFilterStatus.text")); // NOI18N
        lblFilterStatus.setName("lblFilterStatus"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFilterStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblFilterStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void onDataChanged() {
        tableModel.update();
    }

    @Override
    public void onEntryChanged(SolverConfigurationEntry entry) {
        tableModel.updateEntry(entry);
    }

    private class SolverConfigDialog extends JDialog {

        private boolean apply;

        public SolverConfigDialog(EDACCSolverConfigEntry entry) {
            super();

            setTitle("Edit Solver Configuration");
            JButton btnCancel = new JButton("Cancel");
            JButton btnApply = new JButton("Apply");
            apply = false;
            btnCancel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SolverConfigDialog.this.setVisible(false);
                }
            });

            btnApply.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SolverConfigDialog.this.apply = true;
                    SolverConfigDialog.this.setVisible(false);
                }
            });

            JPanel bottom = new JPanel();
            bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));
            bottom.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            bottom.add(Box.createHorizontalGlue());

            bottom.add(btnCancel);

            bottom.add(Box.createRigidArea(new Dimension(10, 0)));
            bottom.add(btnApply);

            Container contentPane = getContentPane();
            contentPane.add(entry, BorderLayout.CENTER);
            contentPane.add(bottom, BorderLayout.SOUTH);
        }

        public boolean userApplied() {
            return apply;
        }
    }

    private void itemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditActionPerformed
        if (table.getSelectedRow() != -1) {
            // TODO: fix
            SolverConfigurationEntry entry = tableModel.getEntry(table.convertRowIndexToModel(table.getSelectedRow()));
            try {
                SolverConfigurationEntry copy = new SolverConfigurationEntry(entry);
                EDACCSolverConfigEntry entryDialog = new EDACCSolverConfigEntry(copy, new EDACCSolverConfigEntryAdapter());

                SolverConfigDialog dialog = new SolverConfigDialog(entryDialog);
                dialog.setLocationRelativeTo(EDACCApp.getApplication().getMainFrame());
                dialog.setModal(true);
                EDACCApp.getApplication().show(dialog);
                if (dialog.userApplied()) {
                    entry.assign(copy);
                }
                dialog.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.fireEntryChanged(entry);
        }
    }//GEN-LAST:event_itemEditActionPerformed

    private void itemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRemoveActionPerformed
        for (int row : table.getSelectedRows()) {
            SolverConfigurationEntry entry = tableModel.getEntry(table.convertRowIndexToModel(row));
            model.removeSolverConfigurationEntry(entry);
            if (entry.getSolverConfig() != null) {
                expController.solverConfigCache.markAsDeleted(entry.getSolverConfig());
            }
        }
        model.fireDataChanged();
    }//GEN-LAST:event_itemRemoveActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() >= 2) {
            itemEditActionPerformed(null);
            evt.consume();
        }
    }//GEN-LAST:event_tableMouseClicked

    private void itemCalculateCostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCalculateCostsActionPerformed
        final ArrayList<SolverConfiguration> selected = new ArrayList<SolverConfiguration>();
        for (int row : table.getSelectedRows()) {
            SolverConfigurationEntry entry = tableModel.getEntry(table.convertRowIndexToModel(row));
            if (entry.getSolverConfig() != null) {
                selected.add(entry.getSolverConfig());
            }
        }
        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No saved solver configurations for cost calculations selected.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            final String costFunction = JOptionPane.showInputDialog("Cost function");
            if (costFunction == null) {
                JOptionPane.showMessageDialog(this, "No cost function selected.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Tasks.startTask(new TaskRunnable() {

                    @Override
                    public void run(Tasks task) {
                        try {
                            expController.calculateCosts(task, costFunction, selected);
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    model.fireDataChanged();
                                }
                            });

                        } catch (final Exception e) {
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    JOptionPane.showMessageDialog(EDACCExperimentModeSolverConfigurationTablePanel.this, "Error while calculating costs:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                                }
                            });
                        }
                    }
                });
            }
        }
    }//GEN-LAST:event_itemCalculateCostsActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemCalculateCosts;
    private javax.swing.JMenuItem itemEdit;
    private javax.swing.JMenuItem itemRemove;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblFilterStatus;
    private javax.swing.JPopupMenu popupMenu;
    public javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    private class TableModel extends DefaultTableModel {

        private String[] columns = new String[]{"Solver", "Solver Configuration", "Hint", "Cost", "Cost Function", "Parameters"};
        ArrayList<String> parameterStrings;
        ArrayList<SolverConfigurationEntry> entries;

        public void update() {
            // TODO: fix!
            entries = new ArrayList<SolverConfigurationEntry>();
            parameterStrings = new ArrayList<String>();
            for (Solver solver : model.getSolvers()) {
                for (SolverConfigurationEntry entry : model.getEntries(solver)) {
                    entries.add(entry);
                    ArrayList<ParameterInstance> pis = new ArrayList<ParameterInstance>();
                    for (int row = 0; row < entry.getTableModel().getRowCount(); row++) {
                        if (entry.getTableModel().isSelected(row)) {
                            ParameterInstance pi = new ParameterInstance();
                            pi.setParameter_id(entry.getTableModel().getParameterAt(row).getId());
                            pi.setValue(entry.getTableModel().getValueAt(row));
                            pis.add(pi);
                        }
                    }
                    parameterStrings.add(edacc.experiment.Util.getSolverParameterString(pis, solver));
                }
            }
            fireTableDataChanged();
        }

        public void updateEntry(SolverConfigurationEntry entry) {
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i) == entry) {
                    ArrayList<ParameterInstance> pis = new ArrayList<ParameterInstance>();
                    for (int row = 0; row < entry.getTableModel().getRowCount(); row++) {
                        if (entry.getTableModel().isSelected(row)) {
                            ParameterInstance pi = new ParameterInstance();
                            pi.setParameter_id(entry.getTableModel().getParameterAt(row).getId());
                            pi.setValue(entry.getTableModel().getValueAt(row));
                            pis.add(pi);
                        }
                    }
                    parameterStrings.set(i, edacc.experiment.Util.getSolverParameterString(pis, entry.getSolver()));
                    fireTableRowsUpdated(i, i);
                    break;
                }
            }
        }

        public SolverConfigurationEntry getEntry(int row) {
            return entries.get(row);
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public int getRowCount() {
            return entries == null ? 0 : entries.size();
        }

        @Override
        public Object getValueAt(int row, int column) {
            try {
                switch (column) {
                    case 0:
                        return entries.get(row).getSolver();
                    case 1:
                        return entries.get(row).getName();
                    case 2:
                        return entries.get(row).getHint();
                    case 3:
                        return entries.get(row).getSolverConfig() != null ? entries.get(row).getSolverConfig().getCost() : null;
                    case 4:
                        return entries.get(row).getSolverConfig() != null ? entries.get(row).getSolverConfig().getCost_function() : null;
                    case 5:
                        return parameterStrings.get(row);
                    default:
                        return "";
                }
            } catch (Exception e) {
                return "";
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Solver.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return Float.class;
                case 4:
                    return String.class;
                case 5:
                    return String.class;
                default:
                    return String.class;
            }
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public SolverConfigurationEntry getSelectedSolverConfigEntry() {
        int row;
        if ((row = table.getSelectedRow()) == -1) {
            return null;
        }
        row = table.convertRowIndexToModel(row);
        return tableModel.getEntry(row);
    }
}
