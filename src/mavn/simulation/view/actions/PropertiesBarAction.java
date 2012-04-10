/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.simulation.view.state.simulator.SimulationTypeViewStateInterface;
import simulyn.mediator.SimulationMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class PropertiesBarAction implements ActionListener
{

    private SimulationMediatorInterface simulationMediator;
    private SimulationPropertiesStateInterface simulationPropertiesState;
    private SimulationTypeViewStateInterface simulationViewState;

    public PropertiesBarAction(SimulationMediatorInterface outputMediator,
            SimulationPropertiesStateInterface simulationState)
    {
        this.simulationMediator = outputMediator;
        this.simulationPropertiesState = simulationState;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("loadPropertiesAction"))
        {
            this.simulationMediator.onLoadSimulationProperties();
        }
        if (e.getActionCommand().equals("useTargetSimulationAction"))
        {
            this.simulationPropertiesState.onDiagnosticSimulation();
            this.simulationViewState.onDiagnosticSimluationView();
        }
        if (e.getActionCommand().equals("useMonteCaroloSimuationAction"))
        {
            this.simulationPropertiesState.onMonteCarloSimulation();
            this.simulationViewState.onMonteCarloSimulationView();
        }
        if (e.getActionCommand().equals("useGridSimulationAction"))
        {
            this.simulationPropertiesState.onPixelGridSimulation();
            this.simulationViewState.onPixelGridSimulationView();
        }
    }

    public void setSimulationViewState(SimulationTypeViewStateInterface simulationViewState)
    {
        this.simulationViewState = simulationViewState;
    }
}
