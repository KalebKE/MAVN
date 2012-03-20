/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.plot.mediator.worker;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingWorker;
import mavn.simModel.plot.mediator.PlotMediator;

/**
 *
 * @author Kaleb
 */
public class MutliplePointModelPlotWorker extends SwingWorker
{

    private ArrayList<Point> hit;
    private ArrayList<Point> miss;
    private PlotMediator controller;

    public MutliplePointModelPlotWorker(ArrayList<Point> hit, ArrayList<Point> miss, PlotMediator controller)
    {
        this.hit = hit;
        this.miss = miss;
        this.controller = controller;
    }

    @Override
    protected Object doInBackground() throws Exception
    {
        try
        {
            Iterator hits = hit.iterator();
            double[] xHit = new double[hit.size()];
            double[] yHit = new double[hit.size()];
            Iterator misses = miss.iterator();
            double[] xMisses = new double[miss.size()];
            double[] yMisses = new double[miss.size()];

            int hitCount = 0;
            while (hits.hasNext())
            {
                Point pointHit = (Point) hits.next();

                xHit[hitCount] = pointHit.getX();
                yHit[hitCount] = pointHit.getY();
                hitCount++;
            }

            int missCount = 0;
            while (misses.hasNext())
            {
                Point pointMiss = (Point) misses.next();
                xMisses[missCount] = pointMiss.getX();
                yMisses[missCount] = pointMiss.getY();
                missCount++;
            }

            controller.getPlot().addScatterPlot("Hit", Color.red, xHit, yHit);
            controller.getPlot().addScatterPlot("Miss", Color.black, xMisses, yMisses);
            controller.updateUI();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
