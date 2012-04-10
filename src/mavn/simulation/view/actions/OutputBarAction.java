/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.spreadsheet.mediator.SSMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class OutputBarAction implements ActionListener
{
    private SSMediatorInterface ssMediator;

    public OutputBarAction(SSMediatorInterface ssMediator)
    {
        this.ssMediator = ssMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("saveOutputAction"))
        {
            this.ssMediator.onSaveSimulationOutput();
        }
        if (e.getActionCommand().equals("clearOutputAction"))
        {
            this.ssMediator.onClearModelResult();
        }
    }
}
