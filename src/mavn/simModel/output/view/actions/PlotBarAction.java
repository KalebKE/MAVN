/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.simModel.output.view.state.OutputViewStateInterface;
import mavn.simModel.plot.mediator.PlotMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class PlotBarAction implements ActionListener
{

    private PlotMediatorInterface plotMediator;
    private OutputViewStateInterface viewState;

    public PlotBarAction(PlotMediatorInterface plotMediator)
    {
        this.plotMediator = plotMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("useScatterPlotAction"))
        {
            this.viewState.onScatterPlot();
            this.plotMediator.onScatterPlot();
        }
        if (e.getActionCommand().equals("useLinePlotAction"))
        {
            this.viewState.onLinePlot();
            this.plotMediator.onLinePlot();
        }
        if (e.getActionCommand().equals("clearPlotAction"))
        {
            this.plotMediator.onClearUI();
        }
    }

    public void setViewState(OutputViewStateInterface viewState)
    {
        this.viewState = viewState;
    }
}
