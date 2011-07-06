/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCComputeInstancePropertyDialog.java
 *
 * Created on 19.10.2010, 15:47:29
 */
package edacc;

import edacc.events.TaskEvents;
import edacc.manageDB.ManageDBInstances;
import edacc.manageDB.ProblemOccuredDuringPropertyComputation;
import edacc.model.Instance;
import edacc.model.Property;
import edacc.model.PropertyDAO;
import edacc.model.Tasks;
import java.lang.String;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dgall
 */
public class EDACCComputeInstancePropertyDialog extends javax.swing.JDialog implements TaskEvents {

    private Vector<Instance> instances;
    private ManageDBInstances manageDBInstances;

    /** Creates new form EDACCComputeInstancePropertyDialog */
    public EDACCComputeInstancePropertyDialog(java.awt.Frame parent, ManageDBInstances manageDBInstances, Vector<Instance> instances) {
        super(parent, "Compute property for the selected instances", true);
        initComponents();
        this.instances = instances;
        this.manageDBInstances = manageDBInstances;
        showProperties();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listProperties = new javax.swing.JList();
        bCompute = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        listProperties.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listProperties.setName("listProperties"); // NOI18N
        jScrollPane1.setViewportView(listProperties);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCComputeInstancePropertyDialog.class);
        bCompute.setText(resourceMap.getString("bCompute.text")); // NOI18N
        bCompute.setName("bCompute"); // NOI18N
        bCompute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bComputeActionPerformed(evt);
            }
        });

        bCancel.setText(resourceMap.getString("bCancel.text")); // NOI18N
        bCancel.setName("bCancel"); // NOI18N
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(bCompute))
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCompute)
                    .addComponent(bCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComputeActionPerformed
        //Vector<Property> properties = ((PropertyListModel) listProperties.getModel()).getPropertyList();
        Vector<Property> properties = new Vector<Property>();
        // for (int i = 0; i < listProperties.getVisibleRowCount(); i++) {
        Object[] objs = listProperties.getSelectedValues();
        for (Object obj : objs) {
            if (obj instanceof Property) {
                properties.add((Property) obj);
            }
        }
        Tasks.startTask("computeProperties", new Class[]{Vector.class, Vector.class, edacc.model.Tasks.class}, new Object[]{instances, properties, null}, manageDBInstances, EDACCComputeInstancePropertyDialog.this);
    }//GEN-LAST:event_bComputeActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bCompute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listProperties;
    // End of variables declaration//GEN-END:variables

    private void showProperties() {
        try {
            Vector<Property> props = PropertyDAO.getAllInstanceProperties();
            listProperties.setModel(new PropertyListModel(props));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occured while trying to load the properties from the DB: \n" + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void onTaskSuccessful(String methodName, Object result) {
    }

    @Override
    public void onTaskStart(String methodName) {
    }

    @Override
    public void onTaskFailed(String methodName, Throwable e) {
        if (methodName.equals("computeProperties")) {
            if (e instanceof ProblemOccuredDuringPropertyComputation) {
                Vector<Exception> data = ((ProblemOccuredDuringPropertyComputation)e).getExceptionsCollector();
                String[][] tableData = new String[data.size()][1];
                for (int i = 0; i < data.size(); i++) {
                    tableData[i][0] = data.get(i).toString();
                }
                String[] columnName = {"Exception"};
                DefaultTableModel tableModel = new DefaultTableModel(tableData, columnName);
                EDACCExtendedWarning.showMessageDialog(EDACCExtendedWarning.OK_OPTIONS,
                        EDACCApp.getApplication().getMainFrame(),
                        "An error occured while computing the Properties. \n Please check the regular expression or computation methode of the Property",
                        new JTable(tableModel));
            }

        }
    }

    private class PropertyListModel extends AbstractListModel {

        private Vector<Property> properties;

        public PropertyListModel(List<Property> properties) {
            this.properties = new Vector<Property>(properties);
        }

        @Override
        public int getSize() {
            return properties.size();
        }

        @Override
        public Object getElementAt(int index) {
            return properties.get(index);
        }

        /**
         *
         * @return a new vector containing the properties in the JList.
         */
        public Vector<Property> getPropertyList() {
            return (Vector<Property>) properties.clone();
        }
    }
}
