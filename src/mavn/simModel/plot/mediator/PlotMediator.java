/*
ResultPlotMediator -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.plot.mediator;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import mavn.simModel.algorithm.model.multiplePointSimulation.UniformMultiPointSimulation;
import mavn.simModel.algorithm.model.singlePointSimulation.SinglePointSimulation;
import mavn.simModel.input.model.observer.TargetModelObserver;
import mavn.simModel.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.simModel.plot.mediator.worker.MutliplePointModelPlotWorker;
import mavn.simModel.plot.mediator.worker.SinglePointModelPlotWorker;
import mavn.simModel.output.mediator.state.SinglePointInputModelState;
import mavn.simModel.output.model.SimulationOutputModel;
import mavn.simModel.plot.model.PointOutputModelInterface;
import mavn.simModel.plot.model.PointOutputModel;
import mavn.simModel.plot.model.observer.PointOutputModelObserver;
import mavn.simModel.output.model.observer.OutputModelObserver;
import mavn.simModel.output.view.state.OutputModelStateInterface;
import org.math.plot.Plot2DPanel;
import simulyn.algorithm.model.AlgorithmModelInterface;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.PlotViewInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;
import simulyn.ui.components.plotter.SimulynDefaultPlotView;

/**
 * ModelViewMediatorInterface implementations are used to completely decouple
 * the Model from the View using a Model-View-Mediator (MVM) architecture. This
 * pattern is also known as a Model-View-Presenter using a Passive View strategy.
 *
 * Mediators differ from Controllers in how they couple the View to the Model.
 * With a Controller, the View requests Actions to be taken upon the Model via
 * the Controller and the Model will then update the View with the new State.
 * With a Mediator, the View requests Actions to be taken upon the Model via the
 * Mediator and the Model will update the Mediator with the new State. The Mediator
 * will then provide the View with the new State.
 *
 * An MVM is used to manage the Views for the Network UI, Plot UI, and Result UI.
 * This is because we want the UI's to be completely decoupled from any form
 * of a model or controller, mediator, presenter, etc... We want the UI's to be as
 * decoupled and as portable as possible so they can be reused in many ways.
 * But we don't know how the UI's might be used in the future. This is why
 * the MVM is used.
 *
 * ResultPlotMediator is a implementation of the Simulyn Framework that
 * provides a coupling between MAVN's Result Models, Input Models, and Result Views.
 * It manages a Plot UI View that is designed to allow the user
 * to view the simulation results once the Input Model has been loaded into the simluation.
 * It provides the logic for the View's Actions and renders the Model Results with a
 * Plot used to back a Scatter Plot within the View.
 * @author Kaleb
 */
public class PlotMediator implements OutputViewMediatorInterface,
        PlotMediatorInterface, OutputModelObserver,
        PointOutputModelObserver, TargetModelObserver
{
    // Model Algorithm Models are responsible for running the simulation
    // with their algorithm. They use a Command Pattern and a SwingWorker
    // so the GUI won't hang on big computations.

    private AlgorithmModelInterface singlePointSimulation;
    private AlgorithmModelInterface multiplePointSimulation;
    // Table Model for the View's JTable that is pushed to the View
    // once the the Result Model State has been added to the tableModel.
    private PointOutputModelInterface pointModelOutput;
    private MediatorStateInterface singlePointSimInputModelState;
    // The View the Mediator manages
    private SimulynDefaultPlotView view;
    // The Plot2DPanel that backs the Plot View. The Plot2DPanel has its
    // State set here and is then pushed to the Plot View.
    private Plot2DPanel plot;
    // Simulation Properties State Manager
    private SimulationPropertiesStateInterface simulationPropertiesState;
    // Model Result Models are Observers of the Algorithm Models
    private OutputModelInterface simulationModelResult;
    // Simulation Model State Manger
    private OutputModelStateInterface modelResultState;
    // The SwingWorker is used to create the new Plot for the
    // Plot View. This SwingWorker manages results from MultiplePointSimulations.
    private SwingWorker loadDartSimResult;
    // The SwingWorker is used to create the new Plot for the
    // Plot View. This SwingWorker manages results from SinglePointSimulations.
    private SwingWorker loadTargetResult;

    /**
     * Initialize the state.
     * @param mainView the View that will be updated with the output
     * @param dartGunIterator the DartGunInterface collection 
     */
    public PlotMediator(AlgorithmModelInterface singlePointSimulation,
            AlgorithmModelInterface multiplePointSimulation,
            PointOutputModelInterface pointModelOutput, OutputModelInterface simulationModelResult)
    {

        view = new SimulynDefaultPlotView();

        // local instances of the simulation model
        this.singlePointSimulation = singlePointSimulation;
        this.multiplePointSimulation = multiplePointSimulation;

        // local instance of the result model
        this.simulationModelResult = simulationModelResult;
        // this class should observe changes to the result model
        ((SimulationOutputModel) this.simulationModelResult).registerObserver(this);
        // have the result model observe changes to the simulation model
        ((SinglePointSimulation) this.singlePointSimulation).registerObserver((SimulationOutputModel) this.simulationModelResult);

        // local instance of the result model
        this.pointModelOutput = pointModelOutput;
        // this class should observe changes to the result model
        ((PointOutputModel) this.pointModelOutput).registerObserver(this);
        // have the result model observe changes to the simulation model
        ((UniformMultiPointSimulation) this.multiplePointSimulation).registerObserver((PointOutputModel) this.pointModelOutput);
    }

    @Override
    public JPanel getView()
    {
        return view;
    }

    /**
     * Returns an instance of the Plot that is currently being used and
     * will eventually be pushed to the View after the State is added
     * by one of the SwingWorkers.
     * @return a Plot with or without State.
     */
    @Override
    public Plot2DPanel getPlot()
    {
        return plot;
    }

    @Override
    public void onLinePlot()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onScatterPlot()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Hook to Observe the Simulation Model Result Subject. This method is called
     * when a new Model Result is available.
     * @param modelResult the State of the Result Model, either a hit or a miss.
     */
    @Override
    public void updateModelResult(double[][] modelResult)
    {
        // Indicate that Simulation Model Results are now available.
        modelResultState.resultAvailable();

        // If a Single Point Simluation from a Target Point is being used AND
        // the Input Model for the Single Point Simluation is available.
        if (simulationPropertiesState.isTarget() && ((SinglePointInputModelState) singlePointSimInputModelState).isInputModelTargetReady())
        {
            // Create a new Plot for the SwingWorker to add State too.
            plot = new Plot2DPanel();
            // Create a new new Point from the X and Y coordinates from the Target Input Model.
            Point point = new Point();
            point.setLocation(((SinglePointInputModelState) singlePointSimInputModelState).getInputModelTarget()[0][0],
                    ((SinglePointInputModelState) singlePointSimInputModelState).getInputModelTarget()[1][0]);

            // Pass off the creation of the Plot to a Command Pattern with a SwingWorker
            // to ensure the GUI doesn't hang. The Command Pattern will use the new Plot,
            // add the Simulation Model Result's State to the Plot, and call updateUI()
            // to push the Plot to the View.
            loadTargetResult = new SinglePointModelPlotWorker(modelResult, this, point);
            loadTargetResult.execute();
        }
    }

    /**
     * Hook to Observe the Dart Model Result Subject. The State of the
     * Model Result describes how many points hit shapes in the image and how many
     * points missed shapes in the image.
     * @param hit
     * @param miss
     */
    @Override
    public void updateDartResults(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        plot = new Plot2DPanel();
        loadDartSimResult = new MutliplePointModelPlotWorker(hit, miss, this);
        loadDartSimResult.execute();
    }

    /**
     * Hook to Observe the State of Target Model Input Subjects.
     * The State is eventually pushed to the View.
     * @param modelInput the State of Target Model Input Subjects.
     */
    @Override
    public void updateTargetModelInput(double[][] modelInput)
    {
        // Set the modelInput to the Mediators State so it can be worked with locally.
        ((SinglePointInputModelState) singlePointSimInputModelState).setInputModelTarget(modelInput);
    }

    /**
     * Called when the Mediator has new State ready to be pushed to the View.
     */
    @Override
    public void updateUI()
    {
        ((PlotViewInterface) view).setPlot(plot);
    }
}
