/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.simModel.algorithm.model.point;

/**
 *
 * @author Kaleb
 */
public class Point
{
    private double x;
    private double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Point()
    {
        super();
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}
