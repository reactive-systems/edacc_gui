/*
 * AnalysisBottomPanel.java
 *
 * Created on 29.06.2010, 11:52:39
 */
package edacc.experiment;

import edacc.EDACCPlotTabView;
import edacc.events.TaskEvents;
import edacc.experiment.plots.DependencyException;
import edacc.experiment.plots.Plot;
import edacc.experiment.plots.PlotPanel;
import edacc.model.TaskRunnable;
import edacc.model.Tasks;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import org.jdesktop.application.Action;
import org.rosuda.JRI.Rengine;

/**
 * This class represents the bottom panel of the AnalysisPanel. It implements the functionality
 * to generate, show and hide plots.
 * @see edacc.experiment.AnalysisPanel
 * @author simon
 */
public class AnalysisBottomPanel extends javax.swing.JPanel implements edacc.events.PlotTabEvents, TaskEvents {

    private AnalysisPanel analysisPanel;
    private PlotPanel panel;
    private Plot plot;

    /** Creates new form AnalysisBottomPanel */
    @SuppressWarnings("LeakingThisInConstructor")
    public AnalysisBottomPanel(AnalysisPanel analysisPanel) {
        initComponents();
        this.analysisPanel = analysisPanel;
        EDACCPlotTabView.addListener(this);
        tabViewCountChanged(EDACCPlotTabView.getTabViewCount());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGeneratePlot = new javax.swing.JButton();
        btnShowPlots = new javax.swing.JButton();

        setName("Form"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(AnalysisBottomPanel.class, this);
        btnGeneratePlot.setAction(actionMap.get("btnGeneratePlot")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(AnalysisBottomPanel.class);
        btnGeneratePlot.setText(resourceMap.getString("btnGeneratePlot.text")); // NOI18N
        btnGeneratePlot.setName("btnGeneratePlot"); // NOI18N
        btnGeneratePlot.setPreferredSize(new java.awt.Dimension(115, 25));

        btnShowPlots.setAction(actionMap.get("btnShowPlots")); // NOI18N
        btnShowPlots.setText(resourceMap.getString("btnShowPlots.text")); // NOI18N
        btnShowPlots.setName("btnShowPlots"); // NOI18N
        btnShowPlots.setPreferredSize(new java.awt.Dimension(115, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGeneratePlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowPlots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGeneratePlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowPlots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This will generate the plot for the current plot type in the AnalysisPanel.
     * @see edacc.experiment.AnalysisPanel
     */
    @Action
    public void btnGeneratePlot() {
        // first get the current plot
        plot = analysisPanel.getSelectedPlot();
        if (plot == null) {
            return;
        }
        if (AnalysisController.analysisPanel == null) {
            AnalysisController.analysisPanel = analysisPanel;
        }

        // then generate a PlotPanel and add, plot the plot, and finally add it
        // to the main tab view (onTaskSuccessful will do this)
        try {
            panel = new PlotPanel(plot);
            final Rengine re = AnalysisController.getREngine(panel);
            panel.setDeviceNumber(AnalysisController.getCurrentDeviceNumber());
            Tasks.startTask(new TaskRunnable() {

                @Override
                public void run(Tasks task) {
                    synchronized (AnalysisController.syncR) {
                        try {
                            EDACCPlotTabView.setTabViewsVisible(true);
                            plot.plot(re, panel.pointInformations);
                            AnalysisBottomPanel.this.onTaskSuccessful("plot", null);
                        } catch (final Throwable ex) {
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    AnalysisBottomPanel.this.onTaskFailed("plot", ex);
                                }
                            });
                        }
                    }
                }
            }, true, EDACCPlotTabView.getMainTabView());
        } catch (REngineInitializationException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error while initializing R: " + ex.getMessage(), "Initialization Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }

    @Action
    public void btnShowPlots() {
        if ("Hide Plots".equals(btnShowPlots.getText())) {
            EDACCPlotTabView.setTabViewsVisible(false);
        } else {
            EDACCPlotTabView.setTabViewsVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGeneratePlot;
    private javax.swing.JButton btnShowPlots;
    // End of variables declaration//GEN-END:variables

    @Override
    public final void tabViewCountChanged(int count) {
        // if there are no plots, the show plots button should be disabled
        if (count == 0) {
            btnShowPlots.setEnabled(false);
        } else {
            btnShowPlots.setEnabled(true);
        }
    }

    @Override
    public void tabViewVisibilityChanged(boolean visible) {
        // set the title for the show plots button according to the state of the tab view visibility
        if (visible) {
            btnShowPlots.setText("Hide Plots");
        } else {
            btnShowPlots.setText("Show Plots");
        }
    }

    @Override
    public void onTaskSuccessful(String methodName, Object result) {
        if ("plot".equals(methodName)) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    EDACCPlotTabView.addPanelInMainTabView(plot.getPlotTitle(), panel);
                }
            });

        }
    }

    @Override
    public void onTaskStart(String methodName) {
    }

    @Override
    public void onTaskFailed(String methodName, Throwable e) {
        if ("plot".equals(methodName)) {
            if (e instanceof DependencyException) {
                javax.swing.JOptionPane.showMessageDialog(EDACCPlotTabView.getMainTabView(), e.getMessage(), "Invalid input", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else if (e instanceof SQLException) {
                javax.swing.JOptionPane.showMessageDialog(EDACCPlotTabView.getMainTabView(), e.getMessage(), "Database error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(EDACCPlotTabView.getMainTabView(), e, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
