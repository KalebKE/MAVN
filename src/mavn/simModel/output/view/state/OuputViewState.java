/*
ResultsState -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.output.view.state;

import mavn.simModel.output.view.layoutPanel.ControlBar;

/**
 * A class that maintains all of the state required for the simulations output.
 * This class is an implementation of the applications State Pattern.
 * @author Kaleb
 */
public class OuputViewState implements OutputViewStateInterface
{

    private boolean animated;
    private boolean propertiesLoaded;
    private boolean simulationLoaded;
    private boolean resultAvailable;
    private ControlBar outputViewBar;
    private ControlBar inputViewBar;

    /**
     * Initialize the state.
     * @param view the view the class manages the state for.
     */
    public OuputViewState(ControlBar outputViewBar, ControlBar inputViewBar)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;

        propertiesLoaded = false;
        animated = true;

        this.propertiesUnloaded();
        this.simulationUnloaded();
        this.animate(animated);
        this.onMonteCarloSimulation();
        this.onScatterPlot();
    }

    /**
     * Check if the simulation has been run.
     * @return boolean indicating a simulation has been run.
     */
    @Override
    public boolean isResultAvailable()
    {
        return resultAvailable;
    }

    /**
     * Check to see if the simulation properties have been loaded.
     * @return boolean indicating loaded simulation properties.
     */
    @Override
    public boolean isPropertiesLoaded()
    {
        return propertiesLoaded;
    }

    /**
     * Check to see if a simulation has been loaded.
     * @return boolean indicating a simulation has been loaded.
     */
    @Override
    public boolean isSimulationLoaded()
    {
        return simulationLoaded;
    }

    /**
     * Inidcate that the simulation properties have been loaded.
     */
    @Override
    public void propertiesLoaded()
    {
        // Disable these buttons
        outputViewBar.getRunSimulationButton().setEnabled(true);
        inputViewBar.getRunSimulationButton().setEnabled(true);
        propertiesLoaded = true;
    }

    /**
     * Inidicate that the simulation properties have not been loaded.
     */
    @Override
    public void propertiesUnloaded()
    {
        outputViewBar.getSimulationPropertiesButton().setEnabled(true);
        // Disable these buttons
        outputViewBar.getRunSimulationButton().setEnabled(false);
        outputViewBar.getSaveModelOutputButton().setEnabled(false);
        outputViewBar.getClearModelOutputButton().setEnabled(false);

        inputViewBar.getSimulationPropertiesButton().setEnabled(true);
        // Disable these buttons
        inputViewBar.getRunSimulationButton().setEnabled(false);
        inputViewBar.getSaveModelOutputButton().setEnabled(false);
        inputViewBar.getClearModelOutputButton().setEnabled(false);

        propertiesLoaded = false;
        resultAvailable = false;
    }

    /**
     * Indicate that the simulation has been run.
     */
    @Override
    public void resultAvailable(boolean available)
    {
        if (available)
        {
            // Disable these buttons
            outputViewBar.getRunSimulationButton().setEnabled(true);
            outputViewBar.getSaveModelOutputButton().setEnabled(true);
            outputViewBar.getClearModelOutputButton().setEnabled(true);

            // Disable these buttons
            inputViewBar.getRunSimulationButton().setEnabled(true);
            inputViewBar.getSaveModelOutputButton().setEnabled(true);
            inputViewBar.getClearModelOutputButton().setEnabled(true);

            resultAvailable = true;
        }
        if (!available)
        {
            // Disable these buttons
            outputViewBar.getRunSimulationButton().setEnabled(false);
            outputViewBar.getSaveModelOutputButton().setEnabled(false);
            outputViewBar.getClearModelOutputButton().setEnabled(false);

            // Disable these buttons
            inputViewBar.getRunSimulationButton().setEnabled(false);
            inputViewBar.getSaveModelOutputButton().setEnabled(false);
            inputViewBar.getClearModelOutputButton().setEnabled(false);

            resultAvailable = false;
        }
    }

    /**
     * Indicate that a simulation has been loaded.
     */
    @Override
    public void simulationLoaded()
    {
        // Disable these buttons
        outputViewBar.getSimulationPropertiesButton().setEnabled(true);
        outputViewBar.getSaveSimulationButton().setEnabled(true);
        outputViewBar.getClearSimulationButton().setEnabled(true);
        outputViewBar.getMonteCarloSimulationButton().setEnabled(true);
        outputViewBar.getTargetSimulationButton().setEnabled(true);
        outputViewBar.getGridSimulationButton().setEnabled(true);
        outputViewBar.getAnimateSimulationButton().setEnabled(true);
        outputViewBar.getClearNetworkButton().setEnabled(true);
        outputViewBar.getClearPlotButton().setEnabled(true);
        outputViewBar.getScatterPlotButton().setEnabled(true);
        outputViewBar.getRenderSimulationButton().setEnabled(true);
        outputViewBar.getLinePlotButton().setEnabled(true);
        outputViewBar.getRunSimulationButton().setEnabled(true);

        outputViewBar.getMonteCarloSimulationButton().getModel().setPressed(true);
        outputViewBar.getMonteCarloSimulationButton().getModel().setSelected(true);

        // Disable these buttons
        inputViewBar.getSimulationPropertiesButton().setEnabled(true);
        inputViewBar.getSaveSimulationButton().setEnabled(true);
        inputViewBar.getClearSimulationButton().setEnabled(true);
        inputViewBar.getMonteCarloSimulationButton().setEnabled(true);
        inputViewBar.getTargetSimulationButton().setEnabled(true);
        inputViewBar.getGridSimulationButton().setEnabled(true);
        inputViewBar.getAnimateSimulationButton().setEnabled(true);
        inputViewBar.getClearNetworkButton().setEnabled(true);
        inputViewBar.getClearPlotButton().setEnabled(true);
        inputViewBar.getScatterPlotButton().setEnabled(true);
        inputViewBar.getRenderSimulationButton().setEnabled(true);
        inputViewBar.getLinePlotButton().setEnabled(true);
        inputViewBar.getRunSimulationButton().setEnabled(true);

        inputViewBar.getMonteCarloSimulationButton().getModel().setPressed(true);
        inputViewBar.getMonteCarloSimulationButton().getModel().setSelected(true);
    }

    /**
     * Indicate that a simulation has not been loaded.
     */
    @Override
    public void simulationUnloaded()
    {
        outputViewBar.getClearSimulationButton().setEnabled(false);
        outputViewBar.getSaveSimulationButton().setEnabled(false);

        outputViewBar.getClearModelOutputButton().setEnabled(false);
        outputViewBar.getMonteCarloSimulationButton().setEnabled(false);
        outputViewBar.getTargetSimulationButton().setEnabled(false);
        outputViewBar.getGridSimulationButton().setEnabled(false);

        outputViewBar.getResetSimulationButton().setEnabled(false);

        outputViewBar.getAnimateSimulationButton().setEnabled(false);
        outputViewBar.getClearNetworkButton().setEnabled(false);
        outputViewBar.getClearPlotButton().setEnabled(false);
        outputViewBar.getScatterPlotButton().setEnabled(false);
        outputViewBar.getRenderSimulationButton().setEnabled(false);
        outputViewBar.getLinePlotButton().setEnabled(false);

        outputViewBar.getSimulationPropertiesButton().setEnabled(false);
        outputViewBar.getRunSimulationButton().setEnabled(false);
        outputViewBar.getSaveModelOutputButton().setEnabled(false);
        outputViewBar.getSaveModelOutputButton().setEnabled(false);

        inputViewBar.getClearSimulationButton().setEnabled(false);
        inputViewBar.getSaveSimulationButton().setEnabled(false);

        inputViewBar.getClearModelOutputButton().setEnabled(false);
        inputViewBar.getMonteCarloSimulationButton().setEnabled(false);
        inputViewBar.getTargetSimulationButton().setEnabled(false);
        inputViewBar.getGridSimulationButton().setEnabled(false);

        inputViewBar.getResetSimulationButton().setEnabled(false);

        inputViewBar.getAnimateSimulationButton().setEnabled(false);
        inputViewBar.getClearNetworkButton().setEnabled(false);
        inputViewBar.getClearPlotButton().setEnabled(false);
        inputViewBar.getScatterPlotButton().setEnabled(false);
        inputViewBar.getRenderSimulationButton().setEnabled(false);
        inputViewBar.getLinePlotButton().setEnabled(false);

        inputViewBar.getSimulationPropertiesButton().setEnabled(false);
        inputViewBar.getRunSimulationButton().setEnabled(false);
        inputViewBar.getSaveModelOutputButton().setEnabled(false);
        inputViewBar.getSaveModelOutputButton().setEnabled(false);

        simulationLoaded = false;
    }

    @Override
    public void animate(boolean animate)
    {
        this.animated = animate;
        if (animate)
        {
            outputViewBar.getAnimateSimulationButton().getModel().setPressed(true);
            outputViewBar.getAnimateSimulationButton().getModel().setSelected(true);

            inputViewBar.getAnimateSimulationButton().getModel().setPressed(true);
            inputViewBar.getAnimateSimulationButton().getModel().setSelected(true);

            outputViewBar.getRenderSimulationButton().getModel().setPressed(false);
            outputViewBar.getRenderSimulationButton().getModel().setSelected(false);

            inputViewBar.getRenderSimulationButton().getModel().setPressed(false);
            inputViewBar.getRenderSimulationButton().getModel().setSelected(false);
        }
        if (!animate)
        {
            outputViewBar.getRenderSimulationButton().getModel().setPressed(true);
            outputViewBar.getRenderSimulationButton().getModel().setSelected(true);

            inputViewBar.getRenderSimulationButton().getModel().setPressed(true);
            inputViewBar.getRenderSimulationButton().getModel().setSelected(true);

            outputViewBar.getAnimateSimulationButton().getModel().setPressed(false);
            outputViewBar.getAnimateSimulationButton().getModel().setSelected(false);

            inputViewBar.getAnimateSimulationButton().getModel().setPressed(false);
            inputViewBar.getAnimateSimulationButton().getModel().setSelected(false);
        }
    }

    @Override
    public boolean isAnimated()
    {
        return animated;
    }

    @Override
    public void onOutputView()
    {
        this.outputViewBar.getOutputViewButton().getModel().setPressed(true);
        this.outputViewBar.getOutputViewButton().getModel().setSelected(true);
        this.outputViewBar.getInputViewButton().getModel().setPressed(false);
        this.outputViewBar.getInputViewButton().getModel().setSelected(false);

        this.inputViewBar.getOutputViewButton().getModel().setPressed(true);
        this.inputViewBar.getOutputViewButton().getModel().setSelected(true);
        this.inputViewBar.getInputViewButton().getModel().setPressed(false);
        this.inputViewBar.getInputViewButton().getModel().setSelected(false);
    }

    @Override
    public void onInputView()
    {
        this.outputViewBar.getOutputViewButton().getModel().setPressed(false);
        this.outputViewBar.getOutputViewButton().getModel().setSelected(false);
        this.outputViewBar.getInputViewButton().getModel().setPressed(true);
        this.outputViewBar.getInputViewButton().getModel().setSelected(true);

        this.inputViewBar.getOutputViewButton().getModel().setPressed(false);
        this.inputViewBar.getOutputViewButton().getModel().setSelected(false);
        this.inputViewBar.getInputViewButton().getModel().setPressed(true);
        this.inputViewBar.getInputViewButton().getModel().setSelected(true);
    }

    @Override
    public void onMonteCarloSimulation()
    {
        this.outputViewBar.getMonteCarloSimulationButton().getModel().setPressed(true);
        this.outputViewBar.getMonteCarloSimulationButton().getModel().setSelected(true);

        this.outputViewBar.getGridSimulationButton().getModel().setPressed(false);
        this.outputViewBar.getGridSimulationButton().getModel().setSelected(false);

        this.outputViewBar.getTargetSimulationButton().getModel().setPressed(false);
        this.outputViewBar.getTargetSimulationButton().getModel().setSelected(false);

        this.inputViewBar.getMonteCarloSimulationButton().getModel().setPressed(true);
        this.inputViewBar.getMonteCarloSimulationButton().getModel().setSelected(true);

        this.inputViewBar.getGridSimulationButton().getModel().setPressed(false);
        this.inputViewBar.getGridSimulationButton().getModel().setSelected(false);

        this.inputViewBar.getTargetSimulationButton().getModel().setPressed(false);
        this.inputViewBar.getTargetSimulationButton().getModel().setSelected(false);
    }

    @Override
    public void onTargetSimluation()
    {
        this.outputViewBar.getMonteCarloSimulationButton().getModel().setPressed(false);
        this.outputViewBar.getMonteCarloSimulationButton().getModel().setSelected(false);

        this.outputViewBar.getGridSimulationButton().getModel().setPressed(false);
        this.outputViewBar.getGridSimulationButton().getModel().setSelected(false);

        this.outputViewBar.getTargetSimulationButton().getModel().setPressed(true);
        this.outputViewBar.getTargetSimulationButton().getModel().setSelected(true);

        this.inputViewBar.getMonteCarloSimulationButton().getModel().setPressed(false);
        this.inputViewBar.getMonteCarloSimulationButton().getModel().setSelected(false);

        this.inputViewBar.getGridSimulationButton().getModel().setPressed(false);
        this.inputViewBar.getGridSimulationButton().getModel().setSelected(false);

        this.inputViewBar.getTargetSimulationButton().getModel().setPressed(true);
        this.inputViewBar.getTargetSimulationButton().getModel().setSelected(true);
    }

    @Override
    public void onGridSimulation()
    {
        this.outputViewBar.getMonteCarloSimulationButton().getModel().setPressed(false);
        this.outputViewBar.getMonteCarloSimulationButton().getModel().setSelected(false);

        this.outputViewBar.getGridSimulationButton().getModel().setPressed(true);
        this.outputViewBar.getGridSimulationButton().getModel().setSelected(true);

        this.outputViewBar.getTargetSimulationButton().getModel().setPressed(false);
        this.outputViewBar.getTargetSimulationButton().getModel().setSelected(false);

        this.inputViewBar.getMonteCarloSimulationButton().getModel().setPressed(false);
        this.inputViewBar.getMonteCarloSimulationButton().getModel().setSelected(false);

        this.inputViewBar.getGridSimulationButton().getModel().setPressed(true);
        this.inputViewBar.getGridSimulationButton().getModel().setSelected(true);

        this.inputViewBar.getTargetSimulationButton().getModel().setPressed(false);
        this.inputViewBar.getTargetSimulationButton().getModel().setSelected(false);
    }

    public void onScatterPlot()
    {
        this.outputViewBar.getScatterPlotButton().getModel().setPressed(true);
        this.outputViewBar.getScatterPlotButton().getModel().setSelected(true);

        this.outputViewBar.getLinePlotButton().getModel().setPressed(false);
        this.outputViewBar.getLinePlotButton().getModel().setSelected(false);

        this.inputViewBar.getScatterPlotButton().getModel().setPressed(true);
        this.inputViewBar.getScatterPlotButton().getModel().setSelected(true);

        this.inputViewBar.getLinePlotButton().getModel().setPressed(false);
        this.inputViewBar.getLinePlotButton().getModel().setSelected(false);
    }

    public void onLinePlot()
    {
        this.outputViewBar.getScatterPlotButton().getModel().setPressed(false);
        this.outputViewBar.getScatterPlotButton().getModel().setSelected(false);

        this.outputViewBar.getLinePlotButton().getModel().setPressed(true);
        this.outputViewBar.getLinePlotButton().getModel().setSelected(true);

        this.inputViewBar.getScatterPlotButton().getModel().setPressed(false);
        this.inputViewBar.getScatterPlotButton().getModel().setSelected(false);

        this.inputViewBar.getLinePlotButton().getModel().setPressed(true);
        this.inputViewBar.getLinePlotButton().getModel().setSelected(true);
    }
}
