/*
InputViewW1Model -- an class within the Machine Artificial Vision
Network(Machine Artificial Vision Network)
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
package mavn.simModel.input.view;

import mavn.simModel.input.view.changeEvent.InputModelChangeEvent;
import mavn.simModel.input.model.observer.W1ModelObserver;
import simulyn.input.controller.InputControllerInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.ui.components.inputModelPanel.InputViewAbstract;
import simulyn.ui.components.spreadsheetTable.SimTable;


/**
 * InputViewW0Model is the View for the W10 Input Model within MAVN.
 * The W1 Input Model defines the W1 (transfer edges) for the Input Layer.
 * InputViewTargetModel Observes the W1InputModel Subject through the
 * W1ModelObserver interface. When the W1InputModel receives new State,
 * it notifies it's Observers of the change. This updates the State within
 * the InputViewW1Model and its Rendering View, which is responsible for
 * displaying the State itself.
 * InputViewAbstract implementations are intended to manage the Input Model Views
 * within the Simulyn Framework. They are the View in a Model-View-Controller
 * architecture that manages the Input Models. Within Simulyn, Input Model's
 * are the simulation's model's inputs. Some simulations require many inputs
 * for their model's, some require just one input. Each InputViewAbstract
 * implementation instance is intended to manage one simulation model input.
 * @author Kaleb
 */
public class InputViewW1Model extends InputViewAbstract implements W1ModelObserver
{

    /**
     * Initialize the InputViewAbtract.
     * @param inputController the Input Controller Interface you want to associate
     * with this View.
     * @param inputModel the Input Model Interface you want to associate with this View.
     * @param inputModelChanged the Input Model Manager for the application.
     */
    public InputViewW1Model(InputControllerInterface inputController, InputModelInterface inputModel, InputModelChangeEvent modelChanged)
    {
        super(inputController, inputModel, modelChanged);
        this.inputPanelHeaderLabel.setText("Weight Matrix: W1");
        this.inputPanelDescriptionLabel.setText("W1 Matrix: Defines ANDing Node Connections");
    }

    /**
     * Hook for the InputThetaModel Subject.
     * @param modelInput the new model.
     */
    @Override
    public void updateW1ModelInput(double[][] modelInput)
    {
        // Set the model for the Rendering View.
        ((SimTable) this.inputPane).setModel(modelInput);
        // Indicate that the Input View State should change to reflect
        // that an Input Model has been loaded.
        this.inputViewState.inputModelReady(true);
        // Inform the Input Model Change Event that an Input Model has been loaded.
        this.firePropertyChange("propertiesChanged", !inputModel.isModelInputReady(), inputModel.isModelInputReady());
    }
}
