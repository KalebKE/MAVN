/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.input.view.changeEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import mavn.globals.Globals;
import mavn.simModel.network.mediator.NetworkRendererInterface;
import mavn.simModel.output.view.state.OutputModelStateInterface;
import mavn.util.math.Transpose;
import mavn.view.SimControlView;
import simulyn.input.model.InputModelInterface;

/**
 *
 * @author Kaleb
 */
public class InputModelChangeEvent implements PropertyChangeListener
{

    private ArrayList<InputModelInterface> models;
    private NetworkRendererInterface mediator;
    private OutputModelStateInterface modelResultState;
    private SimControlView view;

    public InputModelChangeEvent(ArrayList<InputModelInterface> models,
            NetworkRendererInterface mediator,
            OutputModelStateInterface modelResultState,
            SimControlView view)
    {
        this.models = models;
        this.mediator = mediator;
        this.modelResultState = modelResultState;
        this.view = view;
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
                modelResultState.simulationLoaded();
                mediator.setNetwork(Transpose.tranposeMatrix(models.get(Globals.W2_CONTROLLER).getModelInput()), models.get(Globals.W1_CONTROLLER).getModelInput(), Transpose.tranposeMatrix(models.get(Globals.W0_CONTROLLER).getModelInput()));
                view.setOutputView();
            }
        }
    }
}
