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

import java.util.ArrayList;
import java.util.Iterator;
import mavn.controller.InputControllerInterface;
import mavn.controller.OutputControllerInterface;
import mavn.dartGun.DartGunInterface;
import mavn.math.model.AlgorithmModelInterface;
import mavn.model.InputModelInterface;
import mavn.state.InputStateInterface;
import mavn.state.OutputStateInterface;
import mavn.view.ModelView;
import mavn.view.NetworkViewAbstract;

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

    protected Iterator<InputModelInterface> inputModels;
    protected Iterator<InputControllerInterface> inputControllers;
    protected Iterator<InputStateInterface> inputStates;
    protected Iterator<OutputControllerInterface> outputControllers;
    protected Iterator<OutputStateInterface> outputStates;
    protected ModelView view;
    protected NetworkViewAbstract networkPanel;
    // Collections
    protected ArrayList<InputControllerInterface> inputControllersList;
    protected ArrayList<InputModelInterface> inputModelsList;
    protected ArrayList<InputStateInterface> inputStatesList;
    protected ArrayList<OutputControllerInterface> outputControllersList;
    protected ArrayList<OutputStateInterface> outputStatesList;
    protected ArrayList<DartGunInterface> dartGuns;
    protected ArrayList<AlgorithmModelInterface> algorithms;
    // Input Controllers
    protected InputControllerInterface w2Controller;
    protected InputControllerInterface w1Controller;
    protected InputControllerInterface w0Controller;
    protected InputControllerInterface thetaController;
    protected InputControllerInterface targetController;
    // Input Models
    protected InputModelInterface w2Model;
    protected InputModelInterface w1Model;
    protected InputModelInterface w0Model;
    protected InputModelInterface thetaModel;
    protected InputModelInterface targetModel;
    // Input States
    protected InputStateInterface w0State;
    protected InputStateInterface w1State;
    protected InputStateInterface w2State;
    protected InputStateInterface targetState;
    protected InputStateInterface thetaState;
    // Output Controlller
    protected OutputControllerInterface resultsController;
    // Ouput State
    protected OutputStateInterface resultsState;
    protected DartGunInterface cmwcDartGun;
    protected DartGunInterface caDartGun;
    protected DartGunInterface javaDartGun;
    protected DartGunInterface mtDartGun;
    protected DartGunInterface xOrDartGun;
    protected AlgorithmModelInterface singlePoint;
    protected AlgorithmModelInterface cmwc;
    protected AlgorithmModelInterface ca;
    protected AlgorithmModelInterface mt;
    protected AlgorithmModelInterface random;
    protected AlgorithmModelInterface xor;

    /**
     * Return the Input Controller Module collection. Input Controller Modules
     * implement InputControllerInterface and are responsible for controlling
     * Input Modules. Implementations concern themselves with importing external
     * data into the application and then allowing other classes (Views, Models and other Controllers)
     * to work with that data locally.
     * @return A collection of InputerControllerInterface implementations currently
     * being used in the application.
     */
    public Iterator<InputControllerInterface> getInputControllers()
    {
        return inputControllers;
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
    public Iterator<InputModelInterface> getInputModels()
    {
        return inputModels;
    }

    /**
     * Return the Input State Module collection. InputStateModules usually
     * implement InputStateInterface. Implementations are concerend with
     * managing the Input State of the Views. These implementations are key to
     * managing the user experience and reducing the complexity of the application.
     * @return A collection of InputStateInterface implementations currently
     * being used by the application.
     */
    public Iterator<InputStateInterface> getInputStates()
    {
        return inputStates;
    }

    /**
     * Return the Output Controller Module collection.
     * @return A collection of OutputControllerInterface implementations currently
     * being used by the application.
     */
    public Iterator<OutputControllerInterface> getOutputControllers()
    {
        return outputControllers;
    }

    /**
     * Set the Input Controller Module collection.
     * @param inputControllers A collection of InputerControllerInterface implementations currently
     * being used by the application.
     */
    public void setInputControllers(Iterator<InputControllerInterface> inputControllers)
    {
        this.inputControllers = inputControllers;
    }

    /**
     * Set the Input Model Modules collection.
     * @param inputModels A collection of InputModelInterface implementations currenlty
     * being used by the application.
     */
    public void setInputModels(Iterator<InputModelInterface> inputModels)
    {
        this.inputModels = inputModels;
    }

    /**
     * Set the Input State Module collection.
     * @param inputStates A collection of InputStateInterface implementations currently
     * being used by the application.
     */
    public void setInputStates(Iterator<InputStateInterface> inputStates)
    {
        this.inputStates = inputStates;
    }

    /**
     * Set the Output Controller Module collection.
     * @param outputControllers A collection of OutputControllerInterface implementations currently
     * being used by the application.
     */
    public void setOutputControllers(Iterator<OutputControllerInterface> outputControllers)
    {
        this.outputControllers = outputControllers;
    }

    /**
     * Get the Output State Module collection.
     * @return A collection of OutputStateInterace implementations currently
     * being used by the application.
     */
    public Iterator<OutputStateInterface> getOutputStates()
    {
        return outputStates;
    }

    /**
     * Get the Output State Module collection.
     * @param outputStates A collection of OutputStateInterace implementations currently
     * being used by the application.
     */
    public void setOutputStates(Iterator<OutputStateInterface> outputStates)
    {
        this.outputStates = outputStates;
    }

    /**
     * Return the View.
     * @return the View being used by the application.
     */
    public ModelView getView()
    {
        return view;
    }

    /**
     * Set the View.
     * @param view the View being used by the application.
     */
    public void setView(ModelView view)
    {
        this.view = view;
    }

    public NetworkViewAbstract getNetworkPanel()
    {
        return networkPanel;
    }
}
