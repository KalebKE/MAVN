/*
ResultTableMediator -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.spreadsheet.mediator;

import mavn.simulation.view.state.output.SimulationViewOutputStateInterface;
import file.save.controller.SaveFileControllerInterface;
import file.save.controller.SaveSpreadsheetFileController;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import mavn.algorithm.model.point.Point;
import mavn.input.model.observer.TargetModelObserver;
import mavn.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.input.model.TargetInputModel;
import mavn.spreadsheet.mediator.worker.DiagnosticSimulationTableWorker;
import mavn.spreadsheet.mediator.state.SSMediatorOutputModelState;
import mavn.output.view.state.OutputMediatorInputModelState;
import mavn.spreadsheet.model.SimulationOutputModel;
import mavn.spreadsheet.model.ImageRatioOutputModel;
import mavn.spreadsheet.model.ShapesRatioOutputModel;
import mavn.spreadsheet.model.observer.ImageRatioOutputModelObserver;
import mavn.spreadsheet.model.observer.OutputModelObserver;
import mavn.spreadsheet.model.observer.ShapesRatioOutputModelObserver;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;
import simulyn.ui.components.modelRenderer.SimulynDefaultTableRenderer;

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
 * ResultTableMediator is a implementation of the Simulyn Framework that
 * provides a coupling between MAVN's Result Models, Input Models, and Result Views.
 * It manages a Result UI View that is designed to allow the user
 * to run a simulation once the Input Model has been loaded into the simluation.
 * It provides the logic for the View's Actions and renders the Model Results with a
 * TableModel used to back a JTable within the View.
 * @author Kaleb
 */
public class SSMediator implements OutputViewMediatorInterface,
        SSMediatorInterface, OutputModelObserver,
        ShapesRatioOutputModelObserver, ImageRatioOutputModelObserver,
        TargetModelObserver
{
    // Table Model for the View's JTable that is pushed to the View
    // once the the Result Model State has been added to the tableModel.

    private DefaultTableModel tableModel;
    private InputModelInterface targetModel;
    // Mediator State Managers are responsbile for keeping
    // track of what State has been updated by the ResultModel
    // and when that State should be pushed to the View.
    private MediatorStateInterface ssMediatorOutputModelState;
    private MediatorStateInterface ssMediatorInputModelState;
    // Model Result Models are Observers of the Algorithm Models
    private OutputModelInterface simulationModelResult;
    private OutputModelInterface shapesRatioModelResult;
    private OutputModelInterface imageRatioModelResult;
    private SaveFileControllerInterface saveFileController;
    // The View the Mediator manages
    private SimulynDefaultTableRenderer view;
    // Simulation Properties State Manager
    private SimulationPropertiesStateInterface simulationPropertiesState;
    // Simulation Model State Manger
    private SimulationViewOutputStateInterface ssMediatorViewState;
    // The SwingWorker is used to create the new TableModel for the
    // View.
    private SwingWorker loadTargetResult;

    /**
     * Initialize a new ResultTableMediator.
     *
     * @param simulationModelResult the Simulation Result Model implementation to be
     * used by the Mediator. It Observes the SinglePointSimulations and
     * MultiplePointSimluations for new Model Results. It is also a Subject
     * Obsererved by this class for new Model Results.
     * 
     * @param shapesRatioModelResult the Shapes Ratio Result implementation to be
     * used by the Mediator. It Observes the SinglePointSimulations and
     * MultiplePointSimluations for new Model Results and then Transforms the
     * results into a ratio of point misses/hits for each shape within the image.
     * It is also a Subject Obsererved by this class for new Model Results.
     * 
     * @param imageRatioResultthe Shapes Ratio Result implementation to be
     * used by the Mediator. It Observes the SinglePointSimulations and
     * MultiplePointSimluations for new Model Results and then Transforms the
     * results into a ratio of point misses/hits from the image. It is also a
     * Subject Obsererved by this class for new Model Results.
     */
    public SSMediator(InputModelInterface targetModel,
            OutputModelInterface simulationModelResult,
            OutputModelInterface shapesRatioModelResult,
            OutputModelInterface imageRatioModelResult,
            SimulationPropertiesStateInterface simulationPropertiesState)
    {
        // local instances of the simulation model
        this.targetModel = targetModel;

        this.simulationPropertiesState = simulationPropertiesState;

        // local instance of the result model
        this.simulationModelResult = simulationModelResult;
        // this class should observe changes to the result model
        ((SimulationOutputModel) this.simulationModelResult).registerObserver(this);
        // have the result model observe changes to the simulation model
        ((TargetInputModel) this.targetModel).registerObserver((TargetModelObserver) this);

        // local instance of the result model
        this.shapesRatioModelResult = shapesRatioModelResult;
        // this class should observe changes to the result model
        ((ShapesRatioOutputModel) this.shapesRatioModelResult).registerObserver(this);

        // local instance of the result model
        this.imageRatioModelResult = imageRatioModelResult;
        // this class should observe changes to the result model
        ((ImageRatioOutputModel) this.imageRatioModelResult).registerObserver(this);

        // Create a new View to Mediate.
        this.view = new SimulynDefaultTableRenderer();
        this.tableModel = new DefaultTableModel();
        this.ssMediatorInputModelState = new OutputMediatorInputModelState();
        this.ssMediatorOutputModelState = new SSMediatorOutputModelState(this);
    }

    public SimulationViewOutputStateInterface getModelResultState()
    {
        return ssMediatorViewState;
    }

    public SimulationPropertiesStateInterface getSimulationPropertiesState()
    {
        return simulationPropertiesState;
    }

    @Override
    public JPanel getView()
    {
        return view;
    }

    /**
     * Get the TableModel used to back the JTable in the View. This method
     * is usually called by the MediatorStateInterface classes are responsible
     * for managing the TableModel's State. 
     * @return the DefaultTableModel used to back the JTable in the View.
     */
    public DefaultTableModel getTableModel()
    {
        return (DefaultTableModel) view.getTable().getModel();
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
        this.setTableModel(new DefaultTableModel(names, 20));
        // Indicate that Simulation Model Results are now available.
        this.getModelResultState().resultAvailable(false);
        this.updateUI();
    }

    public void setModelResultState(SimulationViewOutputStateInterface modelResultState)
    {
        this.ssMediatorViewState = modelResultState;
    }

    /**
     * Set the TableModel that will be pushed to the JTable
     * in the View.
     * @param tableModel the TableModel containing the State
     * from the Result Models that will be pushed to the View.
     */
    public void setTableModel(DefaultTableModel tableModel)
    {
        this.tableModel = tableModel;
    }

    /**
     * Hook to Observe the Simulation Model Result Subject. This method is called
     * when a new Model Result is available.
     * @param modelResult the State of the Result Model, either a hit or a miss.
     */
    @Override
    public void updateModelResult(double[][] modelResult)
    {
        // If a Single Point Simluation from a Target Point is being used AND
        // the Input Model for the Single Point Simluation is available.
        if (simulationPropertiesState.isTargetModel() && ((OutputMediatorInputModelState) ssMediatorInputModelState).isInputModelTargetReady())
        {
            // Create a new new Point from the X and Y coordinates from the Target Input Model.
            Point point = new Point();
            point.setLocation(((OutputMediatorInputModelState) ssMediatorInputModelState).getInputModelTarget()[0][0],
                    ((OutputMediatorInputModelState) ssMediatorInputModelState).getInputModelTarget()[1][0]);

            // Pass off the creation of the TableModel to a Command Pattern with a SwingWorker
            // to ensure the GUI doesn't hang. The Command Pattern will create a new TableModel,
            // add the Simulation Model Result's State to the TableModel, push the TableModel to this class,
            // and call updateUI() to push the TableModel to the View.
            loadTargetResult = new DiagnosticSimulationTableWorker(modelResult, this, point);
            loadTargetResult.execute();
        }
    }

    /**
     * Hook to Observe the Shapes Ratio Model Result Subject. The State of the
     * Model Result describes how many points missed each shape versus the total
     * number of darts fired at the image.
     * @param shapesRatioResult the State of the Shapes Ratio Model Result.
     */
    @Override
    public void updateShapesRatioModelResult(double[][] shapesRatioResult)
    {
        Double[] shapesRatioResults = new Double[shapesRatioResult.length];

        for (int i = 0; i < shapesRatioResults.length; i++)
        {
            shapesRatioResults[i] = shapesRatioResult[i][0];
        }

        // Add the ShapeRatioModelResult's State from the MutliplePointSimulation to
        // a tableModel that will be pushed to a JTable in the View.
        tableModel.addColumn("Shapes Ratio", shapesRatioResults);
        // The MultiplePointTableState class knows when to create a new TableModel
        // and when to call updateUI() to push that TableModel to the JTable in the View.
        ((SSMediatorOutputModelState) ssMediatorOutputModelState).setShapeRatioModelUpdated(true);
    }

    /**
     * Hook to Observe the Image Ratio Model Result Subject. The State of the
     * Model Result describes how many points missed shapes in the images versus the total
     * number of darts fired at the image.
     * @param imageRatioResult the State of the Image Ratio Model Result.
     */
    @Override
    public void updateImageRatioModelResult(double[][] imageRatioResult)
    {
        Double[] imageRatioResults = new Double[imageRatioResult.length];
        for (int i = 0; i < imageRatioResults.length; i++)
        {
            imageRatioResults[i] = imageRatioResult[i][0];
        }

        // Add the ImageRatioModelResult's State from the MutliplePointSimulation to
        // a tableModel that will be pushed to a JTable in the View.
        tableModel.addColumn("Image Ratio", imageRatioResults);
        // The MultiplePointTableState class knows when to create a new TableModel
        // and when to call updateUI() to push that TableModel to the JTable in the View.
        ((SSMediatorOutputModelState) ssMediatorOutputModelState).setImageRatioModelUpdated(true);
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
        ((OutputMediatorInputModelState) ssMediatorInputModelState).setInputModelTarget(modelInput);
    }

    /**
     * Called when the Mediator has new State ready to be pushed to the View.
     */
    @Override
    public void updateUI()
    {
        view.setModel(tableModel);
        // Indicate that Simulation Model Results are now available.
        ssMediatorViewState.resultAvailable(true);
        this.tableModel = new DefaultTableModel();
    }

    /**
     * Provides the logic for when a View Action requests that the Result
     * Models should be persisted.
     */
    @Override
    public void onSaveSimulationOutput()
    {

        int numRow = this.getTableModel().getRowCount();
        int numCol = this.getTableModel().getColumnCount();

        double[][] output = new double[numRow][numCol];

        for (int i = 0; i < numRow; i++)
        {
            for (int j = 0; j < numCol; j++)
            {
                if (this.getTableModel().getValueAt(i, j) != null)
                {
                    output[i][j] = (Double) this.getTableModel().getValueAt(i, j);
                }
            }
        }

        saveFileController =
                new SaveSpreadsheetFileController(output);
    }
}
