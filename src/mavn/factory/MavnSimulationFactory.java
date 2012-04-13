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
import simulyn.ui.components.inputModelPanel.InputViewAbstract;

/**
 * A Controller class for the MAVN application. This class serves as the master
 * controller for the MAVN application. It is responsible for intializing all of the
 * Input and Output Models for the MVC. 
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
        initModelInputControllers();
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

    @Override
    public void initInputModels()
    {
        // Initialize the input models
        targetInputModel = new TargetInputModel();
        thetaModel = new ThetaInputModel();
        w0Model = new W0InputModel();
        w1Model = new W1InputModel();
        w2Model = new W2InputModel();

        // Add the input models to the collection
        // Use globals to associate the object with the correct index
        inputModels.add(Globals.TARGET_MODEL, targetInputModel);
        inputModels.add(Globals.THETA_MODEL, thetaModel);
        inputModels.add(Globals.W0_MODEL, w0Model);
        inputModels.add(Globals.W1_MODEL, w1Model);
        inputModels.add(Globals.W2_MODEL, w2Model);
    }

    @Override
    public void initModelInputControllers()
    {
        // Initialize the input controllers.
        targetController = new InputController(targetInputModel);
        thetaController = new InputController(thetaModel);
        w0Controller = new InputController(w0Model);
        w1Controller = new InputController(w1Model);
        w2Controller = new InputController(w2Model);
    }

    public void initSimulationProperties()
    {
        simulationPropertiesState = new SimulationPropertiesState();
        simulationPropertiesFrame = new SimulationPropertiesFrame(simulationPropertiesState);
        ((SimulationPropertiesState) simulationPropertiesState).setView(simulationPropertiesFrame);
    }

    public void initSimulationMediator()
    {
        simulationMediator = new SimulationMediator(inputModels, singlePointSimulation,
                uniformPointSimulation, gridPointSimulation, (NetworkMediator) networkMediator,
                (PlotMediator) plotMediator, simulationPropertiesState, simulationPropertiesFrame,
                (SSMediator) ssMediator);
    }

    public void initSSMediator()
    {
        ssMediator = new SSMediator(targetInputModel, plotOutputModel,
                shapesRatioOutputModel, imageRatioOutputModel, simulationPropertiesState);
    }

    public void initPlotMediator()
    {
        plotMediator = new PlotMediator(targetInputModel, (NetworkMediatorInterface) networkMediator, plotOutputModel,
                pointOutputModel, pointHitOutputModel, pointMissOutputModel, timerOutputModel);
    }

    public void initNetworkMediator()
    {
        networkMediator = new NetworkMediator(andLayerModelResult,
                orLayerModelResult, outputLayerModelResult);
    }

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

    public void initControlBarViews()
    {
        outputControlBar = new ControlBar(simulationBarAction,
                propertiesBarAction, modelOuputBarAction, viewBarAction,
                newtorkViewBarAction, plotViewBarAction, runSimulationAction);

        inputControlBar = new ControlBar(simulationBarAction, propertiesBarAction,
                modelOuputBarAction, viewBarAction, newtorkViewBarAction,
                plotViewBarAction, runSimulationAction);

        simulationInputState = new SimulationViewInputState((ControlBar) outputControlBar, (ControlBar) inputControlBar);
        ((SimulationPropertiesFrame) simulationPropertiesFrame).setSimulationViewState(simulationInputState);
    }

    public void initOutputModelViews()
    {
        outputLayoutPanel = new ModelOutputDefaultLayoutView(outputControlBar,
                ((SSMediator) ssMediator).getView(), ((PlotMediatorInterface) plotMediator).getView(),
                ((NetworkMediatorInterface) networkMediator).getView());
    }

    public void initInputModelChangeEvent()
    {
        modelChanged = new InputModelChangeEvent(inputModels, (NetworkMediatorInterface) networkMediator, simulationInputState);
    }

    public void initInputModelViewState()
    {
        targetState = new InputViewState();
        thetaState = new InputViewState();
        w0State = new InputViewState();
        w1State = new InputViewState();
        w2State = new InputViewState();
    }

    public void initInputModelActions()
    {
        targetPanelAction = new InputModelViewAction(targetController, targetInputModel);
        thetaPanelAction = new InputModelViewAction(thetaController, thetaModel);
        w0PanelAction = new InputModelViewAction(w0Controller, w0Model);
        w1PanelAction = new InputModelViewAction(w1Controller, w1Model);
        w2PanelAction = new InputModelViewAction(w2Controller, w2Model);
    }

    @Override
    public void initInputModelViews()
    {
        targetPanel = new InputViewTargetModel(targetPanelAction, targetController, targetInputModel, targetState, modelChanged);
        thetaPanel = new InputViewThetaModel(thetaPanelAction, thetaController, thetaModel, thetaState, modelChanged);
        w0Panel = new InputViewW0Model(w0PanelAction, w0Controller, w0Model, w0State, modelChanged);
        w1Panel = new InputViewW1Model(w1PanelAction, w1Controller, w1Model, w1State, modelChanged);
        w2Panel = new InputViewW2Model(w2PanelAction, w2Controller, w2Model, w2State, modelChanged);

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
        ((ThetaInputModel) thetaModel).registerObserver((InputViewThetaModel) thetaPanel);
        ((W0InputModel) w0Model).registerObserver((InputViewW0Model) w0Panel);
        ((W1InputModel) w1Model).registerObserver((InputViewW1Model) w1Panel);
        ((W2InputModel) w2Model).registerObserver((InputViewW2Model) w2Panel);

        inputView = new InputViewGridLayoutPanel(inputViews, inputControlBar);
    }

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

    public void initViewState()
    {
        plotMediatorViewState = new PlotMediatorViewState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((PlotMediator) plotMediator).setPlotViewState(plotMediatorViewState);

        simulationTypeViewState = new SimulationTypeViewState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((PropertiesBarAction) propertiesBarAction).setSimulationViewState(simulationTypeViewState);
        view.setSimulationTypeState(simulationTypeViewState);
        ((SimulationMediator)this.simulationMediator).setSimulationTypeState(simulationTypeViewState);

        ssMediatorViewState = new SimulationViewOutputState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((SSMediator) ssMediator).setModelResultState(ssMediatorViewState);

        networkViewState = new NetworkMediatorViewState((ControlBar) inputControlBar, (ControlBar) outputControlBar, view);
        ((NetworkMediator) networkMediator).setNetworkViewState(networkViewState);
        ((NetworkMediator) networkMediator).animateNetwork(true);
    }

    /**
     * Initialize the Network Output Models.
     */
    public void initNetworkOutputModels()
    {
        super.andLayerModelResult = new AndLayerOutputModel();
        super.orLayerModelResult = new OrLayerOutputModel();
        super.outputLayerModelResult = new OutputLayerOutputModel();

        ((AndLayerAlgorithmModelInterface) singlePointSimulation).registerObserver((AndLayerAlgorithmModelObserver) andLayerModelResult);
        ((AndLayerAlgorithmModelInterface) uniformPointSimulation).registerObserver((AndLayerAlgorithmModelObserver) andLayerModelResult);
        ((AndLayerAlgorithmModelInterface) gridPointSimulation).registerObserver((AndLayerAlgorithmModelObserver) andLayerModelResult);

        ((OrLayerAlgorithmModelInterface) singlePointSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);
        ((OrLayerAlgorithmModelInterface) uniformPointSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);
        ((OrLayerAlgorithmModelInterface) gridPointSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);

        ((OutputLayerAlgorithmModelInterface) singlePointSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
        ((OutputLayerAlgorithmModelInterface) uniformPointSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
        ((OutputLayerAlgorithmModelInterface) gridPointSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
    }

    /**
     * Initialize the Point Output Models.
     */
    public void initPointOutputModels()
    {
        pointOutputModel = new PointOutputModel();
        pointHitOutputModel = new PointHitOutputModel();
        pointMissOutputModel = new PointMissOutputModel();
        timerOutputModel = new TimerOutputModel();

        super.plotOutputModel = new SimulationOutputModel();
        super.shapesRatioOutputModel = new ShapesRatioOutputModel();
        super.imageRatioOutputModel = new ImageRatioOutputModel();

        ((DiagnosticSimulation) this.singlePointSimulation).registerObserver((SimulationOutputModel) this.plotOutputModel);

        ((MultiplePointSimulationInterface) this.uniformPointSimulation).registerObserver((MultiplePointAlgorithmModelObserver) this.plotOutputModel);
        ((PointAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((PointGeneratorAlgorithmModelObserver) this.pointOutputModel);
        ((PointHitAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((PointHitAlgorithmModelObserver) this.pointHitOutputModel);
        ((PointMissAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((PointMissAlgorithmModelObserver) this.pointMissOutputModel);
        ((TimerAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((TimerAlgorithmModelObserver) this.timerOutputModel);
        // have the result model observe changes to the simulation model
        ((MonteCarloSimulation) this.uniformPointSimulation).registerObserver((ShapesRatioOutputModel) this.shapesRatioOutputModel);
        // have the result model observe changes to the simulation model
        ((MonteCarloSimulation) this.uniformPointSimulation).registerObserver((ImageRatioOutputModel) this.imageRatioOutputModel);

        ((MultiplePointSimulationInterface) this.gridPointSimulation).registerObserver((MultiplePointAlgorithmModelObserver) this.plotOutputModel);
        ((PointAlgorithmModelInterface) this.gridPointSimulation).registerObserver((PointGeneratorAlgorithmModelObserver) this.pointOutputModel);
        ((PointHitAlgorithmModelInterface) this.gridPointSimulation).registerObserver((PointHitAlgorithmModelObserver) this.pointHitOutputModel);
        ((PointMissAlgorithmModelInterface) this.gridPointSimulation).registerObserver((PointMissAlgorithmModelObserver) this.pointMissOutputModel);
        // have the result model observe changes to the simulation model
        ((PixelGridSimulation) this.gridPointSimulation).registerObserver((ShapesRatioOutputModel) this.shapesRatioOutputModel);
        ((TimerAlgorithmModelInterface) this.gridPointSimulation).registerObserver((TimerAlgorithmModelObserver) this.timerOutputModel);
        // have the result model observe changes to the simulation model
        ((PixelGridSimulation) this.gridPointSimulation).registerObserver((ImageRatioOutputModel) this.imageRatioOutputModel);
    }

    public void initAlgorithmModels()
    {
        singlePointSimulation = new DiagnosticSimulation();
        uniformPointSimulation = new MonteCarloSimulation(new JavaRandomPointGenerator());
        gridPointSimulation = new PixelGridSimulation();

        ((TargetInputModel) targetInputModel).registerObserver((TargetInputModelObserver) singlePointSimulation);
        ((ThetaInputModel) thetaModel).registerObserver((ThetaInputModelObserver) singlePointSimulation);
        ((W0InputModel) w0Model).registerObserver((W0InputModelObserver) singlePointSimulation);
        ((W1InputModel) w1Model).registerObserver((W1InputModelObserver) singlePointSimulation);
        ((W2InputModel) w2Model).registerObserver((W2InputModelObserver) singlePointSimulation);

        ((ThetaInputModel) thetaModel).registerObserver((ThetaInputModelObserver) uniformPointSimulation);
        ((W0InputModel) w0Model).registerObserver((W0InputModelObserver) uniformPointSimulation);
        ((W1InputModel) w1Model).registerObserver((W1InputModelObserver) uniformPointSimulation);
        ((W2InputModel) w2Model).registerObserver((W2InputModelObserver) uniformPointSimulation);

        ((ThetaInputModel) thetaModel).registerObserver((ThetaInputModelObserver) gridPointSimulation);
        ((W0InputModel) w0Model).registerObserver((W0InputModelObserver) gridPointSimulation);
        ((W1InputModel) w1Model).registerObserver((W1InputModelObserver) gridPointSimulation);
        ((W2InputModel) w2Model).registerObserver((W2InputModelObserver) gridPointSimulation);
    }

    @Override
    public void initModelAlgorithmDartGuns()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void initModelInputStates()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void initModelResultStates()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
