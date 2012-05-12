/*
SimulationMediator -- A class within the Machine Artificial Vision
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
package mavn.simulation.mediator;

import file.open.controller.directory.OpenSpreadsheetDirectoryController;
import file.open.observer.OpenFileObserver;
import file.save.controller.directory.SaveDirectoryControllerInterface;
import file.save.controller.directory.SaveSpreadsheetDirectoryController;
import java.util.ArrayList;
import mavn.algorithm.model.multiplePointSimulation.MonteCarloSimulation;
import mavn.algorithm.model.point.generator.CMWC4096PointGenerator;
import mavn.algorithm.model.point.generator.CellularAutomatonPointGenerator;
import mavn.algorithm.model.point.generator.JavaRandomPointGenerator;
import mavn.algorithm.model.point.generator.MersenneTwisterPointGenerator;
import mavn.algorithm.model.point.generator.XORShiftPointGenerator;
import mavn.algorithm.properties.view.SimulationPropertiesFrame;
import mavn.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.plot.mediator.PlotMediatorInterface;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;
import mavn.simulation.view.state.simulator.SimulationTypeViewStateInterface;
import mavn.spreadsheet.mediator.SSMediatorInterface;
import simulyn.algorithm.model.AlgorithmModelInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.simulation.mediator.SimulationMediatorInterface;


/**
 * SimulationMediator can be thought of as the master mediator for the simulation.
 * It coordinates the Simulations Controllers/Mediators and allows them
 * to stay fairly decoupled from each.
 * @author Kaleb
 */
public class SimulationMediator implements SimulationMediatorInterface
{
    private ArrayList<InputModelInterface> inputModels;
    private ArrayList<OpenFileObserver> fileObservers;
    // Model Algorithm Models are responsible for running the simulation
    // with their algorithm. They use a Command Pattern and a SwingWorker
    // so the GUI won't hang on big computations.
    private AlgorithmModelInterface diagnosticSimulation;
    private AlgorithmModelInterface monteCarloSimluation;
    private AlgorithmModelInterface pixelGridSimulation;
    private NetworkMediatorInterface networkMediator;
    private PlotMediatorInterface plotMediator;
    private SimulationPropertiesStateInterface simulationPropertiesState;
    private SimulationPropertiesFrame propertiesView;
    private SimulationViewInputStateInterface simulationViewState;
    private SimulationTypeViewStateInterface simulationTypeState;
    private SSMediatorInterface ssMediator;
    private final String[] fileNames =
    {
        "target", "theta", "w0", "w1", "w2"
    };

   /**
    * Initialize a new SimulationMediator.
    * @param inputModels the simulations Input Models.
    * @param diagnosticSimulation the desired Diagnostic Simulation.
    * @param monteCarloSimulation the desired Monte Carlo Simulation.
    * @param pixelGridSimulation the desired Pixel Grid Simulation.
    * @param networkMediator the Mediator for the Network Output.
    * @param plotMediator the Mediator for the Plot Output.
    * @param simulationPropertiesState the Simulation Properties.
    * @param propertiesView the Simulation Properties View.
    * @param ssMediator the Mediator for the Plot Output.
    */
    public SimulationMediator(
            ArrayList<InputModelInterface> inputModels,
            AlgorithmModelInterface diagnosticSimulation,
            AlgorithmModelInterface monteCarloSimulation,
            AlgorithmModelInterface pixelGridSimulation,
            NetworkMediatorInterface networkMediator,
            PlotMediatorInterface plotMediator,
            SimulationPropertiesStateInterface simulationPropertiesState,
            SimulationPropertiesFrame propertiesView,
            SSMediatorInterface ssMediator)
    {
        this.pixelGridSimulation = pixelGridSimulation;
        this.inputModels = inputModels;
        this.networkMediator = networkMediator;
        this.plotMediator = plotMediator;
        this.propertiesView = propertiesView;
        this.diagnosticSimulation = diagnosticSimulation;
        this.simulationPropertiesState = simulationPropertiesState;
        this.ssMediator = ssMediator;
        this.monteCarloSimluation = monteCarloSimulation;

        // Cast the InputModels into OpenFileObservers so we can use them
        // to load the InputModels from external files.
        fileObservers = new ArrayList<OpenFileObserver>();
        for (int i = 0; i < this.inputModels.size(); i++)
        {
            fileObservers.add((OpenFileObserver) this.inputModels.get(i));
        }
    }

    /**
     * Provides the logic for when a View Action requests that a new
     * Input Model should be loaded into the simulation.
     */
    @Override
    public void onLoadSimulationInputModel()
    {
        OpenSpreadsheetDirectoryController importModel =
                new OpenSpreadsheetDirectoryController(fileObservers);
        importModel.getDirectoryChooser();
    }

    /**
     * Provides the logic for when a View Action requests that a new
     * set of Simulation Properties should be selected.
     */
    @Override
    public void onLoadSimulationProperties()
    {
        propertiesView.setVisible(true);
    }

    /**
     * Provides the logic for when a View Action requests that a new simulation
     * should be run using the current Input Models and the result should be
     * made available to the Output Models.
     */
    @Override
    public void onRunSimulation()
    {
        if (simulationPropertiesState.isDiagnosticSimulation())
        {
            diagnosticSimulation.execute();
        }
        if (simulationPropertiesState.isMonteCarloSimulation())
        {
            if (simulationPropertiesState.isCaRng())
            {
                ((MonteCarloSimulation) monteCarloSimluation).setPointGenerator(new CellularAutomatonPointGenerator());
                monteCarloSimluation.execute();
            }
            if (simulationPropertiesState.isCmwcRng())
            {
                ((MonteCarloSimulation) monteCarloSimluation).setPointGenerator(new CMWC4096PointGenerator());
                monteCarloSimluation.execute();
            }
            if (simulationPropertiesState.isRandomRng())
            {
                ((MonteCarloSimulation) monteCarloSimluation).setPointGenerator(new JavaRandomPointGenerator());
                monteCarloSimluation.execute();
            }
            if (simulationPropertiesState.isMtRng())
            {
                ((MonteCarloSimulation) monteCarloSimluation).setPointGenerator(new MersenneTwisterPointGenerator());
                monteCarloSimluation.execute();
            }
            if (simulationPropertiesState.isXORRng())
            {
                ((MonteCarloSimulation) monteCarloSimluation).setPointGenerator(new XORShiftPointGenerator());
                monteCarloSimluation.execute();
            }
        }
        if (simulationPropertiesState.isPixelGridSimulation())
        {
            pixelGridSimulation.execute();
        }
    }

    /**
     * Save the simulation Input Model's. 
     */
    @Override
    public void onSaveSimulationInputModel()
    {
        ArrayList<double[][]> models = new ArrayList<double[][]>();

        for (int i = 0; i < inputModels.size(); i++)
        {
            models.add(this.inputModels.get(i).getModelInput());
        }

        SaveDirectoryControllerInterface saveDirectoryController = new SaveSpreadsheetDirectoryController(models, fileNames);
    }

    /**
     * Clear the simulation Input Models and reset to the default state.
     */
    @Override
    public void onClearSimulation()
    {
        for (int i = 0; i < this.inputModels.size(); i++)
        {
            inputModels.get(i).setModelInput(new double[0][0]);
            inputModels.get(i).setModelInputReady(false);
        }

        double[][] w2 = new double[0][0];
        double[][] w1 = new double[0][0];
        double[][] w0 = new double[0][0];

        this.ssMediator.onClearModelResult();
        this.plotMediator.onClearUI();
        this.networkMediator.setNetwork(w2, w1, w0);
        this.simulationViewState.onPropertiesUnloaded();
        this.simulationViewState.onSimulationUnloaded();
        this.simulationPropertiesState.onMonteCarloSimulation();
        this.simulationTypeState.onMonteCarloSimulationView();
    }

    /**
     * Set the SimulationTypeState.
     * @param simulationTypeState the simulation Type State.
     */
    public void setSimulationTypeState(SimulationTypeViewStateInterface simulationTypeState)
    {
        this.simulationTypeState = simulationTypeState;
    }

    /**
     * Set the SimulationViewState.
     * @param simulationViewState the simulation View State.
     */
    public void setSimulationViewState(SimulationViewInputStateInterface simulationViewState)
    {
        this.simulationViewState = simulationViewState;
    }
}
