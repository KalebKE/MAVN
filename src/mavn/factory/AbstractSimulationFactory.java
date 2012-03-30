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

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import mavn.simModel.algorithm.model.point.generator.PointGeneratorInterface;
import mavn.simModel.algorithm.properties.view.SimulationPropertiesFrame;
import mavn.simModel.algorithm.properties.view.state.PointGeneratorStateInterface;
import mavn.simModel.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.simModel.input.view.changeEvent.InputModelChangeEvent;
import mavn.simModel.input.view.layoutPanel.InputViewGridLayoutPanel;
import mavn.simModel.network.mediator.NetworkMediatorInterface;
import mavn.simModel.output.view.layoutPanel.ModelOutputDefaultLayoutView;
import mavn.simModel.output.view.state.OutputViewStateInterface;
import mavn.simModel.plot.model.PointHitOutputModelInterface;
import mavn.simModel.plot.model.PointMissOutputModelInterface;
import mavn.simModel.plot.model.PointOutputModelInterface;
import mavn.simModel.plot.model.TimerOutputModelInterface;
import mavn.view.SimControlView;
import simulyn.algorithm.model.AlgorithmModelInterface;
import simulyn.input.controller.InputControllerInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.input.view.state.InputViewStateInterface;
import simulyn.output.mediator.OutputMediatorInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;
import simulyn.ui.components.inputModelPanel.InputViewAbstract;

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

     // Model Input Actions
    protected ActionListener targetPanelAction;
    protected ActionListener thetaPanelAction;
    protected ActionListener w0PanelAction;
    protected ActionListener w1PanelAction;
    protected ActionListener w2PanelAction;

    // Control Bar Actions
    protected ActionListener simControlAction;
    protected ActionListener simulationBarAction;
    protected ActionListener propertiesBarAction;
    protected ActionListener modelOuputBarAction;
    protected ActionListener viewBarAction;
    protected ActionListener newtorkViewBarAction;
    protected ActionListener plotViewBarAction;
    protected ActionListener runSimulationAction;
    protected ActionListener outputControllViewAction;

    protected ArrayList<InputModelInterface> inputModels;
    protected ArrayList<InputViewAbstract> inputViews;

    protected AlgorithmModelInterface singlePointSimulation;
    protected AlgorithmModelInterface uniformPointSimulation;
    protected AlgorithmModelInterface gridPointSimulation;

    protected ItemListener outputMediatorEvent;

    // Model Input Controllers
    protected InputControllerInterface targetController;
    protected InputControllerInterface thetaController;
    protected InputControllerInterface w0Controller;
    protected InputControllerInterface w1Controller;
    protected InputControllerInterface w2Controller;

    // Model Input Models
    protected InputModelInterface w2Model;
    protected InputModelInterface w1Model;
    protected InputModelInterface w0Model;
    protected InputModelInterface thetaModel;
    protected InputModelInterface targetModel;

    // Model Input Change Event
    protected InputModelChangeEvent modelChanged;

    // Model Input Views
    protected InputViewGridLayoutPanel inputView;
    protected InputViewAbstract targetPanel;
    protected InputViewAbstract thetaPanel;
    protected InputViewAbstract w0Panel;
    protected InputViewAbstract w1Panel;
    protected InputViewAbstract w2Panel;
   
    // Model Input States
    protected InputViewStateInterface w0State;
    protected InputViewStateInterface w1State;
    protected InputViewStateInterface w2State;
    protected InputViewStateInterface targetState;
    protected InputViewStateInterface thetaState;
    
    protected JPanel ouputControlBar;
    protected JPanel inputControlBar;

    protected ModelOutputDefaultLayoutView outputLayoutPanel;

    protected NetworkMediatorInterface networkRendererMediator;

    protected OutputMediatorInterface controlBarMediator;

    // Model Result Models
    protected OutputModelInterface andLayerModelResult;
    protected OutputModelInterface orLayerModelResult;
    protected OutputModelInterface outputLayerModelResult;
    protected OutputModelInterface simulationModelResult;
    protected OutputModelInterface shapesRatioOutputModel;
    protected OutputModelInterface imageRatioOutputModel;

    protected OutputViewMediatorInterface outputMediator;
    protected OutputViewMediatorInterface networkMediator;
    protected OutputViewMediatorInterface plotMediator;

    // Model Result State
    protected OutputViewStateInterface outputViewState;

    // Model Algorithm Dart Gun's
    protected PointGeneratorInterface cmwcPointGenerator;
    protected PointGeneratorInterface caPointGenerator;
    protected PointGeneratorInterface javaPointGenerator;
    protected PointGeneratorInterface mtPointGenerator;
    protected PointGeneratorInterface xOrPointGenerator;

    protected PointGeneratorStateInterface pointGeneratorState;

    // Model Algorithm Dart Gun Result
    protected PointOutputModelInterface pointOutputModel;
    protected PointHitOutputModelInterface pointHitOutputModel;
    protected PointMissOutputModelInterface pointMissOutputModel;

       // Model Views
    protected SimControlView view;
    protected SimulationPropertiesStateInterface propertiesState;
    protected SimulationPropertiesFrame propertiesFrame;

    protected TimerOutputModelInterface timerOutputModel;

    public SimulationPropertiesFrame getPropertiesFrame()
    {
        return propertiesFrame;
    }

    public SimulationPropertiesStateInterface getPropertiesState()
    {
        return propertiesState;
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

    public ModelOutputDefaultLayoutView getNetworkPanel()
    {
        return outputLayoutPanel;
    }

    public InputViewGridLayoutPanel getModelPanel()
    {
        return inputView;
    }
}
