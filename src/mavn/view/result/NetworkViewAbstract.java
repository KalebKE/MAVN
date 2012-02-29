/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NetworkPanel.java
 *
 * Created on Feb 22, 2012, 9:05:26 AM
 */
package mavn.view.result;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import mavn.factory.AbstractSimulationFactory;
import mavn.globals.Globals;
import mavn.simModel.result.view.state.RngStateInterface;
import mavn.simModel.properties.view.PropertiesFrame;
import mavn.simModel.result.model.observer.AndLayerModelObserver;
import mavn.simModel.result.model.observer.DartsModelObserver;
import mavn.simModel.result.model.observer.ImageRatioModelObserver;
import mavn.simModel.result.model.observer.ModelResultModelObserver;
import mavn.simModel.result.model.observer.OrLayerModelObserver;
import mavn.simModel.result.model.observer.OutputLayerModelObserver;
import mavn.simModel.result.model.observer.ShapesRatioModelObserver;
import org.math.plot.Plot2DPanel;
import util.components.BlinkerButton;

/**
 *
 * @author Kaleb
 */
public abstract class NetworkViewAbstract extends javax.swing.JPanel implements
        NetworkViewInterface, ModelResultModelObserver, DartsModelObserver,
        AndLayerModelObserver, OrLayerModelObserver, OutputLayerModelObserver,
        ShapesRatioModelObserver, ImageRatioModelObserver
{

    protected double[][] w2 = new double[0][0];
    protected double[][] w1 = new double[0][0];
    protected double[][] w0 = new double[0][0];
    protected double[] andLayerResult;
    protected double[] orLayerResult;
    protected double[] outputLayerResult;
    protected Double[] shapesRatioResults;
    protected Double[] imageRatioResults;
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
        DefaultTableModel model = new DefaultTableModel();
        model.setNumRows(15);
        model.setColumnCount(10);
        resultTable.setModel(model);
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
        try
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
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModelResult(double[][] modelResult)
    {
        controller.getModelResultStates().get(Globals.RESULTS_STATE).resultAvailable();

        if (controller.getPropertiesState().isTarget())
        {
            double[][] data = modelResult;

            Object[][] d = new Object[data.length][];

            for (int i = 0; i < data.length; i++)
            {
                d[i] = new Object[data[i].length];

                for (int j = 0; j < data[i].length; j++)
                {

                    if (data[i][j] == 1)
                    {
                        d[i][j] = "Dart Hit Image: " + data[i][j] + " @ " + "X = " + controller.getModelInputControllers().get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput()[0][0]
                                + " Y = " + controller.getModelInputControllers().get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput()[1][0];
                    }
                    if (data[i][j] == -1)
                    {
                        d[i][j] = "Dart Missed Image: " + data[i][j] + " @ " + "X = " + controller.getModelInputControllers().get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput()[0][0]
                                + " Y = " + controller.getModelInputControllers().get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput()[1][0];
                    }
                }
            }
            String[] columnNames;
            columnNames = new String[data[0].length];

            int count = 0;
            for (String s : columnNames)
            {
                columnNames[count] = "" + count;
                count++;
            }

            DefaultTableModel model = new DefaultTableModel(d, columnNames);
            model.setNumRows(15);
            model.setColumnCount(1);
            resultTable.setModel(model);


            double[] x =
            {
                controller.getModelInputControllers().get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput()[0][0]
            };
            double[] y =
            {
                controller.getModelInputControllers().get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput()[1][0]
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

        if (controller.getPropertiesState().isDart())
        {  
            String[] columnNames = new String[10];

            int count = 0;
            for (String s : columnNames)
            {
                columnNames[count] = "" + count;
                count++;
            }

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Shapes Ratio", shapesRatioResults);
            model.addColumn("Image Ratio", imageRatioResults);
            resultTable.setModel(model);
        }
    }

    @Override
    public void updateAndLayerModelResult(double[][] matrix)
    {
        andLayerResult = new double[matrix.length];
        for (int i = 0; i < andLayerResult.length; i++)
        {
            andLayerResult[i] = matrix[i][0];
        }
    }

    @Override
    public void updateOrLayerModelResult(double[][] matrix)
    {
        orLayerResult = new double[matrix.length];
        for (int i = 0; i < orLayerResult.length; i++)
        {
            orLayerResult[i] = matrix[i][0];
        }
    }

    @Override
    public void updateOutputLayerModelResult(double[][] matrix)
    {
        outputLayerResult = new double[matrix.length];
        for (int i = 0; i < outputLayerResult.length; i++)
        {
            outputLayerResult[i] = matrix[i][0];
        }

        if (controller.getModelResultStates().get(Globals.RESULTS_STATE).isAnimated())
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

    @Override
    public void updateShapesRatioModelResult(double[][] shapesRatioResult)
    {
        shapesRatioResults = new Double[shapesRatioResult.length];
        for (int i = 0; i < shapesRatioResults.length; i++)
        {
            shapesRatioResults[i] = shapesRatioResult[i][0];
        }
    }

    @Override
    public void updateImageRatioModelResult(double[][] imageRatioResult)
    {
        imageRatioResults = new Double[imageRatioResult.length];
        for (int i = 0; i < imageRatioResults.length; i++)
        {
            imageRatioResults[i] = imageRatioResult[i][0];
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
        runButton = new BlinkerButton("Run!", "Blue");
        jLabelResults = new javax.swing.JLabel();
        saveResultsButton = new BlinkerButton("Save Result", "Blue");
        clearResultsMatrixButton = new BlinkerButton("Clear Results", "Red");
        propertiesButton = new BlinkerButton("Properties", "Green");
        loadModelButton = new BlinkerButton("Load Model", "Blue");
        animateNetworkCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        viewNetworkPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        outputViewScrollPane = new javax.swing.JScrollPane();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel28.setText("Simulator:");

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

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout simulationControlPanelLayout = new javax.swing.GroupLayout(simulationControlPanel);
        simulationControlPanel.setLayout(simulationControlPanelLayout);
        simulationControlPanelLayout.setHorizontalGroup(
            simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simulationControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(simulationControlPanelLayout.createSequentialGroup()
                        .addComponent(jLabelResults)
                        .addContainerGap(538, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simulationControlPanelLayout.createSequentialGroup()
                        .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(simulationControlPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addGap(35, 35, 35)
                                    .addComponent(animateNetworkCheckBox))
                                .addGroup(simulationControlPanelLayout.createSequentialGroup()
                                    .addComponent(loadModelButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(propertiesButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(runButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(saveResultsButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(clearResultsMatrixButton))))
                        .addGap(151, 151, 151))))
        );

        simulationControlPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearResultsMatrixButton, propertiesButton, runButton, saveResultsButton});

        simulationControlPanelLayout.setVerticalGroup(
            simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simulationControlPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelResults)
                    .addGroup(simulationControlPanelLayout.createSequentialGroup()
                        .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(animateNetworkCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(simulationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loadModelButton)
                            .addComponent(saveResultsButton)
                            .addComponent(clearResultsMatrixButton)
                            .addComponent(runButton)
                            .addComponent(propertiesButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228))
        );

        simulationControlPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {clearResultsMatrixButton, loadModelButton, propertiesButton, runButton, saveResultsButton});

        jRadioButton1.setText("View Option #1");
        jRadioButton1.setEnabled(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("View Option #2");
        jRadioButton2.setEnabled(false);

        jRadioButton3.setText("View Option #3");
        jRadioButton3.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(outputViewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3))
                    .addComponent(outputViewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simulationControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
            .addComponent(viewNetworkPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(viewNetworkPane, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simulationControlPanel, 0, 305, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
    {//GEN-HEADEREND:event_runButtonActionPerformed
        controller.getView().runSimulation();
}//GEN-LAST:event_runButtonActionPerformed

    private void saveResultsButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveResultsButtonActionPerformed
    {//GEN-HEADEREND:event_saveResultsButtonActionPerformed
        controller.getModelResultControllers().get(Globals.RESULTS_CONTROLLER).saveResults();
}//GEN-LAST:event_saveResultsButtonActionPerformed

    private void clearResultsMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearResultsMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearResultsMatrixButtonActionPerformed

        controller.getModelResultStates().get(Globals.RESULTS_STATE).simulationLoaded();
        controller.getModelResultStates().get(Globals.RESULTS_STATE).propertiesLoaded();
        plot.removeAllPlots();

        plot.repaint();
}//GEN-LAST:event_clearResultsMatrixButtonActionPerformed

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_propertiesButtonActionPerformed
    {//GEN-HEADEREND:event_propertiesButtonActionPerformed
        controller.getPropertiesFrame().setVisible(true);
}//GEN-LAST:event_propertiesButtonActionPerformed

    private void loadModelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loadModelButtonActionPerformed
    {//GEN-HEADEREND:event_loadModelButtonActionPerformed
        controller.getView().setModelView();
    }//GEN-LAST:event_loadModelButtonActionPerformed

    private void animateNetworkCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_animateNetworkCheckBoxActionPerformed
    {//GEN-HEADEREND:event_animateNetworkCheckBoxActionPerformed
        if (animateNetworkCheckBox.isSelected())
        {
            controller.getModelResultStates().get(Globals.RESULTS_STATE).animate(true);
        } else
        {
            controller.getModelResultStates().get(Globals.RESULTS_STATE).animate(false);
        }
    }//GEN-LAST:event_animateNetworkCheckBoxActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton1ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox animateNetworkCheckBox;
    private javax.swing.JButton clearResultsMatrixButton;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabelResults;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadModelButton;
    protected javax.swing.JScrollPane outputViewScrollPane;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JTable resultTable;
    private javax.swing.JButton runButton;
    private javax.swing.JButton saveResultsButton;
    protected javax.swing.JPanel simulationControlPanel;
    private javax.swing.JScrollPane viewNetworkPane;
    // End of variables declaration//GEN-END:variables
}
