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
package mavn.simModel.input.model;

import java.util.ArrayList;
import mavn.simModel.input.model.observer.ThetaModelObserver;
import simulyn.input.model.InputModelInterface;

/**
 * All updates to the Theta Matrix should be made through ThetaModel using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class ThetaInputModel extends InputModelInterface
{

    /**
     * Initialize the state.
     */
    public ThetaInputModel()
    {
        modelInputObservers = new ArrayList<ThetaModelObserver>();
    }

    /**
     * Register Observer.
     * @param o the ThetaObserver
     */
    public void registerObserver(ThetaModelObserver o)
    {
        modelInputObservers.add(o);
    }

    /**
     * Remove Observer.
     * @param o the ThetaObserver
     */
    public void removeObserver(ThetaModelObserver o)
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
            ThetaModelObserver matrixObserver = (ThetaModelObserver) modelInputObservers.get(i);
            matrixObserver.updateThetaModelInput(matrix);
        }
    }
}
