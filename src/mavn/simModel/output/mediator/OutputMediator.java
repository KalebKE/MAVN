/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.mediator;

import mavn.simModel.output.view.mediator.OutputViewMediator;
import file.save.controller.SaveFileControllerInterface;
import javax.swing.table.DefaultTableModel;
import mavn.simModel.algorithm.model.multiplePointSimulation.UniformMultiPointSimulation;
import mavn.simModel.algorithm.model.point.generator.CMWC4096PointGenerator;
import mavn.simModel.algorithm.model.point.generator.CellularAutomatonPointGenerator;
import mavn.simModel.algorithm.model.point.generator.JavaRandomPointGenerator;
import mavn.simModel.algorithm.model.point.generator.MersenneTwisterPointGenerator;
import mavn.simModel.algorithm.model.point.generator.XORShiftPointGenerator;
import mavn.simModel.algorithm.properties.view.SimulationPropertiesFrame;
import simulyn.algorithm.model.AlgorithmModelInterface;
import simulyn.output.mediator.OutputMediatorInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class OutputMediator implements OutputMediatorInterface
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
    public OutputMediator(AlgorithmModelInterface singlePointSimulation,
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

    @Override
    public void onAnimateNetwork(boolean animate)
    {
        ((OutputViewMediator) mediator).getModelResultState().animate(animate);
    }

    /**
     * Provides the logic for when a View Action requests that
     * the Model Result be cleared from the View.
     */
    @Override
    public void onClearModelResult()
    {
        Object[] names =
        {
            "Simulation Results"
        };
        ((OutputViewMediator) mediator).setTableModel(new DefaultTableModel(names, 20));
        // Indicate that Simulation Model Results are now available.
        ((OutputViewMediator) mediator).getModelResultState().resultAvailable(false);
        mediator.updateUI();
    }

    /**
     * Provides the logic for when a View Action requests that a new
     * Input Model should be loaded into the simulation.
     */
    @Override
    public void onLoadModel()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Provides the logic for when a View Action requests that a new
     * set of Simulation Properties should be selected.
     */
    @Override
    public void onLoadProperties()
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
        if (((OutputViewMediator) mediator).getSimulationPropertiesState().isTargetModel())
        {
            singlePointSimulation.execute();
        }
        if (((OutputViewMediator) mediator).getSimulationPropertiesState().isPointGeneratedModel())
        {
            if (((OutputViewMediator) mediator).getSimulationPropertiesState().isCaRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new CellularAutomatonPointGenerator());
                uniformPointSimulation.execute();
            }
            if (((OutputViewMediator) mediator).getSimulationPropertiesState().isCmwcRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new CMWC4096PointGenerator());
                uniformPointSimulation.execute();
            }
            if (((OutputViewMediator) mediator).getSimulationPropertiesState().isRandomRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new JavaRandomPointGenerator());
                uniformPointSimulation.execute();
            }
            if (((OutputViewMediator) mediator).getSimulationPropertiesState().isMtRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new MersenneTwisterPointGenerator());
                uniformPointSimulation.execute();
            }
            if (((OutputViewMediator) mediator).getSimulationPropertiesState().isXORRng())
            {
                ((UniformMultiPointSimulation) uniformPointSimulation).setPointGenerator(new XORShiftPointGenerator());
                uniformPointSimulation.execute();
            }
        }
        if (((OutputViewMediator) mediator).getSimulationPropertiesState().isGridGeneratedModel())
        {
            gridPointSimulation.execute();
        }
    }

    /**
     * Provides the logic for when a View Action requests that the Result
     * Models should be persisted.
     */
    @Override
    public void onSaveResults()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
