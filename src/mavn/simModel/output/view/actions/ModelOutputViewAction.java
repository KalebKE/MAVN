/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulyn.output.mediator.OutputMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class ModelOutputViewAction implements ActionListener
{

    private OutputMediatorInterface mediator;

    public ModelOutputViewAction(OutputMediatorInterface mediator)
    {
        this.mediator = mediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("loadModelAction"))
        {
            mediator.onLoadModel();
        }
        if (e.getActionCommand().equals("loadPropertiesAction"))
        {
            mediator.onLoadProperties();
        }
        if (e.getActionCommand().equals("runSimulationAction"))
        {
            mediator.onRunSimulation();
        }
        if (e.getActionCommand().equals("saveOutputAction"))
        {
            mediator.onRunSimulation();
        }
        if (e.getActionCommand().equals("clearOutputAction"))
        {
            mediator.onRunSimulation();
        }
    }
}
