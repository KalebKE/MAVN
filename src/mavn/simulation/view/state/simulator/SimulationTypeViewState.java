/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.state.simulator;

import mavn.simulation.view.controlBar.ControlBar;
import mavn.simulation.view.SimControlView;

/**
 *
 * @author Kaleb
 */
public class SimulationTypeViewState implements SimulationTypeViewStateInterface
{
    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView simulatorView;

    public SimulationTypeViewState(ControlBar outputViewBar, ControlBar inputViewBar, SimControlView view)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;
        this.simulatorView = view;
    }

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
