/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NetworkPanel.java
 *
 * Created on Feb 22, 2012, 9:05:26 AM
 */
package mavn.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import mavn.factory.AbstractSimulationFactory;
import mavn.globals.Globals;
import mavn.observer.AndLayerObserver;
import mavn.observer.DartsObserver;
import mavn.observer.OrLayerObserver;
import mavn.observer.OutputLayerObserver;
import mavn.observer.ResultsObserver;
import mavn.state.RngStateInterface;
import mavn.state.properties.view.PropertiesFrame;
import org.math.plot.Plot2DPanel;
import org.math.plot.plots.Plot;
import util.components.BlinkerButton;

/**
 *
 * @author Kaleb
 */
public abstract class NetworkViewAbstract extends javax.swing.JPanel implements ResultsObserver, NetworkViewInterface, DartsObserver, AndLayerObserver, OrLayerObserver, OutputLayerObserver
{

    protected double[][] w2 = new double[0][0];
    protected double[][] w1 = new double[0][0];
    protected double[][] w0 = new double[0][0];
    protected double[] andLayerResult;
    protected double[] orLayerResult;
    protected double[] outputLayerResult;
    protected AbstractSimulationFactory controller;
    protected NetworkPanel networkView;
    protected Plot2DPanel plot;

    /** Creates new form NetworkPanel */
    public NetworkViewAbstract(AbstractSimulationFactory controller)
    {
        this.controller = controller;
        initComponents();
        networkView = new NetworkPanel(w2, w1, w0);
        viewNetworkPane.setViewportView(networkView);
        viewNetworkPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    }

    public NetworkPanel getNetworkView()
    {
        return networkView;
    }

    /**
     * Get the Clear Results Matrix Button. Clears the results from the
     * Results JTextArea.
     * @return the Clear Results Matrix Button.
     */
    public JButton getClearResultsMatrixButton()
    {
        return clearResultsMatrixButton;
    }

    public JButton getLoadModelButton()
    {
        return loadModelButton;
    }

    /**
     * Get the Properties Button. This button allows the user
     * to edit the properties of the application.
     * @return the Properties Button.
     */
    public JButton getPropertiesButton()
    {
        return propertiesButton;
    }

    /**
     * Get the Run Button. This button allows the user to run the simulation.
     * @return the Run Button.
     */
    public JButton getRunButton()
    {
        return runButton;
    }

    public JTextArea getResultsTextArea()
    {
        return resultsTextArea;
    }

    /**
     * Get the Save Results Matrix Button. Saves the data for the model and the
     * Results JTextArea.
     * @return the Save Results Matrix Button.
     */
    public JButton getSaveResultsButton()
    {
        return saveResultsButton;
    }

    public void fireNodes()
    {
        networkView.fireNodes(getNodeResults());
    }

    @Override
    public void setNetwork(double[][] w2, double[][] w1, double[][] w0)
    {
        networkView = new NetworkPanel(w2, w1, w0);
        viewNetworkPane.setViewportView(networkView);
    }

    /**
     * Update the results for the simulation.
     * @param hit the points of darts that hit a shape
     * @param miss the points of darts that missed a shape
     */
    @Override
    public void updateDartResults(ArrayList<double[][]> hit, ArrayList<double[][]> miss)
    {
        Iterator hits = hit.iterator();
        double[] xHit = new double[hit.size()];
        double[] yHit = new double[hit.size()];
        Iterator misses = miss.iterator();
        double[] xMisses = new double[miss.size()];
        double[] yMisses = new double[miss.size()];

        int hitCount = 0;
        while (hits.hasNext())
        {
            double[][] tempHit = (double[][]) hits.next();

            xHit[hitCount] = tempHit[0][0];
            yHit[hitCount] = tempHit[1][0];
            hitCount++;
        }
        int missCount = 0;

        while (misses.hasNext())
        {
            double[][] tempHit = (double[][]) misses.next();
            xMisses[missCount] = tempHit[0][0];
            yMisses[missCount] = tempHit[1][0];
            missCount++;
        }

        plot.addScatterPlot("Hit", Color.red, xHit, yHit);
        plot.addScatterPlot("Miss", Color.black, xMisses, yMisses);
    }

    /**
     * Overload method to update the Results Matrix.
     * @param matrix the new matrix
     * @param description the new matrix description
     */
    @Override
    public void updateResultsModel(String results)
    {
        controller.getView().getOutputControllers().get(Globals.RESULTS_CONTROLLER).setResults(results);
        controller.getView().getOutputStates().get(Globals.RESULTS_STATE).resultAvailable();
        resultsTextArea.append(results);

        if (!((RngStateInterface) controller.getView().getOutputStates().get(Globals.RESULTS_STATE)).isDartGunState())
        {
            double[] x =
            {
                controller.getView().getInputControllers().get(Globals.TARGET_CONTROLLER).getModel()[0][0]
            };
            double[] y =
            {
                controller.getView().getInputControllers().get(Globals.TARGET_CONTROLLER).getModel()[1][0]
            };
            if (outputLayerResult[0] == 1)
            {
                plot.addScatterPlot("Hit", Color.red, x, y);
            }
            if (outputLayerResult[0] == -1)
            {
                plot.addScatterPlot("Miss", Color.black, x, y);
            }
            networkView.repaint();
        }
    }

    @Override
    public void updateAndLayerModel(double[][] matrix)
    {

        andLayerResult = new double[matrix.length];
        for (int i = 0; i < andLayerResult.length; i++)
        {
            andLayerResult[i] = matrix[i][0];
        }
    }

    @Override
    public void updateOrLayerModel(double[][] matrix)
    {
        orLayerResult = new double[matrix.length];
        for (int i = 0; i < orLayerResult.length; i++)
        {
            orLayerResult[i] = matrix[i][0];
        }
    }

    @Override
    public void updateOutputLayerModel(double[][] matrix)
    {
        outputLayerResult = new double[matrix.length];
        for (int i = 0; i < outputLayerResult.length; i++)
        {
            outputLayerResult[i] = matrix[i][0];
        }

        if (controller.getView().getOutputStates().get(Globals.RESULTS_STATE).isAnimated())
        {
            try
            {
                Thread.sleep(20);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(NetworkViewAbstract.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.fireNodes();
            networkView.repaint();
        }
    }

    private double[] getNodeResults()
    {
        ArrayList<Double> list = new ArrayList<Double>();

        list.add((double) 1);
        list.add((double) 1);
        for (int i = 0; i < andLayerResult.length; i++)
        {
            list.add(andLayerResult[i]);
        }

        for (int i = 0; i < orLayerResult.length; i++)
        {
            list.add(orLayerResult[i]);
        }

        for (int i = 0; i < outputLayerResult.length; i++)
        {
            list.add(outputLayerResult[i]);
        }

        double[] nodeResults = new double[list.size()];

        for (int i = 0; i < nodeResults.length; i++)
        {
            nodeResults[i] = (Double) list.get(i);
        }

        return nodeResults;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simulationControlPanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        resultsTextArea = new javax.swing.JTextArea();
        runButton = new BlinkerButton("Run!", "Blue");
        jLabelResults = new javax.swing.JLabel();
        saveResultsButton = new BlinkerButton("Save Result", "Blue");
        clearResultsMatrixButton = new BlinkerButton("Clear Results", "Red");
        propertiesButton = new BlinkerButton("Properties", "Green");
        loadModelButton = new BlinkerButton("Load Model", "Blue");
        animateNetworkCheckBox = new javax.swing.JCheckBox();
        outputViewScrollPane = new javax.swing.JScrollPane();
        viewNetworkPane = new javax.swing.JScrollPane();

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel28.setText("Simulator:");

        resultsTextArea.setColumns(20);
        resultsTextArea.setRows(5);
        jScrollPane7.setViewportView(resultsTextArea);

        runButton.setText("Run!");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        saveResultsButton.setText("Save Result");
        saveResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveResultsButtonActionPerformed(evt);
            }
        });

        clearResultsMatrixButton.setText("Clear Results");
        clearResultsMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearResultsMatrixButtonActionPerformed(evt);
            }
        });

        propertiesButton.setText(" Properties");
        propertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesButtonActionPerformed(evt);
            }
        });

        loadModelButton.setText("Load Model");
        loadModelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadModelButtonActionPerformed(evt);
            }
        });

        animateNetworkCheckBox.setText("Animate Network");
        animateNetworkCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animateNetworkCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout simulationControlPanelLayout = new javax.swing.GroupLayout(simulationControlPanel);
        simulationControlPanel.setLayout(simulationControlPanelLayout);
        simulationControlPanelLayout.setHorizontalGroup(
            simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simulationControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simulationControlPanelLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(35, 35, 35)
                        .addComponent(animateNetworkCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 389, Short.MAX_VALUE)
                        .addComponent(jLabelResults))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simulationControlPanelLayout.createSequentialGroup()
                        .addComponent(loadModelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(propertiesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveResultsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearResultsMatrixButton)))
                .addContainerGap())
        );

        simulationControlPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearResultsMatrixButton, propertiesButton, runButton, saveResultsButton});

        simulationControlPanelLayout.setVerticalGroup(
            simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simulationControlPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelResults)
                    .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(animateNetworkCheckBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadModelButton)
                    .addComponent(saveResultsButton)
                    .addComponent(clearResultsMatrixButton)
                    .addComponent(runButton)
                    .addComponent(propertiesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        simulationControlPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {clearResultsMatrixButton, loadModelButton, propertiesButton, runButton, saveResultsButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(outputViewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simulationControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(viewNetworkPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(viewNetworkPane, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputViewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(simulationControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
    {//GEN-HEADEREND:event_runButtonActionPerformed
        controller.getView().runSimulation();
}//GEN-LAST:event_runButtonActionPerformed

    private void saveResultsButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveResultsButtonActionPerformed
    {//GEN-HEADEREND:event_saveResultsButtonActionPerformed
        controller.getView().getOutputControllers().get(Globals.RESULTS_CONTROLLER).saveResults();
}//GEN-LAST:event_saveResultsButtonActionPerformed

    private void clearResultsMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearResultsMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearResultsMatrixButtonActionPerformed
        this.getResultsTextArea().setText("");
        controller.getView().getOutputStates().get(Globals.RESULTS_STATE).simulationLoaded();
        controller.getView().getOutputStates().get(Globals.RESULTS_STATE).propertiesLoaded();
        plot.removeAllPlots();

        plot.repaint();
}//GEN-LAST:event_clearResultsMatrixButtonActionPerformed

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_propertiesButtonActionPerformed
    {//GEN-HEADEREND:event_propertiesButtonActionPerformed
        PropertiesFrame propertiesFrame = new PropertiesFrame(controller.getView());
}//GEN-LAST:event_propertiesButtonActionPerformed

    private void loadModelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loadModelButtonActionPerformed
    {//GEN-HEADEREND:event_loadModelButtonActionPerformed
        controller.getView().setModelView();
    }//GEN-LAST:event_loadModelButtonActionPerformed

    private void animateNetworkCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_animateNetworkCheckBoxActionPerformed
    {//GEN-HEADEREND:event_animateNetworkCheckBoxActionPerformed
        if (animateNetworkCheckBox.isSelected())
        {
            controller.getView().getOutputStates().get(Globals.RESULTS_STATE).animate(true);
        } else
        {
            controller.getView().getOutputStates().get(Globals.RESULTS_STATE).animate(false);
        }
    }//GEN-LAST:event_animateNetworkCheckBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox animateNetworkCheckBox;
    private javax.swing.JButton clearResultsMatrixButton;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabelResults;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton loadModelButton;
    protected javax.swing.JScrollPane outputViewScrollPane;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JTextArea resultsTextArea;
    private javax.swing.JButton runButton;
    private javax.swing.JButton saveResultsButton;
    protected javax.swing.JPanel simulationControlPanel;
    private javax.swing.JScrollPane viewNetworkPane;
    // End of variables declaration//GEN-END:variables
}
