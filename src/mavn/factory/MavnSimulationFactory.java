/*
MavnController -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network).
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
import mavn.simModel.algorithm.model.multiplePointSimulation.GridMultiPointSimulation;
import mavn.simModel.algorithm.model.multiplePointSimulation.MultiplePointModelInterface;
import mavn.simModel.algorithm.model.multiplePointSimulation.UniformMultiPointSimulation;
import mavn.simModel.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.AndLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.OrLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.OutputLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.PointAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.PointHitAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.PointMissAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.generator.JavaRandomPointGenerator;
import mavn.simModel.algorithm.model.point.observer.PointGeneratorAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.PointHitAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.PointMissAlgorithmModelObserver;
import mavn.simModel.algorithm.model.singlePointSimulation.SinglePointSimulation;
import mavn.simModel.algorithm.model.timer.TimerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.timer.observer.TimerAlgorithmModelObserver;
import mavn.simModel.algorithm.properties.view.SimulationPropertiesFrame;
import mavn.simModel.input.model.TargetInputModel;
import mavn.simModel.input.model.ThetaInputModel;
import mavn.simModel.input.model.W0InputModel;
import mavn.simModel.input.model.W1InputModel;
import mavn.simModel.input.model.W2InputModel;
import mavn.simModel.algorithm.properties.view.state.PointGeneratorState;
import mavn.simModel.algorithm.properties.view.state.SimulationPropertiesState;
import mavn.simModel.output.mediator.OutputMediator;
import mavn.simModel.input.model.observer.TargetModelObserver;
import mavn.simModel.input.model.observer.ThetaModelObserver;
import mavn.simModel.input.model.observer.W0ModelObserver;
import mavn.simModel.input.model.observer.W1ModelObserver;
import mavn.simModel.input.model.observer.W2ModelObserver;
import mavn.simModel.input.view.changeEvent.InputModelChangeEvent;
import mavn.simModel.input.view.InputViewTargetModel;
import mavn.simModel.input.view.InputViewThetaModel;
import mavn.simModel.input.view.InputViewW0Model;
import mavn.simModel.input.view.InputViewW1Model;
import mavn.simModel.input.view.InputViewW2Model;
import mavn.simModel.input.view.action.InputViewAction;
import mavn.simModel.input.view.layoutPanel.InputViewGridLayoutPanel;
import mavn.simModel.input.view.state.InputViewState;
import mavn.simModel.network.mediator.NetworkMediator;
import mavn.simModel.network.mediator.NetworkMediatorInterface;
import mavn.simModel.network.model.AndLayerOutputModel;
import mavn.simModel.network.model.OrLayerOutputModel;
import mavn.simModel.network.model.OutputLayerOutputModel;
import mavn.simModel.output.view.mediator.OutputViewMediator;
import mavn.simModel.output.model.ImageRatioOutputModel;
import mavn.simModel.output.model.ShapesRatioOutputModel;
import mavn.simModel.output.model.SimulationOutputModel;
import mavn.simModel.output.view.actions.NetworkBarAction;
import mavn.simModel.output.view.actions.OutputBarAction;
import mavn.simModel.output.view.actions.PlotBarAction;
import mavn.simModel.output.view.actions.PropertiesBarAction;
import mavn.simModel.output.view.actions.RunSimulationBarAction;
import mavn.simModel.output.view.actions.SimulationBarAction;
import mavn.simModel.output.view.actions.ViewBarAction;
import mavn.simModel.output.view.layoutPanel.ControlBar;
import mavn.simModel.output.view.layoutPanel.ModelOutputDefaultLayoutView;
import mavn.simModel.output.view.state.OutputViewState;
import mavn.simModel.plot.mediator.PlotMediator;
import mavn.simModel.plot.mediator.PlotMediatorInterface;
import mavn.simModel.plot.model.PointHitOutputModel;
import mavn.simModel.plot.model.PointMissOutputModel;
import mavn.simModel.plot.model.PointOutputModel;
import mavn.simModel.plot.model.TimerOutputModel;
import mavn.view.SimControlView;
import mavn.view.action.SimControlAction;
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
        initSimulationModels();
        initNetworkOutputModels();
        initPointOutputModels();
        initModelInputControllers();
        initOutputMediator();
        initPlotMediator();
        initNetworkMediator();
        initModelViews();
        view.setOutputView();
        // Display the view.
        this.view.setVisible(true);
    }

    @Override
    public void initInputModels()
    {
        // Initialize the input models
        targetModel = new TargetInputModel();
        thetaModel = new ThetaInputModel();
        w0Model = new W0InputModel();
        w1Model = new W1InputModel();
        w2Model = new W2InputModel();

        // Add the input models to the collection
        // Use globals to associate the object with the correct index
        inputModels.add(Globals.TARGET_MODEL, targetModel);
        inputModels.add(Globals.THETA_MODEL, thetaModel);
        inputModels.add(Globals.W0_MODEL, w0Model);
        inputModels.add(Globals.W1_MODEL, w1Model);
        inputModels.add(Globals.W2_MODEL, w2Model);
    }

    @Override
    public void initModelInputControllers()
    {
        // Initialize the input controllers.
        targetController = new InputController(targetModel);
        thetaController = new InputController(thetaModel);
        w0Controller = new InputController(w0Model);
        w1Controller = new InputController(w1Model);
        w2Controller = new InputController(w2Model);
    }

    public void initOutputMediator()
    {
        pointGeneratorState = new PointGeneratorState();

        propertiesState = new SimulationPropertiesState();
        propertiesFrame = new SimulationPropertiesFrame(
                pointGeneratorState, propertiesState);
        ((SimulationPropertiesState) propertiesState).setView(propertiesFrame);

        outputMediator = new OutputViewMediator(targetModel, simulationModelResult,
                shapesRatioOutputModel, imageRatioOutputModel, propertiesState);

        controlBarMediator = new OutputMediator(singlePointSimulation,
                uniformPointSimulation, gridPointSimulation, outputMediator, propertiesFrame);

    }

    public void initPlotMediator()
    {
        plotMediator = new PlotMediator(targetModel, pointOutputModel,
                pointHitOutputModel, pointMissOutputModel, timerOutputModel,
                simulationModelResult, propertiesState);
    }

    public void initNetworkMediator()
    {
        networkMediator = new NetworkMediator(andLayerModelResult,
                orLayerModelResult, outputLayerModelResult, propertiesState);
    }

    @Override
    public void initModelViews()
    {
        simulationBarAction = new SimulationBarAction(inputModels,
                (NetworkMediator) networkMediator, controlBarMediator,
                (PlotMediator) plotMediator);
        propertiesBarAction = new PropertiesBarAction(controlBarMediator,
                propertiesState);
        modelOuputBarAction = new OutputBarAction(controlBarMediator);
        newtorkViewBarAction = new NetworkBarAction(controlBarMediator,
                (NetworkMediator) networkMediator);
        viewBarAction = new ViewBarAction();
        plotViewBarAction = new PlotBarAction((PlotMediator) plotMediator);
        runSimulationAction = new RunSimulationBarAction(
                (NetworkMediator) networkMediator, controlBarMediator,
                (PlotMediator) plotMediator);

        ouputControlBar = new ControlBar(simulationBarAction,
                propertiesBarAction, modelOuputBarAction, viewBarAction,
                newtorkViewBarAction, plotViewBarAction, runSimulationAction);

        inputControlBar = new ControlBar(simulationBarAction, propertiesBarAction,
                modelOuputBarAction, viewBarAction, newtorkViewBarAction,
                plotViewBarAction, runSimulationAction);

        outputViewState = new OutputViewState((ControlBar) ouputControlBar, (ControlBar) inputControlBar);

        ((SimulationPropertiesFrame) propertiesFrame).setOutputState(outputViewState);
        ((OutputViewMediator) outputMediator).setModelResultState(outputViewState);
        ((NetworkMediator) networkMediator).setModelResultState(outputViewState);
        ((PropertiesBarAction) propertiesBarAction).setOutputViewState(outputViewState);
        ((PlotMediator) plotMediator).setOutputViewState(outputViewState);

        outputLayoutPanel = new ModelOutputDefaultLayoutView(ouputControlBar,
                ((OutputViewMediator) outputMediator).getView(), ((PlotMediatorInterface) plotMediator).getView(),
                ((NetworkMediatorInterface) networkMediator).getView());

        modelChanged = new InputModelChangeEvent(inputModels, (NetworkMediatorInterface) networkMediator, outputViewState);

        targetState = new InputViewState();
        thetaState = new InputViewState();
        w0State = new InputViewState();
        w1State = new InputViewState();
        w2State = new InputViewState();

        targetPanelAction = new InputViewAction(targetController, targetModel);
        thetaPanelAction = new InputViewAction(thetaController, thetaModel);
        w0PanelAction = new InputViewAction(w0Controller, w0Model);
        w1PanelAction = new InputViewAction(w1Controller, w1Model);
        w2PanelAction = new InputViewAction(w2Controller, w2Model);

        targetPanel = new InputViewTargetModel(targetPanelAction, targetController, targetModel, targetState, modelChanged);
        thetaPanel = new InputViewThetaModel(thetaPanelAction, thetaController, thetaModel, thetaState, modelChanged);
        w0Panel = new InputViewW0Model(w0PanelAction, w0Controller, w0Model, w0State, modelChanged);
        w1Panel = new InputViewW1Model(w1PanelAction, w1Controller, w1Model, w1State, modelChanged);
        w2Panel = new InputViewW2Model(w2PanelAction, w2Controller, w2Model, w2State, modelChanged);

        ((InputViewAction) targetPanelAction).setView(targetPanel);
        ((InputViewAction) thetaPanelAction).setView(thetaPanel);
        ((InputViewAction) w0PanelAction).setView(w0Panel);
        ((InputViewAction) w1PanelAction).setView(w1Panel);
        ((InputViewAction) w2PanelAction).setView(w2Panel);

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
        ((TargetInputModel) targetModel).registerObserver((InputViewTargetModel) targetPanel);
        ((ThetaInputModel) thetaModel).registerObserver((InputViewThetaModel) thetaPanel);
        ((W0InputModel) w0Model).registerObserver((InputViewW0Model) w0Panel);
        ((W1InputModel) w1Model).registerObserver((InputViewW1Model) w1Panel);
        ((W2InputModel) w2Model).registerObserver((InputViewW2Model) w2Panel);

        inputView = new InputViewGridLayoutPanel(inputViews, inputControlBar);
        simControlAction = new SimControlAction(inputModels);
        view = new SimControlView(newtorkViewBarAction, modelOuputBarAction,
                simControlAction, plotViewBarAction, propertiesBarAction,
                runSimulationAction, viewBarAction, inputView,
                outputLayoutPanel, outputViewState);
        modelChanged.setView(view);
        ((ViewBarAction) viewBarAction).setView(view);
        ((PlotBarAction) plotViewBarAction).setViewState(outputViewState);
        ((SimulationBarAction) simulationBarAction).setOutputViewState(outputViewState);
        ((OutputViewState)outputViewState).setView(view);
        ((OutputViewState)outputViewState).init();
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

        super.simulationModelResult = new SimulationOutputModel();
        super.shapesRatioOutputModel = new ShapesRatioOutputModel();
        super.imageRatioOutputModel = new ImageRatioOutputModel();

        ((SinglePointSimulation) this.singlePointSimulation).registerObserver((SimulationOutputModel) this.simulationModelResult);

        ((MultiplePointModelInterface) this.uniformPointSimulation).registerObserver((MultiplePointAlgorithmModelObserver) this.simulationModelResult);
        ((PointAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((PointGeneratorAlgorithmModelObserver) this.pointOutputModel);
        ((PointHitAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((PointHitAlgorithmModelObserver) this.pointHitOutputModel);
        ((PointMissAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((PointMissAlgorithmModelObserver) this.pointMissOutputModel);
        ((TimerAlgorithmModelInterface) this.uniformPointSimulation).registerObserver((TimerAlgorithmModelObserver) this.timerOutputModel);
        // have the result model observe changes to the simulation model
        ((UniformMultiPointSimulation) this.uniformPointSimulation).registerObserver((ShapesRatioOutputModel) this.shapesRatioOutputModel);
        // have the result model observe changes to the simulation model
        ((UniformMultiPointSimulation) this.uniformPointSimulation).registerObserver((ImageRatioOutputModel) this.imageRatioOutputModel);

        ((MultiplePointModelInterface) this.gridPointSimulation).registerObserver((MultiplePointAlgorithmModelObserver) this.simulationModelResult);
        ((PointAlgorithmModelInterface) this.gridPointSimulation).registerObserver((PointGeneratorAlgorithmModelObserver) this.pointOutputModel);
        ((PointHitAlgorithmModelInterface) this.gridPointSimulation).registerObserver((PointHitAlgorithmModelObserver) this.pointHitOutputModel);
        ((PointMissAlgorithmModelInterface) this.gridPointSimulation).registerObserver((PointMissAlgorithmModelObserver) this.pointMissOutputModel);
        // have the result model observe changes to the simulation model
        ((GridMultiPointSimulation) this.gridPointSimulation).registerObserver((ShapesRatioOutputModel) this.shapesRatioOutputModel);
        ((TimerAlgorithmModelInterface) this.gridPointSimulation).registerObserver((TimerAlgorithmModelObserver) this.timerOutputModel);
        // have the result model observe changes to the simulation model
        ((GridMultiPointSimulation) this.gridPointSimulation).registerObserver((ImageRatioOutputModel) this.imageRatioOutputModel);
    }

    public void initSimulationModels()
    {
        singlePointSimulation = new SinglePointSimulation();
        uniformPointSimulation = new UniformMultiPointSimulation(new JavaRandomPointGenerator());
        gridPointSimulation = new GridMultiPointSimulation();

        ((TargetInputModel) targetModel).registerObserver((TargetModelObserver) singlePointSimulation);
        ((ThetaInputModel) thetaModel).registerObserver((ThetaModelObserver) singlePointSimulation);
        ((W0InputModel) w0Model).registerObserver((W0ModelObserver) singlePointSimulation);
        ((W1InputModel) w1Model).registerObserver((W1ModelObserver) singlePointSimulation);
        ((W2InputModel) w2Model).registerObserver((W2ModelObserver) singlePointSimulation);

        ((ThetaInputModel) thetaModel).registerObserver((ThetaModelObserver) uniformPointSimulation);
        ((W0InputModel) w0Model).registerObserver((W0ModelObserver) uniformPointSimulation);
        ((W1InputModel) w1Model).registerObserver((W1ModelObserver) uniformPointSimulation);
        ((W2InputModel) w2Model).registerObserver((W2ModelObserver) uniformPointSimulation);

        ((ThetaInputModel) thetaModel).registerObserver((ThetaModelObserver) gridPointSimulation);
        ((W0InputModel) w0Model).registerObserver((W0ModelObserver) gridPointSimulation);
        ((W1InputModel) w1Model).registerObserver((W1ModelObserver) gridPointSimulation);
        ((W2InputModel) w2Model).registerObserver((W2ModelObserver) gridPointSimulation);
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
