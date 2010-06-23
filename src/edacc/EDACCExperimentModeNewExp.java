/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCExperimentModeNewSolver.java
 *
 * Created on 14.06.2010, 11:47:44
 */
package edacc;

import java.awt.event.KeyEvent;

/**
 *
 * @author balint
 */
public class EDACCExperimentModeNewExp extends javax.swing.JDialog {

    public String ExpName;
    public String ExpDesc;
    public boolean canceled;

    /** Creates new form EDACCExperimentModeNewSolver */
    public EDACCExperimentModeNewExp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.txtExperimentName.requestFocus();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblExperimentName = new javax.swing.JLabel();
        txtExperimentName = new javax.swing.JTextField();
        lblExperimentDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExperimentDescription = new javax.swing.JTextArea();
        btnCreateExperiment = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCExperimentModeNewExp.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        lblExperimentName.setText(resourceMap.getString("lblExperimentName.text")); // NOI18N
        lblExperimentName.setName("lblExperimentName"); // NOI18N

        txtExperimentName.setToolTipText(resourceMap.getString("txtExperimentName.toolTipText")); // NOI18N
        txtExperimentName.setName("txtExperimentName"); // NOI18N
        txtExperimentName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExperimentNameKeyPressed(evt);
            }
        });

        lblExperimentDescription.setText(resourceMap.getString("lblExperimentDescription.text")); // NOI18N
        lblExperimentDescription.setName("lblExperimentDescription"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtExperimentDescription.setColumns(20);
        txtExperimentDescription.setRows(5);
        txtExperimentDescription.setName("txtExperimentDescription"); // NOI18N
        txtExperimentDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExperimentDescriptionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtExperimentDescription);

        btnCreateExperiment.setText(resourceMap.getString("btnCreateExperiment.text")); // NOI18N
        btnCreateExperiment.setToolTipText(resourceMap.getString("btnCreateExperiment.toolTipText")); // NOI18N
        btnCreateExperiment.setName("btnCreateExperiment"); // NOI18N
        btnCreateExperiment.setPreferredSize(new java.awt.Dimension(80, 25));
        btnCreateExperiment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateExperimentActionPerformed(evt);
            }
        });
        btnCreateExperiment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCreateExperimentKeyPressed(evt);
            }
        });

        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.setPreferredSize(new java.awt.Dimension(80, 25));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblExperimentDescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblExperimentName))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                            .addComponent(txtExperimentName, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCreateExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExperimentName)
                    .addComponent(txtExperimentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExperimentDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateExperimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateExperimentActionPerformed
        if (txtExperimentName.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "The experiment must have a name.", "Create experiment", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            this.ExpName = this.txtExperimentName.getText();
            this.ExpDesc = this.txtExperimentDescription.getText();
            this.canceled = false;
            this.txtExperimentDescription.setText("");
            this.txtExperimentName.setText("");
            this.setVisible(false);
        }


    }//GEN-LAST:event_btnCreateExperimentActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.canceled = true;
        this.txtExperimentDescription.setText("");
        this.txtExperimentName.setText("");
        this.setVisible(false);
}//GEN-LAST:event_btnCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.ExpName="";
        this.ExpDesc="";
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.canceled=true;        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.canceled=true;        
    }//GEN-LAST:event_formWindowClosing

    private void btnCreateExperimentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCreateExperimentKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) //create Experiment
        {
            this.btnCreateExperimentActionPerformed(null);
        }
    }//GEN-LAST:event_btnCreateExperimentKeyPressed

    private void btnCancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) //create Experiment
        {
            this.btnCancelActionPerformed(null);
        }
    }//GEN-LAST:event_btnCancelKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) //create Experiment
        {
            this.btnCancelActionPerformed(null);
        }
    }//GEN-LAST:event_formKeyPressed

    private void txtExperimentDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExperimentDescriptionKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) //create Experiment
        {
            this.btnCreateExperimentActionPerformed(null);
        }else  if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) //create Experiment
        {
            this.btnCancelActionPerformed(null);
        }

    }//GEN-LAST:event_txtExperimentDescriptionKeyPressed

    private void txtExperimentNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExperimentNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) //create Experiment
        {
            this.btnCreateExperimentActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) //create Experiment
        {
            this.btnCancelActionPerformed(null);
        }
    }//GEN-LAST:event_txtExperimentNameKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateExperiment;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExperimentDescription;
    private javax.swing.JLabel lblExperimentName;
    private javax.swing.JTextArea txtExperimentDescription;
    private javax.swing.JTextField txtExperimentName;
    // End of variables declaration//GEN-END:variables
}
