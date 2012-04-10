/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.plot.mediator.PlotMediatorInterface;
import mavn.spreadsheet.mediator.SSMediatorInterface;
import simulyn.mediator.SimulationMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class RunSimulationBarAction implements ActionListener
{

    private NetworkMediatorInterface networkMediator;
    private SimulationMediatorInterface simulationMediator;
    private PlotMediatorInterface plotMediator;
    private SSMediatorInterface ssMediator;

    public RunSimulationBarAction(NetworkMediatorInterface networkMediator,
            PlotMediatorInterface plotMediator,
            SimulationMediatorInterface simulationMediator,
            SSMediatorInterface ssMediator)
    {
        this.networkMediator = networkMediator;
        this.simulationMediator = simulationMediator;
        this.plotMediator = plotMediator;
        this.ssMediator = ssMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("runSimulationAction"))
        {
            this.simulationMediator.onRunSimulation();
        }
        if (e.getActionCommand().equals("resetSimulationAction"))
        {
            this.ssMediator.onClearModelResult();
            this.plotMediator.onClearUI();
            this.networkMediator.resetNetwork();
        }
    }
}
