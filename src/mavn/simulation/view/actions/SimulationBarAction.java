/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import file.open.controller.directory.OpenSpreadsheetDirectoryController;
import file.open.observer.OpenFileObserver;
import file.save.controller.directory.SaveDirectoryControllerInterface;
import file.save.controller.directory.SaveSpreadsheetDirectoryController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import mavn.network.mediator.NetworkMediatorInterface;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;
import mavn.plot.mediator.PlotMediatorInterface;
import mavn.spreadsheet.mediator.SSMediatorInterface;
import simulyn.input.model.InputModelInterface;

/**
 *
 * @author Kaleb
 */
public class SimulationBarAction implements ActionListener
{

    private ArrayList<InputModelInterface> inputModels;
    private ArrayList<OpenFileObserver> fileObservers;
    private SaveDirectoryControllerInterface saveDirectoryController;
    private NetworkMediatorInterface networkMediator;
    private SSMediatorInterface ssMediator;
    private SimulationViewInputStateInterface outputViewState;
    private PlotMediatorInterface plotMediator;
    private final String[] fileNames = {"target", "theta", "w0", "w1", "w2"};

    public SimulationBarAction(ArrayList<InputModelInterface> inputModels,
            NetworkMediatorInterface networkMediator,
            PlotMediatorInterface plotMediator,
            SSMediatorInterface outputMediator)
    {
        this.inputModels = inputModels;
        this.networkMediator = networkMediator;
        this.ssMediator = outputMediator;
        this.plotMediator = plotMediator;

        fileObservers = new ArrayList<OpenFileObserver>();

        for (int i = 0; i < this.inputModels.size(); i++)
        {
            fileObservers.add((OpenFileObserver) this.inputModels.get(i));
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
            ArrayList<double[][]> models = new ArrayList<double[][]>();
            
            for (int i = 0; i < inputModels.size(); i++)
            {
                models.add(this.inputModels.get(i).getModelInput());
            }

            saveDirectoryController = new SaveSpreadsheetDirectoryController(models, fileNames);
        }
        if (e.getActionCommand().equals("clearSimluationAction"))
        {
            for (int i = 0; i < this.inputModels.size(); i++)
            {
                inputModels.get(i).setModelInput(new double[0][0]);
                inputModels.get(i).setModelInputReady(false);
            }

            double[][] w2 = new double[0][0];
            double[][] w1 = new double[0][0];
            double[][] w0 = new double[0][0];

            this.ssMediator.onClearModelResult();
            this.plotMediator.onClearUI();
            this.networkMediator.setNetwork(w2, w1, w0);
            this.outputViewState.onPropertiesUnloaded();
            this.outputViewState.onSimulationUnloaded();
        }
    }

    public void setOutputViewState(SimulationViewInputStateInterface outputViewState)
    {
        this.outputViewState = outputViewState;
    }
}
