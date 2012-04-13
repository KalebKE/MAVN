/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import simulyn.mediator.SimulationMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class SimulationMediator implements SimulationMediatorInterface
{

    private ArrayList<InputModelInterface> inputModels;
    private ArrayList<OpenFileObserver> fileObservers;
    // Model Algorithm Models are responsible for running the simulation
    // with their algorithm. They use a Command Pattern and a SwingWorker
    // so the GUI won't hang on big computations.
    private AlgorithmModelInterface singlePointSimulation;
    private AlgorithmModelInterface uniformPointSimulation;
    private AlgorithmModelInterface gridPointSimulation;
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
     * @param singlePointSimulation the SinglePointSimulation implementation to
     * be used by the Mediator. It knows how to run a MAVN SinglePointSimulation.
     * It is a Subject in a Observer Pattern and is Observed by the Simulation
     * Result Models.
     *
     * @param uniformPointSimulation the MultiplePointSimulatoin implenation to
     * be used by the Mediator. It knows how to run a MAVN MultiplePointSimulation.
     * It is a Subject in a Observer Pattern and is Observed by the Simulation
     * Result Models.
     * @param mediator
     */
    public SimulationMediator(
            ArrayList<InputModelInterface> inputModels,
            AlgorithmModelInterface singlePointSimulation,
            AlgorithmModelInterface uniformPointSimulation,
            AlgorithmModelInterface gridPointSimulation,
            NetworkMediatorInterface networkMediator,
            PlotMediatorInterface plotMediator,
            SimulationPropertiesStateInterface simulationPropertiesState,
            SimulationPropertiesFrame propertiesView,
            SSMediatorInterface ssMediator)
    {
        this.gridPointSimulation = gridPointSimulation;
        this.inputModels = inputModels;
        this.networkMediator = networkMediator;
        this.plotMediator = plotMediator;
        this.propertiesView = propertiesView;
        this.singlePointSimulation = singlePointSimulation;
        this.simulationPropertiesState = simulationPropertiesState;
        this.ssMediator = ssMediator;
        this.uniformPointSimulation = uniformPointSimulation;

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
        OpenSpreadsheetDirectoryController importModel = new OpenSpreadsheetDirectoryController(fileObservers);
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
     * made available to the Result Models.
     */
    @Override
    public void onRunSimulation()
    {
        if (simulationPropertiesState.isDiagnosticSimulation())
        {
            singlePointSimulation.execute();
        }
        if (simulationPropertiesState.isMonteCarloSimulation())
        {
            if (simulationPropertiesState.isCaRng())
            {
                ((MonteCarloSimulation) uniformPointSimulation).setPointGenerator(new CellularAutomatonPointGenerator());
                uniformPointSimulation.execute();
            }
            if (simulationPropertiesState.isCmwcRng())
            {
                ((MonteCarloSimulation) uniformPointSimulation).setPointGenerator(new CMWC4096PointGenerator());
                uniformPointSimulation.execute();
            }
            if (simulationPropertiesState.isRandomRng())
            {
                ((MonteCarloSimulation) uniformPointSimulation).setPointGenerator(new JavaRandomPointGenerator());
                uniformPointSimulation.execute();
            }
            if (simulationPropertiesState.isMtRng())
            {
                ((MonteCarloSimulation) uniformPointSimulation).setPointGenerator(new MersenneTwisterPointGenerator());
                uniformPointSimulation.execute();
            }
            if (simulationPropertiesState.isXORRng())
            {
                ((MonteCarloSimulation) uniformPointSimulation).setPointGenerator(new XORShiftPointGenerator());
                uniformPointSimulation.execute();
            }
        }
        if (simulationPropertiesState.isPixelGridSimulation())
        {
            gridPointSimulation.execute();
        }
    }

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

    public void setSimulationTypeState(SimulationTypeViewStateInterface simulationTypeState)
    {
        this.simulationTypeState = simulationTypeState;
    }

    public void setSimulationViewState(SimulationViewInputStateInterface simulationViewState)
    {
        this.simulationViewState = simulationViewState;
    }
}
