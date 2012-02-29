/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.view.input;

import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.simModel.input.model.observer.ThetaModelObserver;
import spreadsheetTable.SimTable;

/**
 *
 * @author Kaleb
 */
public class ModelInputThetaPanel extends ModelInputPanelAbstract implements ThetaModelObserver
{

    public ModelInputThetaPanel(ModelInputFileInterface inputController, ModelInputInterface inputModel, ModelChangeEvent modelChanged)
    {
        super(inputController, inputModel, modelChanged);
        this.inputPanelHeaderLabel.setText("Theta Matrix: Theta2");
        this.inputPanelDescriptionLabel.setText("Theta Matrix: Defines Vector Boundaries");
    }

    @Override
    public void updateThetaModelInput(double[][] modelInput)
    {
        ((SimTable) this.inputPane).setModel(modelInput);
        this.firePropertyChange("propertiesChanged", !inputModel.isModelInputReady(), inputModel.isModelInputReady());
    }
}
