/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCManagePropertyMode.java
 *
 * Created on 01.09.2010, 13:16:04
 */

package edacc;

import edacc.properties.ManageResultProperties;
import edacc.properties.ResultPropertyTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rretz
 */
public class EDACCManagePropertyMode extends javax.swing.JPanel {
    private ManageResultProperties manageResultProperties;
    private ResultPropertyTableModel resPropertyTableModel;

    /** Creates new form EDACCManagePropertyMode */
    public EDACCManagePropertyMode() {
        initComponents();

        // initialize tableResultProperty
        resPropertyTableModel = new ResultPropertyTableModel();
        tableResultProperty.setRowSorter(new TableRowSorter<ResultPropertyTableModel>(resPropertyTableModel));

        manageResultProperties = new ManageResultProperties(this, panelManageResultProperty, tableResultProperty);



    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        managePropertyPane = new javax.swing.JTabbedPane();
        panelManageResultProperty = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelMangeResultPropertyShow = new javax.swing.JPanel();
        panelManageResultPropertyShowButtons = new javax.swing.JPanel();
        buttonNewResultProperty = new javax.swing.JButton();
        buttonRemoveResultProperty = new javax.swing.JButton();
        panelManageResultPropertyTable = new javax.swing.JScrollPane();
        tableResultProperty = new javax.swing.JTable();
        panelManageResultPropertyEdit = new javax.swing.JPanel();
        panelManageResultPropertyEditButtons = new javax.swing.JPanel();
        panelManageResultPropertyEditInput = new javax.swing.JPanel();
        textResultPropertyFieldName = new javax.swing.JTextField();
        labelResultPropertyname = new javax.swing.JLabel();
        textResultPropertyFieldPrefix = new javax.swing.JTextField();
        labelResultPropertyPrefix = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaResultPropertyDescription = new javax.swing.JTextArea();
        labelResultPropertyDescription = new javax.swing.JLabel();
        comboBoxResultPropertyValuetype = new javax.swing.JComboBox();
        labelResultPropertyValuetype = new javax.swing.JLabel();
        buttonResultPropertyAddValueType = new javax.swing.JButton();
        buttonSaveResultProperty = new javax.swing.JButton();

        setName("Form"); // NOI18N

        managePropertyPane.setName("managePropertyPane"); // NOI18N

        panelManageResultProperty.setName("panelManageResultProperty"); // NOI18N

        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.8);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        panelMangeResultPropertyShow.setName("panelMangeResultPropertyShow"); // NOI18N

        panelManageResultPropertyShowButtons.setName("panelManageResultPropertyShowButtons"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCManagePropertyMode.class);
        buttonNewResultProperty.setText(resourceMap.getString("buttonNewResultProperty.text")); // NOI18N
        buttonNewResultProperty.setName("buttonNewResultProperty"); // NOI18N

        buttonRemoveResultProperty.setText(resourceMap.getString("buttonRemoveResultProperty.text")); // NOI18N
        buttonRemoveResultProperty.setName("buttonRemoveResultProperty"); // NOI18N

        javax.swing.GroupLayout panelManageResultPropertyShowButtonsLayout = new javax.swing.GroupLayout(panelManageResultPropertyShowButtons);
        panelManageResultPropertyShowButtons.setLayout(panelManageResultPropertyShowButtonsLayout);
        panelManageResultPropertyShowButtonsLayout.setHorizontalGroup(
            panelManageResultPropertyShowButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageResultPropertyShowButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonNewResultProperty, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemoveResultProperty)
                .addContainerGap(428, Short.MAX_VALUE))
        );

        panelManageResultPropertyShowButtonsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonNewResultProperty, buttonRemoveResultProperty});

        panelManageResultPropertyShowButtonsLayout.setVerticalGroup(
            panelManageResultPropertyShowButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageResultPropertyShowButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageResultPropertyShowButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNewResultProperty)
                    .addComponent(buttonRemoveResultProperty))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelManageResultPropertyShowButtonsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonNewResultProperty, buttonRemoveResultProperty});

        panelManageResultPropertyTable.setName("panelManageResultPropertyTable"); // NOI18N

        tableResultProperty.setModel(new javax.swing.table.DefaultTableModel(
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
        tableResultProperty.setName("tableResultProperty"); // NOI18N
        panelManageResultPropertyTable.setViewportView(tableResultProperty);

        javax.swing.GroupLayout panelMangeResultPropertyShowLayout = new javax.swing.GroupLayout(panelMangeResultPropertyShow);
        panelMangeResultPropertyShow.setLayout(panelMangeResultPropertyShowLayout);
        panelMangeResultPropertyShowLayout.setHorizontalGroup(
            panelMangeResultPropertyShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMangeResultPropertyShowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMangeResultPropertyShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelManageResultPropertyTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(panelManageResultPropertyShowButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMangeResultPropertyShowLayout.setVerticalGroup(
            panelMangeResultPropertyShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMangeResultPropertyShowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelManageResultPropertyTable, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelManageResultPropertyShowButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panelMangeResultPropertyShow);

        panelManageResultPropertyEdit.setName("panelManageResultPropertyEdit"); // NOI18N

        panelManageResultPropertyEditButtons.setName("panelManageResultPropertyEditButtons"); // NOI18N

        javax.swing.GroupLayout panelManageResultPropertyEditButtonsLayout = new javax.swing.GroupLayout(panelManageResultPropertyEditButtons);
        panelManageResultPropertyEditButtons.setLayout(panelManageResultPropertyEditButtonsLayout);
        panelManageResultPropertyEditButtonsLayout.setHorizontalGroup(
            panelManageResultPropertyEditButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );
        panelManageResultPropertyEditButtonsLayout.setVerticalGroup(
            panelManageResultPropertyEditButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        panelManageResultPropertyEditInput.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelManageResultPropertyEditInput.setName("panelManageResultPropertyEditInput"); // NOI18N

        textResultPropertyFieldName.setText(resourceMap.getString("textResultPropertyFieldName.text")); // NOI18N
        textResultPropertyFieldName.setName("textResultPropertyFieldName"); // NOI18N

        labelResultPropertyname.setText(resourceMap.getString("labelResultPropertyname.text")); // NOI18N
        labelResultPropertyname.setName("labelResultPropertyname"); // NOI18N

        textResultPropertyFieldPrefix.setText(resourceMap.getString("textResultPropertyFieldPrefix.text")); // NOI18N
        textResultPropertyFieldPrefix.setName("textResultPropertyFieldPrefix"); // NOI18N

        labelResultPropertyPrefix.setText(resourceMap.getString("labelResultPropertyPrefix.text")); // NOI18N
        labelResultPropertyPrefix.setName("labelResultPropertyPrefix"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        textAreaResultPropertyDescription.setColumns(20);
        textAreaResultPropertyDescription.setRows(4);
        textAreaResultPropertyDescription.setWrapStyleWord(true);
        textAreaResultPropertyDescription.setName("textAreaResultPropertyDescription"); // NOI18N
        jScrollPane1.setViewportView(textAreaResultPropertyDescription);

        labelResultPropertyDescription.setText(resourceMap.getString("labelResultPropertyDescription.text")); // NOI18N
        labelResultPropertyDescription.setName("labelResultPropertyDescription"); // NOI18N

        comboBoxResultPropertyValuetype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxResultPropertyValuetype.setName("comboBoxResultPropertyValuetype"); // NOI18N

        labelResultPropertyValuetype.setText(resourceMap.getString("labelResultPropertyValuetype.text")); // NOI18N
        labelResultPropertyValuetype.setName("labelResultPropertyValuetype"); // NOI18N

        buttonResultPropertyAddValueType.setText(resourceMap.getString("buttonResultPropertyAddValueType.text")); // NOI18N
        buttonResultPropertyAddValueType.setName("buttonResultPropertyAddValueType"); // NOI18N

        javax.swing.GroupLayout panelManageResultPropertyEditInputLayout = new javax.swing.GroupLayout(panelManageResultPropertyEditInput);
        panelManageResultPropertyEditInput.setLayout(panelManageResultPropertyEditInputLayout);
        panelManageResultPropertyEditInputLayout.setHorizontalGroup(
            panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageResultPropertyEditInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageResultPropertyEditInputLayout.createSequentialGroup()
                        .addGroup(panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelResultPropertyPrefix)
                            .addComponent(labelResultPropertyname)
                            .addComponent(labelResultPropertyDescription))
                        .addGap(71, 71, 71)
                        .addGroup(panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                            .addComponent(textResultPropertyFieldPrefix, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                            .addComponent(textResultPropertyFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)))
                    .addGroup(panelManageResultPropertyEditInputLayout.createSequentialGroup()
                        .addComponent(labelResultPropertyValuetype)
                        .addGap(71, 71, 71)
                        .addComponent(comboBoxResultPropertyValuetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonResultPropertyAddValueType)))
                .addContainerGap())
        );
        panelManageResultPropertyEditInputLayout.setVerticalGroup(
            panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageResultPropertyEditInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageResultPropertyEditInputLayout.createSequentialGroup()
                        .addGroup(panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelResultPropertyname)
                            .addComponent(textResultPropertyFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textResultPropertyFieldPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelManageResultPropertyEditInputLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(labelResultPropertyPrefix)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelResultPropertyDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageResultPropertyEditInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxResultPropertyValuetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelResultPropertyValuetype)
                    .addComponent(buttonResultPropertyAddValueType))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonSaveResultProperty.setText(resourceMap.getString("buttonSaveResultProperty.text")); // NOI18N
        buttonSaveResultProperty.setName("buttonSaveResultProperty"); // NOI18N

        javax.swing.GroupLayout panelManageResultPropertyEditLayout = new javax.swing.GroupLayout(panelManageResultPropertyEdit);
        panelManageResultPropertyEdit.setLayout(panelManageResultPropertyEditLayout);
        panelManageResultPropertyEditLayout.setHorizontalGroup(
            panelManageResultPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageResultPropertyEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageResultPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelManageResultPropertyEditInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelManageResultPropertyEditLayout.createSequentialGroup()
                        .addComponent(buttonSaveResultProperty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelManageResultPropertyEditButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelManageResultPropertyEditLayout.setVerticalGroup(
            panelManageResultPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageResultPropertyEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelManageResultPropertyEditInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageResultPropertyEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelManageResultPropertyEditButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaveResultProperty))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(panelManageResultPropertyEdit);

        javax.swing.GroupLayout panelManageResultPropertyLayout = new javax.swing.GroupLayout(panelManageResultProperty);
        panelManageResultProperty.setLayout(panelManageResultPropertyLayout);
        panelManageResultPropertyLayout.setHorizontalGroup(
            panelManageResultPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageResultPropertyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelManageResultPropertyLayout.setVerticalGroup(
            panelManageResultPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManageResultPropertyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        managePropertyPane.addTab(resourceMap.getString("panelManageResultProperty.TabConstraints.tabTitle"), panelManageResultProperty); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(managePropertyPane, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(managePropertyPane, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonNewResultProperty;
    private javax.swing.JButton buttonRemoveResultProperty;
    private javax.swing.JButton buttonResultPropertyAddValueType;
    private javax.swing.JButton buttonSaveResultProperty;
    private javax.swing.JComboBox comboBoxResultPropertyValuetype;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelResultPropertyDescription;
    private javax.swing.JLabel labelResultPropertyPrefix;
    private javax.swing.JLabel labelResultPropertyValuetype;
    private javax.swing.JLabel labelResultPropertyname;
    private javax.swing.JTabbedPane managePropertyPane;
    private javax.swing.JPanel panelManageResultProperty;
    private javax.swing.JPanel panelManageResultPropertyEdit;
    private javax.swing.JPanel panelManageResultPropertyEditButtons;
    private javax.swing.JPanel panelManageResultPropertyEditInput;
    private javax.swing.JPanel panelManageResultPropertyShowButtons;
    private javax.swing.JScrollPane panelManageResultPropertyTable;
    private javax.swing.JPanel panelMangeResultPropertyShow;
    private javax.swing.JTable tableResultProperty;
    private javax.swing.JTextArea textAreaResultPropertyDescription;
    private javax.swing.JTextField textResultPropertyFieldName;
    private javax.swing.JTextField textResultPropertyFieldPrefix;
    // End of variables declaration//GEN-END:variables

}
