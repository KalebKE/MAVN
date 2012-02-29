/*
MavnControllerAbstract-- an abstract class within the Machine Artificial Vision Network(Machine Artificial Vision Network).
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
import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.simModel.result.controller.ModelResultControllerInterface;
import mavn.dartGun.DartGunInterface;
import mavn.simModel.algorithm.model.AlgorithmModelInterface;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.simModel.input.view.state.InputStateInterface;
import mavn.simModel.properties.state.PropertiesStateInterface;
import mavn.simModel.properties.view.PropertiesFrame;
import mavn.simModel.result.model.DartResultInterface;
import mavn.simModel.result.model.ModelResultInterface;
import mavn.simModel.result.view.state.OutputStateInterface;
import mavn.view.input.ModelChangeEvent;
import mavn.view.input.ModelInputControlPanel;
import mavn.view.input.ModelInputPanelAbstract;
import mavn.view.input.SimControlView;
import mavn.view.result.NetworkViewAbstract;

/**
 * MavnControllerAbstract is a special implementation of the MavnControllerInterface.
 * The abstraction is designed to allow the MAVN application to flexibly manage the
 * Input and Output Modules within the MVC architecture. These Modules are usually
 * concerned with controlling other MVC's that support MAVN, managing input and output, managing state and
 * managing the applications algorithms. Any class that needs access
 * to the Modules can do so with an implementation of MavnControllerAbstract.
 * @author Kaleb
 */
public abstract class AbstractSimulationFactory implements SimulationFactoryInterface
{
    // Model Input Collections

    protected ArrayList<ModelInputFileInterface> modelInputControllersList;
    protected ArrayList<OpenFileObserver> modelInputFileControllersList;
    protected ArrayList<ModelInputInterface> modelInputModelsList;
    protected ArrayList<InputStateInterface> modelInputStatesList;
    // Model Result Collections
    protected ArrayList<ModelResultControllerInterface> modelResultControllersList;
    protected ArrayList<ModelResultInterface> modelResultModelsList;
    protected ArrayList<OutputStateInterface> modelResultStatesList;
    // Model Algorithm Collections
    protected ArrayList<AlgorithmModelInterface> modelAlgorithmsModelsList;
    protected ArrayList<DartGunInterface> modelDartGunModelsList;
    protected ArrayList<DartResultInterface> modelDartResultModelsList;
    // Model Input View Collections
    protected ArrayList<ModelInputPanelAbstract> modelInputPanelsList;
    // Model Input Controllers
    protected ModelInputFileInterface targetController;
    protected ModelInputFileInterface thetaController;
    protected ModelInputFileInterface w0Controller;
    protected ModelInputFileInterface w1Controller;
    protected ModelInputFileInterface w2Controller;
    // Model Input Models
    protected ModelInputInterface w2Model;
    protected ModelInputInterface w1Model;
    protected ModelInputInterface w0Model;
    protected ModelInputInterface thetaModel;
    protected ModelInputInterface targetModel;
    // Model Input States
    protected InputStateInterface w0State;
    protected InputStateInterface w1State;
    protected InputStateInterface w2State;
    protected InputStateInterface targetState;
    protected InputStateInterface thetaState;
    // Model Result Controlller
    protected ModelResultControllerInterface resultsController;
    // Model Result Models
    protected ModelResultInterface andLayerModelResult;
    protected ModelResultInterface orLayerModelResult;
    protected ModelResultInterface outputLayerModelResult;
    protected ModelResultInterface simulationResult;
    protected ModelResultInterface shapesRatioResult;
    protected ModelResultInterface imageRatioResult;
    // Model Result State
    protected OutputStateInterface resultsState;
    // Model Algorithm Model
    protected AlgorithmModelInterface singlePoint;
    protected AlgorithmModelInterface cmwc;
    protected AlgorithmModelInterface ca;
    protected AlgorithmModelInterface mt;
    protected AlgorithmModelInterface random;
    protected AlgorithmModelInterface xor;
    // Model Algorithm Dart Gun's
    protected DartGunInterface cmwcDartGun;
    protected DartGunInterface caDartGun;
    protected DartGunInterface javaDartGun;
    protected DartGunInterface mtDartGun;
    protected DartGunInterface xOrDartGun;
    // Model Algorithm Dart Gun Result
    protected DartResultInterface dartResult;
    // Model Views
    protected SimControlView view;
    protected NetworkViewAbstract networkPanel;
    protected ModelInputControlPanel modelPanel;
    protected ModelInputPanelAbstract targetPanel;
    protected ModelInputPanelAbstract thetaPanel;
    protected ModelInputPanelAbstract w0Panel;
    protected ModelInputPanelAbstract w1Panel;
    protected ModelInputPanelAbstract w2Panel;
    protected ModelChangeEvent modelChanged;

    protected PropertiesStateInterface propertiesState;
    protected PropertiesFrame propertiesFrame;

    public ArrayList<AlgorithmModelInterface> getModelAlgorithmsModelsList()
    {
        return modelAlgorithmsModelsList;
    }

    public ArrayList<DartResultInterface> getModelDartResultModelsList()
    {
        return modelDartResultModelsList;
    }

    /**
     * Return the Input Controller Module collection. Input Controller Modules
     * implement InputControllerInterface and are responsible for controlling
     * Input Modules. Implementations concern themselves with importing external
     * data into the application and then allowing other classes (Views, Models and other Controllers)
     * to work with that data locally.
     * @return A collection of InputerControllerInterface implementations currently
     * being used in the application.
     */
    public ArrayList<ModelInputFileInterface> getModelInputControllers()
    {
        return modelInputControllersList;
    }

    /**
     * Return the Input Model Module collection. Input Model Modules implement
     * InputModelInterface and are responsible for implementing the Models
     * that import the data into the application. They generally use an Observer
     * pattern to update any observers of changes to the model.Many objects can
     * observe specific changes to the Input Model. This allows for
     * fine grain control of the applications Input Model.
     * @return A collection of InputModelInterface implementations currenlty
     * being used by the application.
     */
    public ArrayList<ModelInputInterface> getModelInputModels()
    {
        return modelInputModelsList;
    }

    public ArrayList<OpenFileObserver> getModelInputFileControllersList()
    {
        return modelInputFileControllersList;
    }

    /**
     * Return the Input State Module collection. InputStateModules usually
     * implement InputStateInterface. Implementations are concerend with
     * managing the Input State of the Views. These implementations are key to
     * managing the user experience and reducing the complexity of the application.
     * @return A collection of InputStateInterface implementations currently
     * being used by the application.
     */
    public ArrayList<InputStateInterface> getModelInputStates()
    {
        return modelInputStatesList;
    }

    public PropertiesFrame getPropertiesFrame()
    {
        return propertiesFrame;
    }

    public PropertiesStateInterface getPropertiesState()
    {
        return propertiesState;
    }

    public ArrayList<ModelResultInterface> getModelResultModelsList()
    {
        return modelResultModelsList;
    }

    /**
     * Return the Output Controller Module collection.
     * @return A collection of OutputControllerInterface implementations currently
     * being used by the application.
     */
    public ArrayList<ModelResultControllerInterface> getModelResultControllers()
    {
        return modelResultControllersList;
    }

    /**
     * Set the Input Controller Module collection.
     * @param inputControllers A collection of InputerControllerInterface implementations currently
     * being used by the application.
     */
    public void setModelInputControllers(ArrayList<ModelInputFileInterface> inputControllers)
    {
        this.modelInputControllersList = inputControllers;
    }

    /**
     * Set the Input Model Modules collection.
     * @param inputModels A collection of InputModelInterface implementations currenlty
     * being used by the application.
     */
    public void setModelInputModels(ArrayList<ModelInputInterface> inputModels)
    {
        this.modelInputModelsList = inputModels;
    }

    /**
     * Set the Input State Module collection.
     * @param inputStates A collection of InputStateInterface implementations currently
     * being used by the application.
     */
    public void setModelInputStates(ArrayList<InputStateInterface> inputStates)
    {
        this.modelInputStatesList = inputStates;
    }

    /**
     * Set the Output Controller Module collection.
     * @param outputControllers A collection of OutputControllerInterface implementations currently
     * being used by the application.
     */
    public void setModelResultControllers(ArrayList<ModelResultControllerInterface> modelResultControllers)
    {
        this.modelResultControllersList = modelResultControllers;
    }

    /**
     * Get the Output State Module collection.
     * @return A collection of OutputStateInterace implementations currently
     * being used by the application.
     */
    public ArrayList<OutputStateInterface> getModelResultStates()
    {
        return modelResultStatesList;
    }

    /**
     * Get the Output State Module collection.
     * @param outputStates A collection of OutputStateInterace implementations currently
     * being used by the application.
     */
    public void setModelResultStates(ArrayList<OutputStateInterface> outputStates)
    {
        this.modelResultStatesList = outputStates;
    }

    /**
     * Return the View.
     * @return the View being used by the application.
     */
    public SimControlView getView()
    {
        return view;
    }

    /**
     * Set the View.
     * @param view the View being used by the application.
     */
    public void setView(SimControlView view)
    {
        this.view = view;
    }

    public NetworkViewAbstract getNetworkPanel()
    {
        return networkPanel;
    }

    public ModelInputControlPanel getModelPanel()
    {
        return modelPanel;
    }
}
