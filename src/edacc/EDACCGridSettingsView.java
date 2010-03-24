/*
 * EDACCGridSettingsView.java
 *
 * Created on Nov 26, 2009, 9:34:21 PM
 */
package edacc;

import edacc.manageDB.ManageDBGridQueues;
import edacc.model.GridQueue;
import edacc.model.GridSettingsDAO;
import edacc.model.NoConnectionToDBException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author Daniel D.
 */
public class EDACCGridSettingsView extends javax.swing.JDialog {

    /** Creates new form EDACCGridSettingsView */
    public EDACCGridSettingsView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNumNodes = new javax.swing.JLabel();
        lblWallTime = new javax.swing.JLabel();
        txtNumNodes = new javax.swing.JTextField();
        txtWalltime = new javax.swing.JTextField();
        lblMaxJobsInQueue = new javax.swing.JLabel();
        txtMaxJobsInQueue = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblPBSScript = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        lblNumCPUs = new javax.swing.JLabel();
        txtNumCPUs = new javax.swing.JTextField();
        lblAvailNodes = new javax.swing.JLabel();
        txtAvailNodes = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCGridSettingsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        lblNumNodes.setLabelFor(txtNumNodes);
        lblNumNodes.setText(resourceMap.getString("lblNumNodes.text")); // NOI18N
        lblNumNodes.setName("lblNumNodes"); // NOI18N

        lblWallTime.setLabelFor(txtWalltime);
        lblWallTime.setText(resourceMap.getString("lblWallTime.text")); // NOI18N
        lblWallTime.setName("lblWallTime"); // NOI18N

        txtNumNodes.setText(resourceMap.getString("txtNumNodes.text")); // NOI18N
        txtNumNodes.setName("txtNumNodes"); // NOI18N

        txtWalltime.setText(resourceMap.getString("txtWalltime.text")); // NOI18N
        txtWalltime.setName("txtWalltime"); // NOI18N

        lblMaxJobsInQueue.setLabelFor(txtMaxJobsInQueue);
        lblMaxJobsInQueue.setText(resourceMap.getString("lblMaxJobsInQueue.text")); // NOI18N
        lblMaxJobsInQueue.setName("lblMaxJobsInQueue"); // NOI18N

        txtMaxJobsInQueue.setText(resourceMap.getString("txtMaxJobsInQueue.text")); // NOI18N
        txtMaxJobsInQueue.setName("txtMaxJobsInQueue"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCGridSettingsView.class, this);
        btnOk.setAction(actionMap.get("btnOk")); // NOI18N
        btnOk.setText(resourceMap.getString("btnOk.text")); // NOI18N
        btnOk.setName("btnOk"); // NOI18N

        btnCancel.setAction(actionMap.get("btnCancel")); // NOI18N
        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N

        lblPBSScript.setText(resourceMap.getString("lblPBSScript.text")); // NOI18N
        lblPBSScript.setName("lblPBSScript"); // NOI18N

        btnAdd.setText(resourceMap.getString("btnPBSScript.text")); // NOI18N
        btnAdd.setName("btnPBSScript"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd(evt);
            }
        });

        lblName.setLabelFor(txtName);
        lblName.setText(resourceMap.getString("lblName.text")); // NOI18N
        lblName.setName("lblName"); // NOI18N

        lblLocation.setLabelFor(txtLocation);
        lblLocation.setText(resourceMap.getString("lblLocation.text")); // NOI18N
        lblLocation.setName("lblLocation"); // NOI18N

        txtName.setText(resourceMap.getString("txtName.text")); // NOI18N
        txtName.setName("txtName"); // NOI18N

        txtLocation.setText(resourceMap.getString("txtLocation.text")); // NOI18N
        txtLocation.setName("txtLocation"); // NOI18N

        lblNumCPUs.setText(resourceMap.getString("lblNumCPUs.text")); // NOI18N
        lblNumCPUs.setName("lblNumCPUs"); // NOI18N

        txtNumCPUs.setText(resourceMap.getString("txtNumCPUs.text")); // NOI18N
        txtNumCPUs.setName("txtNumCPUs"); // NOI18N

        lblAvailNodes.setLabelFor(txtAvailNodes);
        lblAvailNodes.setText(resourceMap.getString("lblAvailNodes.text")); // NOI18N
        lblAvailNodes.setName("lblAvailNodes"); // NOI18N

        txtAvailNodes.setText(resourceMap.getString("txtAvailNodes.text")); // NOI18N
        txtAvailNodes.setName("txtAvailNodes"); // NOI18N

        lblDescription.setLabelFor(taDescription);
        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        taDescription.setColumns(20);
        taDescription.setRows(5);
        taDescription.setName("taDescription"); // NOI18N
        jScrollPane1.setViewportView(taDescription);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPBSScript)
                                        .addGap(124, 124, 124))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblNumCPUs)
                                                .addComponent(lblNumNodes, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblMaxJobsInQueue, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblWallTime, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblAvailNodes)
                                            .addGap(193, 193, 193))))
                                .addComponent(lblLocation)
                                .addComponent(lblName))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDescription)
                                .addGap(251, 251, 251)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLocation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtAvailNodes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtWalltime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtNumCPUs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtMaxJobsInQueue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtNumNodes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLocation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumNodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumNodes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumCPUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumCPUs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWalltime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWallTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvailNodes)
                    .addComponent(txtAvailNodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxJobsInQueue)
                    .addComponent(txtMaxJobsInQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPBSScript)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private JFileChooser pbsFileChooser;
    private void btnAdd(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd
        if (pbsFileChooser == null) {
            pbsFileChooser = new JFileChooser();
            pbsFileChooser.setMultiSelectionEnabled(false);
        }
        if (pbsFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                ManageDBGridQueues.getInstance().addPBSScript(pbsFileChooser.getSelectedFile());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error while saving settings: \n" + ex.getMessage(), "Error saving settings", JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_btnAdd

    public void loadSettings() throws SQLException {
        GridQueue q = ManageDBGridQueues.getInstance().getDefaultQueue();
        if (q != null) {
            txtName.setText(q.getName());
            txtLocation.setText(q.getLocation());
            txtNumNodes.setText(String.valueOf(q.getNumNodes()));
            txtNumCPUs.setText(String.valueOf(q.getNumCPUs()));
            txtWalltime.setText(String.valueOf(q.getWalltime()));
            txtAvailNodes.setText(String.valueOf(q.getAvailNodes()));
            txtMaxJobsInQueue.setText(String.valueOf(q.getMaxJobsQueue()));
            taDescription.setText(q.getDescription());
        }
    }

    @Action
    public void btnOk() {
        try {
            String name = txtName.getText();
            String location = txtLocation.getText();
            int numNodes = Integer.parseInt(txtNumNodes.getText());
            int numCPUs = Integer.parseInt(txtNumCPUs.getText());
            int walltime = Integer.parseInt(txtWalltime.getText());
            int availNodes = Integer.parseInt(txtAvailNodes.getText());
            int maxJobsInQueue = Integer.parseInt(txtMaxJobsInQueue.getText());
            String description = taDescription.getText();
            ManageDBGridQueues.getInstance().saveGridSettings(name, location, numNodes, numCPUs, walltime, availNodes, maxJobsInQueue, description);
            this.setVisible(false);
        } catch (NoConnectionToDBException e) {
            JOptionPane.showMessageDialog(this, "Couldn't save settings. No connection to database", "No database connection", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error while saving settings: \n" + e.getMessage(), "Error saving settings", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error saving settings, integers expected", "Integers expected", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error saving settings: The specified PBS script couldn't be found!", "File not found", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Action
    public void btnCancel() {
        this.setVisible(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvailNodes;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblMaxJobsInQueue;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumCPUs;
    private javax.swing.JLabel lblNumNodes;
    private javax.swing.JLabel lblPBSScript;
    private javax.swing.JLabel lblWallTime;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField txtAvailNodes;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtMaxJobsInQueue;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumCPUs;
    private javax.swing.JTextField txtNumNodes;
    private javax.swing.JTextField txtWalltime;
    // End of variables declaration//GEN-END:variables
}
