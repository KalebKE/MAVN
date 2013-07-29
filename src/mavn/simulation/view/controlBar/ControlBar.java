/*
 ControlBar -- A class within the Machine Artificial Vision
 Network(Machine Artificial Vision Network).
 Copyright (C) 2012, Kaleb Kircher.

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package mavn.simulation.view.controlBar;

import java.awt.Font;
import simulyn.ui.components.ControlToggleButton;
import simulyn.ui.components.ControlButton;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * The Control Bar for the MAVN Simulation.
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
    
    private JButton importSimulationButton;
    private JButton saveSimulationButton;
    private JButton clearSimulationButton;
    private JButton simulationPropertiesButton;
    private JButton clearModelOutputButton;
    private JButton saveModelOutputButton;
    private JButton clearPlotButton;

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

    /**
     * Initialize a Control Bar.
     *
     * @param simulationBarAction the Simulation Bar ActionListener.
     * @param propertiesBarAction the Properties Bar ActionListener.
     * @param modelOuputBarAction the Output Bar ActionListner.
     * @param viewBarAction the View Bar ActionListener.
     * @param newtorkViewBarAction the Network Bar ActionListener.
     * @param plotViewBarAction the Plot Bar ActionListener.
     * @param runSimulationAction the Run Simulation Bar ActionListener.
     */
    public ControlBar(ActionListener simulationBarAction, ActionListener propertiesBarAction,
            ActionListener modelOuputBarAction, ActionListener viewBarAction,
            ActionListener newtorkViewBarAction, ActionListener plotViewBarAction)
    {
        initComponents();

        this.simulationBarAction = simulationBarAction;
        this.propertiesBarAction = propertiesBarAction;
        this.modelOuputBarAction = modelOuputBarAction;
        this.newtorkViewBarAction = newtorkViewBarAction;
        this.viewBarAction = viewBarAction;
        this.plotViewBarAction = plotViewBarAction;
        
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
        simulationControlBar.setLayout(new BoxLayout(simulationControlBar, BoxLayout.LINE_AXIS));
        viewControlBar.setLayout(new BoxLayout(viewControlBar, BoxLayout.LINE_AXIS));

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

    private void initButtons()
    {
        importSimulationButton = new ControlButton(getIcon("/icons/import_simulation_icon.png"));
        importSimulationButton.setText("Load Simulation");
        importSimulationButton.setFont(new Font("Roboto", Font.BOLD, 12));
        simulationControlBar.add(importSimulationButton);

        saveSimulationButton = new ControlButton(getIcon("/icons/export_simulation_icon.png"));
        saveSimulationButton.setText("Save Simulation");
        saveSimulationButton.setFont(new Font("Roboto", Font.BOLD, 12));
        simulationControlBar.add(saveSimulationButton);

        clearSimulationButton = new ControlButton(getIcon("/icons/clear_simulation_icon.png"));
        clearSimulationButton.setText("Clear Simulation");
        clearSimulationButton.setFont(new Font("Roboto", Font.BOLD, 12));
        simulationControlBar.add(clearSimulationButton);

        simulationPropertiesButton = new ControlButton(getIcon("/icons/simulationProperties.png"));

        targetSimulationButton = new ControlToggleButton(getIcon("/icons/target.png"));

        monteCarloSimulationButton = new ControlToggleButton(getIcon("/icons/monteCarlo.png"));

        gridSimulationButton = new ControlToggleButton(getIcon("/icons/gridSimulation.png"));

        clearModelOutputButton = new ControlButton(getIcon("/icons/clearSimulationOutput.png"));

        saveModelOutputButton = new ControlButton(getIcon("/icons/saveModel.png"));

        JToggleButton.ToggleButtonModel ouputToggleModel = new JToggleButton.ToggleButtonModel();
       
        outputViewButton = new ControlToggleButton(getIcon("/icons/network_view_icon.png"));
        outputViewButton.setText("Network View");
        outputViewButton.setFont(new Font("Roboto", Font.BOLD, 12));
        outputViewButton.setModel(ouputToggleModel);
        outputViewButton.getModel().setPressed(true);
        outputViewButton.getModel().setSelected(true);
        viewControlBar.add(outputViewButton);

        JToggleButton.ToggleButtonModel inputToggleModel = new JToggleButton.ToggleButtonModel();
        inputViewButton = new ControlToggleButton(getIcon("/icons/spread_sheet_view_icon.png"));
        inputViewButton.setText("Spreadsheet View");
        inputViewButton.setFont(new Font("Roboto", Font.BOLD, 12));
        inputViewButton.setModel(inputToggleModel);
        inputViewButton.getModel().setPressed(false);
        inputViewButton.getModel().setSelected(false);
        viewControlBar.add(inputViewButton);

        animateSimulationButton = new ControlToggleButton(getIcon("/icons/animateSimulation.png"));

        renderSimulationButton = new ControlToggleButton(getIcon("/icons/renderSimulation.png"));

        clearNetworkButton = new ControlButton(getIcon("/icons/clearNetwork.png"));

        scatterPlotButton = new ControlToggleButton(getIcon("/icons/scatterPlot.png"));

        linePlotButton = new ControlToggleButton(getIcon("/icons/linePlot.png"));

        clearPlotButton = new ControlButton(getIcon("/icons/clearPlot.png"));
    }

    private void initActions()
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
    }

    /**
     * Get an Image Icon from the path.
     *
     * @param iconPath the path of the desired image.
     * @return an Image Icon from the path.
     */
    public ImageIcon getIcon(String iconPath)
    {
        // Set Icon for button
        ImageIcon icon = createImageIcon(iconPath, "");
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg, "");

        return newIcon;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        controlPanel = new javax.swing.JPanel();
        simulationControlBar = new javax.swing.JPanel();
        viewControlBar = new javax.swing.JPanel();

        simulationControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Simulation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N

        javax.swing.GroupLayout simulationControlBarLayout = new javax.swing.GroupLayout(simulationControlBar);
        simulationControlBar.setLayout(simulationControlBarLayout);
        simulationControlBarLayout.setHorizontalGroup(
            simulationControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        simulationControlBarLayout.setVerticalGroup(
            simulationControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        viewControlBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Views", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N

        javax.swing.GroupLayout viewControlBarLayout = new javax.swing.GroupLayout(viewControlBar);
        viewControlBar.setLayout(viewControlBarLayout);
        viewControlBarLayout.setHorizontalGroup(
            viewControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        viewControlBarLayout.setVerticalGroup(
            viewControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addComponent(simulationControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(562, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(simulationControlBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewControlBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JPanel simulationControlBar;
    private javax.swing.JPanel viewControlBar;
    // End of variables declaration//GEN-END:variables
}
