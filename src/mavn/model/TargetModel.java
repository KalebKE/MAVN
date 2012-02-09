/*
TargetModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.model;

import java.util.ArrayList;
import mavn.observer.TargetObserver;

/**
 * All updates to the Target Matrix should be made through TargetModel using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class TargetModel extends InputModelInterface
{

    /**
     * Initialize the state.
     */
    public TargetModel()
    {
        matrixObservers = new ArrayList<TargetObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(TargetObserver o)
    {
        matrixObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(TargetObserver o)
    {
        int i = matrixObservers.indexOf(o);
        if (i >= 0)
        {
            matrixObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < matrixObservers.size(); i++)
        {
            TargetObserver matrixObserver = (TargetObserver) matrixObservers.get(i);
            matrixObserver.updateTargetMatrix(matrix);
        }
    }
}
