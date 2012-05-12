/*
ThetaModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
import mavn.input.model.observer.ThetaInputModelObserver;
import simulyn.input.model.InputModelInterface;

/**
 * Theta Input Model keeps track of the current Theta Input for the simulation.
 * The Theta Input can come from an external file or be defined within the
 * simulation by the user.
 * The Theta Input essentially represents the boundary edge locations for the
 * shapes within the image. It is added to the result of the Target Input
 * multiplied by the W2 Input which represents the edge directions. 
 * All updates to the Theta Input Model should be made through ThetaInputModel using
 * setMatrix(double[][] matrix). This will notify all Observers of the change
 * and provide them with the new model state.
 * @author Kaleb
 */
public class ThetaInputModel extends InputModelInterface
{

    /**
     * Initialize the state.
     */
    public ThetaInputModel()
    {
        modelInputObservers = new ArrayList<ThetaInputModelObserver>();
    }

    /**
     * Register the ThetaInputModelObserver Observer.
     * @param o the ThetaObserver
     */
    public void registerObserver(ThetaInputModelObserver o)
    {
        modelInputObservers.add(o);
    }

    /**
     * Remove the ThetaInputModelObserver Observer.
     * @param o the ThetaObserver
     */
    public void removeObserver(ThetaInputModelObserver o)
    {
        int i = modelInputObservers.indexOf(o);
        if (i >= 0)
        {
            modelInputObservers.remove(i);
        }
    }

    /**
     * Notify all ThetaInputModelObserver observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelInputObservers.size(); i++)
        {
            ThetaInputModelObserver matrixObserver = (ThetaInputModelObserver) modelInputObservers.get(i);
            matrixObserver.updateThetaInputModel(matrix);
        }
    }
}
