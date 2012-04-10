/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.plot.mediator.PlotMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class PlotBarAction implements ActionListener
{
    private PlotMediatorInterface plotMediator;

    public PlotBarAction(PlotMediatorInterface plotMediator)
    {
        this.plotMediator = plotMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("useScatterPlotAction"))
        {
            this.plotMediator.onScatterPlot();
        }
        if (e.getActionCommand().equals("useLinePlotAction"))
        {
            this.plotMediator.onLinePlot();
        }
        if (e.getActionCommand().equals("clearPlotAction"))
        {
            this.plotMediator.onClearUI();
        }
    }
}
