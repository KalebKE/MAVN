/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.algorithm.model.timer;

/**
 *
 * @author Kaleb
 */
public class StopWatch
{

    private double startTime = 0;
    private double stopTime = 0;
    private boolean running = false;

    public void start()
    {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    public void stop()
    {
        this.stopTime = System.nanoTime();
        this.running = false;
    }

    //elaspsed time in milliseconds
    public double getElapsedTime()
    {
        double elapsed;
        if (running)
        {
            elapsed = ((double)System.nanoTime() - startTime);
        } else
        {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }

    //elaspsed time in seconds
    public double getElapsedTimeSecs()
    {
        double elapsed;
        if (running)
        {
            elapsed = (((double)System.nanoTime() - startTime) / 1000);
        } else
        {
            elapsed = ((stopTime - startTime) / 1000000000);
        }
        return elapsed;
    }
}
