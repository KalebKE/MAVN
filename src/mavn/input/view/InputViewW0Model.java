/*
InputViewW0Model -- an class within the Machine Artificial Vision
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
import mavn.input.view.changeEvent.InputModelChangeEvent;
import mavn.input.model.observer.W0InputModelObserver;
import simulyn.input.controller.InputControllerInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.input.view.state.InputViewStateInterface;
import simulyn.ui.components.inputModel.InputViewAbstract;
import simulyn.ui.components.spreadsheetTable.SimTable;

/**
 * InputViewW0Model is the View for the W0 Input Model within MAVN.
 * The W0 Input Model defines the W0 (transfer edges) for the Input Layer.
 * InputViewTargetModel Observes the W0InputModel Subject through the
 * W0ModelObserver interface. When the W0InputModel receives new State,
 * it notifies it's Observers of the change. This updates the State within
 * the InputViewW0Model and its Rendering View, which is responsible for
 * displaying the State itself.
 * InputViewAbstract implementations are intended to manage the Input Model Views
 * within the Simulyn Framework. They are the View in a Model-View-Controller
 * architecture that manages the Input Models. Within Simulyn, Input Model's
 * are the simulation's model's inputs. Some simulations require many inputs
 * for their model's, some require just one input. Each InputViewAbstract
 * implementation instance is intended to manage one simulation model input.
 * @author Kaleb
 */
public class InputViewW0Model extends InputViewAbstract implements W0InputModelObserver
{

    /**
     * Initialize the InputViewW0Model.
     * @param action the Action Listener responsible for managing the Actions
     * for the InputViewAbstract implementation.
     * @param inputModel the Input Model Interface that will back the
     * InputViewAbstract implementation.
     * @param inputViewState the state of the Input View implementation.
     * @param inputModelChanged the Model Change Event Listener for the Input
     * Models.
     */
    public InputViewW0Model(ActionListener action, InputControllerInterface inputController,
            InputModelInterface inputModel,
            InputViewStateInterface inputViewState,
            InputModelChangeEvent modelChanged)
    {
        super(action, inputController, inputModel, inputViewState, modelChanged);
        this.inputPanelHeaderLabel.setText("Weight Matrix: W0");
        this.inputPanelDescriptionLabel.setText("W0 Matrix: Defines ORing Node Connections");
    }

    /**
     * Hook for the W0InputModel Subject.
     * @param modelInput the new model.
     */
    @Override
    public void updateW0InputModel(double[][] modelInput)
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
