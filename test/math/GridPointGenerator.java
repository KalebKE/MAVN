/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package math;

import mavn.algorithm.model.point.Point;

/**
 *
 * @author Kaleb
 */
public class GridPointGenerator
{
    public int numPoints;
    public int bounds;
    public int resolution;

    public GridPointGenerator(int bounds, int resolution)
    {
        this.bounds = bounds;
        this.resolution = resolution;
    }

    public Point[] getPoints()
    {
        int xPoints = bounds*resolution;
        int yPoints = bounds*resolution;
        numPoints = (xPoints*yPoints);
        Point[] points = new Point[numPoints];

        for(int i = 0; i < numPoints; i++)
        {
            points[i] = new Point();
        }

        double increment = ((double)1)/resolution;

        double x = 0;
        double y = 0;
        int count = 0;
        for(int i = 0; i < points.length; i++)
        {
           points[i].setLocation(x, y);

           if(((bounds*resolution)-1)== count)
           {
               x = x + increment;
               y = 0;
               count = -1;
           }
           else
           {
               y = y + increment;
           }
           count++;
        }
        return points;
    }

    public static void main(String[] args)
    {
        GridPointGenerator grid = new GridPointGenerator(6, 3);
        Point[] points = grid.getPoints();

        System.out.println("Size: " + points.length);
        for(Point x: points)
        {
            System.out.print(x.getX() + " ,");
            System.out.print(x.getY());
            System.out.println("");
        }
    }

}
