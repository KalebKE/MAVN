/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.view.input;

import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.simModel.input.model.observer.W0ModelObserver;
import spreadsheetTable.SimTable;

/**
 *
 * @author Kaleb
 */
public class ModelInputW0Panel extends ModelInputPanelAbstract implements W0ModelObserver
{
    public ModelInputW0Panel(ModelInputFileInterface inputController, ModelInputInterface inputModel, ModelChangeEvent modelChanged)
    {
        super(inputController, inputModel, modelChanged);
        this.inputPanelHeaderLabel.setText("Weight Matrix: W0");
        this.inputPanelDescriptionLabel.setText("W0 Matrix: Defines ORing Node Connections");
    }

    @Override
    public void updateW0ModelInput(double[][] modelInput)
    {
       ((SimTable) this.inputPane).setModel(modelInput);
       this.firePropertyChange("propertiesChanged", !inputModel.isModelInputReady(), inputModel.isModelInputReady());
    }

}
