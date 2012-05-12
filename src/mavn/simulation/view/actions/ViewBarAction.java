/*
ViewBarAction -- A class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
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
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.simulation.view.SimControlView;

/**
 * ViewBarAction is an ActionListener implementation used
 * to manage the Actions from the Simulation Control View.
 * @author Kaleb
 */
public class ViewBarAction implements ActionListener
{

    private SimControlView view;

    /**
     * Initialize a new ViewCarAction.
     */
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

    /**
     * Set the Simulation Control View.
     * @param view the SimControlView. 
     */
    public void setView(SimControlView view)
    {
        this.view = view;
    }
}
