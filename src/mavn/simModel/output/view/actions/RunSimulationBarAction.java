/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.simModel.network.mediator.NetworkMediatorInterface;
import mavn.simModel.plot.mediator.PlotMediatorInterface;
import simulyn.output.mediator.OutputMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class RunSimulationBarAction implements ActionListener
{

    private NetworkMediatorInterface networkMediator;
    private OutputMediatorInterface outputMediator;
    private PlotMediatorInterface plotMediator;

    public RunSimulationBarAction(NetworkMediatorInterface networkMediator,
            OutputMediatorInterface outputMediator,
            PlotMediatorInterface plotMediator)
    {
        this.networkMediator = networkMediator;
        this.outputMediator = outputMediator;
        this.plotMediator = plotMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("runSimulationAction"))
        {
            this.outputMediator.onRunSimulation();
        }
        if (e.getActionCommand().equals("resetSimulationAction"))
        {
            this.outputMediator.onClearModelResult();
            this.plotMediator.onClearUI();
            this.networkMediator.resetNetwork();
        }
    }
}
