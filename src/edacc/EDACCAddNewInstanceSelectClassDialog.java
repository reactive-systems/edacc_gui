/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCAddNewInstanceSelectClassDialog.java
 *
 * Created on 20.03.2010, 12:43:23
 */

package edacc;

import edacc.manageDB.AddInstanceTreeSelectionListener;
import edacc.model.InstanceClass;
import edacc.model.InstanceClassDAO;
import edacc.model.NoConnectionToDBException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author rretz
 */
public class EDACCAddNewInstanceSelectClassDialog extends javax.swing.JDialog {
    InstanceClass input;
    private int searchDepth = 0;
    private DefaultTreeModel treeModel;
    private String fileExtension;

    /** Creates new form EDACCAddNewInstanceSelectClassDialog */
    public EDACCAddNewInstanceSelectClassDialog(java.awt.Frame parent, boolean modal) throws NoConnectionToDBException, SQLException {
        super(parent, modal);
        initComponents();
        //Filling the tree with all instance source class
        treeModel = InstanceClassDAO.getSourceAsTree();
        jTreeSourceClass.setModel(treeModel);
        jTreeSourceClass.setRootVisible(false);
        jTreeSourceClass.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTreeSourceClass.addTreeSelectionListener(new AddInstanceTreeSelectionListener(this));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupAutomaticOrManuel = new javax.swing.ButtonGroup();
        jPanelButtons = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonChoose = new javax.swing.JRadioButton();
        jRadioButtonAutomatic = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeSourceClass = new javax.swing.JTree();
        jLabelExtension = new javax.swing.JLabel();
        jTextFieldExtension = new javax.swing.JTextField();
        chkCompress = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCAddNewInstanceSelectClassDialog.class);
        setTitle(resourceMap.getString("AddNewInstanceSelectClass.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(400, 400));
        setName("AddNewInstanceSelectClass"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanelButtons.setName("jPanelButtons"); // NOI18N

        jButtonOk.setText(resourceMap.getString("jButtonOk.text")); // NOI18N
        jButtonOk.setToolTipText(resourceMap.getString("jButtonOk.toolTipText")); // NOI18N
        jButtonOk.setName("jButtonOk"); // NOI18N
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonCancel.setText(resourceMap.getString("jButtonCancel.text")); // NOI18N
        jButtonCancel.setToolTipText(resourceMap.getString("jButtonCancel.toolTipText")); // NOI18N
        jButtonCancel.setName("jButtonCancel"); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addComponent(jButtonCancel)
                .addContainerGap())
        );

        jPanelButtonsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancel, jButtonOk});

        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonOk)
                .addComponent(jButtonCancel))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        buttonGroupAutomaticOrManuel.add(jRadioButtonChoose);
        jRadioButtonChoose.setText(resourceMap.getString("jRadioButtonChoose.text")); // NOI18N
        jRadioButtonChoose.setToolTipText(resourceMap.getString("jRadioButtonChoose.toolTipText")); // NOI18N
        jRadioButtonChoose.setName("jRadioButtonChoose"); // NOI18N

        buttonGroupAutomaticOrManuel.add(jRadioButtonAutomatic);
        jRadioButtonAutomatic.setText(resourceMap.getString("jRadioButtonAutomatic.text")); // NOI18N
        jRadioButtonAutomatic.setToolTipText(resourceMap.getString("jRadioButtonAutomatic.toolTipText")); // NOI18N
        jRadioButtonAutomatic.setName("jRadioButtonAutomatic"); // NOI18N
        jRadioButtonAutomatic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAutomaticActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTreeSourceClass.setName("jTreeSourceClass"); // NOI18N
        jScrollPane1.setViewportView(jTreeSourceClass);

        jLabelExtension.setText(resourceMap.getString("jLabelExtension.text")); // NOI18N
        jLabelExtension.setName("jLabelExtension"); // NOI18N

        jTextFieldExtension.setText(resourceMap.getString("jTextFieldExtension.text")); // NOI18N
        jTextFieldExtension.setName("jTextFieldExtension"); // NOI18N
        jTextFieldExtension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExtensionActionPerformed(evt);
            }
        });

        chkCompress.setText(resourceMap.getString("chkCompress.text")); // NOI18N
        chkCompress.setName("chkCompress"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonAutomatic, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                        .addGap(80, 80, 80))
                    .addComponent(jRadioButtonChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExtension)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCompress)
                            .addComponent(jTextFieldExtension, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonAutomatic)
                .addGap(10, 10, 10)
                .addComponent(jRadioButtonChoose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkCompress)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelExtension)
                    .addComponent(jTextFieldExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Modifies the input object according to the user inputs, checks them and close the jDialog.
 * @param evt jButtonOk clicked
 */
    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        this.input = new InstanceClass();
        this.fileExtension = jTextFieldExtension.getText();
        if(buttonGroupAutomaticOrManuel.getSelection() == null){
            JOptionPane.showMessageDialog(this,
                    "Please choose if the new instance class is a source class or a user class." ,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
       }else {
          if(jRadioButtonAutomatic.isSelected()){
            this.input.setName("");
            this.setVisible(false);
          }else{
            if(jTreeSourceClass.getSelectionPath() == null){
                JOptionPane.showMessageDialog(this,
                    "Please select one of the instance source classes from the tree or select \"Automatic instance class generation\"." ,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }else{
                this.input = (InstanceClass) ((DefaultMutableTreeNode)jTreeSourceClass.getSelectionPath().getLastPathComponent()).getUserObject();
                this.setVisible(false);
            }

          }
       }
        
    }//GEN-LAST:event_jButtonOkActionPerformed
    /**
     * Modifies the input object to null and close the jDialog.
     * @param evt jButtonCancel clicked
     */
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        input = null;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            treeModel = InstanceClassDAO.getSourceAsTree();
            jTreeSourceClass.setModel(treeModel);
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
    }//GEN-LAST:event_formComponentShown

    private void jTextFieldExtensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExtensionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExtensionActionPerformed

    private void jRadioButtonAutomaticActionPerformed(java.awt.event.ActionEvent evt) {                                                      
       jTreeSourceClass.setSelectionPath(null);
    }                                                     

    public InstanceClass getInput(){
        return input;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupAutomaticOrManuel;
    private javax.swing.JCheckBox chkCompress;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelExtension;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JRadioButton jRadioButtonAutomatic;
    private javax.swing.JRadioButton jRadioButtonChoose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldExtension;
    private javax.swing.JTree jTreeSourceClass;
    // End of variables declaration//GEN-END:variables

    public void refresh() {
         try {
            treeModel = InstanceClassDAO.getSourceAsTree();
            jTreeSourceClass.setModel(treeModel);
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
    }

    public void setManualSelection() {
        jRadioButtonChoose.setSelected(true);
    }


    public String getFileExtension(){
        return fileExtension;
    }

    public Boolean isCompress() {
        return chkCompress.isSelected();
    }

}
