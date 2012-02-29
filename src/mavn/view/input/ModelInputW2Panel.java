/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.view.input;

import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.simModel.input.model.ModelInputInterface;
import mavn.simModel.input.model.observer.W2ModelObserver;
import spreadsheetTable.SimTable;

/**
 *
 * @author Kaleb
 */
public class ModelInputW2Panel extends ModelInputPanelAbstract implements W2ModelObserver
{
    public ModelInputW2Panel(ModelInputFileInterface inputController, ModelInputInterface inputModel, ModelChangeEvent modelChanged)
    {
        super(inputController, inputModel, modelChanged);
        this.inputPanelHeaderLabel.setText("Weight Matrix: W2");
        this.inputPanelDescriptionLabel.setText("W2 Matrix: Defines Shape Vector Directions");
    }

    @Override
    public void updateW2ModelInput(double[][] modelInput)
    {
      ((SimTable) this.inputPane).setModel(modelInput);
      this.firePropertyChange("propertiesChanged", !inputModel.isModelInputReady(), inputModel.isModelInputReady());
    }
}
