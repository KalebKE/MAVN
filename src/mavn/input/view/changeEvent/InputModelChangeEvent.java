/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Kaleb
 */
public class InputModelChangeEvent implements PropertyChangeListener
{

    private ArrayList<InputModelInterface> models;
    private NetworkMediatorInterface mediator;
    private SimulationViewInputStateInterface modelResultState;
    private SimControlView view;

    public InputModelChangeEvent(ArrayList<InputModelInterface> models,
            NetworkMediatorInterface mediator,
            SimulationViewInputStateInterface modelResultState)
    {
        this.models = models;
        this.mediator = mediator;
        this.modelResultState = modelResultState;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("propertiesChanged"))
        {
            boolean modelsLoaded = true;

            Iterator iterate = models.iterator();

            while (iterate.hasNext())
            {
                if (!((InputModelInterface) iterate.next()).isModelInputReady())
                {
                    modelsLoaded = false;
                }
            }

            if (modelsLoaded)
            {
                modelResultState.onSimulationLoaded();
                mediator.setNetwork(Transpose.tranposeMatrix(models.get(Globals.W2_CONTROLLER).getModelInput()), models.get(Globals.W1_CONTROLLER).getModelInput(), Transpose.tranposeMatrix(models.get(Globals.W0_CONTROLLER).getModelInput()));
                view.setOutputView();
            }
        }
    }

    public void setView(SimControlView view)
    {
        this.view = view;
    }
    
}
