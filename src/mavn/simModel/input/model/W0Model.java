/*
W0Model -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
import mavn.simModel.input.model.observer.W0ModelObserver;

/**
 * All updates to the W0 Matrix should be made through W0Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class W0Model extends ModelInputInterface
{

    /**
     * Initialize the state.
     */
    public W0Model()
    {
        modelInputObservers = new ArrayList<W0ModelObserver>();
    }

    /**
     * Register Observer.
     * @param o the W0Observer.
     */
    public void registerObserver(W0ModelObserver o)
    {
        modelInputObservers.add(o);
    }

    /**
     * Remove Observer.
     * @param o the W0Observer.
     */
    public void removeObserver(W0ModelObserver o)
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
            W0ModelObserver matrixObserver = (W0ModelObserver) modelInputObservers.get(i);
            matrixObserver.updateW0ModelInput(matrix);
        }
    }
}