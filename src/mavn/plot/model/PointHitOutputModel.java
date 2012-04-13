/*
PointHitOutputModel -- a class within the Machine Artificial Vision Network
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
package mavn.plot.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;
import mavn.algorithm.model.point.observer.PointHitAlgorithmModelObserver;
import mavn.plot.model.observer.PointHitOutputModelObserver;

/**
 * Point Hit Output Model stores the Output Model State from Points that hit
 * shapes within the image during the simulation. It is both a Observer of
 * the simulation's Algorithm Model and a Subject with its own Observers,
 * typically Output Mediators who render the State and then push it to the View.
 * @author Kaleb
 */
public class PointHitOutputModel extends PointHitOutputModelInterface implements
        PointHitAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public PointHitOutputModel()
    {
        modelOutputObservers = new ArrayList<PointHitOutputModelObserver>();
    }

    /**
     * Register the Point Hit Output Model Observer observer.
     * @param o the Point Hit Output Model Observer
     */
    public void registerObserver(PointHitOutputModelObserver o)
    {
        modelOutputObservers.add(o);
    }

    /**
     * Remove the Point Hit Output Model Observer observer.
     * @param o the Point Hit Output Model Observer.
     */
    public void removeObserver(PointHitOutputModelObserver o)
    {
        int i = modelOutputObservers.indexOf(o);
        if (i >= 0)
        {
            modelOutputObservers.remove(i);
        }
    }

    /**
     * Notify all Point Hit Output Model Observers.
     */
    @Override
    public void notifyPointHitObservers()
    {
        for (int i = 0; i < modelOutputObservers.size(); i++)
        {
            PointHitOutputModelObserver matrixObserver = (PointHitOutputModelObserver) modelOutputObservers.get(i);
            matrixObserver.updatePointHit(hit);
        }
    }

    /**
     * Hook to Observe the Algorithm Model Subject.
     * @param point the Point that hit a shape within the image.
     */
    @Override
    public void updatePointHit(Point point)
    {
        this.setPointHit(point);
    }
}
