/*
PlotMediator -- a class within the Machine Artificial Vision Network
(Machine Artificial Vision Network).
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
package mavn.plot.mediator;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import mavn.algorithm.model.point.Point;
import mavn.input.model.observer.TargetInputModelObserver;
import mavn.input.model.TargetInputModel;
import mavn.network.mediator.NetworkMediator;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.plot.mediator.worker.MultiplePointModelPlotWorker;
import mavn.plot.mediator.worker.SinglePointModelPlotWorker;
import mavn.simulation.inputModel.state.SimulationTargetInputModelState;
import mavn.spreadsheet.model.SimulationOutputModel;
import mavn.plot.model.PointOutputModelInterface;
import mavn.plot.model.PointOutputModel;
import mavn.plot.model.observer.PointOutputModelObserver;
import mavn.spreadsheet.model.observer.OutputModelObserver;
import mavn.plot.mediator.state.PlotMediatorViewStateInterface;
import mavn.plot.model.PointHitOutputModel;
import mavn.plot.model.PointHitOutputModelInterface;
import mavn.plot.model.PointMissOutputModel;
import mavn.plot.model.PointMissOutputModelInterface;
import mavn.plot.model.TimerOutputModel;
import mavn.plot.model.TimerOutputModelInterface;
import mavn.plot.model.observer.PointHitOutputModelObserver;
import mavn.plot.model.observer.PointMissOutputModelObserver;
import mavn.plot.model.observer.TimerOutputModelObserver;
import org.math.plot.Plot2DPanel;
import simulyn.input.model.InputModelInterface;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.PlotViewInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;
import simulyn.ui.components.plotter.SimulynDefaultPlotView;

/**
 * OutputViewMediatorInterface implementations are used to completely decouple
 * the Model from the View using a Model-View-Mediator (MVM) architecture. This
 * pattern is also known as a Model-View-Presenter using a Passive View strategy.
 * It has a single method used to update the view with new state: updateUI().
 *
 * Mediators differ from Controllers in how they couple the View to the Model.
 *
 * With a Controller, the View requests Actions to be taken upon the Model via
 * the Controller. The Controller will call the Model and when the the Model is
 * ready it will update the View with the new State.
 * With a Mediator, the View requests Actions to be taken upon the Model via the
 * Mediator and the Model will update the Mediator (not the View) with the new
 * State. After the the Mediator appropriately renders the State, the
 * Mediator will then provide the View with the rendered State.
 *
 * An MVM is used to manage the Views for the Network UI, Plot UI, and Spreadsheet UI.
 * This is because we want the UI's to be completely decoupled from any form
 * of a model or controller, mediator, presenter, etc... We want the UI's to be as
 * decoupled and as portable as possible so they can be reused in many ways.
 *
 * PlotMediator is a implementation providing a coupling between
 * the Output Models and the Output Views. It manages a Plot UI View that is
 * designed to allow the user to view the simulations Output Models in a graphical
 * environment. It provides the logic for the View's Actions and renders the
 * Output Models with a two-dimensional Plot used to render a Scatter Plot
 * and Line Plot.
 * @author Kaleb
 */
public class PlotMediator implements OutputViewMediatorInterface,
        OutputModelObserver, PlotMediatorInterface,
        PointOutputModelObserver, PointHitOutputModelObserver,
        PointMissOutputModelObserver, TargetInputModelObserver,
        TimerOutputModelObserver
{
    // Lists to keep track of model outputs locally so they can
    // be rendered and pushed to the Plot View.

    private ArrayList<Point> timerPointList;
    private ArrayList<Point> hitPointList;
    private ArrayList<Point> missPointList;
    // Target Input Model is used to render the Diagnostic Simulation
    // Plot View.
    private InputModelInterface targetInputModel;
    // Target Input Model State used for Diagnostic Simulations
    private MediatorStateInterface plotMediatorInputModelState;
    // Network Mediator for Animated Simulations.
    private NetworkMediatorInterface networkMediator;
    // The Plot Mediator's View State.
    private PlotMediatorViewStateInterface plotViewState;
    // The Point Output Model for non-Animated Simulations.
    private PointOutputModelInterface pointOutputModel;
    // The Point Output Models for Animated Simulations
    private PointHitOutputModelInterface pointHitOutputModel;
    private PointMissOutputModelInterface pointMissOutputModel;
    // The Plot2DPanel that backs the Plot View. The Plot2DPanel has its
    // State set here and is then pushed to the Plot View.
    private Plot2DPanel plot;
    // Model Result Models are Observers of the Algorithm Models
    private OutputModelInterface plotOutputModel;
    // The SwingWorker is used to create the new Plot for the
    // Plot View. This SwingWorker manages results from MultiplePointSimulations.
    // The View the Mediator manages
    private SimulynDefaultPlotView view;
    // Swing Worker to render Multiple Point Simulation Output Models when
    // the Simulation is not Animated. Keeps the UI from hanging for a second
    // while the simulations Output Models render. 
    private SwingWorker renderPointOutputModels;
    // The SwingWorker is used to create the new Plot for the
    // Plot View. This SwingWorker manages results from Single Point
    // Diagnostic Simulations.
    private SwingWorker renderDiagnosticOutputModels;
    // The Timer Output Model for simulations that are timed and rendered
    // with a Line Plot. 
    private TimerOutputModelInterface timerOutputModel;

    /**
     * Initialized the Plot Mediator.
     *
     * @param targetInputModel the Target Input Model used for Diagnostic Single Point
     * Simulations
     * @param networkMediator the Network Mediator for Animated simulation State.
     * @param plotOutputModel the Plot Output Model Subject to Register as a Simulation
     * Output Model Observer.
     * @param pointOutputModel the Point Output Model Subject to Register as a Point
     * Output Model Observer.
     * @param pointHitOutputModel the Point Hit Output Model Subject to Register
     * as a Point Hit Output Model Observer.
     * @param pointMissOutputModel the Point Miss Output Model Subject to Register
     * as a Point Miss Output Model Observer.
     * @param timerOutputModel the Timer Output Model Subject to Register as a
     * Timer Output Model Observer. 
     */
    public PlotMediator(
            InputModelInterface targetInputModel,
            NetworkMediatorInterface networkMediator,
            OutputModelInterface plotOutputModel,
            PointOutputModelInterface pointOutputModel,
            PointHitOutputModelInterface pointHitOutputModel,
            PointMissOutputModelInterface pointMissOutputModel,
            TimerOutputModelInterface timerOutputModel)
    {
        this.networkMediator = networkMediator;
        this.pointHitOutputModel = pointHitOutputModel;
        this.pointMissOutputModel = pointMissOutputModel;
        this.timerOutputModel = timerOutputModel;
        this.targetInputModel = targetInputModel;
        ((TargetInputModel) this.targetInputModel).registerObserver((TargetInputModelObserver) this);

        // local instance of the result model
        this.plotOutputModel = plotOutputModel;
        // this class should observe changes to the result model
        ((SimulationOutputModel) this.plotOutputModel).registerObserver((OutputModelObserver) this);

        // local instance of the result model
        this.pointOutputModel = pointOutputModel;
        // this class should observe changes to the result model
        ((PointOutputModel) this.pointOutputModel).registerObserver((PointOutputModelObserver) this);
        // this class should observe changes to the result model
        ((PointHitOutputModel) this.pointHitOutputModel).registerObserver((PointHitOutputModelObserver) this);
        // this class should observe changes to the result model
        ((PointMissOutputModel) this.pointMissOutputModel).registerObserver((PointMissOutputModelObserver) this);
        ((TimerOutputModel) this.timerOutputModel).registerObserver((TimerOutputModelObserver) this);

        // Create a new instance of the View.
        view = new SimulynDefaultPlotView();
        // Create a new instance of the Input Model State required
        // for Single Point Diagnostic Simulations.
        plotMediatorInputModelState = new SimulationTargetInputModelState();
        // Create a new Plot that will eventually be set with
        // the simulations Output Model State and then pushed to the View.
        plot = new Plot2DPanel();

        // Lists to keep track of the point hits and misses that will be
        // rendered with the Plot.
        hitPointList = new ArrayList<Point>();
        missPointList = new ArrayList<Point>();
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

    /**
     * Get the Plot Mediator View. 
     * @return the JPanel representing the Plot Mediator View.
     */
    @Override
    public JPanel getView()
    {
        return view;
    }

    /**
     * Remove all plots from the current Plot and update the View.
     */
    @Override
    public void onClearUI()
    {
        plot.removeAllPlots();
        updateUI();
    }

    /**
     * Renders a Line Plot representing the amount of time the simulation
     * took to run versus the number of inputs fed into the simulations
     * network. 
     */
    @Override
    public void onLinePlot()
    {
        plot = new Plot2DPanel();

        double[][] pointArray = new double[timerPointList.size()][2];

        for (int i = 0; i < pointArray.length; i++)
        {
            pointArray[i][0] = timerPointList.get(i).getX();
            pointArray[i][1] = timerPointList.get(i).getY();
        }

        plot.addLinePlot("", pointArray);

        this.updateUI();
        this.plotViewState.onLinePlot();
    }

    /**
     * Renders a Scatter Plot representing the Points that hit shapes and Points
     * that missed shapes during the simulation
     */
    @Override
    public void onScatterPlot()
    {
        this.updatePoints(this.hitPointList, this.missPointList);
        this.plotViewState.onScatterPlot();
    }

    /**
     * Set the Plot View State for the Plot Mediator. Plot View State
     * keeps track of what View should be rendered and when. 
     * @param plotViewState the Plot View State implementation that will
     * be used by the Plot Mediator. 
     */
    public void setPlotViewState(PlotMediatorViewStateInterface plotViewState)
    {
        this.plotViewState = plotViewState;
        this.plotViewState.onScatterPlot();
    }

    /**
     * The Hook to Observe Point Hit Output Model Subjects to be notified
     * when new Point Hit State is available.
     * @param point the Point representing a hit on the image.
     */
    @Override
    public void updatePointHit(Point point)
    {
        if (point != null && ((NetworkMediator) networkMediator).getNetworkModelState().isAnimated())
        {
            double[][] points =
            {
                {
                    point.getX(), point.getY()
                }
            };

            ((PlotViewInterface) view).setHitVector(points);
        }
    }

    /**
     * The Hook to Observe Point Miss Output Model Subjects to be notified
     * when new Point Miss State is available.
     * @param point the Point representing a miss on the image.
     */
    @Override
    public void updatePointMiss(Point point)
    {
        if (point != null && ((NetworkMediator) networkMediator).getNetworkModelState().isAnimated())
        {
            double[][] points =
            {
                {
                    point.getX(), point.getY()
                }
            };

            ((PlotViewInterface) view).setMissVector(points);
        }
    }

    /**
     * Hook to Observe the Simulation Model Result Subject. This method is called
     * when a new Model Result is available.
     * @param modelResult the State of the Result Model, either a hit or a miss.
     */
    @Override
    public void updateModelResult(double[][] modelResult)
    {
        {
            // Create a new Plot for the SwingWorker to add State too.
            plot = new Plot2DPanel();
            // Create a new new Point from the X and Y coordinates from the Target Input Model.
            Point point = new Point();
            point.setLocation(((SimulationTargetInputModelState) plotMediatorInputModelState).getTargetInputModel()[0][0],
                    ((SimulationTargetInputModelState) plotMediatorInputModelState).getTargetInputModel()[1][0]);

            // Pass off the creation of the Plot to a Command Pattern with a SwingWorker
            // to ensure the GUI doesn't hang. The Command Pattern will use the new Plot,
            // add the Simulation Model Result's State to the Plot, and call updateUI()
            // to push the Plot to the View.
            renderDiagnosticOutputModels = new SinglePointModelPlotWorker(modelResult, this, point);
            renderDiagnosticOutputModels.execute();
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
    public void updatePoints(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        this.hitPointList = hit;
        this.missPointList = miss;

        if (!((NetworkMediator) networkMediator).getNetworkModelState().isAnimated())
        {
            plot = new Plot2DPanel();
            renderPointOutputModels = new MultiplePointModelPlotWorker(this.hitPointList, this.missPointList, this);
            renderPointOutputModels.execute();
        }
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
        ((SimulationTargetInputModelState) plotMediatorInputModelState).setTargetInputModel(modelInput);
    }

    /**
     * Hook to Observe the Timer Output Model Subject to be notified when
     * new Timer State is available.
     * @param timerPoints the Timer Points from the simualtion.
     */
    @Override
    public void updateTimerOutput(ArrayList<Point> timerPoints)
    {
        this.timerPointList = timerPoints;
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

