/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;
import simulyn.mediator.SimulationMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class SimulationBarAction implements ActionListener
{

    private SimulationMediatorInterface simulationMediator;

    public SimulationBarAction(SimulationMediatorInterface simulationMediator)
    {
        this.simulationMediator = simulationMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("importSimulationAction"))
        {
            simulationMediator.onLoadSimulationInputModel();
        }

        if (e.getActionCommand().equals("exportSimulationAction"))
        {
            simulationMediator.onSaveSimulationInputModel();
        }
        if (e.getActionCommand().equals("clearSimluationAction"))
        {
            simulationMediator.onClearSimulation();
        }
    }
}
