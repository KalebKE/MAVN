/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlBar.java
 *
 * Created on Mar 26, 2012, 1:21:21 PM
 */
package mavn.simModel.output.view.layoutPanel;

import components.ControlButton;
import components.ControlToggleButton;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Kaleb
 */
public class ControlBar extends javax.swing.JPanel
{

    private ActionListener simulationBarAction;
    private ActionListener propertiesBarAction;
    private ActionListener modelOuputBarAction;
    private ActionListener viewBarAction;
    private ActionListener newtorkViewBarAction;
    private ActionListener plotViewBarAction;
    private ActionListener runSimulationAction;
    private JButton importSimulationButton;
    private JButton saveSimulationButton;
    private JButton clearSimulationButton;
    private JButton simulationPropertiesButton;
    private JButton clearModelOutputButton;
    private JButton saveModelOutputButton;
    private JButton clearPlotButton;
    private JButton runSimulationButton;
    private JButton resetSimulationButton;
    private JButton clearNetworkButton;
    private JToggleButton targetSimulationButton;
    private JToggleButton monteCarloSimulationButton;
    private JToggleButton gridSimulationButton;
    private JToggleButton outputViewButton;
    private JToggleButton inputViewButton;
    private JToggleButton animateSimulationButton;
    private JToggleButton renderSimulationButton;
    private JToggleButton scatterPlotButton;
    private JToggleButton linePlotButton;

    /** Creates new form ControlBar */
    public ControlBar(ActionListener simulationBarAction, ActionListener propertiesBarAction,
            ActionListener modelOuputBarAction, ActionListener viewBarAction,
            ActionListener newtorkViewBarAction, ActionListener plotViewBarAction,
            ActionListener runSimulationAction)
    {
        initComponents();

        this.simulationBarAction = simulationBarAction;
        this.propertiesBarAction = propertiesBarAction;
        this.modelOuputBarAction = modelOuputBarAction;
        this.newtorkViewBarAction = newtorkViewBarAction;
        this.viewBarAction = viewBarAction;
        this.plotViewBarAction = plotViewBarAction;
        this.runSimulationAction = runSimulationAction;

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
        simulationControlBar.setLayout(new BoxLayout(simulationControlBar, BoxLayout.LINE_AXIS));
        modelControlBar.setLayout(new BoxLayout(modelControlBar, BoxLayout.LINE_AXIS));
        viewControlBar.setLayout(new BoxLayout(viewControlBar, BoxLayout.LINE_AXIS));
        networkControlBar.setLayout(new BoxLayout(networkControlBar, BoxLayout.LINE_AXIS));
        plotControlBar.setLayout(new BoxLayout(plotControlBar, BoxLayout.LINE_AXIS));
        runSimulationControlBar.setLayout(new BoxLayout(runSimulationControlBar, BoxLayout.LINE_AXIS));
        outputControlBar.setLayout(new BoxLayout(outputControlBar, BoxLayout.LINE_AXIS));

        initButtons();
        initActions();
    }

    public JToggleButton getAnimateSimulationButton()
    {
        return animateSimulationButton;
    }

    public JButton getClearModelOutputButton()
    {
        return clearModelOutputButton;
    }

    public JButton getClearNetworkButton()
    {
        return clearNetworkButton;
    }

    public JButton getClearPlotButton()
    {
        return clearPlotButton;
    }

    public JButton getClearSimulationButton()
    {
        return clearSimulationButton;
    }

    public JPanel getControlPanel()
    {
        return controlPanel;
    }

    public JToggleButton getGridSimulationButton()
    {
        return gridSimulationButton;
    }

    public JButton getImportSimulationButton()
    {
        return importSimulationButton;
    }

    public JToggleButton getInputViewButton()
    {
        return inputViewButton;
    }

    public JToggleButton getLinePlotButton()
    {
        return linePlotButton;
    }

    public JToggleButton getMonteCarloSimulationButton()
    {
        return monteCarloSimulationButton;
    }

    public JToggleButton getOutputViewButton()
    {
        return outputViewButton;
    }

    public JToggleButton getRenderSimulationButton()
    {
        return renderSimulationButton;
    }

    public JButton getResetSimulationButton()
    {
        return resetSimulationButton;
    }

    public JButton getRunSimulationButton()
    {
        return runSimulationButton;
    }

    public JButton getSaveModelOutputButton()
    {
        return saveModelOutputButton;
    }

    public JButton getSaveSimulationButton()
    {
        return saveSimulationButton;
    }

    public JToggleButton getScatterPlotButton()
    {
        return scatterPlotButton;
    }

    public JButton getSimulationPropertiesButton()
    {
        return simulationPropertiesButton;
    }

    public JToggleButton getTargetSimulationButton()
    {
        return targetSimulationButton;
    }

    public void initButtons()
    {

        importSimulationButton = new ControlButton(getIcon("/icons/importSimulation.png"));
        simulationControlBar.add(importSimulationButton);

        saveSimulationButton = new ControlButton(getIcon("/icons/saveSimulation.png"));
        simulationControlBar.add(saveSimulationButton);

        clearSimulationButton = new ControlButton(getIcon("/icons/clearSimulation.png"));
        simulationControlBar.add(clearSimulationButton);

        simulationPropertiesButton = new ControlButton(getIcon("/icons/simulationProperties.png"));
        modelControlBar.add(simulationPropertiesButton);

        targetSimulationButton = new ControlToggleButton(getIcon("/icons/target.png"));
        modelControlBar.add(targetSimulationButton);

        monteCarloSimulationButton = new ControlToggleButton(getIcon("/icons/monteCarlo.png"));
        modelControlBar.add(monteCarloSimulationButton);

        gridSimulationButton = new ControlToggleButton(getIcon("/icons/gridSimulation.png"));
        modelControlBar.add(gridSimulationButton);

        clearModelOutputButton = new ControlButton(getIcon("/icons/clearSimulationOutput.png"));
        outputControlBar.add(clearModelOutputButton);

        saveModelOutputButton = new ControlButton(getIcon("/icons/saveModel.png"));
        outputControlBar.add(saveModelOutputButton);

        JToggleButton.ToggleButtonModel ouputToggleModel = new JToggleButton.ToggleButtonModel();
        outputViewButton = new ControlToggleButton(getIcon("/icons/outputView.png"));
        outputViewButton.setModel(ouputToggleModel);
        outputViewButton.getModel().setPressed(true);
        outputViewButton.getModel().setSelected(true);
        viewControlBar.add(outputViewButton);

        JToggleButton.ToggleButtonModel inputToggleModel = new JToggleButton.ToggleButtonModel();
        inputViewButton = new ControlToggleButton(getIcon("/icons/inputView.png"));
        inputViewButton.setModel(inputToggleModel);
        inputViewButton.getModel().setPressed(false);
        inputViewButton.getModel().setSelected(false);
        viewControlBar.add(inputViewButton);

        animateSimulationButton = new ControlToggleButton(getIcon("/icons/animateSimulation.png"));
        networkControlBar.add(animateSimulationButton);

        renderSimulationButton = new ControlToggleButton(getIcon("/icons/renderSimulation.png"));
        networkControlBar.add(renderSimulationButton);

        clearNetworkButton = new ControlButton(getIcon("/icons/clearNetwork.png"));
        networkControlBar.add(clearNetworkButton);

        scatterPlotButton = new ControlToggleButton(getIcon("/icons/scatterPlot.png"));
        plotControlBar.add(scatterPlotButton);

        linePlotButton = new ControlToggleButton(getIcon("/icons/linePlot.png"));
        plotControlBar.add(linePlotButton);

        clearPlotButton = new ControlButton(getIcon("/icons/clearPlot.png"));
        plotControlBar.add(clearPlotButton);

        runSimulationButton = new ControlButton(getIcon("/icons/runSimulation.png"));
        runSimulationControlBar.add(runSimulationButton);

        resetSimulationButton = new ControlButton(getIcon("/icons/resetSimulation.png"));
        runSimulationControlBar.add(resetSimulationButton);
    }

    public void initActions()
    {
        // Simulation Bar Actions
        importSimulationButton.addActionListener(this.simulationBarAction);
        importSimulationButton.setActionCommand("importSimulationAction");
        saveSimulationButton.addActionListener(this.simulationBarAction);
        saveSimulationButton.setActionCommand("exportSimulationAction");
        clearSimulationButton.addActionListener(this.simulationBarAction);
        clearSimulationButton.setActionCommand("clearSimluationAction");

        // Simulation Properties Bar Actions
        simulationPropertiesButton.addActionListener(this.propertiesBarAction);
        simulationPropertiesButton.setActionCommand("loadPropertiesAction");
        targetSimulationButton.addActionListener(this.propertiesBarAction);
        targetSimulationButton.setActionCommand("useTargetSimulationAction");
        monteCarloSimulationButton.addActionListener(this.propertiesBarAction);
        monteCarloSimulationButton.setActionCommand("useMonteCaroloSimuationAction");
        gridSimulationButton.addActionListener(this.propertiesBarAction);
        gridSimulationButton.setActionCommand("useGridSimulationAction");

        // Simulation View Action
        outputViewButton.addActionListener(this.viewBarAction);
        outputViewButton.setActionCommand("useOutputViewAction");
        inputViewButton.addActionListener(this.viewBarAction);
        inputViewButton.setActionCommand("useInputViewAction");

        // Outupt View Actions
        clearModelOutputButton.addActionListener(this.modelOuputBarAction);
        clearModelOutputButton.setActionCommand("clearOutputAction");
        saveModelOutputButton.addActionListener(this.modelOuputBarAction);
        saveModelOutputButton.setActionCommand("saveOutputAction");

        // Network View Action
        animateSimulationButton.addActionListener(this.newtorkViewBarAction);
        animateSimulationButton.setActionCommand("animateNetworkAction");
        renderSimulationButton.addActionListener(this.newtorkViewBarAction);
        renderSimulationButton.setActionCommand("renderNetworkAction");
        clearNetworkButton.addActionListener(this.newtorkViewBarAction);
        clearNetworkButton.setActionCommand("clearNetworkAction");

        // Plot View Action
        scatterPlotButton.addActionListener(this.plotViewBarAction);
        scatterPlotButton.setActionCommand("useScatterPlotAction");
        linePlotButton.addActionListener(this.plotViewBarAction);
        linePlotButton.setActionCommand("useLinePlotAction");
        clearPlotButton.addActionListener(this.plotViewBarAction);
        clearPlotButton.setActionCommand("clearPlotAction");

        // Run Simulation Action
        runSimulationButton.addActionListener(this.runSimulationAction);
        runSimulationButton.setActionCommand("runSimulationAction");
        resetSimulationButton.addActionListener(this.runSimulationAction);
        resetSimulationButton.setActionCommand("resetSimulationAction");
    }

    public ImageIcon getIcon(String iconPath)
    {
        // Set Icon for button
        ImageIcon icon = createImageIcon(iconPath, "");
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg, "");

        return newIcon;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon createImageIcon(String path,
            String description)
    {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null)
        {
            return new ImageIcon(imgURL, description);
        } else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
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

        controlPanel = new javax.swing.JPanel();
        simulationControlBar = new javax.swing.JPanel();
        modelControlBar = new javax.swing.JPanel();
        viewControlBar = new javax.swing.JPanel();
        networkControlBar = new javax.swing.JPanel();
        plotControlBar = new javax.swing.JPanel();
        runSimulationControlBar = new javax.swing.JPanel();
        outputControlBar = new javax.swing.JPanel();

        controlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        simulationControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Import/Export Simulation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        javax.swing.GroupLayout simulationControlBarLayout = new javax.swing.GroupLayout(simulationControlBar);
        simulationControlBar.setLayout(simulationControlBarLayout);
        simulationControlBarLayout.setHorizontalGroup(
            simulationControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        simulationControlBarLayout.setVerticalGroup(
            simulationControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        modelControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        javax.swing.GroupLayout modelControlBarLayout = new javax.swing.GroupLayout(modelControlBar);
        modelControlBar.setLayout(modelControlBarLayout);
        modelControlBarLayout.setHorizontalGroup(
            modelControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 132, Short.MAX_VALUE)
        );
        modelControlBarLayout.setVerticalGroup(
            modelControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        viewControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "View", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        javax.swing.GroupLayout viewControlBarLayout = new javax.swing.GroupLayout(viewControlBar);
        viewControlBar.setLayout(viewControlBarLayout);
        viewControlBarLayout.setHorizontalGroup(
            viewControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        viewControlBarLayout.setVerticalGroup(
            viewControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        networkControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Network", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        javax.swing.GroupLayout networkControlBarLayout = new javax.swing.GroupLayout(networkControlBar);
        networkControlBar.setLayout(networkControlBarLayout);
        networkControlBarLayout.setHorizontalGroup(
            networkControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        networkControlBarLayout.setVerticalGroup(
            networkControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        plotControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Plot", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        javax.swing.GroupLayout plotControlBarLayout = new javax.swing.GroupLayout(plotControlBar);
        plotControlBar.setLayout(plotControlBarLayout);
        plotControlBarLayout.setHorizontalGroup(
            plotControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        plotControlBarLayout.setVerticalGroup(
            plotControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        runSimulationControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Run Simulation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        javax.swing.GroupLayout runSimulationControlBarLayout = new javax.swing.GroupLayout(runSimulationControlBar);
        runSimulationControlBar.setLayout(runSimulationControlBarLayout);
        runSimulationControlBarLayout.setHorizontalGroup(
            runSimulationControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );
        runSimulationControlBarLayout.setVerticalGroup(
            runSimulationControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        outputControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        javax.swing.GroupLayout outputControlBarLayout = new javax.swing.GroupLayout(outputControlBar);
        outputControlBar.setLayout(outputControlBarLayout);
        outputControlBarLayout.setHorizontalGroup(
            outputControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        outputControlBarLayout.setVerticalGroup(
            outputControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addComponent(simulationControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modelControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runSimulationControlBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(networkControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plotControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(runSimulationControlBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modelControlBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewControlBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(simulationControlBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(plotControlBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(networkControlBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(outputControlBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel modelControlBar;
    private javax.swing.JPanel networkControlBar;
    private javax.swing.JPanel outputControlBar;
    private javax.swing.JPanel plotControlBar;
    private javax.swing.JPanel runSimulationControlBar;
    private javax.swing.JPanel simulationControlBar;
    private javax.swing.JPanel viewControlBar;
    // End of variables declaration//GEN-END:variables
}
