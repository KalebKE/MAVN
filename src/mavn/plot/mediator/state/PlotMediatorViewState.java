/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.plot.mediator.state;

import mavn.simulation.view.SimControlView;
import mavn.simulation.view.controlBar.ControlBar;

/**
 *
 * @author Kaleb
 */
public class PlotMediatorViewState implements PlotMediatorViewStateInterface
{

    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView view;

    public PlotMediatorViewState(ControlBar outputViewBar, ControlBar inputViewBar, SimControlView view)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;
        this.view = view;
    }

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
