/*
PlotBarAction -- A class within the Machine Artificial Vision
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
import mavn.plot.mediator.PlotMediatorInterface;

/**
 * PlotBarAction is an ActionListener implementation used
 * to manage the Actions from the Plot Control Bar. This class allows
 * the View to be decoupled from the Mediator.
 * @author Kaleb
 */
public class PlotBarAction implements ActionListener
{

    private PlotMediatorInterface plotMediator;

    /**
     * Initialize a new PlotBarAction.
     * @param plotMediator the Plot Mediator that the Action will call.
     */
    public PlotBarAction(PlotMediatorInterface plotMediator)
    {
        this.plotMediator = plotMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("useScatterPlotAction"))
        {

        }
        if (e.getActionCommand().equals("useLinePlotAction"))
        {

        }
        if (e.getActionCommand().equals("clearPlotAction"))
        {
            this.plotMediator.onClearUI();
        }
    }
}
