/*
InputModelViewAction -- a class class within the Machine Artificial
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
package mavn.input.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulyn.input.controller.InputControllerInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.ui.components.inputModel.InputViewAbstract;
import simulyn.ui.components.spreadsheetTable.SimTable;


/**
 * An Action Listener for the Input Model Views.
 * @author Kaleb
 */
public class InputModelViewAction implements ActionListener
{

    private InputControllerInterface inputController;
    private InputModelInterface inputModel;
    private InputViewAbstract view;

    /**
     * Initialize a new InputModelViewAction.
     * @param inputController the Input Model Controller
     * @param inputModel the Input Model
     */
    public InputModelViewAction(InputControllerInterface inputController,
            InputModelInterface inputModel)
    {
        this.inputController = inputController;
        this.inputModel = inputModel;
    }

    /**
     * The Input Model Action Event's.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();

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

    /**
     * Set the View.
     * @param view the Input Model View.
     */
    public void setView(InputViewAbstract view)
    {
        this.view = view;
    }    
}
