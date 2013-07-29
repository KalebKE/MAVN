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

import java.awt.Color;
import java.awt.Dimension;
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
import mavn.spreadsheet.model.ImageRatioOutputModel;
import mavn.spreadsheet.model.ShapesRatioOutputModel;
import mavn.spreadsheet.model.observer.ImageRatioOutputModelObserver;
import mavn.spreadsheet.model.observer.ShapesRatioOutputModelObserver;
import simulyn.input.model.InputModelInterface;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.PlotViewInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;
import simulyn.ui.components.plotter.SimulynDefaultPlotView;

/**
 * OutputViewMediatorInterface implementations are used to completely decouple
 * the Model from the View using a Model-View-Mediator (MVM) architecture. This
 * pattern is also known as a Model-View-Presenter using a Passive View
 * strategy. It has a single method used to update the view with new state:
 * updateUI().
 *
 * Mediators differ from Controllers in how they couple the View to the Model.
 *
 * With a Controller, the View requests Actions to be taken upon the Model via
 * the Controller. The Controller will call the Model and when the the Model is
 * ready it will update the View with the new State. With a Mediator, the View
 * requests Actions to be taken upon the Model via the Mediator and the Model
 * will update the Mediator (not the View) with the new State. After the the
 * Mediator appropriately renders the State, the Mediator will then provide the
 * View with the rendered State.
 *
 * An MVM is used to manage the Views for the Network UI, Plot UI, and
 * Spreadsheet UI. This is because we want the UI's to be completely decoupled
 * from any form of a model or controller, mediator, presenter, etc... We want
 * the UI's to be as decoupled and as portable as possible so they can be reused
 * in many ways.
 *
 * PlotMediator is a implementation providing a coupling between the Output
 * Models and the Output Views. It manages a Plot UI View that is designed to
 * allow the user to view the simulations Output Models in a graphical
 * environment. It provides the logic for the View's Actions and renders the
 * Output Models with a two-dimensional Plot used to render a Scatter Plot and
 * Line Plot.
 *
 * @author Kaleb
 */
public class PlotMediator implements OutputViewMediatorInterface,
        OutputModelObserver, PlotMediatorInterface,
        PointOutputModelObserver, PointHitOutputModelObserver,
        PointMissOutputModelObserver, TargetInputModelObserver,
        TimerOutputModelObserver, ShapesRatioOutputModelObserver, ImageRatioOutputModelObserver
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
    // Model Result Models are Observers of the Algorithm Models
    private OutputModelInterface plotOutputModel;
    private OutputModelInterface shapesRatioModelResult;
    private OutputModelInterface imageRatioModelResult;
    // The SwingWorker is used to create the new Plot for the
    // Plot View. This SwingWorker manages results from MultiplePointSimulations.
    // The View the Mediator managesR
    private SimulynDefaultPlotView imagePlotView;
    private SimulynDefaultPlotView histogramPlotView;
    private SimulynDefaultPlotView segmentRatioPlotView;
    private SimulynDefaultPlotView imageRatioPlotView;
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
     * @param targetInputModel the Target Input Model used for Diagnostic Single
     * Point Simulations
     * @param networkMediator the Network Mediator for Animated simulation
     * State.
     * @param plotOutputModel the Plot Output Model Subject to Register as a
     * Simulation Output Model Observer.
     * @param pointOutputModel the Point Output Model Subject to Register as a
     * Point Output Model Observer.
     * @param pointHitOutputModel the Point Hit Output Model Subject to Register
     * as a Point Hit Output Model Observer.
     * @param pointMissOutputModel the Point Miss Output Model Subject to
     * Register as a Point Miss Output Model Observer.
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
            TimerOutputModelInterface timerOutputModel,
            OutputModelInterface shapesRatioModelResult,
            OutputModelInterface imageRatioModelResult)
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
        this.shapesRatioModelResult = shapesRatioModelResult;

        // this class should observe changes to the result model
        ((ShapesRatioOutputModel) this.shapesRatioModelResult).registerObserver(this);

        // local instance of the result model
        this.imageRatioModelResult = imageRatioModelResult;
        // this class should observe changes to the result model
        ((ImageRatioOutputModel) this.imageRatioModelResult).registerObserver(this);

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
        imagePlotView = new SimulynDefaultPlotView();

        imagePlotView.setMaximumSize(new Dimension(500, 500));

        imagePlotView.getPlot().setBackground(new Color(85, 85, 85));

        // Create a new instance of the View.
        histogramPlotView = new SimulynDefaultPlotView();

        histogramPlotView.setMaximumSize(new Dimension(400, 400));

        histogramPlotView.getPlot().setBackground(new Color(85, 85, 85));

        // Create a new instance of the View.
        segmentRatioPlotView = new SimulynDefaultPlotView();

        segmentRatioPlotView.setMaximumSize(new Dimension(400, 400));

        segmentRatioPlotView.getPlot().setBackground(new Color(85, 85, 85));

        // Create a new instance of the View.
        imageRatioPlotView = new SimulynDefaultPlotView();

        imageRatioPlotView.setMaximumSize(new Dimension(400, 400));

        imageRatioPlotView.getPlot().setBackground(new Color(85, 85, 85));

        // Create a new instance of the Input Model State required
        // for Single Point Diagnostic Simulations.
        plotMediatorInputModelState = new SimulationTargetInputModelState();

        // Lists to keep track of the point hits and misses that will be
        // rendered with the Plot.
        hitPointList = new ArrayList<Point>();
        missPointList = new ArrayList<Point>();
    }

    /**
     * Get the Plot Mediator View.
     *
     * @return the JPanel representing the Plot Mediator View.
     */
    @Override
    public JPanel getImagePlotView()
    {
        return imagePlotView;
    }

    /**
     * Remove all plots from the current Plot and update the View.
     */
    @Override
    public void onClearUI()
    {
        imagePlotView.getPlot().removeAllPlots();
        histogramPlotView.getPlot().removeAllPlots();
        segmentRatioPlotView.getPlot().removeAllPlots();
        imageRatioPlotView.getPlot().removeAllPlots();
        updateUI();
    }

    /**
     * Set the Plot View State for the Plot Mediator. Plot View State keeps
     * track of what View should be rendered and when.
     *
     * @param plotViewState the Plot View State implementation that will be used
     * by the Plot Mediator.
     */
    public void setPlotViewState(PlotMediatorViewStateInterface plotViewState)
    {
        this.plotViewState = plotViewState;
        this.plotViewState.onScatterPlot();
    }

    /**
     * The Hook to Observe Point Hit Output Model Subjects to be notified when
     * new Point Hit State is available.
     *
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

            ((PlotViewInterface) imagePlotView).addScatterPlot(points, new Color(51,
                    181, 229));
        }
    }

    /**
     * The Hook to Observe Point Miss Output Model Subjects to be notified when
     * new Point Miss State is available.
     *
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

            ((PlotViewInterface) imagePlotView).addScatterPlot(points, new Color(255,
                    68, 68));
        }
    }

    /**
     * Hook to Observe the Simulation Model Result Subject. This method is
     * called when a new Model Result is available.
     *
     * @param modelResult the State of the Result Model, either a hit or a miss.
     */
    @Override
    public void updateModelResult(double[][] modelResult)
    {
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

    /**
     * Hook to Observe the Dart Model Result Subject. The State of the Model
     * Result describes how many points hit shapes in the image and how many
     * points missed shapes in the image.
     *
     * @param hit
     * @param miss
     */
    @Override
    public void updatePoints(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        double[] points = new double[(hit.size() + miss.size()) * 2];

        for (int i = 0; i < hit.size(); i++)
        {
            points[i] = hit.get(i).getX();
        }

        for (int i = hit.size(); i < hit.size() * 2; i++)
        {
            points[i] = hit.get(i - hit.size()).getY();
        }

        for (int i = hit.size() * 2; i < (hit.size() * 2) + miss.size(); i++)
        {
            points[i] = miss.get(i - (hit.size() * 2)).getX();
        }

        for (int i = (hit.size() * 2) + miss.size(); i < (hit.size() * 2) + (miss.size() * 2); i++)
        {
            points[i] = miss.get(i - ((hit.size() * 2) + miss.size())).getY();
        }

        this.histogramPlotView.getPlot().addHistogramPlot("", new Color(153,
                204, 0), points, 10);

        double[][] hits = new double[hit.size()][2];

        for (int i = 0; i < hits.length; i++)
        {
            hits[i][0] = hit.get(i).getX();
            hits[i][1] = hit.get(i).getY();
        }

        ((PlotViewInterface) imagePlotView).addScatterPlot(hits, new Color(51,
                181, 229));

        double[][] misses = new double[miss.size()][2];

        for (int i = 0; i < misses.length; i++)
        {
            misses[i][0] = miss.get(i).getX();
            misses[i][1] = miss.get(i).getY();
        }

        ((PlotViewInterface) imagePlotView).addScatterPlot(misses, new Color(255,
                68, 68));

        this.hitPointList = hit;
        this.missPointList = miss;

        if (!((NetworkMediator) networkMediator).getNetworkModelState().isAnimated())
        {
            renderPointOutputModels = new MultiplePointModelPlotWorker(this.hitPointList, this.missPointList, this);
            renderPointOutputModels.execute();
        }
    }

    /**
     * Hook to Observe the State of Target Model Input Subjects. The State is
     * eventually pushed to the View.
     *
     * @param modelInput the State of Target Model Input Subjects.
     */
    @Override
    public void updateTargetInputModel(double[][] modelInput)
    {
        // Set the modelInput to the Mediators State so it can be worked with locally.
        ((SimulationTargetInputModelState) plotMediatorInputModelState).setTargetInputModel(modelInput);
    }

    /**
     * Hook to Observe the Timer Output Model Subject to be notified when new
     * Timer State is available.
     *
     * @param timerPoints the Timer Points from the simualtion.
     */
    @Override
    public void updateTimerOutput(ArrayList<Point> timerPoints)
    {
        this.timerPointList = timerPoints;
    }

    @Override
    public void updateShapesRatioModelResult(double[][] shapesRatioResult)
    {
        double[][] shapesRatioResults = new double[shapesRatioResult.length][3];


        for (int i = 0; i < shapesRatioResult.length; i++)
        {
            shapesRatioResults[i][0] = i;
            shapesRatioResults[i][1] = shapesRatioResult[i][0];
            shapesRatioResults[i][2] = 1;
        }

        //this.segmentRatioPlotView.getPlot().addBarPlot("", shapesRatioResults);
        this.segmentRatioPlotView.getPlot().addHistogramPlot("", new Color(255,
                68, 68), shapesRatioResults);

    }

    @Override
    public void updateImageRatioModelResult(double[][] imageRatioResult)
    {
        double[][] imageRatioResults = new double[imageRatioResult.length][3];


        for (int i = 0; i < imageRatioResult.length; i++)
        {
            imageRatioResults[i][0] = i;
            imageRatioResults[i][1] = imageRatioResult[i][0];
            imageRatioResults[i][2] = 1;
        }

        //this.segmentRatioPlotView.getPlot().addBarPlot("", shapesRatioResults);
        this.imageRatioPlotView.getPlot().addHistogramPlot("", new Color(51,
                181, 229), imageRatioResults);
    }

    /**
     * Called when the Mediator has new State ready to be pushed to the View.
     */
    @Override
    public void updateUI()
    {
    }

    @Override
    public JPanel getHistogramPlotView()
    {
        return this.histogramPlotView;
    }

    @Override
    public JPanel getSegmentRatioPlotView()
    {
        return this.segmentRatioPlotView;
    }

    @Override
    public JPanel getImageRatioPlotView()
    {
        return this.imageRatioPlotView;
    }
}
