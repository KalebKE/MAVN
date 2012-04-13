/*
InputModelChangeEvent -- a class class within the Machine Artificial
Vision Network(Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher.

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
package mavn.input.view.changeEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import mavn.globals.Globals;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;
import mavn.util.math.Transpose;
import mavn.simulation.view.SimControlView;
import simulyn.input.model.InputModelInterface;

/**
 * Input Model Change Event keeps tracks of what Input Models have been loaded
 * and what Input Models still need to be loaded. It manages Model State
 * appropriatly. 
 * @author Kaleb
 */
public class InputModelChangeEvent implements PropertyChangeListener
{

    private ArrayList<InputModelInterface> models;
    private NetworkMediatorInterface mediator;
    private SimulationViewInputStateInterface simulationViewInputState;
    private SimControlView view;

    /**
     * Initialize a new InputModelChangeEvent.
     * @param models the Input Models that will be kept track of.
     * @param mediator the Network Mediator that will have network State
     * pushed to it when State is available.
     * @param modelResultState the Simulation View's Input State manager.
     */
    public InputModelChangeEvent(ArrayList<InputModelInterface> models,
            NetworkMediatorInterface mediator,
            SimulationViewInputStateInterface modelResultState)
    {
        this.models = models;
        this.mediator = mediator;
        this.simulationViewInputState = modelResultState;
    }

    /**
     * Verifies the Input Model State. Called whenever the Input Model State
     * is changed.
     * @param evt the Property Event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        // Check if all of the Input Models have been loaded.
        if (evt.getPropertyName().equals("propertiesChanged"))
        {
            boolean modelsLoaded = true;

            Iterator iterate = models.iterator();

            while (iterate.hasNext())
            {
                // If an Input Model isn't ready, indicate.
                if (!((InputModelInterface) iterate.next()).isModelInputReady())
                {
                    modelsLoaded = false;
                }
            }

            // If the Input Model is ready, update the View State. Otherwise,
            // do nothing.
            if (modelsLoaded)
            {
                simulationViewInputState.onSimulationLoaded();
                mediator.setNetwork(Transpose.tranposeMatrix(models.get(Globals.W2_MODEL).getModelInput()), models.get(Globals.W1_MODEL).getModelInput(), Transpose.tranposeMatrix(models.get(Globals.W0_MODEL).getModelInput()));
                view.setOutputView();
            }
        }
    }

    /**
     * Set the Simulation View.
     * @param view the Simulation View.
     */
    public void setView(SimControlView view)
    {
        this.view = view;
    }
}
