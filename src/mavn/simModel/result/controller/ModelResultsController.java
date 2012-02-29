/*
OutputController -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.result.controller;

import file.save.controller.SaveFileControllerInterface;
import java.util.ArrayList;
import mavn.factory.AbstractSimulationFactory;
import mavn.globals.Globals;
import mavn.simModel.algorithm.observer.AndLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.DartsAlgorithmObserver;
import mavn.simModel.algorithm.observer.ImageRatioAlgorithmObserver;
import mavn.simModel.algorithm.observer.ModelResultAlgorithmObserver;
import mavn.simModel.algorithm.observer.OrLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.OutputLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.ShapesRatioAlgorithmObserver;

/**
 * OutputController is an implementatoin of OutputController Interface that
 * manages the output of the application. 
 * @author Kaleb
 */
public class ModelResultsController implements ModelResultControllerInterface,
        AndLayerAlgorithmObserver, OrLayerAlgorithmObserver, OutputLayerAlgorithmObserver,
        ModelResultAlgorithmObserver, DartsAlgorithmObserver, ShapesRatioAlgorithmObserver, ImageRatioAlgorithmObserver
{
    /*
     * If you are developing with this class, please take
     * note that it is considerably harder to decouple the Output Controllers than the
     * Input Controllers because the Output Controllers are dependent on a bunch of
     * state and other models burried somewhere in the application. While an input controller only
     * requires only one collection dependency, this class requires two. To decouple this class,
     * there is a bunch of collection abstractions that have been defined by the main application
     * controller. They are indexed by static variables in the Globals class. 
     */

    private SaveFileControllerInterface saveFileController;
    private AbstractSimulationFactory controller;

    /**
     * Initialize the state.
     * @param mainView the View that will be updated with the output
     * @param dartGunIterator the DartGunInterface collection 
     */
    public ModelResultsController(AbstractSimulationFactory controller)
    {
        this.controller = controller;
    }

    /**
     * Run the MAVN Algorithm.
     * To keep the interface simple, this is the only method used
     * to call the MAVN Algorithm. The specific implementation that is
     * called depends on the Output State Modules.
     */
    @Override
    public void runSimulation()
    {
        if (controller.getPropertiesState().isTarget())
        {
            controller.getModelAlgorithmsModelsList().get(Globals.SINGLE_POINT_ALGORITHM).calculate();
        }
        if (controller.getPropertiesState().isDart())
        {
            if (controller.getPropertiesState().isCaRng())
            {
                controller.getModelAlgorithmsModelsList().get(Globals.CELLULAR_AUTOMATON_ALGORITHM).calculate();
            }
            if (controller.getPropertiesState().isCmwcRng())
            {
                controller.getModelAlgorithmsModelsList().get(Globals.CMWC4096_ALGORITHM).calculate();
            }
            if (controller.getPropertiesState().isMtRng())
            {
                controller.getModelAlgorithmsModelsList().get(Globals.MT_ALGORITHM).calculate();
            }
            if (controller.getPropertiesState().isRandomRng())
            {
                controller.getModelAlgorithmsModelsList().get(Globals.JAVA_RNG_ALGORITHM).calculate();
            }
            if (controller.getPropertiesState().isXORRng())
            {
                controller.getModelAlgorithmsModelsList().get(Globals.XOR_ALGORITHM).calculate();
            }
        }

    }

    /**
     * Save the simulation results to an external file.
     */
    @Override
    public void saveResults()
    {
    }

    @Override
    public void updateAndLayerModelResult(double[][] andLayerResult)
    {
        controller.getModelResultModelsList().get(Globals.AND_LAYER_MODEL).setModelResult(andLayerResult);
    }

    @Override
    public void updateOrLayerModelResult(double[][] orLayerResult)
    {
        controller.getModelResultModelsList().get(Globals.OR_LAYER_MODEL).setModelResult(orLayerResult);
    }

    @Override
    public void updateModelResult(double[][] modelResult)
    {
        controller.getModelResultModelsList().get(Globals.SIMULATION_RESULT).setModelResult(modelResult);
    }

    @Override
    public void updateOutputLayerModelResult(double[][] outputLayerResult)
    {
        controller.getModelResultModelsList().get(Globals.OUTPUT_LAYER_MODEL).setModelResult(outputLayerResult);
    }

    @Override
    public void updateDartResults(ArrayList<double[][]> hit, ArrayList<double[][]> miss)
    {
        controller.getModelDartResultModelsList().get(Globals.DART_RESULT_MODEL).setDartResult(hit, miss);
    }

    @Override
    public void updateShapesRatioModelResult(double[][] shapesRatio)
    {
        controller.getModelResultModelsList().get(Globals.SHAPES_RATIO_RESULT).setModelResult(shapesRatio);
    }

    @Override
    public void updateImageRatioModelResult(double[][] imageRatio)
    {
        controller.getModelResultModelsList().get(Globals.IMAGE_RATIO_RESULT).setModelResult(imageRatio);
    }
}
