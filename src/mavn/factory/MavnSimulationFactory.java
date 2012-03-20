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
import javax.swing.JPanel;
import mavn.globals.Globals;
import mavn.simModel.algorithm.model.multiplePointSimulation.MultiplePointModelInterface;
import mavn.simModel.algorithm.model.multiplePointSimulation.UniformMultiPointSimulation;
import mavn.simModel.algorithm.model.network.AndLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.OrLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.OutputLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.ImageRatioAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.PointModelInterface;
import mavn.simModel.algorithm.model.point.ShapesRatioAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.generator.JavaRandomPointGenerator;
import mavn.simModel.algorithm.model.point.observer.ImageRatioAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.PointGeneratorModelObserver;
import mavn.simModel.algorithm.model.point.observer.ShapesRatioAlgorithmModelObserver;
import mavn.simModel.algorithm.model.singlePointSimulation.SinglePointModelInterface;
import mavn.simModel.algorithm.model.singlePointSimulation.SinglePointSimulation;
import mavn.simModel.input.model.TargetInputModel;
import mavn.simModel.input.model.ThetaInputModel;
import mavn.simModel.input.model.W0InputModel;
import mavn.simModel.input.model.W1InputModel;
import mavn.simModel.input.model.W2InputModel;
import mavn.simModel.algorithm.properties.view.state.SimulationPropertiesState;
import mavn.simModel.algorithm.properties.view.SimulationPropertiesFrame;
import mavn.simModel.input.view.changeEvent.InputModelChangeEvent;
import mavn.simModel.input.view.InputViewTargetModel;
import mavn.simModel.input.view.InputViewThetaModel;
import mavn.simModel.input.view.InputViewW0Model;
import mavn.simModel.input.view.InputViewW1Model;
import mavn.simModel.input.view.InputViewW2Model;
import mavn.simModel.input.view.layoutPanel.InputViewGridLayoutPanel;
import mavn.simModel.network.mediator.NetworkMediator;
import mavn.simModel.network.mediator.NetworkRendererInterface;
import mavn.simModel.network.model.AndLayerOutputModel;
import mavn.simModel.network.model.OrLayerOutputModel;
import mavn.simModel.network.model.OutputLayerOutputModel;
import mavn.simModel.output.mediator.OutputMediator;
import mavn.simModel.output.model.ImageRatioOutputModel;
import mavn.simModel.output.model.ShapesRatioOutputModel;
import mavn.simModel.output.model.SimulationOutputModel;
import mavn.simModel.output.view.actions.ModelOutputViewAction;
import mavn.simModel.output.view.layoutPanel.ModelOutputDefaultLayoutView;
import mavn.simModel.plot.mediator.PlotMediator;
import mavn.simModel.plot.mediator.PlotMediatorInterface;
import mavn.simModel.plot.model.PointOutputModel;
import mavn.view.SimControlView;
import simulyn.input.controller.InputController;
import simulyn.input.model.InputModelInterface;
import simulyn.output.mediator.OutputMediatorInterface;
import simulyn.ui.components.inputModelPanel.InputViewAbstract;
import simulyn.ui.components.outputModelPanel.SimulynDefaultOutputView;

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

    @Override
    public void initModelViews()
    {
        pointOutputModel = new PointOutputModel();
        ((PointModelInterface) multiplePointSimulation).registerObserver
                ((PointGeneratorModelObserver) pointOutputModel);


        outputMediator = new OutputMediator(singlePointSimulation,
                multiplePointSimulation, simulationModelResult,
                shapesRatioResult, imageRatioResult);

        networkMediator = new NetworkMediator(singlePointSimulation,
                multiplePointSimulation, andLayerModelResult,
                orLayerModelResult, outputLayerModelResult);

        plotMediator = new PlotMediator(singlePointSimulation,
                multiplePointSimulation, pointOutputModel, simulationModelResult);

        outputControllViewAction = new ModelOutputViewAction((OutputMediatorInterface) outputMediator);
        outputViewController = new SimulynDefaultOutputView(outputControllViewAction);
        outputViewController.setOutputViewRendererPanel(((OutputMediatorInterface)outputMediator).getView());

        outputLayoutPanel = new ModelOutputDefaultLayoutView(
                (JPanel) outputViewController, ((PlotMediatorInterface) plotMediator).getView(),
                ((NetworkRendererInterface) networkMediator).getView());


        modelChanged = new InputModelChangeEvent(inputModels, networkRendererMediator, outputModelsState, view);

        targetPanel = new InputViewTargetModel(targetController, targetModel, modelChanged);
        thetaPanel = new InputViewThetaModel(thetaController, thetaModel, modelChanged);
        w0Panel = new InputViewW0Model(w0Controller, w0Model, modelChanged);
        w1Panel = new InputViewW1Model(w1Controller, w1Model, modelChanged);
        w2Panel = new InputViewW2Model(w2Controller, w2Model, modelChanged);

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

        //.modelPanel = new InputViewGridLayoutPanel(modelInputPanelsList);
        propertiesFrame = new SimulationPropertiesFrame(outputModelsState, pointModelOutputState, propertiesState);
        propertiesState = new SimulationPropertiesState(propertiesFrame);

        inputView = new InputViewGridLayoutPanel(inputViews);
        view = new SimControlView(inputView, outputLayoutPanel);
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
        ((AndLayerAlgorithmModelInterface) multiplePointSimulation).registerObserver((AndLayerAlgorithmModelObserver) andLayerModelResult);

        ((OrLayerAlgorithmModelInterface) singlePointSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);
        ((OrLayerAlgorithmModelInterface) multiplePointSimulation).registerObserver((OrLayerAlgorithmModelObserver) orLayerModelResult);

        ((OutputLayerAlgorithmModelInterface) singlePointSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
        ((OutputLayerAlgorithmModelInterface) multiplePointSimulation).registerObserver((OutputLayerAlgorithmModelObserver) outputLayerModelResult);
    }

    /**
     * Initialize the Point Output Models.
     */
    public void initPointOutputModels()
    {
        super.simulationModelResult = new SimulationOutputModel((MultiplePointModelInterface) multiplePointSimulation, (SinglePointModelInterface) singlePointSimulation);
        super.shapesRatioResult = new ShapesRatioOutputModel();
        super.imageRatioResult = new ImageRatioOutputModel();

        ((ShapesRatioAlgorithmModelInterface) multiplePointSimulation).registerObserver((ShapesRatioAlgorithmModelObserver) shapesRatioResult);
        ((ImageRatioAlgorithmModelInterface) multiplePointSimulation).registerObserver((ImageRatioAlgorithmModelObserver) imageRatioResult);
    }

    public void initSimulationModels()
    {
        singlePointSimulation = new SinglePointSimulation();
        multiplePointSimulation = new UniformMultiPointSimulation(new JavaRandomPointGenerator());
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
