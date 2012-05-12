/*
MavnController -- a class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher

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
package mavn.factory;

import java.util.ArrayList;
import mavn.globals.Globals;
import mavn.algorithm.model.multiplePointSimulation.PixelGridSimulation;
import mavn.algorithm.model.multiplePointSimulation.MultiplePointSimulationInterface;
import mavn.algorithm.model.multiplePointSimulation.MonteCarloSimulation;
import mavn.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.algorithm.model.network.AndLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.OrLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.OutputLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.algorithm.model.point.PointAlgorithmModelInterface;
import mavn.algorithm.model.point.PointHitAlgorithmModelInterface;
import mavn.algorithm.model.point.PointMissAlgorithmModelInterface;
import mavn.algorithm.model.point.generator.JavaRandomPointGenerator;
import mavn.algorithm.model.point.observer.PointGeneratorAlgorithmModelObserver;
import mavn.algorithm.model.point.observer.PointHitAlgorithmModelObserver;
import mavn.algorithm.model.point.observer.PointMissAlgorithmModelObserver;
import mavn.algorithm.model.singlePointSimulation.DiagnosticSimulation;
import mavn.algorithm.model.timer.TimerAlgorithmModelInterface;
import mavn.algorithm.model.timer.observer.TimerAlgorithmModelObserver;
import mavn.algorithm.properties.view.SimulationPropertiesFrame;
import mavn.input.model.TargetInputModel;
import mavn.input.model.ThetaInputModel;
import mavn.input.model.W0InputModel;
import mavn.input.model.W1InputModel;
import mavn.input.model.W2InputModel;
import mavn.algorithm.properties.view.state.SimulationPropertiesState;
import mavn.simulation.mediator.SimulationMediator;
import mavn.input.model.observer.TargetInputModelObserver;
import mavn.input.model.observer.ThetaInputModelObserver;
import mavn.input.model.observer.W0InputModelObserver;
import mavn.input.model.observer.W1InputModelObserver;
import mavn.input.model.observer.W2InputModelObserver;
import mavn.input.view.changeEvent.InputModelChangeEvent;
import mavn.input.view.InputViewTargetModel;
import mavn.input.view.InputViewThetaModel;
import mavn.input.view.InputViewW0Model;
import mavn.input.view.InputViewW1Model;
import mavn.input.view.InputViewW2Model;
import mavn.input.view.action.InputModelViewAction;
import mavn.input.view.layout.InputViewGridLayoutPanel;
import mavn.input.view.state.InputViewState;
import mavn.network.mediator.NetworkMediator;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.network.mediator.state.NetworkMediatorViewState;
import mavn.network.model.AndLayerOutputModel;
import mavn.network.model.OrLayerOutputModel;
import mavn.network.model.OutputLayerOutputModel;
import mavn.spreadsheet.mediator.SSMediator;
import mavn.spreadsheet.model.ImageRatioOutputModel;
import mavn.spreadsheet.model.ShapesRatioOutputModel;
import mavn.spreadsheet.model.SimulationOutputModel;
import mavn.simulation.view.actions.NetworkBarAction;
import mavn.simulation.view.actions.OutputBarAction;
import mavn.simulation.view.actions.PlotBarAction;
import mavn.simulation.view.actions.PropertiesBarAction;
import mavn.simulation.view.actions.RunSimulationBarAction;
import mavn.simulation.view.actions.SimulationBarAction;
import mavn.simulation.view.actions.ViewBarAction;
import mavn.simulation.view.controlBar.ControlBar;
import mavn.output.view.layout.ModelOutputDefaultLayoutView;
import mavn.simulation.view.state.input.SimulationViewInputState;
import mavn.plot.mediator.PlotMediator;
import mavn.plot.mediator.PlotMediatorInterface;
import mavn.plot.mediator.state.PlotMediatorViewState;
import mavn.plot.model.PointHitOutputModel;
import mavn.plot.model.PointMissOutputModel;
import mavn.plot.model.PointOutputModel;
import mavn.plot.model.TimerOutputModel;
import mavn.simulation.view.SimControlView;
import mavn.simulation.view.state.output.SimulationViewOutputState;
import mavn.simulation.view.state.simulator.SimulationTypeViewState;
import simulyn.input.controller.InputController;
import simulyn.input.model.InputModelInterface;
import simulyn.ui.components.inputModel.InputViewAbstract;

/**
 * A Factory class for the MAVN application.
 * @author Kaleb Kircher
 */
public class MavnSimulationFactory extends AbstractSimulationFactory
{

    /**
     * Initialize the MavnController and the MAVN application. MavnController will
     * prepare the state for the application. It will also initialize and display the GUI.
     */
    public MavnSimulationFactory()
    {
        inputModels = new ArrayList<InputModelInterface>();
        inputViews = new ArrayList<InputViewAbstract>();
        initInputModels();
        initAlgorithmModels();
        initNetworkOutputModels();
        initPointOutputModels();
        initInputModelControllers();
        initSimulationProperties();
        initSSMediator();
        initNetworkMediator();
        initPlotMediator();
        initSimulationMediator();
        initSimulationActions();
        initControlBarViews();
        initOutputModelViews();
        initInputModelChangeEvent();
        initInputModelViewState();
        initInputModelActions();
        initInputModelViews();
        initSimulationView();
        initViewState();
        view.setOutputView();
        // Display the view.
        this.view.setVisible(true);
    }

    /**
     * Initialize the MAVN Algorithm Models. The Alogirthm Models can be thought
     * of as the individual simulations. They are responsible for running
     * the simulations and coordianting all of the supporting classes.
     */
    @Override
    public void initAlgorithmModels()
    {
        // There are currently 3 different types of Simulations
        diagnosticSimulation = new DiagnosticSimulation();
        monteCarloSimulation = new MonteCarloSimulation(new JavaRandomPointGenerator());
        pixelGridSimulation = new PixelGridSimulation();

        // Register the Diagnostic Simulation with the Input Models.
        ((TargetInputModel) targetInputModel).registerObserver((TargetInputModelObserver) diagnosticSimulation);
        ((ThetaInputModel) thetaInputModel).registerObserver((ThetaInputModelObserver) diagnosticSimulation);
        ((W0InputModel) w0InputModel).registerObserver((W0InputModelObserver) diagnosticSimulation);
        ((W1InputModel) w1InputModel).registerObserver((W1InputModelObserver) diagnosticSimulation);
        ((W2InputModel) w2InputModel).registerObserver((W2InputModelObserver) diagnosticSimulation);

        // Register the Monte Carlo Simulation with the Input Models.
        ((ThetaInputModel) thetaInputModel).registerObserver((ThetaInputModelObserver) monteCarloSimulation);
        ((W0InputModel) w0InputModel).registerObserver((W0InputModelObserver) monteCarloSimulation);
        ((W1InputModel) w1InputModel).registerObserver((W1InputModelObserver) monteCarloSimulation);
        ((W2InputModel) w2InputModel).registerObserver((W2InputModelObserver) monteCarloSimulation);

        // Register the Pixel Grid Simulation with the Input Models.
        ((ThetaInputModel) thetaInputModel).registerObserver((ThetaInputModelObserver) pixelGridSimulation);
        ((W0InputModel) w0InputModel).registerObserver((W0InputModelObserver) pixelGridSimulation);
        ((W1InputModel) w1InputModel).registerObserver((W1InputModelObserver) pixelGridSimulation);
        ((W2InputModel) w2InputModel).registerObserver((W2InputModelObserver) pixelGridSimulation);
    }

    /**
     * Initialize the Simulation Control Bars. The Control Bars are part
     * of the UI. They allow the user to control all of the key functionality
     * of MAVN with one-click of the mouse. 
     */
    @Override
    public void initControlBarViews()
    {
        // Create a new Simulation Control Bar for the Output View.
        outputControlBar = new ControlBar(simulationBarAction,
                propertiesBarAction, modelOuputBarAction, viewBarAction,
                newtorkViewBarAction, plotViewBarAction, runSimulationAction);

        // Create a new Simulation Control Bar for the Input View.
        inputControlBar = new ControlBar(simulationBarAction, propertiesBarAction,
                modelOuputBarAction, viewBarAction, newtorkViewBarAction,
                plotViewBarAction, runSimulationAction);

        // Add the Control Bars to the Simulation Input State Manager.
        simulationInputState = new SimulationViewInputState((ControlBar) outputControlBar, (ControlBar) inputControlBar);
        // Give the Simulation Propreties State an instance of the Simulation Input State.
        ((SimulationPropertiesFrame) simulationPropertiesFrame).setSimulationViewState(simulationInputState);
    }

    /**
     * Initialize the simulation Input Models. The Input Models are responsible
     * for managing all of the inputs required for the simluations algorithms
     * to run. The inputs for the algorithms can come from external files or
     * can be generated within MAVN by the user. The Input Models are part of a
     * Model-View-Controller architecture and push new State to their Observers,
     * usually Input View's.
     */
    @Override
    public void initInputModels()
    {
        // Initialize the Input Models
        // Target Model is only used for the Diagnostic Simulation
        targetInputModel = new TargetInputModel();
        thetaInputModel = new ThetaInputModel();
        w0InputModel = new W0InputModel();
        w1InputModel = new W1InputModel();
        w2InputModel = new W2InputModel();

        // Add the input models to the collection
        // Use globals to associate the object with the correct index
        // Since the number of objects is small, a hashMap would be
        // overkill.
        inputModels.add(Globals.TARGET_MODEL, targetInputModel);
        inputModels.add(Globals.THETA_MODEL, thetaInputModel);
        inputModels.add(Globals.W0_MODEL, w0InputModel);
        inputModels.add(Globals.W1_MODEL, w1InputModel);
        inputModels.add(Globals.W2_MODEL, w2InputModel);
    }

    /**
     * Initialize the simulations Input Model Actions. Input Model Actions
     * decouple the Input Controllers from the Input Models and Input Views
     * by using an ActionListener to manage the dependencies and forward
     * the Actions to the appropriate classes.
     */
    @Override
    public void initInputModelActions()
    {
        targetPanelAction = new InputModelViewAction(targetController, targetInputModel);
        thetaPanelAction = new InputModelViewAction(thetaController, thetaInputModel);
        w0PanelAction = new InputModelViewAction(w0Controller, w0InputModel);
        w1PanelAction = new InputModelViewAction(w1Controller, w1InputModel);
        w2PanelAction = new InputModelViewAction(w2Controller, w2InputModel);
    }

    /**
     * Initialize the Input Model Change Event. The Change Event watches the
     * Input Models and determines if they have been loaded into MAVN. Once
     * all of the Input Models have been loaded, it notifies other classes
     * that new simulation state should be enabled.
     */
    @Override
    public void initInputModelChangeEvent()
    {
        modelChanged = new InputModelChangeEvent(inputModels, (NetworkMediatorInterface) networkMediator, simulationInputState);
    }

    /**
     * Initialize the Input Model Controllers. The Input Model Controllers
     * use a Controller pattern to manage the Input View and Input Models in a
     * Model-View-Controller architecture.
     */
    @Override
    public void initInputModelControllers()
    {
        // Initialize the input controllers.
        targetController = new InputController(targetInputModel);
        thetaController = new InputController(thetaInputModel);
        w0Controller = new InputController(w0InputModel);
        w1Controller = new InputController(w1InputModel);
        w2Controller = new InputController(w2InputModel);
    }

    /**
     * Initialize Input Model Views. Input Model Views Render the State
     * from the Input Models so the user can interact with the State in a
     * graphical environment (usually a spreadsheet).
     */
    @Override
    public void initInputModelViews()
    {
        targetPanel = new InputViewTargetModel(targetPanelAction, targetController, targetInputModel, targetState, modelChanged);
        thetaPanel = new InputViewThetaModel(thetaPanelAction, thetaController, thetaInputModel, thetaState, modelChanged);
        w0Panel = new InputViewW0Model(w0PanelAction, w0Controller, w0InputModel, w0State, modelChanged);
        w1Panel = new InputViewW1Model(w1PanelAction, w1Controller, w1InputModel, w1State, modelChanged);
        w2Panel = new InputViewW2Model(w2PanelAction, w2Controller, w2InputModel, w2State, modelChanged);

        ((InputModelViewAction) targetPanelAction).setView(targetPanel);
        ((InputModelViewAction) thetaPanelAction).setView(thetaPanel);
        ((InputModelViewAction) w0PanelAction).setView(w0Panel);
        ((InputModelViewAction) w1PanelAction).setView(w1Panel);
        ((InputModelViewAction) w2PanelAction).setView(w2Panel);

        ((InputViewState) targetState).setView(targetPanel);
        ((InputViewState) thetaState).setView(thetaPanel);
        ((InputViewState) w0State).setView(w0Panel);
        ((InputViewState) w1State).setView(w1Panel);
        ((InputViewState) w2State).setView(w2Panel);

        inputViews.add(Globals.TARGET_PANEL, targetPanel);
        inputViews.add(Globals.THETA_PANEL, thetaPanel);
        inputViews.add(Globals.W0_PANEL, w0Panel);
        inputViews.add(Globals.W1_PANEL, w1Panel);
        inputViews.add(Globals.W2_PANEL, w2Panel);

        // register with the input model observers with the view
        ((TargetInputModel) targetInputModel).registerObserver((InputViewTargetModel) targetPanel);
        ((ThetaInputModel) thetaInputModel).registerObserver((InputViewThetaModel) thetaPanel);
        ((W0InputModel) w0InputModel).registerObserver((InputViewW0Model) w0Panel);
        ((W1InputModel) w1InputModel).registerObserver((InputViewW1Model) w1Panel);
        ((W2InputModel) w2InputModel).registerObserver((InputViewW2Model) w2Panel);

        inputView = new InputViewGridLayoutPanel(inputViews, inputControlBar);
    }

    /**
     * Initialize a new Input Model View State. Input Model View State
     * manages the Input View State for the simulation. They enable and
     * disable user functionality based on the Input Models that have
     * been loaded.
     */
    @Override
    public void initInputModelViewState()
    {
        targetState = new InputViewState();
        thetaState = new InputViewState();
        w0State = new InputViewState();
        w1State = new InputViewState();
        w2State = new InputViewState();
    }

    /**
     * Initialize the Network Mediator. The Network Mediator manages the
     * Network View and Network Output Models by decoupling everything
     * with a Mediator Pattern.
     */
    @Override
    public void initNetworkMediator()
    {
        networkMediator = new NetworkMediator(andLayerModelResult,
                orLayerModelResult, outputLayerModelResult);
    }

    /**
     * Initialize the Network Output Models. The Network Output Models
     * manage the Network State from the Network Algorithm Models they Observe.
     */
    @Override
    public void initNetworkOutputModels()
    {
        super.andLayerModelResult = new AndLayerOutputModel();
        super.orLayerModelResult = new OrLayerOutputModel();
        super.outputLayerModelResult = new OutputLayerOutputModel();

        ((AndLayerAlgorithmModelInterface) diagnosticSimulation).registerObserver((AndLayerAlgorithmModelObserver) andLayerModelResult);
        ((AndLayerAlgorithmModelInterface) monteCarloSimulation).registerObserver((AndLayerAlgorithmModelObserver) andLayerModelResult);
        ((AndLayerAlgorithmModelInterface) pixelGridSimulation).registerObserver((AndLayerAlgorithmModelObserver) andLayerModelResult);

        ((OrLayerAlgorithmModelInterface) diagnosticSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);
        ((OrLayerAlgorithmModelInterface) monteCarloSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);
        ((OrLayerAlgorithmModelInterface) pixelGridSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);

        ((OutputLayerAlgorithmModelInterface) diagnosticSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
        ((OutputLayerAlgorithmModelInterface) monteCarloSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
        ((OutputLayerAlgorithmModelInterface) pixelGridSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
    }

    /**
     * Initialize the Output Model View. Output Model Views generally display
     * Outputs from the simulations Input Models.
     */
    @Override
    public void initOutputModelViews()
    {
        outputLayoutPanel = new ModelOutputDefaultLayoutView(outputControlBar,
                ((SSMediator) ssMediator).getView(), ((PlotMediatorInterface) plotMediator).getView(),
                ((NetworkMediatorInterface) networkMediator).getView());
    }

    /**
     * Initialize the Point Output Models. Point Output Models keep track of the
     * Point Algorithm Model State Subjects they Observe. They then update their
     * Observers with the new State.
     */
    @Override
    public void initPointOutputModels()
    {
        pointOutputModel = new PointOutputModel();
        pointHitOutputModel = new PointHitOutputModel();
        pointMissOutputModel = new PointMissOutputModel();
        timerOutputModel = new TimerOutputModel();

        super.plotOutputModel = new SimulationOutputModel();
        super.shapesRatioOutputModel = new ShapesRatioOutputModel();
        super.imageRatioOutputModel = new ImageRatioOutputModel();

        ((DiagnosticSimulation) this.diagnosticSimulation).registerObserver((SimulationOutputModel) this.plotOutputModel);

        ((MultiplePointSimulationInterface) this.monteCarloSimulation).registerObserver((MultiplePointAlgorithmModelObserver) this.plotOutputModel);
        ((PointAlgorithmModelInterface) this.monteCarloSimulation).registerObserver((PointGeneratorAlgorithmModelObserver) this.pointOutputModel);
        ((PointHitAlgorithmModelInterface) this.monteCarloSimulation).registerObserver((PointHitAlgorithmModelObserver) this.pointHitOutputModel);
        ((PointMissAlgorithmModelInterface) this.monteCarloSimulation).registerObserver((PointMissAlgorithmModelObserver) this.pointMissOutputModel);
        ((TimerAlgorithmModelInterface) this.monteCarloSimulation).registerObserver((TimerAlgorithmModelObserver) this.timerOutputModel);
        // have the result model observe changes to the simulation model
        ((MonteCarloSimulation) this.monteCarloSimulation).registerObserver((ShapesRatioOutputModel) this.shapesRatioOutputModel);
        // have the result model observe changes to the simulation model
        ((MonteCarloSimulation) this.monteCarloSimulation).registerObserver((ImageRatioOutputModel) this.imageRatioOutputModel);

        ((MultiplePointSimulationInterface) this.pixelGridSimulation).registerObserver((MultiplePointAlgorithmModelObserver) this.plotOutputModel);
        ((PointAlgorithmModelInterface) this.pixelGridSimulation).registerObserver((PointGeneratorAlgorithmModelObserver) this.pointOutputModel);
        ((PointHitAlgorithmModelInterface) this.pixelGridSimulation).registerObserver((PointHitAlgorithmModelObserver) this.pointHitOutputModel);
        ((PointMissAlgorithmModelInterface) this.pixelGridSimulation).registerObserver((PointMissAlgorithmModelObserver) this.pointMissOutputModel);
        // have the result model observe changes to the simulation model
        ((PixelGridSimulation) this.pixelGridSimulation).registerObserver((ShapesRatioOutputModel) this.shapesRatioOutputModel);
        ((TimerAlgorithmModelInterface) this.pixelGridSimulation).registerObserver((TimerAlgorithmModelObserver) this.timerOutputModel);
        // have the result model observe changes to the simulation model
        ((PixelGridSimulation) this.pixelGridSimulation).registerObserver((ImageRatioOutputModel) this.imageRatioOutputModel);
    }

    /**
     * Initialize a Plot Mediator. The Plot Mediator manages the Plot Models
     * and Plot Views by decoupling everything with a Mediator Pattern.
     */
    @Override
    public void initPlotMediator()
    {
        plotMediator = new PlotMediator(targetInputModel, (NetworkMediatorInterface) networkMediator, plotOutputModel,
                pointOutputModel, pointHitOutputModel, pointMissOutputModel, timerOutputModel);
    }

    /**
     * Initialize the Simulation Properties. The Simulation Properties are
     * responsible for managing the State of the Simuation. This State can be
     * the Type of simulation, or even details about specific simulation Type's
     * that the user has specified.
     */
    @Override
    public void initSimulationProperties()
    {
        simulationPropertiesState = new SimulationPropertiesState();
        simulationPropertiesFrame = new SimulationPropertiesFrame(simulationPropertiesState);
        ((SimulationPropertiesState) simulationPropertiesState).setView(simulationPropertiesFrame);
    }

    /**
     * Initialize a Simulation Mediator. A Simulation Mediator mediates all
     * of the simulations Mediators/Controllers.
     */
    @Override
    public void initSimulationMediator()
    {
        simulationMediator = new SimulationMediator(inputModels, diagnosticSimulation,
                monteCarloSimulation, pixelGridSimulation, (NetworkMediator) networkMediator,
                (PlotMediator) plotMediator, simulationPropertiesState, simulationPropertiesFrame,
                (SSMediator) ssMediator);
    }

    /**
     * Initialize the Simulation Actions. Simulation Actions are ActionListeners
     * used to decouple Actions between classes.
     */
    @Override
    public void initSimulationActions()
    {
        simulationBarAction = new SimulationBarAction(simulationMediator);

        propertiesBarAction = new PropertiesBarAction(simulationMediator,
                simulationPropertiesState);

        modelOuputBarAction = new OutputBarAction((SSMediator) ssMediator);
        newtorkViewBarAction = new NetworkBarAction((NetworkMediator) networkMediator);
        viewBarAction = new ViewBarAction();
        plotViewBarAction = new PlotBarAction((PlotMediator) plotMediator);
        runSimulationAction = new RunSimulationBarAction(
                (NetworkMediator) networkMediator, (PlotMediator) plotMediator, simulationMediator,
                (SSMediator) ssMediator);
    }

    /**
     * Initialize the Simulation View. The Simulation View is the main
     * View for the MAVN Simulation.
     */
    @Override
    public void initSimulationView()
    {
        view = new SimControlView(newtorkViewBarAction, modelOuputBarAction,
                simulationBarAction, plotViewBarAction, propertiesBarAction,
                runSimulationAction, viewBarAction, inputView,
                outputLayoutPanel);
        modelChanged.setView(view);
        ((ViewBarAction) viewBarAction).setView(view);
        ((SimulationMediator) simulationMediator).setSimulationViewState(simulationInputState);
        ((SimulationViewInputState) simulationInputState).setView(view);
        ((SimulationViewInputState) simulationInputState).init();
        simulationPropertiesState.onMonteCarloSimulation();
    }

    /**
     * Initialize the Spreadsheet Mediator. The Spreadsheet Mediator manages
     * the Spreadsheet Output Models and Spreadsheet Output Views by using a
     * Mediator Pattern to decouple everything.
     */
    @Override
    public void initSSMediator()
    {
        ssMediator = new SSMediator(targetInputModel, plotOutputModel,
                shapesRatioOutputModel, imageRatioOutputModel, simulationPropertiesState);
    }

    /**
     * Initialize the View State for the MAVN Simulation.
     */
    @Override
    public void initViewState()
    {
        plotMediatorViewState = new PlotMediatorViewState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((PlotMediator) plotMediator).setPlotViewState(plotMediatorViewState);

        simulationTypeViewState = new SimulationTypeViewState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((PropertiesBarAction) propertiesBarAction).setSimulationViewState(simulationTypeViewState);
        view.setSimulationTypeState(simulationTypeViewState);
        ((SimulationMediator) this.simulationMediator).setSimulationTypeState(simulationTypeViewState);

        ssMediatorViewState = new SimulationViewOutputState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((SSMediator) ssMediator).setModelResultState(ssMediatorViewState);

        networkViewState = new NetworkMediatorViewState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((NetworkMediator) networkMediator).setNetworkViewState(networkViewState);
        ((NetworkMediator) networkMediator).animateNetwork(true);
    }
}
