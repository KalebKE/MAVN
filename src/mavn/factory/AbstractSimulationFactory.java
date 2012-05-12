/*
AbstractSimulationFactory-- A abstract class within the Machine Artificial
Vision Network(Machine Artificial Vision Network).
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
import mavn.algorithm.model.point.generator.PointGeneratorInterface;
import mavn.algorithm.properties.view.SimulationPropertiesFrame;
import mavn.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.input.view.changeEvent.InputModelChangeEvent;
import mavn.input.view.layout.InputViewGridLayoutPanel;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.network.mediator.state.NetworkMediatorViewStateInterface;
import mavn.output.view.layout.ModelOutputDefaultLayoutView;
import mavn.plot.mediator.state.PlotMediatorViewStateInterface;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;
import mavn.plot.model.PointHitOutputModelInterface;
import mavn.plot.model.PointMissOutputModelInterface;
import mavn.plot.model.PointOutputModelInterface;
import mavn.plot.model.TimerOutputModelInterface;
import mavn.simulation.view.SimControlView;
import mavn.simulation.view.state.output.SimulationViewOutputStateInterface;
import mavn.simulation.view.state.simulator.SimulationTypeViewStateInterface;
import simulyn.algorithm.model.AlgorithmModelInterface;
import simulyn.input.controller.InputControllerInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.input.view.state.InputViewStateInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;
import simulyn.simulation.mediator.SimulationMediatorInterface;
import simulyn.ui.components.inputModel.InputViewAbstract;


/**
 * MavnControllerAbstract is a special implementation of the
 * SimulationFactoryInterface.
 * The abstraction is designed to allow the MAVN application to flexibly manage 
 * the Input, Algorithm and Output Modules within the MVC architecture. These
 * Modules are usually concerned with controlling other MVC's that support
 * MAVN, managing input and output, managing state and managing the applications
 * algorithms. 
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

    protected AlgorithmModelInterface diagnosticSimulation;
    protected AlgorithmModelInterface monteCarloSimulation;
    protected AlgorithmModelInterface pixelGridSimulation;

    protected ItemListener outputMediatorEvent;

    // Model Input Controllers
    protected InputControllerInterface targetController;
    protected InputControllerInterface thetaController;
    protected InputControllerInterface w0Controller;
    protected InputControllerInterface w1Controller;
    protected InputControllerInterface w2Controller;

    // Model Input Models
    protected InputModelInterface w2InputModel;
    protected InputModelInterface w1InputModel;
    protected InputModelInterface w0InputModel;
    protected InputModelInterface thetaInputModel;
    protected InputModelInterface targetInputModel;

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
    
    protected JPanel outputControlBar;
    protected JPanel inputControlBar;

    protected ModelOutputDefaultLayoutView outputLayoutPanel;

    protected NetworkMediatorInterface networkRendererMediator;
    protected NetworkMediatorViewStateInterface networkViewState;

    protected SimulationMediatorInterface simulationMediator;

    // Model Result Models
    protected OutputModelInterface andLayerModelResult;
    protected OutputModelInterface orLayerModelResult;
    protected OutputModelInterface outputLayerModelResult;
    protected OutputModelInterface plotOutputModel;
    protected OutputModelInterface shapesRatioOutputModel;
    protected OutputModelInterface imageRatioOutputModel;

    protected OutputViewMediatorInterface ssMediator;
    protected OutputViewMediatorInterface networkMediator;
    protected OutputViewMediatorInterface plotMediator;

    protected PlotMediatorViewStateInterface plotMediatorViewState;

    // Model Algorithm Dart Gun's
    protected PointGeneratorInterface cmwcPointGenerator;
    protected PointGeneratorInterface caPointGenerator;
    protected PointGeneratorInterface javaPointGenerator;
    protected PointGeneratorInterface mtPointGenerator;
    protected PointGeneratorInterface xOrPointGenerator;

    // Model Algorithm Dart Gun Result
    protected PointOutputModelInterface pointOutputModel;
    protected PointHitOutputModelInterface pointHitOutputModel;
    protected PointMissOutputModelInterface pointMissOutputModel;

       // Model Views
    protected SimControlView view;
    protected SimulationPropertiesStateInterface simulationPropertiesState;
    protected SimulationPropertiesFrame simulationPropertiesFrame;

    protected SimulationTypeViewStateInterface simulationTypeViewState;

    // Model Result State
    protected SimulationViewInputStateInterface simulationInputState;

    protected SimulationViewOutputStateInterface ssMediatorViewState;

    protected TimerOutputModelInterface timerOutputModel;
}
