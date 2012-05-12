/*
RunSimulationBarAction -- A class within the Machine Artificial Vision
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
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.plot.mediator.PlotMediatorInterface;
import mavn.spreadsheet.mediator.SSMediatorInterface;
import simulyn.simulation.mediator.SimulationMediatorInterface;
/**
 * RunSimulationBarAction is an ActionListener implementation used
 * to manage the Actions from the Run Simulation Control Bar. This class allows
 * the View to be decoupled from the Mediator.
 * @author Kaleb
 */
public class RunSimulationBarAction implements ActionListener
{

    private NetworkMediatorInterface networkMediator;
    private SimulationMediatorInterface simulationMediator;
    private PlotMediatorInterface plotMediator;
    private SSMediatorInterface ssMediator;

    /**
     * Initialize a RunSimulationBarAction.
     * @param networkMediator the Network Mediator that the Action will call.
     * @param plotMediator the Plot Mediator that the Action will call.
     * @param simulationMediator the Simulation Mediator the Action will call.
     * @param ssMediator the Spreadsheet Mediator that the ACtion will call.
     */
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
