/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCManageSolverPropertyDialog.java
 *
 * Created on 07.09.2010, 14:37:06
 */

package edacc;

import edacc.model.NoConnectionToDBException;
import edacc.model.SolverProperty;
import edacc.model.SolverPropertyIsUsedException;
import edacc.model.SolverPropertyNotInDBException;
import edacc.properties.SolverPropertiesController;
import edacc.properties.SolverPropertyTableModel;
import edacc.properties.SolverPropertyTableSelectionListener;
import edacc.properties.SolverPropertyType;
import edacc.properties.SolverPropertyTypeNotExistException;
import edacc.satinstances.PropertyValueType;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rretz
 */
public class EDACCManageSolverPropertyDialog extends javax.swing.JDialog {
    private SolverPropertiesController controller;
    private SolverPropertyTableModel solPropertyTableModel;
    private SolverPropertyType[] comboBoxSolPropType = {SolverPropertyType.ClientOutput, SolverPropertyType.ResultFile, SolverPropertyType.Parameter};
    private EDACCManagePropertyValueTypesDialog PropertyValueTypesDialog;

    /** Creates new form EDACCManageSolverPropertyDialog */
    public EDACCManageSolverPropertyDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
 
        // initialize tableSolverProperty
        solPropertyTableModel = new SolverPropertyTableModel();
        tableSolverProperty.setModel(solPropertyTableModel);
        tableSolverProperty.setRowSorter(new TableRowSorter<SolverPropertyTableModel>(solPropertyTableModel));
        tableSolverProperty.getSelectionModel().addListSelectionListener(new SolverPropertyTableSelectionListener(tableSolverProperty, controller));

        // Adding new ColumnModel for the suitable representation of boolen values in the table.
        tableSolverProperty.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                lbl.setHorizontalAlignment(JLabel.CENTER);
                return lbl;
            }
        });

        controller = new SolverPropertiesController(this, panelManageSolverProperty, tableSolverProperty);
        
        // initialize comboBoxSolverPropertyType
        for(int i = 0; i < comboBoxSolPropType.length; i++){
            comboBoxSolverPropertyType.addItem(comboBoxSolPropType[i]);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelManageSolverProperty = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelMangeSolverPropertyShow = new javax.swing.JPanel();
        panelManageSolverPropertyShowButtons = new javax.swing.JPanel();
        buttonNewSolverProperty = new javax.swing.JButton();
        buttonRemoveSolverProperty = new javax.swing.JButton();
        panelManageSolverPropertyTable = new javax.swing.JScrollPane();
        tableSolverProperty = new javax.swing.JTable();
        panelManageSolverPropertyEdit = new javax.swing.JPanel();
        panelManageSolverPropertyEditButtons = new javax.swing.JPanel();
        buttonSaveSolverProperty = new javax.swing.JButton();
        panelManageSolverPropertyEditInput = new javax.swing.JPanel();
        textSolverPropertyFieldName = new javax.swing.JTextField();
        labelSolverPropertyname = new javax.swing.JLabel();
        textSolvertPropertyFieldPrefix = new javax.swing.JTextField();
        labelSolverPropertyPrefix = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaResultPropertyDescription = new javax.swing.JTextArea();
        labelSolverPropertyDescription = new javax.swing.JLabel();
        comboBoxSolverPropertyValuetype = new javax.swing.JComboBox();
        labelSolverPropertyValueType = new javax.swing.JLabel();
        buttonSolverPropertyAddValueType = new javax.swing.JButton();
        labeSolverPropertyType = new javax.swing.JLabel();
        comboBoxSolverPropertyType = new javax.swing.JComboBox();
        labeMultipleOccurrences = new javax.swing.JLabel();
        checkBoxMultipleOccurrences = new javax.swing.JCheckBox();
        buttonDone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCManageSolverPropertyDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        panelManageSolverProperty.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("panelManageSolverProperty.border.title"))); // NOI18N
        panelManageSolverProperty.setName("panelManageSolverProperty"); // NOI18N

        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.8);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        panelMangeSolverPropertyShow.setName("panelMangeSolverPropertyShow"); // NOI18N

        panelManageSolverPropertyShowButtons.setName("panelManageSolverPropertyShowButtons"); // NOI18N

        buttonNewSolverProperty.setText(resourceMap.getString("buttonNewSolverProperty.text")); // NOI18N
        buttonNewSolverProperty.setName("buttonNewSolverProperty"); // NOI18N
        buttonNewSolverProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewSolverPropertyActionPerformed(evt);
            }
        });

        buttonRemoveSolverProperty.setText(resourceMap.getString("buttonRemoveSolverProperty.text")); // NOI18N
        buttonRemoveSolverProperty.setName("buttonRemoveSolverProperty"); // NOI18N
        buttonRemoveSolverProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveSolverPropertyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelManageSolverPropertyShowButtonsLayout = new javax.swing.GroupLayout(panelManageSolverPropertyShowButtons);
        panelManageSolverPropertyShowButtons.setLayout(panelManageSolverPropertyShowButtonsLayout);
        panelManageSolverPropertyShowButtonsLayout.setHorizontalGroup(
            panelManageSolverPropertyShowButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageSolverPropertyShowButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonNewSolverProperty, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemoveSolverProperty)
                .addContainerGap(641, Short.MAX_VALUE))
        );
        panelManageSolverPropertyShowButtonsLayout.setVerticalGroup(
            panelManageSolverPropertyShowButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageSolverPropertyShowButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageSolverPropertyShowButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNewSolverProperty)
                    .addComponent(buttonRemoveSolverProperty))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelManageSolverPropertyTable.setName("panelManageSolverPropertyTable"); // NOI18N

        tableSolverProperty.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSolverProperty.setName("tableSolverProperty"); // NOI18N
        tableSolverProperty.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        panelManageSolverPropertyTable.setViewportView(tableSolverProperty);

        javax.swing.GroupLayout panelMangeSolverPropertyShowLayout = new javax.swing.GroupLayout(panelMangeSolverPropertyShow);
        panelMangeSolverPropertyShow.setLayout(panelMangeSolverPropertyShowLayout);
        panelMangeSolverPropertyShowLayout.setHorizontalGroup(
            panelMangeSolverPropertyShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMangeSolverPropertyShowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMangeSolverPropertyShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelManageSolverPropertyTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                    .addComponent(panelManageSolverPropertyShowButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMangeSolverPropertyShowLayout.setVerticalGroup(
            panelMangeSolverPropertyShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMangeSolverPropertyShowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelManageSolverPropertyTable, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelManageSolverPropertyShowButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panelMangeSolverPropertyShow);

        panelManageSolverPropertyEdit.setName("panelManageSolverPropertyEdit"); // NOI18N

        panelManageSolverPropertyEditButtons.setName("panelManageSolverPropertyEditButtons"); // NOI18N

        javax.swing.GroupLayout panelManageSolverPropertyEditButtonsLayout = new javax.swing.GroupLayout(panelManageSolverPropertyEditButtons);
        panelManageSolverPropertyEditButtons.setLayout(panelManageSolverPropertyEditButtonsLayout);
        panelManageSolverPropertyEditButtonsLayout.setHorizontalGroup(
            panelManageSolverPropertyEditButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelManageSolverPropertyEditButtonsLayout.setVerticalGroup(
            panelManageSolverPropertyEditButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        buttonSaveSolverProperty.setText(resourceMap.getString("buttonSaveSolverProperty.text")); // NOI18N
        buttonSaveSolverProperty.setEnabled(false);
        buttonSaveSolverProperty.setName("buttonSaveSolverProperty"); // NOI18N

        panelManageSolverPropertyEditInput.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelManageSolverPropertyEditInput.setName("panelManageSolverPropertyEditInput"); // NOI18N

        textSolverPropertyFieldName.setEnabled(false);
        textSolverPropertyFieldName.setName("textSolverPropertyFieldName"); // NOI18N

        labelSolverPropertyname.setText(resourceMap.getString("labelSolverPropertyname.text")); // NOI18N
        labelSolverPropertyname.setName("labelSolverPropertyname"); // NOI18N

        textSolvertPropertyFieldPrefix.setEnabled(false);
        textSolvertPropertyFieldPrefix.setName("textSolvertPropertyFieldPrefix"); // NOI18N

        labelSolverPropertyPrefix.setText(resourceMap.getString("labelSolverPropertyPrefix.text")); // NOI18N
        labelSolverPropertyPrefix.setName("labelSolverPropertyPrefix"); // NOI18N

        jScrollPane1.setEnabled(false);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        textAreaResultPropertyDescription.setColumns(20);
        textAreaResultPropertyDescription.setRows(4);
        textAreaResultPropertyDescription.setWrapStyleWord(true);
        textAreaResultPropertyDescription.setEnabled(false);
        textAreaResultPropertyDescription.setName("textAreaResultPropertyDescription"); // NOI18N
        jScrollPane1.setViewportView(textAreaResultPropertyDescription);

        labelSolverPropertyDescription.setText(resourceMap.getString("labelSolverPropertyDescription.text")); // NOI18N
        labelSolverPropertyDescription.setName("labelSolverPropertyDescription"); // NOI18N

        comboBoxSolverPropertyValuetype.setEnabled(false);
        comboBoxSolverPropertyValuetype.setName("comboBoxSolverPropertyValuetype"); // NOI18N

        labelSolverPropertyValueType.setText(resourceMap.getString("labelSolverPropertyValueType.text")); // NOI18N
        labelSolverPropertyValueType.setName("labelSolverPropertyValueType"); // NOI18N

        buttonSolverPropertyAddValueType.setText(resourceMap.getString("buttonSolverPropertyAddValueType.text")); // NOI18N
        buttonSolverPropertyAddValueType.setEnabled(false);
        buttonSolverPropertyAddValueType.setName("buttonSolverPropertyAddValueType"); // NOI18N
        buttonSolverPropertyAddValueType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSolverPropertyAddValueTypeActionPerformed(evt);
            }
        });

        labeSolverPropertyType.setText(resourceMap.getString("labeSolverPropertyType.text")); // NOI18N
        labeSolverPropertyType.setName("labeSolverPropertyType"); // NOI18N

        comboBoxSolverPropertyType.setEnabled(false);
        comboBoxSolverPropertyType.setName("comboBoxSolverPropertyType"); // NOI18N
        comboBoxSolverPropertyType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSolverPropertyTypeActionPerformed(evt);
            }
        });

        labeMultipleOccurrences.setText(resourceMap.getString("labeMultipleOccurrences.text")); // NOI18N
        labeMultipleOccurrences.setName("labeMultipleOccurrences"); // NOI18N

        checkBoxMultipleOccurrences.setText(resourceMap.getString("checkBoxMultipleOccurrences.text")); // NOI18N
        checkBoxMultipleOccurrences.setEnabled(false);
        checkBoxMultipleOccurrences.setName("checkBoxMultipleOccurrences"); // NOI18N

        javax.swing.GroupLayout panelManageSolverPropertyEditInputLayout = new javax.swing.GroupLayout(panelManageSolverPropertyEditInput);
        panelManageSolverPropertyEditInput.setLayout(panelManageSolverPropertyEditInputLayout);
        panelManageSolverPropertyEditInputLayout.setHorizontalGroup(
            panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                        .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSolverPropertyPrefix)
                            .addComponent(labelSolverPropertyname)
                            .addComponent(labelSolverPropertyDescription))
                        .addGap(71, 71, 71)
                        .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                            .addComponent(textSolvertPropertyFieldPrefix, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                            .addComponent(textSolverPropertyFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)))
                    .addComponent(labeMultipleOccurrences)
                    .addGroup(panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                        .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                                .addComponent(labeSolverPropertyType)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboBoxSolverPropertyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                                .addComponent(labelSolverPropertyValueType)
                                .addGap(71, 71, 71)
                                .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkBoxMultipleOccurrences)
                                    .addComponent(comboBoxSolverPropertyValuetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(buttonSolverPropertyAddValueType)))
                .addContainerGap())
        );
        panelManageSolverPropertyEditInputLayout.setVerticalGroup(
            panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                        .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSolverPropertyname)
                            .addComponent(textSolverPropertyFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textSolvertPropertyFieldPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelManageSolverPropertyEditInputLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(labelSolverPropertyPrefix)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSolverPropertyDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeSolverPropertyType)
                    .addComponent(comboBoxSolverPropertyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSolverPropertyValuetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSolverPropertyValueType)
                    .addComponent(buttonSolverPropertyAddValueType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageSolverPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeMultipleOccurrences)
                    .addComponent(checkBoxMultipleOccurrences))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelManageSolverPropertyEditLayout = new javax.swing.GroupLayout(panelManageSolverPropertyEdit);
        panelManageSolverPropertyEdit.setLayout(panelManageSolverPropertyEditLayout);
        panelManageSolverPropertyEditLayout.setHorizontalGroup(
            panelManageSolverPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageSolverPropertyEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageSolverPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManageSolverPropertyEditLayout.createSequentialGroup()
                        .addComponent(panelManageSolverPropertyEditInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelManageSolverPropertyEditButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buttonSaveSolverProperty))
                .addContainerGap())
        );
        panelManageSolverPropertyEditLayout.setVerticalGroup(
            panelManageSolverPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManageSolverPropertyEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageSolverPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageSolverPropertyEditLayout.createSequentialGroup()
                        .addComponent(panelManageSolverPropertyEditButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(panelManageSolverPropertyEditLayout.createSequentialGroup()
                        .addComponent(panelManageSolverPropertyEditInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(buttonSaveSolverProperty)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(panelManageSolverPropertyEdit);

        javax.swing.GroupLayout panelManageSolverPropertyLayout = new javax.swing.GroupLayout(panelManageSolverProperty);
        panelManageSolverProperty.setLayout(panelManageSolverPropertyLayout);
        panelManageSolverPropertyLayout.setHorizontalGroup(
            panelManageSolverPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageSolverPropertyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        panelManageSolverPropertyLayout.setVerticalGroup(
            panelManageSolverPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageSolverPropertyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonDone.setText(resourceMap.getString("buttonDone.text")); // NOI18N
        buttonDone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        buttonDone.setName("buttonDone"); // NOI18N
        buttonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelManageSolverProperty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDone))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelManageSolverProperty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonDone, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewSolverPropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewSolverPropertyActionPerformed
        controller.NewSolverProperty();
}//GEN-LAST:event_buttonNewSolverPropertyActionPerformed

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void comboBoxSolverPropertyTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSolverPropertyTypeActionPerformed
        controller.SolverPropertyTypeChanged();
    }//GEN-LAST:event_comboBoxSolverPropertyTypeActionPerformed

    private void buttonSolverPropertyAddValueTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSolverPropertyAddValueTypeActionPerformed
      if(PropertyValueTypesDialog == null){
            JFrame mainFrame = EDACCApp.getApplication().getMainFrame();
            PropertyValueTypesDialog = new EDACCManagePropertyValueTypesDialog(mainFrame, true);
            PropertyValueTypesDialog.setLocationRelativeTo(mainFrame);
            PropertyValueTypesDialog.initialize();
      }
      PropertyValueTypesDialog.setVisible(true);
    }//GEN-LAST:event_buttonSolverPropertyAddValueTypeActionPerformed

    private void buttonRemoveSolverPropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveSolverPropertyActionPerformed
        if(tableSolverProperty.getSelectedRow() == -1){
                 JOptionPane.showMessageDialog(this,
                "Nothing is selected. Select a solver property.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }else{
            try {
                controller.removeSolverProperty(tableSolverProperty.convertRowIndexToModel(tableSolverProperty.getSelectedRow()));
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(EDACCManageSolverPropertyDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EDACCManageSolverPropertyDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SolverPropertyIsUsedException ex) {
                JOptionPane.showMessageDialog(this,
                "Cannot delete the solver property, because it's already in use.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }
            }
    }//GEN-LAST:event_buttonRemoveSolverPropertyActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EDACCManageSolverPropertyDialog dialog = new EDACCManageSolverPropertyDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonNewSolverProperty;
    private javax.swing.JButton buttonRemoveSolverProperty;
    private javax.swing.JButton buttonSaveSolverProperty;
    private javax.swing.JButton buttonSolverPropertyAddValueType;
    private javax.swing.JCheckBox checkBoxMultipleOccurrences;
    private javax.swing.JComboBox comboBoxSolverPropertyType;
    private javax.swing.JComboBox comboBoxSolverPropertyValuetype;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labeMultipleOccurrences;
    private javax.swing.JLabel labeSolverPropertyType;
    private javax.swing.JLabel labelSolverPropertyDescription;
    private javax.swing.JLabel labelSolverPropertyPrefix;
    private javax.swing.JLabel labelSolverPropertyValueType;
    private javax.swing.JLabel labelSolverPropertyname;
    private javax.swing.JPanel panelManageSolverProperty;
    private javax.swing.JPanel panelManageSolverPropertyEdit;
    private javax.swing.JPanel panelManageSolverPropertyEditButtons;
    private javax.swing.JPanel panelManageSolverPropertyEditInput;
    private javax.swing.JPanel panelManageSolverPropertyShowButtons;
    private javax.swing.JScrollPane panelManageSolverPropertyTable;
    private javax.swing.JPanel panelMangeSolverPropertyShow;
    private javax.swing.JTable tableSolverProperty;
    private javax.swing.JTextArea textAreaResultPropertyDescription;
    private javax.swing.JTextField textSolverPropertyFieldName;
    private javax.swing.JTextField textSolvertPropertyFieldPrefix;
    // End of variables declaration//GEN-END:variables

    public void enableSolverPropertyEditField(boolean enable) {
        this.comboBoxSolverPropertyType.setEnabled(enable);       
        this.buttonSaveSolverProperty.setEnabled(enable);
        this.textSolvertPropertyFieldPrefix.setEnabled(enable);
        this.textSolverPropertyFieldName.setEnabled(enable);
        this.textAreaResultPropertyDescription.setEnabled(enable);
        this.textSolverPropertyFieldName.requestFocus();
        this.comboBoxSolverPropertyValuetype.setEnabled(enable);
        this.buttonSolverPropertyAddValueType.setEnabled(enable);
        this.checkBoxMultipleOccurrences.setEnabled(enable);
    }

    public void clearSolverPropertyEditField() {
        this.comboBoxSolverPropertyType.setSelectedIndex(0);
        if(this.comboBoxSolverPropertyValuetype.getItemCount() != 0)
            this.comboBoxSolverPropertyValuetype.setSelectedIndex(0);      
        this.checkBoxMultipleOccurrences.setSelected(false);
        this.textSolverPropertyFieldName.setText("");
        this.textSolvertPropertyFieldPrefix.setText("");
        this.textAreaResultPropertyDescription.setText("");
    }

    public void SolverPropertyTypeChanged() {
        if(this.comboBoxSolverPropertyType.getSelectedItem() != SolverPropertyType.Parameter){
            this.comboBoxSolverPropertyValuetype.setEnabled(true);
            this.buttonSolverPropertyAddValueType.setEnabled(true);
            this.checkBoxMultipleOccurrences.setEnabled(true);
            this.buttonSaveSolverProperty.setEnabled(true);
        }else {
           this.comboBoxSolverPropertyValuetype.setEnabled(false);
           this.buttonSolverPropertyAddValueType.setEnabled(false);
           this.checkBoxMultipleOccurrences.setEnabled(false);
        }
    }

    /**
     * Initialize and chargs the tableSolverPropertys and the comboBoxSolverPropertyValueType with the corresponding items.
     */
    public void initialize() {
        try {
            controller.loadSolverProperties();
            controller.loadPropertyValueTypes();
        } catch (NoConnectionToDBException ex) {
            Logger.getLogger(EDACCManageSolverPropertyDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EDACCManageSolverPropertyDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SolverPropertyNotInDBException ex) {
            Logger.getLogger(EDACCManageSolverPropertyDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SolverPropertyTypeNotExistException ex) {
            Logger.getLogger(EDACCManageSolverPropertyDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EDACCManageSolverPropertyDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Clears all items of the ComboBoxPropertyValueType and add the given items.
     * @param items to add to the combobox
     */
    public void setComboBoxPropertyValueTypesItems(Vector<String> items){
        this.comboBoxSolverPropertyValuetype.removeAllItems();
        for(int i = 0; i < items.size(); i++){
            this.comboBoxSolverPropertyValuetype.addItem(items.get(i));
        }
    }

    /**
     * Fills the input fields with the parameters of the given SolverProperty object and activates the input fields.
     * @param toShow the SolverProperty object to show.
     */
    public void showSolverProperty(SolverProperty toShow){
        this.textSolverPropertyFieldName.setText(toShow.getName());
        this.textSolvertPropertyFieldPrefix.setText(toShow.getPrefix());
        this.textAreaResultPropertyDescription.setText(toShow.getDescription());
        this.comboBoxSolverPropertyType.setSelectedItem(toShow.getSolverPropertyType());
        if(!toShow.getSolverPropertyType().equals(SolverPropertyType.Parameter)){
            this.comboBoxSolverPropertyValuetype.setSelectedItem(toShow.getPropertyValueType());
            this.checkBoxMultipleOccurrences.setSelected(toShow.isMultiple());
        }
        SolverPropertyTypeChanged();
    }

}
