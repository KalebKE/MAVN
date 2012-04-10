/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.plot.mediator.worker;

import java.awt.Color;
import javax.swing.SwingWorker;
import mavn.algorithm.model.point.Point;
import mavn.plot.mediator.PlotMediator;

/**
 *
 * @author Kaleb
 */
public class SinglePointModelPlotWorker extends SwingWorker
{
    private double[][] modelResult;
    private PlotMediator controller;
    private Point point;

    public SinglePointModelPlotWorker(double[][] modelResult, PlotMediator controller, Point point)
    {
        this.modelResult = modelResult;
        this.controller = controller;
        this.point = point;
    }

    @Override
    protected Object doInBackground() throws Exception
    {
        double[][] data = modelResult;
        double[] x =
        {
            point.getX()
        };
        double[] y =
        {
            point.getY()
        };

        if (data[0][0] == 1)
        {
            controller.getPlot().addScatterPlot("Hit", Color.red, x, y);
        }
        if (data[0][0] == -1)
        {
            controller.getPlot().addScatterPlot("Miss", Color.black, x, y);
        }

        controller.updateUI();

        return null;
    }
}
