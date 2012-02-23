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
import mavn.controller.InputController;
import mavn.controller.InputControllerInterface;
import mavn.controller.OutputController;
import mavn.controller.OutputControllerInterface;
import mavn.dartGun.CMWC4096RngDartGun;
import mavn.dartGun.CellularAutomatonRngDartGun;
import mavn.dartGun.DartGunInterface;
import mavn.dartGun.JavaRngDartGun;
import mavn.dartGun.MtRngDartGun;
import mavn.dartGun.XORRngDartGun;
import mavn.globals.Globals;
import mavn.math.model.DartModelInterface;
import mavn.math.model.AlgorithmModelInterface;
import mavn.math.model.AndLayerInterface;
import mavn.math.model.MavnMultiplePointModel;
import mavn.math.model.MavnSinglePointModel;
import mavn.math.model.OrLayerInterface;
import mavn.math.model.OutputLayerInterface;
import mavn.model.InputModelInterface;
import mavn.model.TargetModel;
import mavn.model.ThetaModel;
import mavn.model.W0Model;
import mavn.model.W1Model;
import mavn.model.W2Model;
import mavn.state.InputStateInterface;
import mavn.state.OutputStateInterface;
import mavn.state.ResultsState;
import mavn.state.TargetState;
import mavn.state.ThetaState;
import mavn.state.W0State;
import mavn.state.W1State;
import mavn.state.W2State;
import mavn.view.MavnNetworkView;
import mavn.view.ModelView;

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
        inputModelsList = new ArrayList<InputModelInterface>();
        inputControllersList = new ArrayList<InputControllerInterface>();
        inputStatesList = new ArrayList<InputStateInterface>();
        outputControllersList = new ArrayList<OutputControllerInterface>();
        outputStatesList = new ArrayList<OutputStateInterface>();
        dartGuns = new ArrayList<DartGunInterface>();
        algorithms = new ArrayList<AlgorithmModelInterface>();
        // Initialize the view.
        initView();

        // Display the view.
        this.view.setVisible(true);
    }

    @Override
    public void initAlgorithms()
    {
        singlePoint = new MavnSinglePointModel(inputControllersList);
        cmwc = new MavnMultiplePointModel(inputControllersList, dartGuns.get(Globals.CMWC4096_DARTGUN));
        ca = new MavnMultiplePointModel(inputControllersList, dartGuns.get(Globals.CELLULAR_AUTOMATON_DARTGUN));
        mt = new MavnMultiplePointModel(inputControllersList, dartGuns.get(Globals.MT_DARTGUN));
        random = new MavnMultiplePointModel(inputControllersList, dartGuns.get(Globals.JAVA_RNG_DARTGUN));
        xor = new MavnMultiplePointModel(inputControllersList, dartGuns.get(Globals.XOR_DARTGUN));

        singlePoint.registerObserver(networkPanel);
        ((AndLayerInterface) singlePoint).registerObserver(networkPanel);
        ((OrLayerInterface) singlePoint).registerObserver(networkPanel);
        ((OutputLayerInterface) singlePoint).registerObserver(networkPanel);

        cmwc.registerObserver(networkPanel);
        ((DartModelInterface) cmwc).registerObserver(networkPanel);
        ((AndLayerInterface) cmwc).registerObserver(networkPanel);
        ((OrLayerInterface) cmwc).registerObserver(networkPanel);
        ((OutputLayerInterface) cmwc).registerObserver(networkPanel);

        ca.registerObserver(networkPanel);
        ((DartModelInterface) ca).registerObserver(networkPanel);
        ((AndLayerInterface) ca).registerObserver(networkPanel);
        ((OrLayerInterface) ca).registerObserver(networkPanel);
        ((OutputLayerInterface) ca).registerObserver(networkPanel);

        mt.registerObserver(networkPanel);
        ((DartModelInterface) mt).registerObserver(networkPanel);
        ((AndLayerInterface) mt).registerObserver(networkPanel);
        ((OrLayerInterface) mt).registerObserver(networkPanel);
        ((OutputLayerInterface) mt).registerObserver(networkPanel);

        random.registerObserver(networkPanel);
        ((DartModelInterface) random).registerObserver(networkPanel);
        ((AndLayerInterface) random).registerObserver(networkPanel);
        ((OrLayerInterface) random).registerObserver(networkPanel);
        ((OutputLayerInterface) random).registerObserver(networkPanel);

        xor.registerObserver(networkPanel);
        ((DartModelInterface) xor).registerObserver(networkPanel);
        ((AndLayerInterface) xor).registerObserver(networkPanel);
        ((OrLayerInterface) xor).registerObserver(networkPanel);
        ((OutputLayerInterface) xor).registerObserver(networkPanel);

        algorithms.add(Globals.SINGLE_POINT_ALGORITHM, singlePoint);
        algorithms.add(Globals.CELLULAR_AUTOMATON_ALGORITHM, ca);
        algorithms.add(Globals.CMWC4096_ALGORITHM, cmwc);
        algorithms.add(Globals.JAVA_RNG_ALGORITHM, random);
        algorithms.add(Globals.MT_ALGORITHM, mt);
        algorithms.add(Globals.XOR_ALGORITHM, xor);
    }

    @Override
    public void initDartGuns()
    {
        dartGuns.add(Globals.CELLULAR_AUTOMATON_DARTGUN, caDartGun = new CellularAutomatonRngDartGun());
        dartGuns.add(Globals.CMWC4096_DARTGUN, cmwcDartGun = new CMWC4096RngDartGun());
        dartGuns.add(Globals.JAVA_RNG_DARTGUN, javaDartGun = new JavaRngDartGun());
        dartGuns.add(Globals.MT_DARTGUN, mtDartGun = new MtRngDartGun());
        dartGuns.add(Globals.XOR_DARTGUN, xOrDartGun = new XORRngDartGun());
    }

    @Override
    public void initInputControllers()
    {
        // Initialize the input controllers.
        // Pass them an instance of the desired View and Model
        targetController = new InputController(targetModel);
        thetaController = new InputController(thetaModel);
        w0Controller = new InputController(w0Model);
        w1Controller = new InputController(w1Model);
        w2Controller = new InputController(w2Model);

        // Add the input controllers to the collection.
        // Use the Globals to associate the object with the correct index.
        inputControllersList.add(Globals.TARGET_CONTROLLER, targetController);
        inputControllersList.add(Globals.THETA_CONTROLLER, thetaController);
        inputControllersList.add(Globals.W0_CONTROLLER, w0Controller);
        inputControllersList.add(Globals.W1_CONTROLLER, w1Controller);
        inputControllersList.add(Globals.W2_CONTROLLER, w2Controller);

        // Create an iterator to pass the collections around the application.
        this.setInputControllers(inputControllersList.iterator());
    }

    @Override
    public void initInputModels()
    {
        // Get a new instance of the input models
        targetModel = new TargetModel();
        thetaModel = new ThetaModel();
        w0Model = new W0Model();
        w1Model = new W1Model();
        w2Model = new W2Model();

        // register with the input model observers with the view
        ((TargetModel) targetModel).registerObserver(view);
        ((ThetaModel) thetaModel).registerObserver(view);
        ((W0Model) w0Model).registerObserver(view);
        ((W1Model) w1Model).registerObserver(view);
        ((W2Model) w2Model).registerObserver(view);

        // Add the input models to the collection
        // Use globals to associate the object with the correct index
        inputModelsList.add(Globals.TARGET_MODEL, targetModel);
        inputModelsList.add(Globals.THETA_MODEL, thetaModel);
        inputModelsList.add(Globals.W0_MODEL, w0Model);
        inputModelsList.add(Globals.W1_MODEL, w1Model);
        inputModelsList.add(Globals.W2_MODEL, w2Model);

        // Create an iterator so the collection can be passed around the appliction
        this.setInputModels(inputModelsList.iterator());
    }

    @Override
    public void initInputState()
    {
        // Initialize input state modules by passing them a View
        targetState = new TargetState(view);
        thetaState = new ThetaState(view);
        w0State = new W0State(view);
        w1State = new W1State(view);
        w2State = new W2State(view);

        // Add the input state modules to the collection
        // Use Globals to associate the right object with the right index
        inputStatesList.add(Globals.TARGET_STATE, targetState);
        inputStatesList.add(Globals.THETA_STATE, thetaState);
        inputStatesList.add(Globals.W0_STATE, w0State);
        inputStatesList.add(Globals.W1_STATE, w1State);
        inputStatesList.add(Globals.W2_STATE, w2State);

        // Create an iterator so the collection can be passed around the application
        this.setInputStates(inputStatesList.iterator());
    }

    @Override
    public void initOutputController()
    {
        // Initialize a new output module by passing it a View.
        resultsController = new OutputController(algorithms, outputStatesList);
        outputControllersList.add(Globals.RESULTS_CONTROLLER, resultsController);
        // Create an iterator so the collection can be passed around the application
        this.setOutputControllers(outputControllersList.iterator());
    }

    @Override
    public void initOutputState()
    {
        // Initialize a new output state module by passing it a View.
        resultsState = new ResultsState(networkPanel);
        outputStatesList.add(Globals.RESULTS_STATE, resultsState);
        // Create an iterator so the collection can be passed around the application
        this.setOutputStates(outputStatesList.iterator());
    }

    @Override
    public void initView()
    {
        // Initize a new View by passing it this controller.
        this.networkPanel = new MavnNetworkView(this);
        this.view = new ModelView(this);
    }

    @Override
    public void setInputControllersList(ArrayList<InputControllerInterface> inputControllersList)// </editor-fold>
    {
        this.inputControllersList = inputControllersList;
    }
}
