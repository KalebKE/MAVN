/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.view.input;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import mavn.factory.AbstractSimulationFactory;
import mavn.globals.Globals;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.util.math.Transpose;

/**
 *
 * @author Kaleb
 */
public class ModelChangeEvent implements PropertyChangeListener
{

    private AbstractSimulationFactory controller;

    public ModelChangeEvent(AbstractSimulationFactory controller)
    {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("propertiesChanged"))
        {
            Iterator models = controller.getModelInputModels().iterator();

            boolean modelsLoaded = true;

            while (models.hasNext())
            {
                if (!((ModelInputInterface) models.next()).isModelInputReady())
                {
                    modelsLoaded = false;
                }
            }

            if (modelsLoaded)
            {
                controller.getModelResultStates().get(Globals.RESULTS_STATE).simulationLoaded();
                controller.getNetworkPanel().setNetwork(Transpose.tranposeMatrix(controller.getModelInputModels().get(Globals.W2_CONTROLLER).getModelInput()), controller.getModelInputModels().get(Globals.W1_CONTROLLER).getModelInput(), Transpose.tranposeMatrix(controller.getModelInputModels().get(Globals.W0_CONTROLLER).getModelInput()));
                controller.getView().setNetworkView();
            }
        }
    }
}
