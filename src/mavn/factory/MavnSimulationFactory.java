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

import file.open.observer.OpenFileObserver;
import java.util.ArrayList;
import mavn.simModel.input.controller.ModelInputFileController;
import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.simModel.result.controller.ModelResultsController;
import mavn.simModel.result.controller.ModelResultControllerInterface;
import mavn.dartGun.CMWC4096RngDartGun;
import mavn.dartGun.CellularAutomatonRngDartGun;
import mavn.dartGun.DartGunInterface;
import mavn.dartGun.JavaRngDartGun;
import mavn.dartGun.MtRngDartGun;
import mavn.dartGun.XORRngDartGun;
import mavn.globals.Globals;
import mavn.simModel.algorithm.model.dart.DartModelInterface;
import mavn.simModel.algorithm.model.AlgorithmModelInterface;
import mavn.simModel.algorithm.network.AndLayerInterface;
import mavn.simModel.algorithm.model.MavnMultiplePointModel;
import mavn.simModel.algorithm.model.MavnSinglePointModel;
import mavn.simModel.algorithm.network.ImageRatioInterface;
import mavn.simModel.algorithm.network.OrLayerInterface;
import mavn.simModel.algorithm.network.OutputLayerInterface;
import mavn.simModel.algorithm.network.ShapesRatioInterface;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.simModel.input.model.TargetModel;
import mavn.simModel.input.model.ThetaModel;
import mavn.simModel.input.model.W0Model;
import mavn.simModel.input.model.W1Model;
import mavn.simModel.input.model.W2Model;
import mavn.simModel.input.view.state.InputStateInterface;
import mavn.simModel.result.view.state.OutputStateInterface;
import mavn.simModel.result.view.state.ResultsState;
import mavn.simModel.input.view.state.InputState;
import mavn.simModel.properties.state.PropertiesState;
import mavn.simModel.properties.view.PropertiesFrame;
import mavn.simModel.result.model.AndLayerModel;
import mavn.simModel.result.model.DartResult;
import mavn.simModel.result.model.DartResultInterface;
import mavn.simModel.result.model.ImageRatioModel;
import mavn.simModel.result.model.ModelResultInterface;
import mavn.simModel.result.model.OrLayerModel;
import mavn.simModel.result.model.OutputLayerModel;
import mavn.simModel.result.model.ResultModel;
import mavn.simModel.result.model.ShapesRatioModel;
import mavn.view.input.ModelChangeEvent;
import mavn.view.input.ModelInputControlPanel;
import mavn.view.input.ModelInputPanelAbstract;
import mavn.view.input.ModelInputTargetPanel;
import mavn.view.input.ModelInputThetaPanel;
import mavn.view.input.ModelInputW0Panel;
import mavn.view.input.ModelInputW1Panel;
import mavn.view.input.ModelInputW2Panel;
import mavn.view.result.MavnNetworkView;
import mavn.view.input.SimControlView;

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
        // Initialize the collections.
        modelInputModelsList = new ArrayList<ModelInputInterface>();
        modelInputControllersList = new ArrayList<ModelInputFileInterface>();
        modelInputStatesList = new ArrayList<InputStateInterface>();
        modelResultControllersList = new ArrayList<ModelResultControllerInterface>();
        modelResultModelsList = new ArrayList<ModelResultInterface>();
        modelResultStatesList = new ArrayList<OutputStateInterface>();
        modelDartGunModelsList = new ArrayList<DartGunInterface>();
        modelAlgorithmsModelsList = new ArrayList<AlgorithmModelInterface>();
        modelDartResultModelsList = new ArrayList<DartResultInterface>();
        modelInputPanelsList = new ArrayList<ModelInputPanelAbstract>();
        modelInputFileControllersList = new ArrayList<OpenFileObserver>();

        initModelInputControllers();
        initModelInputModels();
        initModelResultControllers();
        initModelResultModels();
        initModelViews();
        initModelResultStates();
        initModelInputStates();
        initModelAlgorithmDartGuns();
        initModelAlgorithms();

        view.setNetworkView();
        // Display the view.
        this.view.setVisible(true);
    }

    @Override
    public void initModelAlgorithms()
    {
        singlePoint = new MavnSinglePointModel(modelInputControllersList);
        ca = new MavnMultiplePointModel(modelInputControllersList, modelDartGunModelsList.get(Globals.CELLULAR_AUTOMATON_DARTGUN));
        cmwc = new MavnMultiplePointModel(modelInputControllersList, modelDartGunModelsList.get(Globals.CMWC4096_DARTGUN));
        mt = new MavnMultiplePointModel(modelInputControllersList, modelDartGunModelsList.get(Globals.MT_DARTGUN));
        random = new MavnMultiplePointModel(modelInputControllersList, modelDartGunModelsList.get(Globals.JAVA_RNG_DARTGUN));
        xor = new MavnMultiplePointModel(modelInputControllersList, modelDartGunModelsList.get(Globals.XOR_DARTGUN));

        singlePoint.registerObserver((ModelResultsController) resultsController);
        ((AndLayerInterface) singlePoint).registerObserver((ModelResultsController) resultsController);
        ((OrLayerInterface) singlePoint).registerObserver((ModelResultsController) resultsController);
        ((OutputLayerInterface) singlePoint).registerObserver((ModelResultsController) resultsController);

        cmwc.registerObserver((ModelResultsController) resultsController);
        ((DartModelInterface) cmwc).registerObserver((ModelResultsController) resultsController);
        ((AndLayerInterface) cmwc).registerObserver((ModelResultsController) resultsController);
        ((OrLayerInterface) cmwc).registerObserver((ModelResultsController) resultsController);
        ((OutputLayerInterface) cmwc).registerObserver((ModelResultsController) resultsController);
        ((ShapesRatioInterface) cmwc).registerObserver((ModelResultsController) resultsController);
        ((ImageRatioInterface) cmwc).registerObserver((ModelResultsController) resultsController);

        ca.registerObserver((ModelResultsController) resultsController);
        ((DartModelInterface) ca).registerObserver((ModelResultsController) resultsController);
        ((AndLayerInterface) ca).registerObserver((ModelResultsController) resultsController);
        ((OrLayerInterface) ca).registerObserver((ModelResultsController) resultsController);
        ((OutputLayerInterface) ca).registerObserver((ModelResultsController) resultsController);
        ((ShapesRatioInterface) ca).registerObserver((ModelResultsController) resultsController);
        ((ImageRatioInterface) ca).registerObserver((ModelResultsController) resultsController);

        mt.registerObserver((ModelResultsController) resultsController);
        ((DartModelInterface) mt).registerObserver((ModelResultsController) resultsController);
        ((AndLayerInterface) mt).registerObserver((ModelResultsController) resultsController);
        ((OrLayerInterface) mt).registerObserver((ModelResultsController) resultsController);
        ((OutputLayerInterface) mt).registerObserver((ModelResultsController) resultsController);
        ((ShapesRatioInterface) mt).registerObserver((ModelResultsController) resultsController);
        ((ImageRatioInterface) mt).registerObserver((ModelResultsController) resultsController);

        random.registerObserver((ModelResultsController) resultsController);
        ((DartModelInterface) random).registerObserver((ModelResultsController) resultsController);
        ((AndLayerInterface) random).registerObserver((ModelResultsController) resultsController);
        ((OrLayerInterface) random).registerObserver((ModelResultsController) resultsController);
        ((OutputLayerInterface) random).registerObserver((ModelResultsController) resultsController);
        ((ShapesRatioInterface) random).registerObserver((ModelResultsController) resultsController);
        ((ImageRatioInterface) random).registerObserver((ModelResultsController) resultsController);

        xor.registerObserver((ModelResultsController) (ModelResultsController) resultsController);
        ((DartModelInterface) xor).registerObserver((ModelResultsController) resultsController);
        ((AndLayerInterface) xor).registerObserver((ModelResultsController) resultsController);
        ((OrLayerInterface) xor).registerObserver((ModelResultsController) resultsController);
        ((OutputLayerInterface) xor).registerObserver((ModelResultsController) resultsController);
        ((ShapesRatioInterface) xor).registerObserver((ModelResultsController) resultsController);
        ((ImageRatioInterface) xor).registerObserver((ModelResultsController) resultsController);

        modelAlgorithmsModelsList.add(Globals.SINGLE_POINT_ALGORITHM, singlePoint);
        modelAlgorithmsModelsList.add(Globals.CELLULAR_AUTOMATON_ALGORITHM, ca);
        modelAlgorithmsModelsList.add(Globals.CMWC4096_ALGORITHM, cmwc);
        modelAlgorithmsModelsList.add(Globals.JAVA_RNG_ALGORITHM, random);
        modelAlgorithmsModelsList.add(Globals.MT_ALGORITHM, mt);
        modelAlgorithmsModelsList.add(Globals.XOR_ALGORITHM, xor);
    }

    @Override
    public void initModelAlgorithmDartGuns()
    {
        modelDartGunModelsList.add(Globals.CELLULAR_AUTOMATON_DARTGUN, caDartGun = new CellularAutomatonRngDartGun());
        modelDartGunModelsList.add(Globals.CMWC4096_DARTGUN, cmwcDartGun = new CMWC4096RngDartGun());
        modelDartGunModelsList.add(Globals.JAVA_RNG_DARTGUN, javaDartGun = new JavaRngDartGun());
        modelDartGunModelsList.add(Globals.MT_DARTGUN, mtDartGun = new MtRngDartGun());
        modelDartGunModelsList.add(Globals.XOR_DARTGUN, xOrDartGun = new XORRngDartGun());
    }

    @Override
    public void initModelInputControllers()
    {
        // Get a new instance of the input models
        targetModel = new TargetModel();
        thetaModel = new ThetaModel();
        w0Model = new W0Model();
        w1Model = new W1Model();
        w2Model = new W2Model();

        // Initialize the input controllers.
        // Pass them an instance of the desired View and Model
        targetController = new ModelInputFileController(targetModel);
        thetaController = new ModelInputFileController(thetaModel);
        w0Controller = new ModelInputFileController(w0Model);
        w1Controller = new ModelInputFileController(w1Model);
        w2Controller = new ModelInputFileController(w2Model);

        // Add the input controllers to the collection.
        // Use the Globals to associate the object with the correct index.
        modelInputControllersList.add(Globals.TARGET_CONTROLLER, targetController);
        modelInputControllersList.add(Globals.THETA_CONTROLLER, thetaController);
        modelInputControllersList.add(Globals.W0_CONTROLLER, w0Controller);
        modelInputControllersList.add(Globals.W1_CONTROLLER, w1Controller);
        modelInputControllersList.add(Globals.W2_CONTROLLER, w2Controller);
    }

    @Override
    public void initModelInputModels()
    {
        // Add the input models to the collection
        // Use globals to associate the object with the correct index
        modelInputModelsList.add(Globals.TARGET_MODEL, targetModel);
        modelInputModelsList.add(Globals.THETA_MODEL, thetaModel);
        modelInputModelsList.add(Globals.W0_MODEL, w0Model);
        modelInputModelsList.add(Globals.W1_MODEL, w1Model);
        modelInputModelsList.add(Globals.W2_MODEL, w2Model);

        modelInputFileControllersList.add(Globals.TARGET_CONTROLLER, (OpenFileObserver) targetController);
        modelInputFileControllersList.add(Globals.THETA_CONTROLLER, (OpenFileObserver) thetaController);
        modelInputFileControllersList.add(Globals.W0_CONTROLLER, (OpenFileObserver) w0Controller);
        modelInputFileControllersList.add(Globals.W1_CONTROLLER, (OpenFileObserver) w1Controller);
        modelInputFileControllersList.add(Globals.W2_CONTROLLER, (OpenFileObserver) w2Controller);
    }

    @Override
    public void initModelInputStates()
    {
        // Initialize input state modules by passing them a View
        targetState = new InputState(targetPanel);
        thetaState = new InputState(thetaPanel);
        w0State = new InputState(w0Panel);
        w1State = new InputState(w1Panel);
        w2State = new InputState(w2Panel);

        // Add the input state modules to the collection
        // Use Globals to associate the right object with the right index
        modelInputStatesList.add(Globals.TARGET_STATE, targetState);
        modelInputStatesList.add(Globals.THETA_STATE, thetaState);
        modelInputStatesList.add(Globals.W0_STATE, w0State);
        modelInputStatesList.add(Globals.W1_STATE, w1State);
        modelInputStatesList.add(Globals.W2_STATE, w2State);
    }

    @Override
    public void initModelResultControllers()
    {
        // Initialize a new output module by passing it a View.
        resultsController = new ModelResultsController(this);
        modelResultControllersList.add(Globals.RESULTS_CONTROLLER, resultsController);
    }

    @Override
    public void initModelResultModels()
    {
        // Initize a new View by passing it this controller.
        this.networkPanel = new MavnNetworkView(this);

        super.andLayerModelResult = new AndLayerModel();
        ((AndLayerModel) andLayerModelResult).registerObserver(networkPanel);
        modelResultModelsList.add(Globals.AND_LAYER_MODEL, andLayerModelResult);
        super.orLayerModelResult = new OrLayerModel();
        ((OrLayerModel) orLayerModelResult).registerObserver(networkPanel);
        modelResultModelsList.add(Globals.OR_LAYER_MODEL, orLayerModelResult);
        super.outputLayerModelResult = new OutputLayerModel();
        ((OutputLayerModel) outputLayerModelResult).registerObserver(networkPanel);
        modelResultModelsList.add(Globals.OUTPUT_LAYER_MODEL, outputLayerModelResult);
        super.simulationResult = new ResultModel();
        ((ResultModel) simulationResult).registerObserver(networkPanel);
        modelResultModelsList.add(Globals.SIMULATION_RESULT, simulationResult);
        super.shapesRatioResult = new ShapesRatioModel();
        ((ShapesRatioModel) shapesRatioResult).registerObserver(networkPanel);
        modelResultModelsList.add(Globals.SHAPES_RATIO_RESULT, shapesRatioResult);
        super.imageRatioResult = new ImageRatioModel();
        ((ImageRatioModel) imageRatioResult).registerObserver(networkPanel);
        modelResultModelsList.add(Globals.IMAGE_RATIO_RESULT, imageRatioResult);


        super.dartResult = new DartResult();
        ((DartResult) dartResult).registerObserver(networkPanel);
        modelDartResultModelsList.add(Globals.DART_RESULT_MODEL, dartResult);

    }

    @Override
    public void initModelResultStates()
    {
        // Initialize a new output state module by passing it a View.
        resultsState = new ResultsState(networkPanel);
        modelResultStatesList.add(Globals.RESULTS_STATE, resultsState);
    }

    @Override
    public void initModelViews()
    {
        modelChanged = new ModelChangeEvent(this);
        targetPanel = new ModelInputTargetPanel(targetController, targetModel, modelChanged);
        modelInputPanelsList.add(Globals.TARGET_PANEL, targetPanel);
        thetaPanel = new ModelInputThetaPanel(thetaController, thetaModel, modelChanged);
        modelInputPanelsList.add(Globals.THETA_PANEL, thetaPanel);
        w0Panel = new ModelInputW0Panel(w0Controller, w0Model, modelChanged);
        modelInputPanelsList.add(Globals.W0_PANEL, w0Panel);
        w1Panel = new ModelInputW1Panel(w1Controller, w1Model, modelChanged);
        modelInputPanelsList.add(Globals.W1_PANEL, w1Panel);
        w2Panel = new ModelInputW2Panel(w2Controller, w2Model, modelChanged);
        modelInputPanelsList.add(Globals.W2_PANEL, w2Panel);

        // register with the input model observers with the view
        ((TargetModel) targetModel).registerObserver((ModelInputTargetPanel) targetPanel);
        ((ThetaModel) thetaModel).registerObserver((ModelInputThetaPanel) thetaPanel);
        ((W0Model) w0Model).registerObserver((ModelInputW0Panel) w0Panel);
        ((W1Model) w1Model).registerObserver((ModelInputW1Panel) w1Panel);
        ((W2Model) w2Model).registerObserver((ModelInputW2Panel) w2Panel);

        this.modelPanel = new ModelInputControlPanel(modelInputPanelsList);
        this.view = new SimControlView(this);
        propertiesFrame = new PropertiesFrame(this);
        propertiesState = new PropertiesState(propertiesFrame);
    }
}
