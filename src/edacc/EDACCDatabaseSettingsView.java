/*
 * EDACCGridSettingsView.java
 *
 * Created on Nov 26, 2009, 7:54:46 PM
 */

package edacc;

import edacc.model.DatabaseConnector;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel D.
 */
public class EDACCDatabaseSettingsView extends javax.swing.JDialog {

    /** Creates new form EDACCGridSettingsView */
    public EDACCDatabaseSettingsView(java.awt.Frame parent, boolean modal) {
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

        btnConnect = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblHostname = new javax.swing.JLabel();
        txtHostname = new javax.swing.JTextField();
        lblDatabase = new javax.swing.JLabel();
        txtDatabase = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        lblPort = new javax.swing.JLabel();
        chkSSL = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCDatabaseSettingsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setIconImage(null);
        setName("Form"); // NOI18N
        setResizable(false);

        btnConnect.setText(resourceMap.getString("btnConnect.text")); // NOI18N
        btnConnect.setName("btnConnect"); // NOI18N
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblHostname.setLabelFor(txtHostname);
        lblHostname.setText(resourceMap.getString("lblHostname.text")); // NOI18N
        lblHostname.setMaximumSize(new java.awt.Dimension(100, 17));
        lblHostname.setMinimumSize(new java.awt.Dimension(100, 17));
        lblHostname.setName("lblHostname"); // NOI18N
        lblHostname.setPreferredSize(new java.awt.Dimension(100, 17));

        txtHostname.setText(resourceMap.getString("txtHostname.text")); // NOI18N
        txtHostname.setName("txtHostname"); // NOI18N

        lblDatabase.setLabelFor(txtDatabase);
        lblDatabase.setText(resourceMap.getString("lblDatabase.text")); // NOI18N
        lblDatabase.setName("lblDatabase"); // NOI18N
        lblDatabase.setPreferredSize(new java.awt.Dimension(100, 17));

        txtDatabase.setText(resourceMap.getString("txtDatabase.text")); // NOI18N
        txtDatabase.setName("txtDatabase"); // NOI18N

        lblUsername.setLabelFor(txtUsername);
        lblUsername.setText(resourceMap.getString("lblUsername.text")); // NOI18N
        lblUsername.setName("lblUsername"); // NOI18N
        lblUsername.setPreferredSize(new java.awt.Dimension(100, 17));

        txtUsername.setText(resourceMap.getString("txtUsername.text")); // NOI18N
        txtUsername.setName("txtUsername"); // NOI18N

        lblPassword.setLabelFor(txtPassword);
        lblPassword.setText(resourceMap.getString("lblPassword.text")); // NOI18N
        lblPassword.setName("lblPassword"); // NOI18N
        lblPassword.setPreferredSize(new java.awt.Dimension(100, 17));

        txtPassword.setText(resourceMap.getString("txtPassword.text")); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N

        txtPort.setText(resourceMap.getString("txtPort.text")); // NOI18N
        txtPort.setName("txtPort"); // NOI18N

        lblPort.setLabelFor(txtPort);
        lblPort.setText(resourceMap.getString("lblPort.text")); // NOI18N
        lblPort.setMaximumSize(new java.awt.Dimension(100, 17));
        lblPort.setMinimumSize(new java.awt.Dimension(100, 17));
        lblPort.setName("lblPort"); // NOI18N
        lblPort.setPreferredSize(new java.awt.Dimension(100, 17));

        chkSSL.setText(resourceMap.getString("chkSSL.text")); // NOI18N
        chkSSL.setName("chkSSL"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsername))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDatabase))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkSSL, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSSL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConnect)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        try {
            DatabaseConnector.getInstance().connect(txtHostname.getText(), Integer.parseInt(txtPort.getText()), txtUsername.getText(), txtDatabase.getText(), txtPassword.getText());
            this.setVisible(false);
        }
        catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Couldn't find the MySQL jdbc driver Connector-J. Make sure it's in your Java class path", "Database driver not found", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the database: \n\n" + e.getMessage(), "Connection error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnConnectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConnect;
    private javax.swing.JCheckBox chkSSL;
    private javax.swing.JLabel lblDatabase;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtDatabase;
    private javax.swing.JTextField txtHostname;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
