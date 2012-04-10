/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.state.simulator;

/**
 *
 * @author Kaleb
 */
public interface SimulationTypeViewStateInterface
{
    // Simulation View
    public void onSimulatorOutputView();

    public void onSimulatorInputView();

    public void onMonteCarloSimulationView();

    public void onDiagnosticSimluationView();

    public void onPixelGridSimulationView();
}
