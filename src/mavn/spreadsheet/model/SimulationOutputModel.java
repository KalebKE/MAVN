/*
SimulationOutputModel -- A class within the Machine Artificial Vision
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
package mavn.spreadsheet.model;

import java.util.ArrayList;
import mavn.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.algorithm.model.singlePointSimulation.observer.SinglePointAlgorithmModelObserver;
import mavn.spreadsheet.model.observer.OutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 * The Output Model for the simulation Network Output. The Network Output can
 * depend on the type of simulation that is being used. 
 * @author Kaleb
 */
public class SimulationOutputModel extends OutputModelInterface implements
        MultiplePointAlgorithmModelObserver, SinglePointAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public SimulationOutputModel()
    {
        modelResultObservers = new ArrayList<OutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OutputModelObserver o)
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
        try
        {
            for (int i = 0; i < modelResultObservers.size(); i++)
            {
                OutputModelObserver matrixObserver = (OutputModelObserver) modelResultObservers.get(i);
                matrixObserver.updateModelResult(matrix);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * The hook to Observe the Multiple Point Simulation Algorithm Model Subjects.
     * @param imageRatio the number of Points that hit shapes in the
     * image versus the total number of Points that landed on the image.
     */
    @Override
    public void updateMultiplePointAlgorithmModelOutput(double[][] modelResult)
    {
        this.setModelResult(modelResult);
    }

    /**
     * The hook to Observe the Single Point Simulation Algorithm Model Subjects.
     * @param imageRatio the number of Points that hit shapes in the
     * image versus the total number of Points that landed on the image.
     */
    @Override
    public void updateSinglePointModelOutput(double[][] modelResult)
    {
        this.setModelResult(modelResult);
    }
}
