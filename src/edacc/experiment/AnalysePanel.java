/*
 * AnalysePanel.java
 *
 * Created on 29.06.2010, 10:43:45
 */
package edacc.experiment;

import edacc.experiment.plots.*;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author simon
 */
public class AnalysePanel extends javax.swing.JPanel {

    public JComboBox comboType;
    private JLabel jLabel1;
    private java.awt.GridBagConstraints gridBagConstraints;
    private AnalyseBottomPanel bottom;
    private ExperimentController expController;
    
    /** Creates new form AnalysePanel */
    public AnalysePanel(ExperimentController controller) {
        initComponents();
        this.expController = controller;
        bottom = new AnalyseBottomPanel(this);
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setText("Plot type:");
        comboType = new javax.swing.JComboBox();
        comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));

        comboType.addItem(new ScatterOnePropertyTwoSolvers(expController));
      //  comboType.addItem(new ScatterTwoPropertiesOneSolver(expController));
      //  comboType.addItem(new ScatterInstancePropertySolverProperty(expController));
        comboType.addItem(new CactusPlot(expController));
        comboType.addItem(new KernelDensityPlot(expController));
        comboType.addItem(new RTDPlot(expController));
        comboType.addItem(new RTDsPlot(expController));
        
        comboType.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboType.getSelectedItem() instanceof Plot) {
                    initialize();
                    try {
                        initializePlotType((Plot) comboType.getSelectedItem());
                    } catch (SQLException _) {
                        //TODO: show error message
                        initialize();
                    }
                }
            }
        });

    }

    public void initialize() {
        this.removeAll();
        try {
                if (comboType.getSelectedItem() instanceof Plot) {
                    Plot plot = (Plot) comboType.getSelectedItem();
                    plot.loadDefaultValues();
                }
        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO: ERROR MESSAGE!
        }
        setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.001;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboType, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 0.001;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 3, 3);
        add(jLabel1, gridBagConstraints);
    }

    public void initializePlotType(Plot plotType) throws SQLException {
        for (Dependency dependency : plotType.getDependencies()) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy++;
            JLabel label = new JLabel(dependency.getDescription() + ":");
            gridBagConstraints.weightx = 0.05;
            gridBagConstraints.insets = new java.awt.Insets(8, 3, 3, 3);

            add(label, gridBagConstraints);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.weightx = 0.8;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            add(dependency.getGuiObject(), gridBagConstraints);
        }
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.weightx = 1.;
        gridBagConstraints.gridwidth = 2;
        add(bottom, gridBagConstraints);
    }

    public Plot getSelectedPlot() {
        if (comboType.getSelectedItem() instanceof Plot) {
            return (Plot) comboType.getSelectedItem();
        } else {
            return null;
        }
    }

    public void updateDependencies() {
        Plot plot = getSelectedPlot();
        if (plot != null) {
            for (Dependency dep : plot.getDependencies()) {
                if (dep.getGuiObject() instanceof JComboBox) {
                    dep.setValue(((JComboBox) dep.getGuiObject()).getSelectedItem());
                }
            }
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
        java.awt.GridBagConstraints gridBagConstraints;

        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
