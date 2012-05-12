/*
TargetInputModel --
A class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.input.model;

import java.util.ArrayList;
import mavn.input.model.observer.TargetInputModelObserver;
import simulyn.input.model.InputModelInterface;

/**
 * Target Input Model keeps track of the current Target Input for the simulation.
 * The Target Input consists of an X and Y coordinate representing a Point
 * on an image. This point will either land on a shape in the image, or it won't.
 * Target Input is only used for Single Point Simulation's and is defined by
 * the user in an external file or with the Input View.
 * All updates to the Target Input Model should be made through TargetInputModel using
 * setMatrix(double[][] matrix). This will notify all Observers of the change
 * and provide them with the new model state.
 * @author Kaleb
 */
public class TargetInputModel extends InputModelInterface
{

    /**
     * Initialize the state.
     */
    public TargetInputModel()
    {
        modelInputObservers = new ArrayList<TargetInputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(TargetInputModelObserver o)
    {
        modelInputObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(TargetInputModelObserver o)
    {
        int i = modelInputObservers.indexOf(o);
        if (i >= 0)
        {
            modelInputObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelInputObservers.size(); i++)
        {
            TargetInputModelObserver matrixObserver = (TargetInputModelObserver) modelInputObservers.get(i);
            matrixObserver.updateTargetInputModel(matrix);
        }
    }
}
