/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.view.actions;

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
public class SimulationBarAction implements ActionListener
{

    private ArrayList<InputModelInterface> inputModels;
    private ArrayList<OpenFileObserver> fileObservers;

    public SimulationBarAction(ArrayList<InputModelInterface> inputModels)
    {
        this.inputModels = inputModels;

        fileObservers = new ArrayList<OpenFileObserver>();

        for (int i = 0; i < this.inputModels.size(); i++)
        {
            fileObservers.add((OpenFileObserver)this.inputModels.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("importSimulationAction"))
        {
            OpenSpreadsheetDirectoryController importModel = new OpenSpreadsheetDirectoryController(fileObservers);
            importModel.getDirectoryChooser();
        }
        if (e.getActionCommand().equals("exportSimulationAction"))
        {
        }
        if (e.getActionCommand().equals("clearSimluationAction"))
        {
            for (int i = 0; i < this.inputModels.size(); i++)
            {
                inputModels.get(i).setModelInput(new double[0][0]);
                inputModels.get(i).setModelInputReady(false);
            }
        }
    }
}
