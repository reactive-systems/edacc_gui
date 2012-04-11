/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCImportPropertyCSV.java
 *
 * Created on 06-Mar-2012, 17:33:17
 */
package edacc;

import edacc.events.TaskEvents;
import edacc.model.InstancesNotFoundException;
import edacc.properties.SystemPropertyTableRenderer;
import edacc.properties.CSVPropertyTableRenderer;
import edacc.properties.CSVPropertyTableModel;
import edacc.properties.ImportCSVPropSelectionListener;
import edacc.properties.ImportPropertyCSVController;
import edacc.properties.SystemPropertyTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rretz
 */
public class EDACCImportPropertyCSV extends javax.swing.JDialog implements TaskEvents {

    ImportPropertyCSVController controller;

    /** Creates new form EDACCImportPropertyCSV */
    public EDACCImportPropertyCSV(java.awt.Frame parent, boolean modal) {
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

        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanelSystemProprties = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSysProp = new javax.swing.JTable();
        jBtnManageProperties = new javax.swing.JButton();
        jPanelCSVProperties = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCSVProp = new javax.swing.JTable();
        jBtnDrop = new javax.swing.JButton();
        jRBtnOverwrite = new javax.swing.JRadioButton();
        jBtnDone = new javax.swing.JButton();
        jBtnImport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(539, 507));
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCImportPropertyCSV.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jPanelSystemProprties.setName("jPanelSystemProprties"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTableSysProp.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableSysProp.setName("jTableSysProp"); // NOI18N
        jScrollPane2.setViewportView(jTableSysProp);

        jBtnManageProperties.setText(resourceMap.getString("jBtnManageProperties.text")); // NOI18N
        jBtnManageProperties.setName("jBtnManageProperties"); // NOI18N
        jBtnManageProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnManagePropertiesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSystemProprtiesLayout = new javax.swing.GroupLayout(jPanelSystemProprties);
        jPanelSystemProprties.setLayout(jPanelSystemProprtiesLayout);
        jPanelSystemProprtiesLayout.setHorizontalGroup(
            jPanelSystemProprtiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSystemProprtiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSystemProprtiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jBtnManageProperties))
                .addContainerGap())
        );
        jPanelSystemProprtiesLayout.setVerticalGroup(
            jPanelSystemProprtiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSystemProprtiesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnManageProperties)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanelSystemProprties);

        jPanelCSVProperties.setName("jPanelCSVProperties"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTableCSVProp.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableCSVProp.setName("jTableCSVProp"); // NOI18N
        jTableCSVProp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTableCSVProp);

        jBtnDrop.setText(resourceMap.getString("jBtnDrop.text")); // NOI18N
        jBtnDrop.setName("jBtnDrop"); // NOI18N
        jBtnDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDropActionPerformed(evt);
            }
        });

        jRBtnOverwrite.setText(resourceMap.getString("jRBtnOverwrite.text")); // NOI18N
        jRBtnOverwrite.setName("jRBtnOverwrite"); // NOI18N

        javax.swing.GroupLayout jPanelCSVPropertiesLayout = new javax.swing.GroupLayout(jPanelCSVProperties);
        jPanelCSVProperties.setLayout(jPanelCSVPropertiesLayout);
        jPanelCSVPropertiesLayout.setHorizontalGroup(
            jPanelCSVPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCSVPropertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCSVPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCSVPropertiesLayout.createSequentialGroup()
                        .addComponent(jRBtnOverwrite)
                        .addGap(31, 31, 31)
                        .addComponent(jBtnDrop)))
                .addContainerGap())
        );
        jPanelCSVPropertiesLayout.setVerticalGroup(
            jPanelCSVPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCSVPropertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCSVPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnDrop)
                    .addComponent(jRBtnOverwrite))
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanelCSVProperties);

        jBtnDone.setText(resourceMap.getString("jBtnDone.text")); // NOI18N
        jBtnDone.setName("jBtnDone"); // NOI18N
        jBtnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDoneActionPerformed(evt);
            }
        });

        jBtnImport.setText(resourceMap.getString("jBtnImport.text")); // NOI18N
        jBtnImport.setName("jBtnImport"); // NOI18N
        jBtnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtnImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                        .addComponent(jBtnDone)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnImport)
                    .addComponent(jBtnDone))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDoneActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnDoneActionPerformed

    private void jBtnDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDropActionPerformed
        if (jTableCSVProp.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Nothing is selected. Select a property to drop.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            controller.dropCSVProp(jTableCSVProp.convertRowIndexToModel(jTableCSVProp.getSelectedRow()));
        }
    }//GEN-LAST:event_jBtnDropActionPerformed

    private void jBtnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnImportActionPerformed
        controller.importCSVData(jRBtnOverwrite.isSelected());
    }//GEN-LAST:event_jBtnImportActionPerformed

    private void jBtnManagePropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnManagePropertiesActionPerformed
        controller.ManageProperties();
    }//GEN-LAST:event_jBtnManagePropertiesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                EDACCImportPropertyCSV dialog = new EDACCImportPropertyCSV(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void initialize(File csvFile) {
        try {
            controller = new ImportPropertyCSVController(this, csvFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EDACCImportPropertyCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EDACCImportPropertyCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        // init jTableCSVProp
        jTableCSVProp.setModel(controller.getCsvPropTableModel());
        jTableCSVProp.setRowSorter(new TableRowSorter<CSVPropertyTableModel>(controller.getCsvPropTableModel()));
        jTableCSVProp.getSelectionModel().addListSelectionListener(new ImportCSVPropSelectionListener(controller));
        CSVPropertyTableRenderer csvPropRenderer = new CSVPropertyTableRenderer(controller);
        jTableCSVProp.setDefaultRenderer(String.class, csvPropRenderer);

        // init jTableSysProp
        jTableSysProp.setModel(controller.getSysPropTableModel());
        jTableSysProp.setRowSorter(new TableRowSorter<SystemPropertyTableModel>(controller.getSysPropTableModel()));
        SystemPropertyTableRenderer sysPropRenderer = new SystemPropertyTableRenderer(controller);
        jTableSysProp.setDefaultRenderer(String.class, sysPropRenderer);

        controller.refreshTables();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDone;
    private javax.swing.JButton jBtnDrop;
    private javax.swing.JButton jBtnImport;
    private javax.swing.JButton jBtnManageProperties;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCSVProperties;
    private javax.swing.JPanel jPanelSystemProprties;
    private javax.swing.JRadioButton jRBtnOverwrite;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTableCSVProp;
    private javax.swing.JTable jTableSysProp;
    // End of variables declaration//GEN-END:variables

    public int getSelectedCSVPropertyRow() {
        if (jTableCSVProp.getSelectedRow() == -1) {
            return -1;
        }
        return jTableCSVProp.convertRowIndexToModel(jTableCSVProp.getSelectedRow());
    }

    public void setSelectedCSVPropertyRow(int save) {
        jTableCSVProp.setRowSelectionInterval(save, save);
    }

    @Override
    public void onTaskSuccessful(String methodName, Object result) {
        this.dispose();
    }

    @Override
    public void onTaskStart(String methodName) {
    }

    @Override
    public void onTaskFailed(String methodName, Throwable e) {
        try {
            throw e;
        } catch (InstancesNotFoundException ex) {
            JOptionPane.showMessageDialog(jPanel1,
                    "Not all instances given in the csv File could be found in EDACC.\n"
                    + "All other data has been imported", "Error",
                    JOptionPane.WARNING_MESSAGE);
        } catch (Throwable ex) {
            Logger.getLogger(EDACCImportPropertyCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }
}
