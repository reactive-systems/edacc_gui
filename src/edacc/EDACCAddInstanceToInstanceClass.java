/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCAddInstanceToInstanceClass.java
 *
 * Created on 22.03.2010, 17:22:57
 */

package edacc;

import edacc.manageDB.AddInstanceInstanceClassTabelModel;
import edacc.model.InstanceClass;
import edacc.model.InstanceClassDAO;
import edacc.model.NoConnectionToDBException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rretz
 */
public class EDACCAddInstanceToInstanceClass extends javax.swing.JDialog {
    InstanceClass input;
    /** Creates new form EDACCAddInstanceToInstanceClass */
    public EDACCAddInstanceToInstanceClass(java.awt.Frame parent, boolean modal) throws NoConnectionToDBException, SQLException {
        super(parent, modal);
        initComponents();
        //Initiate the instance source class table
        AddInstanceInstanceClassTabelModel sourceTableModel = new AddInstanceInstanceClassTabelModel();
        jTableSourceClass.setModel(sourceTableModel);
        sourceTableModel.addClasses(new Vector<InstanceClass>(InstanceClassDAO.getAllSourceClass()));

        //Initiate the instance source class table
        AddInstanceInstanceClassTabelModel userTableModel = new AddInstanceInstanceClassTabelModel();
        jTableUserClass.setModel(userTableModel);
        userTableModel.addClasses(new Vector<InstanceClass>(InstanceClassDAO.getAllUserClass()));
    }

    public InstanceClass getInput(){
        return input;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSourceOrUser = new javax.swing.ButtonGroup();
        jRadioButtonSourceClass = new javax.swing.JRadioButton();
        jScrollPaneSourceClass = new javax.swing.JScrollPane();
        jTableSourceClass = new javax.swing.JTable();
        jRadioButtonUserClass = new javax.swing.JRadioButton();
        jScrollPaneUserClass = new javax.swing.JScrollPane();
        jTableUserClass = new javax.swing.JTable();
        jPanelButton = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(350, 500));
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        buttonGroupSourceOrUser.add(jRadioButtonSourceClass);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCAddInstanceToInstanceClass.class);
        jRadioButtonSourceClass.setText(resourceMap.getString("jRadioButtonSourceClass.text")); // NOI18N
        jRadioButtonSourceClass.setName("jRadioButtonSourceClass"); // NOI18N

        jScrollPaneSourceClass.setName("jScrollPaneSourceClass"); // NOI18N
        jScrollPaneSourceClass.setPreferredSize(new java.awt.Dimension(375, 125));

        jTableSourceClass.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableSourceClass.setName("jTableSourceClass"); // NOI18N
        jScrollPaneSourceClass.setViewportView(jTableSourceClass);

        buttonGroupSourceOrUser.add(jRadioButtonUserClass);
        jRadioButtonUserClass.setText(resourceMap.getString("jRadioButtonUserClass.text")); // NOI18N
        jRadioButtonUserClass.setName("jRadioButtonUserClass"); // NOI18N

        jScrollPaneUserClass.setName("jScrollPaneUserClass"); // NOI18N
        jScrollPaneUserClass.setPreferredSize(new java.awt.Dimension(200, 200));

        jTableUserClass.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableUserClass.setName("jTableUserClass"); // NOI18N
        jScrollPaneUserClass.setViewportView(jTableUserClass);

        jPanelButton.setName("jPanelButton"); // NOI18N

        jButtonOk.setText(resourceMap.getString("jButtonOk.text")); // NOI18N
        jButtonOk.setName("jButtonOk"); // NOI18N
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonCancel.setText(resourceMap.getString("jButtonCancel.text")); // NOI18N
        jButtonCancel.setName("jButtonCancel"); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonLayout = new javax.swing.GroupLayout(jPanelButton);
        jPanelButton.setLayout(jPanelButtonLayout);
        jPanelButtonLayout.setHorizontalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(jButtonCancel)
                .addContainerGap())
        );

        jPanelButtonLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancel, jButtonOk});

        jPanelButtonLayout.setVerticalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOk)
                    .addComponent(jButtonCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneUserClass, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jRadioButtonSourceClass)
                    .addComponent(jRadioButtonUserClass)
                    .addComponent(jScrollPaneSourceClass, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jPanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonSourceClass)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneSourceClass, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonUserClass)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneUserClass, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        input = new InstanceClass();
        if(buttonGroupSourceOrUser.getSelection() == null){
            JOptionPane.showMessageDialog(this,
                    "Please choose if the instance source class has to be changed or a new " +
                    "instance user class has to be added." ,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            if(jRadioButtonSourceClass.isSelected()){
                if(jTableSourceClass.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this,
                    "Please select one of the instance source classes from the table",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }else{
                    input = (InstanceClass) jTableSourceClass.getModel().getValueAt(jTableSourceClass.getSelectedRow(), 2);
                    this.setVisible(false);
                }
            }else{
                if(jTableUserClass.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this,
                    "Please select one of the instance user classes from the table",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }else{
                    input = (InstanceClass) jTableUserClass.getModel().getValueAt(jTableUserClass.getSelectedRow(), 2);
                    this.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        input = null;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            ((AddInstanceInstanceClassTabelModel)jTableSourceClass.getModel()).setClasses(new Vector<InstanceClass>(InstanceClassDAO.getAllSourceClass()));
            ((AddInstanceInstanceClassTabelModel)jTableUserClass.getModel()).setClasses(new Vector<InstanceClass>(InstanceClassDAO.getAllUserClass()));
         } catch (NoConnectionToDBException ex) {
            JOptionPane.showMessageDialog(this.rootPane,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(this.rootPane,
                    "There is a Problem with the database: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSourceOrUser;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JRadioButton jRadioButtonSourceClass;
    private javax.swing.JRadioButton jRadioButtonUserClass;
    private javax.swing.JScrollPane jScrollPaneSourceClass;
    private javax.swing.JScrollPane jScrollPaneUserClass;
    private javax.swing.JTable jTableSourceClass;
    private javax.swing.JTable jTableUserClass;
    // End of variables declaration//GEN-END:variables

}
