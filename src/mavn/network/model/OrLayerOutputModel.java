/*
OrLayerOutputModel -- a class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
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
package mavn.network.model;

import java.util.ArrayList;
import mavn.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.network.model.observer.OrLayerOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 * OrLayerOuputModel's are responsible for managing State from the Algorithm
 * Subject's Or Layer Output from the simulation's network. It is also
 * a Subject itself and notifies its Observer's when new State is available.
 * Output Model's will typically back Output Mediators that back Output Views.
 * @author Kaleb
 */
public class OrLayerOutputModel extends OutputModelInterface implements OrLayerAlgorithmModelObserver
{

    /**
     * Initialize a OrLayerOutputModel instance.
     */
    public OrLayerOutputModel()
    {
        modelResultObservers = new ArrayList<OrLayerOutputModelObserver>();
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelResultObservers.size(); i++)
        {
            OrLayerOutputModelObserver matrixObserver = (OrLayerOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateOrLayerModelResult(matrix);
        }
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OrLayerOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OrLayerOutputModelObserver o)
    {
        int i = modelResultObservers.indexOf(o);
        if (i >= 0)
        {
            modelResultObservers.remove(i);
        }
    }

    /**
     * Hook for the Algorithm Subject to be updated when new Or Layer Output
     * State is avaiable.
     * @param orLayerResult the output (State) of the Or Layer.
     */
    @Override
    public void updateOrLayerModelOutput(double[][] orLayerResult)
    {
        this.setModelResult(orLayerResult);
    }
}
