/*
 * ImportSummaryPanel.java
 *
 * Created on 13.02.2012, 12:30:14
 */
package edacc.importexport;

import edacc.EDACCApp;
import edacc.model.Solver;
import edacc.model.Verifier;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author simon
 */
public class ImportSummaryPanel extends javax.swing.JPanel {

    private ImportController controller;
    private ImportSolverTableModel tableModel;
    private ImportVerifierTableModel verifierTableModel;
    private JComboBox[] combos;
    private JComboBox[] verifierCombos;

    /** Creates new form ImportSummaryPanel */
    public ImportSummaryPanel(ImportController controller) {
        initComponents();
        this.controller = controller;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblExperiments = new javax.swing.JLabel();
        lblSolvers = new javax.swing.JLabel();
        lblInstances = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblVerifiers = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSolvers = new javax.swing.JTable();
        btnSolverDetails = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVerifiers = new javax.swing.JTable();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(ImportSummaryPanel.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        lblExperiments.setText(resourceMap.getString("lblExperiments.text")); // NOI18N
        lblExperiments.setName("lblExperiments"); // NOI18N

        lblSolvers.setText(resourceMap.getString("lblSolvers.text")); // NOI18N
        lblSolvers.setName("lblSolvers"); // NOI18N

        lblInstances.setText(resourceMap.getString("lblInstances.text")); // NOI18N
        lblInstances.setName("lblInstances"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        lblVerifiers.setText(resourceMap.getString("lblVerifiers.text")); // NOI18N
        lblVerifiers.setName("lblVerifiers"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVerifiers)
                    .addComponent(lblInstances)
                    .addComponent(lblSolvers)
                    .addComponent(lblExperiments))
                .addContainerGap(523, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblExperiments))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblSolvers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblInstances))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblVerifiers))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblSolvers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSolvers.setName("tblSolvers"); // NOI18N
        tblSolvers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSolversMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSolvers);

        btnSolverDetails.setText(resourceMap.getString("btnSolverDetails.text")); // NOI18N
        btnSolverDetails.setName("btnSolverDetails"); // NOI18N
        btnSolverDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolverDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(537, Short.MAX_VALUE)
                .addComponent(btnSolverDetails)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSolverDetails))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tblVerifiers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblVerifiers.setName("tblVerifiers"); // NOI18N
        jScrollPane2.setViewportView(tblVerifiers);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSolversMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSolversMouseClicked
        if (evt.getClickCount() >= 2) {
            evt.consume();
            int index;
            if ((index = tblSolvers.getSelectedRow()) != -1) {
                showSolverDetails(index);
            }
        }
    }//GEN-LAST:event_tblSolversMouseClicked

    private void showSolverDetails(int tableRow) {
        int modelRow = tblSolvers.convertRowIndexToModel(tableRow);
        ImportSolverDetail dialog = new ImportSolverDetail(EDACCApp.getApplication().getMainFrame(), true, tableModel.getSolverAt(modelRow), tableModel.getComboBoxAt(modelRow));
        dialog.setName("ImportSolverDetail");
        EDACCApp.getApplication().show(dialog);
    }

    private void btnSolverDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolverDetailsActionPerformed
        int index;
        if ((index = tblSolvers.getSelectedRow()) != -1) {
            showSolverDetails(index);
        }
    }//GEN-LAST:event_btnSolverDetailsActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSolverDetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblExperiments;
    private javax.swing.JLabel lblInstances;
    private javax.swing.JLabel lblSolvers;
    private javax.swing.JLabel lblVerifiers;
    private javax.swing.JTable tblSolvers;
    private javax.swing.JTable tblVerifiers;
    // End of variables declaration//GEN-END:variables

    public void setExperimentCount(int count) {
        lblExperiments.setText("" + count);
    }

    public void setSolvers(List<Solver> solvers) {
        lblSolvers.setText("" + solvers.size());
        try {
            HashMap<Integer, List<Solver>> commonBinaries = controller.mapFileSolversToExistingSolversWithSolverBinariesInCommon(solvers);
            List<Solver> tmpSolvers = new ArrayList<Solver>();
            for (Solver s : solvers) {
                if (!commonBinaries.containsKey(s.getId())) {
                    tmpSolvers.add(s);
                }
            }
            HashMap<Integer, List<Solver>> commonParameters = controller.mapFileSolversToExistingSolversWithSameParameters(tmpSolvers);

            Solver[] solverArray = new Solver[solvers.size()];
            combos = new JComboBox[solvers.size()];
            for (int i = 0; i < solvers.size(); i++) {
                solverArray[i] = solvers.get(i);
                List<Solver> cbList = commonBinaries.get(solvers.get(i).getId());
                List<Solver> cpList = commonParameters.get(solvers.get(i).getId());
                combos[i] = new JComboBox();
                combos[i].setRenderer(new ComboBoxCellRenderer(cbList));
                combos[i].addItem("Create new solver");
                for (Solver s : cbList) {
                    combos[i].addItem(s);
                }
                if (cpList != null) {
                    for (Solver s : cpList) {
                        combos[i].addItem(s);
                    }
                }
                if (!cbList.isEmpty()) {
                    combos[i].setSelectedIndex(1);
                } else if (cpList != null && !cpList.isEmpty()) {
                    combos[i].setSelectedIndex(1 + cbList.size());
                }
                final int rowIndex = i;
                combos[i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tableModel.setValueAt(null, rowIndex, 2);
                    }
                });
            }
            HashSet<String> str = new HashSet<String>();

            for (Solver dbSolver : controller.getDatabaseSolvers()) {
                str.add(dbSolver.getName());
            }
            tableModel = new ImportSolverTableModel(str);
            tblSolvers.setModel(tableModel);
            tblSolvers.getColumnModel().getColumn(3).setCellRenderer(new NameCellRenderer());
            tblSolvers.getColumnModel().getColumn(2).setCellRenderer(new ComboBoxRenderer(combos));
            tblSolvers.getColumnModel().getColumn(2).setCellEditor(new ComboBoxEditor(combos));
            tableModel.setData(solverArray, combos);

        } catch (SQLException ex) {
            // TODO: error
            Logger.getLogger(ImportSummaryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setInstanceCount(int count) {
        lblInstances.setText("" + count);
    }

    public void setVerifiers(List<Verifier> verifiers) {
        lblVerifiers.setText("" + verifiers.size());
        try {
            HashMap<Integer, List<Verifier>> common = controller.mapFileVerifiersToExistingVerifiers(verifiers);
            Verifier[] verifierArray = new Verifier[verifiers.size()];
            verifierCombos = new JComboBox[verifiers.size()];
            for (int i = 0; i < verifierCombos.length; i++) {
                verifierArray[i] = verifiers.get(i);
                List<Verifier> commonVerifiers = common.get(verifiers.get(i).getId());
                verifierCombos[i] = new JComboBox();
                verifierCombos[i].setRenderer(new VerifierComboBoxCellRenderer());
                verifierCombos[i].addItem("Create new verifier");
                if (commonVerifiers != null) {
                    for (Verifier c : commonVerifiers) {
                        verifierCombos[i].addItem(c);
                    }
                }
                if (verifierCombos[i].getItemCount() > 1) {
                    verifierCombos[i].setSelectedIndex(1);
                } else {
                    verifierCombos[i].setSelectedIndex(0);
                }
                final int rowIndex = i;
                verifierCombos[i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        verifierTableModel.setValueAt(null, rowIndex, 2);
                    }
                });
            }
            verifierTableModel = new ImportVerifierTableModel();
            tblVerifiers.setModel(verifierTableModel);
            tblVerifiers.getColumnModel().getColumn(2).setCellRenderer(new ComboBoxRenderer(verifierCombos));
            tblVerifiers.getColumnModel().getColumn(2).setCellEditor(new ComboBoxEditor(verifierCombos));
            verifierTableModel.setData(verifierArray, verifierCombos);
        } catch (SQLException ex) {
            // TODO: error
            Logger.getLogger(ImportSummaryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validateInput() {
        for (Integer c : tableModel.getNameMap().values()) {
            if (c >= 2) {
                return false;
            }
        }
        return true;
    }

    public HashMap<Integer, Solver> getSolverMap() {
        HashMap<Integer, Solver> solverMap = new HashMap<Integer, Solver>();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getComboBoxAt(row).getSelectedItem() instanceof Solver) {
                solverMap.put(tableModel.getSolverAt(row).getId(), (Solver) tableModel.getComboBoxAt(row).getSelectedItem());
            }
        }
        return solverMap;
    }

    public HashMap<Integer, String> getNameMap() {
        HashMap<Integer, String> nameMap = new HashMap<Integer, String>();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getComboBoxAt(row).getSelectedItem() instanceof String) {
                nameMap.put(tableModel.getSolverAt(row).getId(), tableModel.getSolverNameAt(row));
            }
        }
        return nameMap;
    }

    public HashMap<Integer, Verifier> getVerifierMap() {
        HashMap<Integer, Verifier> verifierMap = new HashMap<Integer, Verifier>();
        for (int row = 0; row < verifierTableModel.getRowCount(); row++) {
            if (verifierTableModel.getComboBoxAt(row).getSelectedItem() instanceof Verifier) {
                verifierMap.put(verifierTableModel.getVerifierAt(row).getId(), (Verifier) verifierTableModel.getComboBoxAt(row).getSelectedItem());
            }
        }
        return verifierMap;
    }

    public HashMap<Integer, String> getVerifierNameMap() {
        HashMap<Integer, String> nameMap = new HashMap<Integer, String>();
        for (int row = 0; row < verifierTableModel.getRowCount(); row++) {
            if (verifierTableModel.getComboBoxAt(row).getSelectedItem() instanceof String) {
                nameMap.put(verifierTableModel.getVerifierAt(row).getId(), verifierTableModel.getVerifierNameAt(row));
            }
        }
        return nameMap;
    }

    private class ComboBoxRenderer implements TableCellRenderer {

        private JComboBox[] combos;

        public ComboBoxRenderer(JComboBox[] combos) {
            this.combos = combos;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComboBox comboBox = combos[row];
            /*if (isSelected) {
            comboBox.setForeground(table.getSelectionForeground());
            comboBox.setBackground(table.getSelectionBackground());
            } else {
            comboBox.setForeground(table.getForeground());
            comboBox.setBackground(table.getBackground());
            }*/

            comboBox.setSelectedItem(value);
            return comboBox;
        }
    }

    private class NameCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == 3) {
                HashMap<String, Integer> nameMap = tableModel.getNameMap();
                Integer count = nameMap.get((String) value);
                if (count != null && count >= 2) {
                    c.setBackground(edacc.experiment.Util.COLOR_ERROR);
                } else {
                    c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                }
            } else {
                c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            }
            return c;
        }
    }

    private class ComboBoxCellRenderer extends DefaultListCellRenderer {

        private HashSet<Integer> markIds;

        public ComboBoxCellRenderer(List<Solver> markSolvers) {
            markIds = new HashSet<Integer>();
            for (Solver s : markSolvers) {
                markIds.add(s.getId());
            }
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            JLabel label = new JLabel();
            // label.setOpaque(true);

            // label.setForeground(comp.getForeground());
            //  label.setBackground(comp.getBackground());
            if (value instanceof Solver) {
                String solverName = ((Solver) value).getName();

                if (markIds.contains(((Solver) value).getId())) {
                    label.setText("Import to " + solverName + " (has solver binaries in common)");
                } else {
                    label.setText("Import to " + solverName + " (has parameters in common)");
                }

            } else if (value instanceof String) {
                label.setText((String) value);
            }
            return label;
        }
    }

    private class VerifierComboBoxCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = new JLabel();
            if (value instanceof Verifier) {
                String verifierName = ((Verifier) value).getName();
                label.setText("Don't import (verifier " + verifierName + " is equal)");
            } else if (value instanceof String) {
                label.setText((String) value);
            }
            return label;
        }
    }

    private class ComboBoxEditor implements TableCellEditor {

        private JComboBox[] combos;

        public ComboBoxEditor(JComboBox[] combos) {
            this.combos = combos;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return combos[row];
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return true;
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

        @Override
        public boolean stopCellEditing() {
            return true;
        }

        @Override
        public void cancelCellEditing() {
        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {
        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {
        }
    }
}
