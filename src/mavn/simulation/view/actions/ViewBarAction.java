/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.simulation.view.SimControlView;

/**
 *
 * @author Kaleb
 */
public class ViewBarAction implements ActionListener
{

    private SimControlView view;

    public ViewBarAction()
    {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("useOutputViewAction"))
        {
            view.setOutputView();
        }
        if (e.getActionCommand().equals("useInputViewAction"))
        {
            view.setInputView();
        }
    }

    public void setView(SimControlView view)
    {
        this.view = view;
    }
}
