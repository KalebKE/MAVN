/*
AndLayerOutputModel -- a class within the Machine Artificial Vision
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
import mavn.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.network.model.observer.AndLayerOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 * AndLayerOuputModel's are responsible for managing State from the Algorithm
 * Subject's And Layer Output from the simulation's network. It is also
 * a Subject itself and notifies its Observer's when new State is available.
 * Output Model's will typically back Output Mediators that back Output Views.
 * @author Kaleb
 */
public class AndLayerOutputModel extends OutputModelInterface implements AndLayerAlgorithmModelObserver
{
    /**
     * Initialize a AndLayerOutputModel instance.
     */
    public AndLayerOutputModel()
    {
        modelResultObservers = new ArrayList<AndLayerOutputModelObserver>();
    }

      /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelResultObservers.size(); i++)
        {
            AndLayerOutputModelObserver matrixObserver = (AndLayerOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateAndLayerModelResult(matrix);
        }
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(AndLayerOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(AndLayerOutputModelObserver o)
    {
        int i = modelResultObservers.indexOf(o);
        if (i >= 0)
        {
            modelResultObservers.remove(i);
        }
    }

    /**
     * Hook for the Algorithm Subject to be updated when new And Layer Output
     * State is avaiable.
     * @param andLayerResult the output (State) of the And Layer.
     */
    @Override
    public void updateAndLayerModelOutput(double[][] andLayerResult)
    {
        this.setModelResult(andLayerResult);
    }
}
