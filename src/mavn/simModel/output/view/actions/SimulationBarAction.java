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
import mavn.simModel.network.mediator.NetworkMediatorInterface;
import mavn.simModel.output.view.state.OutputViewStateInterface;
import mavn.simModel.plot.mediator.PlotMediatorInterface;
import simulyn.input.model.InputModelInterface;
import simulyn.output.mediator.OutputMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class SimulationBarAction implements ActionListener
{

    private ArrayList<InputModelInterface> inputModels;
    private ArrayList<OpenFileObserver> fileObservers;
    private NetworkMediatorInterface networkMediator;
    private OutputMediatorInterface outputMediator;
    private OutputViewStateInterface outputViewState;
    private PlotMediatorInterface plotMediator;

    public SimulationBarAction(ArrayList<InputModelInterface> inputModels,
            NetworkMediatorInterface networkMediator,
            OutputMediatorInterface outputMediator,
            PlotMediatorInterface plotMediator)
    {
        this.inputModels = inputModels;
        this.networkMediator = networkMediator;
        this.outputMediator = outputMediator;
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

            this.outputMediator.onClearModelResult();
            this.plotMediator.onClearUI();
            this.networkMediator.setNetwork(w2, w1, w0);
            this.outputViewState.propertiesUnloaded();
            this.outputViewState.simulationUnloaded();
        }
    }

    public void setOutputViewState(OutputViewStateInterface outputViewState)
    {
        this.outputViewState = outputViewState;
    }
}