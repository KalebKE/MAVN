/*
InputViewTargetModel -- an class within the Machine Artificial Vision
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
package mavn.input.view;

import java.awt.event.ActionListener;
import mavn.input.model.observer.TargetInputModelObserver;
import mavn.input.view.changeEvent.InputModelChangeEvent;
import simulyn.input.controller.InputControllerInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.input.view.state.InputViewStateInterface;
import simulyn.ui.components.inputModel.InputViewAbstract;
import simulyn.ui.components.spreadsheetTable.SimTable;

/**
 * InputViewTargetModel is the View for the Target Input Model within MAVN.
 * The Target Input Model defines the single test Target Point for the simulations
 * input models.
 * InputViewTargetModel Observes the TargetInputModel Subject through the
 * TargetAlgorithmModelObserver interface. When the TargetInputModel receives new State,
 * it notifies its Observers of the change. This updates the State within
 * the InputViewTargetModel and its Rendering View, which is responsible for
 * displaying the State itself. 
 * InputViewAbstract implementations are intended to manage the Input Model Views
 * within the Simulyn Framework. They are the View in a Model-View-Controller
 * architecture that manages the Input Models. Within Simulyn, Input Model's
 * are the simulation's model's inputs. Some simulations require many inputs
 * for their model's, some require just one input. Each InputViewAbstract
 * implementation instance is intended to manage one simulation model input.
 * @author Kaleb
 */
public class InputViewTargetModel extends InputViewAbstract implements TargetInputModelObserver
{

    /**
     * Initialize the InputTargetModel.
     * @param action the Action Listener responsible for managing the Actions
     * for the InputViewAbstract implementation.
     * @param inputModel the Input Model Interface that will back the
     * InputViewAbstract implementation.
     * @param inputViewState the state of the Input View implementation.
     * @param inputModelChanged the Model Change Event Listener for the Input
     * Models.
     */
    public InputViewTargetModel(ActionListener action, InputControllerInterface inputController,
            InputModelInterface inputModel,
            InputViewStateInterface inputViewState,
            InputModelChangeEvent inputModelChanged)
    {
        super(action, inputController, inputModel, inputViewState, inputModelChanged);
        this.inputPanelHeaderLabel.setText("Target Matrix: Target");
        this.inputPanelDescriptionLabel.setText("Current Targets: Defines Node Inputs");
    }

    /**
     * Hook for the TargetInputModel Subject.
     * @param modelInput the new model. 
     */
    @Override
    public void updateTargetInputModel(double[][] modelInput)
    {
        if (modelInput.length == 0)
        {
            ((SimTable) this.inputPane).clearModel();
            this.inputViewState.inputModelReady(false);
        } else
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
}
