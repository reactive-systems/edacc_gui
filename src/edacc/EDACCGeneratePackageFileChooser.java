/*
 * EDACCGeneratePackageFileChooser.java
 *
 * Created on 01.10.2010, 17:35:32
 */
package edacc;

import edacc.events.TaskEvents;
import edacc.experiment.ExperimentController;
import edacc.model.Tasks;
import java.io.File;
import java.util.Calendar;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author simon
 */
public class EDACCGeneratePackageFileChooser extends javax.swing.JDialog implements TaskEvents {

    private ExperimentController expController;

    /** Creates new form EDACCGeneratePackageFileChooser */
    public EDACCGeneratePackageFileChooser(java.awt.Frame parent, boolean modal, ExperimentController expController) {
        super(parent, modal);
        initComponents();
        this.expController = expController;
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
        chkInstances = new javax.swing.JCheckBox();
        chkSolvers = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        packageFileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCGeneratePackageFileChooser.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        chkInstances.setSelected(true);
        chkInstances.setText(resourceMap.getString("chkInstances.text")); // NOI18N
        chkInstances.setName("chkInstances"); // NOI18N

        chkSolvers.setSelected(true);
        chkSolvers.setText(resourceMap.getString("chkSolvers.text")); // NOI18N
        chkSolvers.setName("chkSolvers"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkInstances)
                    .addComponent(chkSolvers))
                .addContainerGap(503, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(chkInstances)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSolvers))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        packageFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        packageFileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        packageFileChooser.setName("packageFileChooser"); // NOI18N
        packageFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packageFileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(packageFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(packageFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void packageFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packageFileChooserActionPerformed

        if (evt.getActionCommand().equals("ApproveSelection")) {
            File folder = new File(packageFileChooser.getSelectedFile().getAbsolutePath());
            if (!folder.exists()) {
                int userInput = javax.swing.JOptionPane.showConfirmDialog(Tasks.getTaskView(), "The directory " + folder.getAbsolutePath() + " doesn't exist. Should it be created?", "Generate cluster package", javax.swing.JOptionPane.YES_NO_OPTION);
                if (userInput == 1) {
                    return;
                } else {
                    folder.mkdirs();
                }
            }

            String location = packageFileChooser.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator");
            Tasks.startTask("generatePackage", new Class[]{String.class, boolean.class, boolean.class, edacc.model.Tasks.class}, new Object[]{location, chkInstances.isSelected(), chkSolvers.isSelected(), null}, expController, this);
            dispose();
        } else if (evt.getActionCommand().equals("CancelSelection")) {
            dispose();
        }
    }//GEN-LAST:event_packageFileChooserActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkInstances;
    private javax.swing.JCheckBox chkSolvers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFileChooser packageFileChooser;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onTaskSuccessful(String methodName, Object result) {
    }

    @Override
    public void onTaskStart(String methodName) {
    }

    @Override
    public void onTaskFailed(String methodName, Throwable e) {
        if (methodName.equals("generatePackage")) {
            javax.swing.JOptionPane.showMessageDialog(null, "Excpetion during package generation:\n\n" + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
