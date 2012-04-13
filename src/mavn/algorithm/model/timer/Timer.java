/*
Timer -- a class within the Machine Artificial Vision
Network (Machine Artificial Vision Network).
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
package mavn.algorithm.model.timer;

/**
 * A Timer class that uses System.nanoTime() to back it.
 * @author Kaleb
 */
public class Timer
{
    private double startTime = 0;
    private double stopTime = 0;
    private boolean running = false;

    /**
     * Start the Timer.
     */
    public void start()
    {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    /**
     * Stop the Timer.
     */
    public void stop()
    {
        this.stopTime = System.nanoTime();
        this.running = false;
    }

    /**
     * Return the elapsed time in nano seconds.
     * @return elapsed time in nano seconds.
     */
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

    /**
     * Elapsed time in seconds.
     * @return elapsed time in seconds.
     */
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
