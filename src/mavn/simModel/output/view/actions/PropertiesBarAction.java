/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.simModel.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.simModel.output.view.state.OutputViewStateInterface;
import simulyn.output.mediator.OutputMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class PropertiesBarAction implements ActionListener
{

    private OutputMediatorInterface outputMediator;
    private SimulationPropertiesStateInterface simulationState;
    private OutputViewStateInterface outputViewState;

    public PropertiesBarAction(OutputMediatorInterface outputMediator,
            SimulationPropertiesStateInterface simulationState)
    {
        this.outputMediator = outputMediator;
        this.simulationState = simulationState;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("loadPropertiesAction"))
        {
            this.outputMediator.onLoadProperties();
        }
        if (e.getActionCommand().equals("useTargetSimulationAction"))
        {
            this.simulationState.useTargetModel();
            this.outputViewState.onTargetSimluation();
        }
        if (e.getActionCommand().equals("useMonteCaroloSimuationAction"))
        {
            this.simulationState.usePointGeneratorModel();
            this.outputViewState.onMonteCarloSimulation();
        }
        if (e.getActionCommand().equals("useGridSimulationAction"))
        {
            this.simulationState.useGridGeneratedModel();
            this.outputViewState.onGridSimulation();
        }
    }

    public void setOutputViewState(OutputViewStateInterface outputViewState)
    {
        this.outputViewState = outputViewState;
    }

    
}
