/*
PlotMediatorStateView -- a class within the Machine Artificial Vision Network
(Machine Artificial Vision Network).
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
package mavn.plot.mediator.state;

import mavn.simulation.view.SimControlView;
import mavn.simulation.view.controlBar.ControlBar;

/**
 * PlotMediatorViewState manages the simulation-level View State related
 * to the Plot Mediator. This includes setting state on the Simulation Control
 * Bars and within the Simulation Control View itself.
 * @author Kaleb
 */
public class PlotMediatorViewState implements PlotMediatorViewStateInterface
{

    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView view;

    /**
     * Initialize a new instance of Plot Mediator View State.
     * @param outputViewBar the Output Control Bar that Plot State will be managed
     * for.
     * @param inputViewBar the Input Control Bar that Plot State will be managed
     * for.
     * @param view the Simulation Control View that Plot State will be managed for.
     */
    public PlotMediatorViewState(ControlBar outputViewBar,
            ControlBar inputViewBar, SimControlView view)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;
        this.view = view;
    }

    /**
     * The View State when a Scatter Plot Action occurs.
     */
    @Override
    public void onScatterPlot()
    {
        this.view.getRenderPointsMenuItem().setSelected(true);
        this.view.getRenderTimeMenuItem().setSelected(false);

        this.outputViewBar.getScatterPlotButton().getModel().setPressed(true);
        this.outputViewBar.getScatterPlotButton().getModel().setSelected(true);

        this.outputViewBar.getLinePlotButton().getModel().setPressed(false);
        this.outputViewBar.getLinePlotButton().getModel().setSelected(false);

        this.inputViewBar.getScatterPlotButton().getModel().setPressed(true);
        this.inputViewBar.getScatterPlotButton().getModel().setSelected(true);

        this.inputViewBar.getLinePlotButton().getModel().setPressed(false);
        this.inputViewBar.getLinePlotButton().getModel().setSelected(false);
    }

    /**
     * The View State when a Line Plot Action occurs.
     */
    @Override
    public void onLinePlot()
    {
        this.view.getRenderPointsMenuItem().setSelected(false);
        this.view.getRenderTimeMenuItem().setSelected(true);

        this.outputViewBar.getScatterPlotButton().getModel().setPressed(false);
        this.outputViewBar.getScatterPlotButton().getModel().setSelected(false);

        this.outputViewBar.getLinePlotButton().getModel().setPressed(true);
        this.outputViewBar.getLinePlotButton().getModel().setSelected(true);

        this.inputViewBar.getScatterPlotButton().getModel().setPressed(false);
        this.inputViewBar.getScatterPlotButton().getModel().setSelected(false);

        this.inputViewBar.getLinePlotButton().getModel().setPressed(true);
        this.inputViewBar.getLinePlotButton().getModel().setSelected(true);
    }
}
