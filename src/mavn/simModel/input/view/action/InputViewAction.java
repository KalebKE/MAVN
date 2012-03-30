/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.input.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulyn.input.controller.InputControllerInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.ui.components.inputModelPanel.InputViewAbstract;
import simulyn.ui.components.spreadsheetTable.SimTable;


/**
 *
 * @author Kaleb
 */
public class InputViewAction implements ActionListener
{

    private InputControllerInterface inputController;
    private InputModelInterface inputModel;
    private InputViewAbstract view;

    public InputViewAction(InputControllerInterface inputController, InputModelInterface inputModel)
    {
        this.inputController = inputController;
        this.inputModel = inputModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        System.out.println(actionCommand);
        if (actionCommand.equals("importModelAction"))
        {
            inputController.onImportInputModel();
        }
        if (actionCommand.equals("saveModelAction"))
        {
            inputController.onExportInputModel();
        }
        if (actionCommand.equals("setModelAction"))
        {
            inputModel.setModelInput(((SimTable) view.getInputPane()).getModel());
        }
    }

    public void setView(InputViewAbstract view)
    {
        this.view = view;
    }    
}
