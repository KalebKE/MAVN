/*
MultiplePointModelPlotWorker -- a class within the Machine Artificial Vision Network
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
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingWorker;
import mavn.algorithm.model.point.Point;
import mavn.plot.mediator.PlotMediator;

/**
 * Multiple Point Model Plot Worker renders the Scatter Plot for the Plot View
 * Mediator when a non-Animated simulation has produced an Point Hit Output Model
 * and Point Miss Output Model that can be rendered. This SwingWorker keeps the
 * UI from hanging while the Scatter Plot is being rendered.
 * @author Kaleb
 */
public class MultiplePointModelPlotWorker extends SwingWorker
{
    private ArrayList<Point> hit;
    private ArrayList<Point> miss;
    private PlotMediator mediator;

    /**
     * Initialize a new Multiple Point Output Model Scatter Plot Swing Worker.
     *
     * Note: This instance is intended to be used only once! A new instance
     * will need to be created for each simulation.
     * 
     * @param hit the ArrayList of Points that hit shapes in the image.
     * @param miss the ArrayList of Points that missed shapes in the image.
     * @param mediator the Plot Mediator this class will render the Scatter Plot for.
     */
    public MultiplePointModelPlotWorker(ArrayList<Point> hit, ArrayList<Point> miss, PlotMediator mediator)
    {
        this.hit = hit;
        this.miss = miss;
        this.mediator = mediator;
    }

    /**
     * Render the Scatter Plot.
     * @return null
     * @throws Exception
     */
    @Override
    protected Object doInBackground() throws Exception
    {
        try
        {
            Iterator hits = hit.iterator();
            double[][] hitArray = new double[hit.size()][2];
            Iterator misses = miss.iterator();
            double[][] missArray = new double[miss.size()][2];

            // Get the hits and tranfer them to a data structure the
            // Scatter Plot object will accept.
            int hitCount = 0;
            while (hits.hasNext())
            {
                Point pointHit = (Point) hits.next();

                hitArray[hitCount][0] = pointHit.getX();
                hitArray[hitCount][1] = pointHit.getY();
                hitCount++;
            }

            // Get the misses and tranfer them to a data structure the
            // Scatter Plot object will accept.
            int missCount = 0;
            while (misses.hasNext())
            {
                Point pointMiss = (Point) misses.next();

                missArray[missCount][0] = pointMiss.getX();
                missArray[missCount][1] = pointMiss.getY();
                missCount++;
            }

            // Render the hits
            mediator.getPlot().addScatterPlot("Hit", Color.red, hitArray);

            // Render the misses
            mediator.getPlot().addScatterPlot("Miss", Color.black, missArray);

            // Update the UI
            mediator.updateUI();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
