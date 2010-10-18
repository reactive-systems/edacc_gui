/*
 * Filter.java
 *
 * Created on 13.10.2010, 11:29:39
 */
package edacc.filter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.application.Action;

/**
 *
 * @author simon
 */
public class Filter extends javax.swing.JDialog {

    private JTable table;
    private TableRowSorter<? extends TableModel> rowSorter;
    private RowFilter<Object, Object> rowFilter;
    private HashMap<Integer, FilterType> colFilter;
    private GridBagLayout argumentLayout;
    private GridBagConstraints gridBagConstraints;
    private Parser parser;
    private String expression;
    private LinkedList<ArgumentPanel> filterArguments;
    private boolean updateFilterTypes;

    /** Creates new form EDACCFilter */
    public Filter(java.awt.Frame parent, boolean modal, JTable table, boolean autoUpdateFilterTypes) {
        super(parent, modal);
        initComponents();
        if (!(table.getRowSorter() instanceof TableRowSorter)) {
            throw new IllegalArgumentException("Expected TableRowSorter.");
        }
        this.table = table;
        rowSorter = (TableRowSorter<? extends TableModel>) table.getRowSorter();
        rowFilter = new RowFilter<Object, Object>() {

            @Override
            public boolean include(Entry<? extends Object, ? extends Object> entry) {
                return Filter.this.include(entry);
            }
        };
        rowSorter.setRowFilter(rowFilter);
        colFilter = new HashMap<Integer, FilterType>();
        argumentLayout = new GridBagLayout();
        pnlArguments.setLayout(argumentLayout);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1000;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        //gridBagConstraints.ipadx = 6;
        //gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        parser = new Parser();
        this.updateFilterTypes = autoUpdateFilterTypes;
        expression = "";
        filterArguments = new LinkedList<ArgumentPanel>();
    }

    public Object getValueAt(int row, int col) {
        return table.getModel().getValueAt(row, col);
    }

    public synchronized boolean include(Entry<? extends Object, ? extends Object> entry) {
        HashMap<Integer, Boolean> arguments = new HashMap<Integer, Boolean>();
        for (ArgumentPanel panel : filterArguments) {
            arguments.put(panel.getArgNum(), panel.getFilterInterface().include(getValueAt((Integer) entry.getIdentifier(), panel.getColumn())));
        }
        try {
            return parser.eval(expression, arguments);
        } catch (Exception ex) {
            return false;
        }
    }

    public void clearFilters() {
        pnlArguments.removeAll();
        txtExpression.setText("");
    }

    public synchronized boolean hasFiltersApplied() {
        try {
            return parser != null && !parser.eval(expression, new HashMap<Integer, Boolean>());
        } catch (Exception ex) {
            return true;
        }
    }

    public void updateFilterTypes() {
        Class<?>[] classes = new Class<?>[table.getModel().getColumnCount()];
        String[] columnNames = new String[table.getModel().getColumnCount()];

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            classes[i] = table.getModel().getColumnClass(i);
            columnNames[i] = table.getModel().getColumnName(i);
        }
        updateFilterTypes(classes, columnNames);
    }

    protected void updateFilterTypes(Class<?>[] classes, String[] columnNames) {
        for (int i = 0; i < classes.length; i++) {
            if (colFilter.containsKey(i)) {
                if (colFilter.get(i).clazz != classes[i]) {
                    colFilter.remove(i);
                    for (int k = pnlArguments.getComponentCount() - 1; k >= 0; k--) {
                        if (pnlArguments.getComponent(k) instanceof ArgumentPanel) {
                            ArgumentPanel panel = (ArgumentPanel) pnlArguments.getComponent(k);
                            if (panel.getColumn() == i) {
                                pnlArguments.remove(k);
                            }
                        }
                    }
                } else {
                    continue;
                }
            }
            colFilter.put(i, new FilterType(i, columnNames[i], classes[i]));
        }
        comboFilterTypes.removeAllItems();
        for (FilterType f : colFilter.values()) {
            comboFilterTypes.addItem(f);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (updateFilterTypes) {
            updateFilterTypes();
        }
        super.setVisible(visible);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlArguments = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        comboFilterTypes = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtExpression = new javax.swing.JTextField();
        btnApply = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDismiss = new javax.swing.JButton();

        setName("Form"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(Filter.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(2, 200));

        pnlArguments.setName("pnlArguments"); // NOI18N
        pnlArguments.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(pnlArguments);

        jPanel4.setName("jPanel4"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(Filter.class, this);
        btnAdd.setAction(actionMap.get("btnAdd")); // NOI18N
        btnAdd.setText(resourceMap.getString("btnAdd.text")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N

        comboFilterTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboFilterTypes.setName("comboFilterTypes"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(comboFilterTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addContainerGap(542, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboFilterTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        txtExpression.setText(resourceMap.getString("txtExpression.text")); // NOI18N
        txtExpression.setName("txtExpression"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtExpression, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtExpression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnApply.setAction(actionMap.get("btnApply")); // NOI18N
        btnApply.setText(resourceMap.getString("btnApply.text")); // NOI18N
        btnApply.setName("btnApply"); // NOI18N
        btnApply.setPreferredSize(new java.awt.Dimension(67, 23));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btnDismiss.setAction(actionMap.get("btnDismiss")); // NOI18N
        btnDismiss.setText(resourceMap.getString("btnDismiss.text")); // NOI18N
        btnDismiss.setName("btnDismiss"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addComponent(btnDismiss)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDismiss))
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        btnDismiss();
    }//GEN-LAST:event_formComponentHidden

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnDismiss;
    private javax.swing.JComboBox comboFilterTypes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlArguments;
    private javax.swing.JTextField txtExpression;
    // End of variables declaration//GEN-END:variables

    class FilterType {

        int column;
        String name;
        Class<?> clazz;

        public FilterType(int column, String name, Class<?> clazz) {
            this.column = column;
            this.name = name;
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Action
    public void btnAdd() {
        int argNum = 0;
        for (int i = 0; i < pnlArguments.getComponentCount(); i++) {
            if (pnlArguments.getComponent(i) instanceof ArgumentPanel) {
                int tmp = ((ArgumentPanel) pnlArguments.getComponent(i)).getArgNum();
                if (argNum < tmp) {
                    argNum = tmp;
                }
            }
        }
        argNum++;
        if (comboFilterTypes.getSelectedItem() instanceof FilterType) {
            FilterType filterType = (FilterType) comboFilterTypes.getSelectedItem();
            if (filterType.clazz == Integer.class || filterType.clazz == Float.class || filterType.clazz == Double.class) {
                pnlArguments.add(new ArgumentPanel(this, new NumberFilter(filterType.name), argNum, filterType.column));
            } else if (filterType.clazz == String.class) {
                pnlArguments.add(new ArgumentPanel(this, new StringFilter(filterType.name), argNum, filterType.column));
            } else if (filterType.clazz == Boolean.class) {
                pnlArguments.add(new ArgumentPanel(this, new BooleanFilter(filterType.name), argNum, filterType.column));
            }
            try {
                if (parser.eval(txtExpression.getText(), new HashMap<Integer, Boolean>())) {
                    // expression is valid and will always evaluate to true (no arguments needed)
                    txtExpression.setText("$" + argNum);
                }
            } catch (Exception ex) {
                try {
                    parser.eval(txtExpression.getText(), new HashMap<Integer, Boolean>(), true);
                    // expression is valid but arguments are needed
                    txtExpression.setText(txtExpression.getText() + " && $" + argNum);
                } catch (Exception ex1) {
                }

            }
            setGridBagConstraints();
        }
    }

    private void setGridBagConstraints() {
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1;
        for (int i = 0; i < pnlArguments.getComponentCount(); i++) {
            gridBagConstraints.gridy++;
            gridBagConstraints.weighty *= 1000;
            argumentLayout.setConstraints(pnlArguments.getComponent(i), gridBagConstraints);
        }
        pnlArguments.repaint();
        pnlArguments.revalidate();
    }

    @Action
    public synchronized void btnApply() {
        expression = txtExpression.getText();
        filterArguments.clear();
        for (int i = 0; i < pnlArguments.getComponentCount(); i++) {
            if (pnlArguments.getComponent(i) instanceof ArgumentPanel) {
                filterArguments.add((ArgumentPanel) pnlArguments.getComponent(i));
                ((ArgumentPanel) pnlArguments.getComponent(i)).getFilterInterface().apply();
            }
        }
        setVisible(false);
    }

    public void remove(ArgumentPanel pnl) {
        pnlArguments.remove(pnl);
        setGridBagConstraints();
    }

    @Action
    public synchronized void btnDismiss() {
        txtExpression.setText(expression);
        pnlArguments.removeAll();
        for (ArgumentPanel panel : filterArguments) {
            panel.getFilterInterface().undo();
            pnlArguments.add(panel);
        }
        setGridBagConstraints();
        setVisible(false);
    }
}
