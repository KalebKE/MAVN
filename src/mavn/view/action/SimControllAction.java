/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.view.action;

import file.open.controller.directory.OpenSpreadsheetDirectoryController;
import file.open.observer.OpenFileObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Kaleb
 */
public class SimControllAction implements ActionListener
{

    private ArrayList<OpenFileObserver> inputModels;

    public SimControllAction(ArrayList<OpenFileObserver> inputModels)
    {
        this.inputModels = inputModels;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("importSimulationAction"))
        {
            OpenSpreadsheetDirectoryController importModel = new OpenSpreadsheetDirectoryController(inputModels);
            importModel.getDirectoryChooser();
        }
    }
}
