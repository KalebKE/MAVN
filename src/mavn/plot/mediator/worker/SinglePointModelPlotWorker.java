/*
SinglePointModelPlotWorker -- A class within the Machine Artificial Vision Network
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
package mavn.plot.mediator.worker;

import java.awt.Color;
import javax.swing.SwingWorker;
import mavn.algorithm.model.point.Point;
import mavn.plot.mediator.PlotMediator;

/**
 * Single Point Model Plot Worker renders the Output Model
 * from Single Point Simulations with a Scatter Plot. This keeps the UI
 * from hanging while the Plot renders.
 * @author Kaleb
 */
public class SinglePointModelPlotWorker extends SwingWorker
{
    private double[][] modelResult;
    private PlotMediator controller;
    private Point point;

    /**
     * Initialize a Single Point Output Model Scatter Plot Swing Worker.
     *
     * Note: This instance is intented to be used only once. A new instance will
     * need to be created for each simulation.
     * @param modelResult the State of the Single Point Simulation Output Model
     * @param mediator the Plot Mediator that the Scatter Plot will be rendered for.
     * @param point the Target Input Model Point from the simulation.
     */
    public SinglePointModelPlotWorker(double[][] modelResult, PlotMediator mediator, Point point)
    {
        this.modelResult = modelResult;
        this.controller = mediator;
        this.point = point;
    }

    /**
     * Render the Point with a Scatter Plot.
     * @return null
     * @throws Exception
     */
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
