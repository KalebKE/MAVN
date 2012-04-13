/*
PointOutputModel-- a class within the Machine Artificial Vision Network
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
import mavn.algorithm.model.point.observer.PointGeneratorAlgorithmModelObserver;
import mavn.plot.model.observer.PointOutputModelObserver;

/**
 * Point Output Model is responsible for storing the entire Point State
 * after a simulation has run. 
 * @author Kaleb
 */
public class PointOutputModel extends PointOutputModelInterface implements
        PointGeneratorAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public PointOutputModel()
    {
        modeOutputObservers = new ArrayList<PointOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(PointOutputModelObserver o)
    {
        modeOutputObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(PointOutputModelObserver o)
    {
        int i = modeOutputObservers.indexOf(o);
        if (i >= 0)
        {
            modeOutputObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyPointsObservers()
    {
        for (int i = 0; i < modeOutputObservers.size(); i++)
        {
            PointOutputModelObserver matrixObserver = (PointOutputModelObserver) modeOutputObservers.get(i);
            matrixObserver.updatePoints(hit, miss);
        }
    }

    /**
     * Hook to Observe the Algorithm Model Subject.
     * @param point the Points that hit and missed a shape within the image.
     */
    @Override
    public void updatePoints(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        this.setPointsOutput(hit, miss);
    }
}
