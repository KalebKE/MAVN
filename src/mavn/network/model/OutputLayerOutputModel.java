/*
OutputLayerOutputModel -- a class within the Machine Artificial Vision
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
import mavn.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.network.model.observer.OutputLayerOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 * OutputLayerOuputModel's are responsible for managing State from the Algorithm
 * Subject's Output Layer Output from the simulation's network. It is also
 * a Subject itself and notifies its Observer's when new State is available.
 * Output Model's will typically back Output Mediators that back Output Views.
 * @author Kaleb
 */
public class OutputLayerOutputModel extends OutputModelInterface implements OutputLayerAlgorithmModelObserver
{

    /**
     * Initialize a OutputLayerOutputModel instance.
     */
    public OutputLayerOutputModel()
    {
        modelResultObservers = new ArrayList<OutputLayerOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OutputLayerOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OutputLayerOutputModelObserver o)
    {
        int i = modelResultObservers.indexOf(o);
        if (i >= 0)
        {
            modelResultObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelResultObservers.size(); i++)
        {
            OutputLayerOutputModelObserver matrixObserver = (OutputLayerOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateOutputLayerModelResult(matrix);
        }
    }

    /**
     * Hook for the Algorithm Subject to be updated when new Output Layer Output
     * State is avaiable.
     * @param outputLayerResult the output (State) of the Ouput Layer.
     */
    @Override
    public void updateOutputLayerModelOutput(double[][] outputLayerResult)
    {
        this.setModelResult(outputLayerResult);
    }
}
