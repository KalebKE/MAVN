/*
SimulationViewInputState -- A class within the Machine Artificial Vision
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
package mavn.simulation.view.state.input;

import mavn.simulation.view.controlBar.ControlBar;
import mavn.simulation.view.SimControlView;

/**
 * SimulationViewInputState maintains all of the View State required for the
 * simulations Input Models. It enables and disables certain UI functionality
 * based on the Input Models State.
 * @author Kaleb
 */
public class SimulationViewInputState implements SimulationViewInputStateInterface
{

    private boolean propertiesLoaded;
    private boolean simulationLoaded;
    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView view;

    /**
     * Initialize a SimulationViewInputState.
     * @param outputViewBar the Output View Control Bar.
     * @param inputViewBar the Input View Control Bar.
     */
    public SimulationViewInputState(ControlBar outputViewBar, ControlBar inputViewBar)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;

        propertiesLoaded = false;
    }

    /**
     * Initialize the Input States.
     */
    public void init()
    {
        this.onPropertiesUnloaded();
        this.onSimulationUnloaded();
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
    public void onPropertiesLoaded()
    {
        // Disable these buttons
        outputViewBar.getRunSimulationButton().setEnabled(true);
        inputViewBar.getRunSimulationButton().setEnabled(true);
        view.getRunSimulationMenuItem().setEnabled(true);

        propertiesLoaded = true;
    }

    /**
     * Inidicate that the simulation properties have not been loaded.
     */
    @Override
    public void onPropertiesUnloaded()
    {
        view.getEditPropertiesMenuItem().setEnabled(true);
        view.getRunSimulationMenuItem().setEnabled(false);
        view.getSaveModelMenuItem().setEnabled(false);
        view.getClearOutputMenuItem().setEnabled(false);

        // Disable these buttons
        outputViewBar.getSimulationPropertiesButton().setEnabled(true);
        outputViewBar.getRunSimulationButton().setEnabled(false);
        outputViewBar.getSaveModelOutputButton().setEnabled(false);
        outputViewBar.getClearModelOutputButton().setEnabled(false);

        // Disable these buttons
        inputViewBar.getSimulationPropertiesButton().setEnabled(true);
        inputViewBar.getRunSimulationButton().setEnabled(false);
        inputViewBar.getSaveModelOutputButton().setEnabled(false);
        inputViewBar.getClearModelOutputButton().setEnabled(false);

        propertiesLoaded = false;
    }

    /**
     * Indicate that a simulation has been loaded.
     */
    @Override
    public void onSimulationLoaded()
    {
        view.getEditPropertiesMenuItem().setEnabled(true);
        view.getSaveModelMenuItem().setEnabled(true);
        view.getCloseSimulationMenuItem().setEnabled(true);
        view.getMonteCarloMenuItem().setEnabled(true);
        view.getMonteCarloMenuItem().setSelected(true);
        view.getPixelResolutionMenuItem().setEnabled(true);
        view.getDiagnosticPointMenuItem().setEnabled(true);
        view.getAnimateNetworkMenuItem().setEnabled(true);
        view.getAnimateNetworkMenuItem().setSelected(true);
        view.getRenderNetworkMenuItem().setEnabled(true);
        view.getClearNetworkMenuItem().setEnabled(true);
        view.getClearPlotterMenuItem().setEnabled(true);
        view.getRenderPointsMenuItem().setEnabled(true);
        view.getRenderPointsMenuItem().setEnabled(true);
        view.getRenderTimeMenuItem().setEnabled(true);
        view.getRunSimulationMenuItem().setEnabled(true);

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
    public void onSimulationUnloaded()
    {
        view.getEditPropertiesMenuItem().setEnabled(false);
        view.getSaveModelMenuItem().setEnabled(false);
        view.getCloseSimulationMenuItem().setEnabled(false);
        view.getMonteCarloMenuItem().setEnabled(false);
        view.getDiagnosticPointMenuItem().setEnabled(false);
        view.getAnimateNetworkMenuItem().setEnabled(false);
        view.getClearNetworkMenuItem().setEnabled(false);
        view.getClearPlotterMenuItem().setEnabled(false);
        view.getRenderPointsMenuItem().setEnabled(false);
        view.getRenderPointsMenuItem().setEnabled(false);
        view.getRenderTimeMenuItem().setEnabled(false);
        view.getRunSimulationMenuItem().setEnabled(false);

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

    /**
     * Set the Simulation Control View.
     * @param view the SimControlView.
     */
    public void setView(SimControlView view)
    {
        this.view = view;
    }
}
