/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.mediator;

import mavn.spreadsheet.mediator.SSMediator;
import file.save.controller.SaveFileControllerInterface;
import mavn.algorithm.model.multiplePointSimulation.UniformMultiPointSimulation;
import mavn.algorithm.model.point.generator.CMWC4096PointGenerator;
import mavn.algorithm.model.point.generator.CellularAutomatonPointGenerator;
import mavn.algorithm.model.point.generator.JavaRandomPointGenerator;
import mavn.algorithm.model.point.generator.MersenneTwisterPointGenerator;
import mavn.algorithm.model.point.generator.XORShiftPointGenerator;
import mavn.algorithm.properties.view.SimulationPropertiesFrame;
import simulyn.algorithm.model.AlgorithmModelInterface;
import simulyn.mediator.SimulationMediatorInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class SimulationMediator implements SimulationMediatorInterface
{

    // Model Algorithm Models are responsible for running the simulation
    // with their algorithm. They use a Command Pattern and a SwingWorker
    // so the GUI won't hang on big computations.
    private AlgorithmModelInterface singlePointSimulation;
    private AlgorithmModelInterface uniformPointSimulation;
    private AlgorithmModelInterface gridPointSimulation;
    private OutputViewMediatorInterface mediator;
    private SaveFileControllerInterface saveFileController;
    private SimulationPropertiesFrame propertiesView;

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
    public SimulationMediator(AlgorithmModelInterface singlePointSimulation,
            AlgorithmModelInterface uniformPointSimulation,
            AlgorithmModelInterface gridPointSimulation,
            OutputViewMediatorInterface mediator,
            SimulationPropertiesFrame propertiesView)
    {
        this.uniformPointSimulation = uniformPointSimulation;
        this.mediator = mediator;
        this.propertiesView = propertiesView;
        this.singlePointSimulation = singlePointSimulation;
        this.gridPointSimulation = gridPointSimulation;
    }

    /**
     * Provides the logic for when a View Action requests that a new
     * Input Model should be loaded into the simulation.
     */
    @Override
    public void onLoadSimulationInputModel()
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
        if (((SSMediator) mediator).getSimulationPropertiesState().isTargetModel())
        {
            singlePointSimulation.execute();
        }
        if (((SSMediator) mediator).getSimulationPropertiesState().isPointGeneratedModel())
        {
            if (((SSMediator) mediator).getSimulationPropertiesState().isCaRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new CellularAutomatonPointGenerator());
                uniformPointSimulation.execute();
            }
            if (((SSMediator) mediator).getSimulationPropertiesState().isCmwcRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new CMWC4096PointGenerator());
                uniformPointSimulation.execute();
            }
            if (((SSMediator) mediator).getSimulationPropertiesState().isRandomRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new JavaRandomPointGenerator());
                uniformPointSimulation.execute();
            }
            if (((SSMediator) mediator).getSimulationPropertiesState().isMtRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new MersenneTwisterPointGenerator());
                uniformPointSimulation.execute();
            }
            if (((SSMediator) mediator).getSimulationPropertiesState().isXORRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new XORShiftPointGenerator());
                uniformPointSimulation.execute();
            }
        }
        if (((SSMediator) mediator).getSimulationPropertiesState().isGridGeneratedModel())
        {
            gridPointSimulation.execute();
        }
    }

    @Override
    public void onSaveSimulationInputModel()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
