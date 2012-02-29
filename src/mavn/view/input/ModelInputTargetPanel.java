/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.view.input;

import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.simModel.input.model.observer.TargetModelObserver;
import spreadsheetTable.SimTable;

/**
 *
 * @author Kaleb
 */
public class ModelInputTargetPanel extends ModelInputPanelAbstract implements TargetModelObserver
{

    public ModelInputTargetPanel(ModelInputFileInterface inputController, ModelInputInterface inputModel, ModelChangeEvent modelChanged)
    {
        super(inputController, inputModel, modelChanged);
        this.inputPanelHeaderLabel.setText("Target Matrix: Target");
        this.inputPanelDescriptionLabel.setText("Current Targets: Defines Node Inputs");
    }

    @Override
    public void updateTargetModelInput(double[][] modelInput)
    {
        ((SimTable) super.inputPane).setModel(modelInput);
        this.firePropertyChange("propertiesChanged", !inputModel.isModelInputReady(), inputModel.isModelInputReady());
    }
}
