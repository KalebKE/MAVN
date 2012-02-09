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
package mavn.controller;

import file.controller.FileController;
import file.controller.FileControllerInterface;
import file.model.SaveCSVFileModel;
import file.model.SaveFileModelInterface;
import java.util.ArrayList;
import mavn.globals.Globals;
import mavn.math.model.MavnAlgorithmModelInterface;
import mavn.state.OutputStateInterface;
import mavn.state.RngStateInterface;

/**
 * OutputController is an implementatoin of OutputController Interface that
 * manages the output of the application. 
 * @author Kaleb
 */
public class OutputController implements OutputControllerInterface
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

    private FileControllerInterface fileController;
    private SaveFileModelInterface saveFileModel;
    private ArrayList<MavnAlgorithmModelInterface> algorithms;
    private ArrayList<OutputStateInterface> outputStates;
    private String results;
    private boolean resultsSet;

    /**
     * Initialize the state.
     * @param mainView the View that will be updated with the output
     * @param dartGunIterator the DartGunInterface collection 
     */
    public OutputController(ArrayList<MavnAlgorithmModelInterface> algorithms, ArrayList<OutputStateInterface> outputStates)
    {
        this.algorithms = algorithms;
        this.outputStates = outputStates;
        saveFileModel = new SaveCSVFileModel();
        fileController = new FileController(saveFileModel);
        resultsSet = false;
    }

    /**
     * Get the results of the simulation.
     * @return the results of the simulation
     */
    @Override
    public String getResults()
    {
        return results;
    }

    /**
     * Check if their are simulation results available.
     * @return boolean indicating available results
     */
    @Override
    public boolean isResultsSet()
    {
        return resultsSet;
    }

    /**
     * Run the MAVN Algorithm.
     * To keep the interface simple, this is the only method used
     * to call the MAVN Algorithm. The specific implementation that is
     * called depends on the Output State Modules.
     */
    @Override
    public void runMavnAlgorithm()
    {
        // If the user wants to only fire *one* dart.
        if (!((RngStateInterface) outputStates.get(Globals.RESULTS_STATE)).isDartGunState())
        {
            algorithms.get(Globals.SINGLE_POINT_ALGORITHM).calculate();
        }
        // If the user wants to fire *multiple* random darts.
        if (((RngStateInterface) outputStates.get(Globals.RESULTS_STATE)).isDartGunState())
        {
            // Use the CMWC4096 RNG to produce the darts.
            if (((RngStateInterface) outputStates.get(Globals.RESULTS_STATE)).isCmwcRng())
            {
                algorithms.get(Globals.CMWC4096_ALGORITHM).calculate();
            }
            // Use the CA RNG to produce the darts.
            if (((RngStateInterface) outputStates.get(Globals.RESULTS_STATE)).isCaRng())
            {
                algorithms.get(Globals.CELLULAR_AUTOMATON_ALGORITHM).calculate();
            }
            // Use the MersenneTwister RNG to produce the darts.
            if (((RngStateInterface) outputStates.get(Globals.RESULTS_STATE)).isMtRng())
            {
                algorithms.get(Globals.MT_ALGORITHM).calculate();
            }
            // Use java.util.random to produce the darts.
            if (((RngStateInterface) outputStates.get(Globals.RESULTS_STATE)).isRandomRng())
            {
                algorithms.get(Globals.JAVA_RNG_ALGORITHM).calculate();
            }
            // Use XORSHIFT RNG to produce the darts. 
            if (((RngStateInterface) outputStates.get(Globals.RESULTS_STATE)).isxORRng())
            {
                algorithms.get(Globals.XOR_ALGORITHM).calculate();
            }
        }
    }

    /**
     * Save the simulation results to an external file.
     */
    @Override
    public void saveResults()
    {
        fileController.getSaveFileChooser(results);
    }

    /**
     * Set the results of the simulation.
     * @param results the simulation results.
     */
    @Override
    public void setResults(String results)
    {
        this.setResultsSet(true);
        this.results = results;
    }

    /**
     * Indicate if their are simulation results available.
     * @param resultsSet boolean indicating available results.
     */
    @Override
    public void setResultsSet(boolean resultsSet)
    {
        this.resultsSet = resultsSet;
    }
}
