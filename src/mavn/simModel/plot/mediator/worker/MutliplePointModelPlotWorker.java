/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.plot.mediator.worker;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingWorker;
import mavn.simModel.algorithm.model.point.Point;
import mavn.simModel.plot.mediator.PlotMediator;

/**
 *
 * @author Kaleb
 */
public class MutliplePointModelPlotWorker extends SwingWorker
{

    private ArrayList<Point> hit;
    private ArrayList<Point> miss;
    private PlotMediator mediator;

    public MutliplePointModelPlotWorker(ArrayList<Point> hit, ArrayList<Point> miss, PlotMediator mediator)
    {
        this.hit = hit;
        this.miss = miss;
        this.mediator = mediator;
    }

    @Override
    protected Object doInBackground() throws Exception
    {
        try
        {
            Iterator hits = hit.iterator();
            double[][] hitArray = new double[hit.size()][2];
            Iterator misses = miss.iterator();
            double[][] missArray = new double[miss.size()][2];

            int hitCount = 0;
            while (hits.hasNext())
            {
                Point pointHit = (Point) hits.next();

                hitArray[hitCount][0] = pointHit.getX();
                hitArray[hitCount][1] = pointHit.getY();
                hitCount++;
            }

            int missCount = 0;
            while (misses.hasNext())
            {
                Point pointMiss = (Point) misses.next();

                missArray[missCount][0] = pointMiss.getX();
                missArray[missCount][1] = pointMiss.getY();
                missCount++;
            }

            mediator.getPlot().addScatterPlot("Hit", Color.red, hitArray);

            mediator.getPlot().addScatterPlot("Miss", Color.black, missArray);

            mediator.updateUI();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
