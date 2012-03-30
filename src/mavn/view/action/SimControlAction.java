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
import simulyn.input.model.InputModelInterface;

/**
 *
 * @author Kaleb
 */
public class SimControlAction implements ActionListener
{

    private ArrayList<InputModelInterface> inputModels;
    private ArrayList<OpenFileObserver> openFileObservers;

    public SimControlAction(ArrayList<InputModelInterface> inputModels)
    {
        this.inputModels = inputModels;
        openFileObservers = new ArrayList<OpenFileObserver>();

        for(int i = 0; i < inputModels.size(); i++)
        {
            openFileObservers.add((OpenFileObserver)inputModels.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("importSimulationAction"))
        {
            OpenSpreadsheetDirectoryController importModel = new OpenSpreadsheetDirectoryController(openFileObservers);
            importModel.getDirectoryChooser();
        }
    }
}
