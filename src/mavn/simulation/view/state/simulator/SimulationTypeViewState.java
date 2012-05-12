/*
SimulationTypeViewInputState -- A class within the Machine Artificial Vision
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
package mavn.simulation.view.state.simulator;

import mavn.simulation.view.controlBar.ControlBar;
import mavn.simulation.view.SimControlView;

/**
 * SimulationTypeViewState maintains all of the View State required for selecting
 * the type of simulation that will be run.
 * It enables and disables certain UI functionality based on the
 * Simulation Properties State.
 * @author Kaleb
 */
public class SimulationTypeViewState implements SimulationTypeViewStateInterface
{
    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView simulatorView;

    /**
     * Initialize a SimulationTypeViewState.
     * @param outputViewBar the Output View Control Bar.
     * @param inputViewBar the Input View Control Bar.
     * @param view the Simulation Control View.
     */
    public SimulationTypeViewState(ControlBar outputViewBar,
            ControlBar inputViewBar, SimControlView view)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;
        this.simulatorView = view;
    }

    /**
     * Indicate that the Simulation Output View is desired.
     */
    @Override
    public void onSimulatorOutputView()
    {
        this.simulatorView.getViewOutputMenuItem().setSelected(true);
        this.simulatorView.getViewInputMenuItem().setSelected(false);

        this.outputViewBar.getOutputViewButton().getModel().setPressed(true);
        this.outputViewBar.getOutputViewButton().getModel().setSelected(true);
        this.outputViewBar.getInputViewButton().getModel().setPressed(false);
        this.outputViewBar.getInputViewButton().getModel().setSelected(false);

        this.inputViewBar.getOutputViewButton().getModel().setPressed(true);
        this.inputViewBar.getOutputViewButton().getModel().setSelected(true);
        this.inputViewBar.getInputViewButton().getModel().setPressed(false);
        this.inputViewBar.getInputViewButton().getModel().setSelected(false);
    }

    /**
     * Indicate that the Simulation Input View is desired.
     */
    @Override
    public void onSimulatorInputView()
    {
        this.simulatorView.getViewOutputMenuItem().setSelected(false);
        this.simulatorView.getViewInputMenuItem().setSelected(true);

        this.outputViewBar.getOutputViewButton().getModel().setPressed(false);
        this.outputViewBar.getOutputViewButton().getModel().setSelected(false);
        this.outputViewBar.getInputViewButton().getModel().setPressed(true);
        this.outputViewBar.getInputViewButton().getModel().setSelected(true);

        this.inputViewBar.getOutputViewButton().getModel().setPressed(false);
        this.inputViewBar.getOutputViewButton().getModel().setSelected(false);
        this.inputViewBar.getInputViewButton().getModel().setPressed(true);
        this.inputViewBar.getInputViewButton().getModel().setSelected(true);
    }

    /**
     * Indicate that a Monte Carlo Simulation View is desired.
     */
    @Override
    public void onMonteCarloSimulationView()
    {
        this.simulatorView.getMonteCarloMenuItem().setSelected(true);
        this.simulatorView.getPixelResolutionMenuItem().setSelected(false);
        this.simulatorView.getDiagnosticPointMenuItem().setSelected(false);

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

    /**
     * Indicate that a Diagnostic Simulation View is desired.
     */
    @Override
    public void onDiagnosticSimluationView()
    {
        this.simulatorView.getMonteCarloMenuItem().setSelected(false);
        this.simulatorView.getPixelResolutionMenuItem().setSelected(false);
        this.simulatorView.getDiagnosticPointMenuItem().setSelected(true);

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

    /**
     * Indicate a Pixel Grid Simulation View is desired.
     */
    @Override
    public void onPixelGridSimulationView()
    {
        this.simulatorView.getMonteCarloMenuItem().setSelected(false);
        this.simulatorView.getPixelResolutionMenuItem().setSelected(true);
        this.simulatorView.getDiagnosticPointMenuItem().setSelected(false);

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
}
