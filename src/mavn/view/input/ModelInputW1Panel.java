/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.view.input;

import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.simModel.input.model.observer.W1ModelObserver;
import spreadsheetTable.SimTable;

/**
 *
 * @author Kaleb
 */
public class ModelInputW1Panel extends ModelInputPanelAbstract implements W1ModelObserver
{

    public ModelInputW1Panel(ModelInputFileInterface inputController, ModelInputInterface inputModel, ModelChangeEvent modelChanged)
    {
        super(inputController, inputModel, modelChanged);
        this.inputPanelHeaderLabel.setText("Weight Matrix: W1");
        this.inputPanelDescriptionLabel.setText("W1 Matrix: Defines ANDing Node Connections");
    }

    @Override
    public void updateW1ModelInput(double[][] modelInput)
    {
        ((SimTable) this.inputPane).setModel(modelInput);
        this.firePropertyChange("propertiesChanged", !inputModel.isModelInputReady(), inputModel.isModelInputReady());
    }
}
