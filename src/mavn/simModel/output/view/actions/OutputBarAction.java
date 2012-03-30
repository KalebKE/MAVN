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
public class OutputBarAction implements ActionListener
{

    private OutputMediatorInterface outputMediator;

    public OutputBarAction(OutputMediatorInterface outputMediator)
    {
        this.outputMediator = outputMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("saveOutputAction"))
        {
            this.outputMediator.onSaveResults();
        }
        if (e.getActionCommand().equals("clearOutputAction"))
        {
            this.outputMediator.onClearModelResult();
        }
    }
}
