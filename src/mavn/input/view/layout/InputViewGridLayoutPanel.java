/*
InputViewGridLayoutPanelAbstract -- an abstract class within the Machine Artificial
Vision Network(Machine Artificial Vision Network).
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
package mavn.input.view.layout;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import mavn.globals.Globals;
import simulyn.ui.components.inputModel.InputViewAbstract;

/**
 * A special JPanel that provides the layout for the Input Model Views.
 * @author Kaleb
 */
public class InputViewGridLayoutPanel extends InputViewLayoutPanelAbstract
{

    private GridLayout layout;

    public InputViewGridLayoutPanel(ArrayList<InputViewAbstract> inputPanels, JPanel controlBar)
    {
        super(controlBar);
        this.inputPanels = inputPanels;
        this.layout = new GridLayout(3, 2);
        this.modelPanel.setLayout(layout);

        this.modelPanel.add(inputPanels.get(Globals.W2_PANEL));
        this.modelPanel.add(inputPanels.get(Globals.THETA_PANEL));
        this.modelPanel.add(inputPanels.get(Globals.W1_PANEL));
        this.modelPanel.add(inputPanels.get(Globals.W0_PANEL));
        this.modelPanel.add(inputPanels.get(Globals.TARGET_PANEL));
    }
}
