/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.input.view.layoutPanel;

import java.awt.GridLayout;
import java.util.ArrayList;
import mavn.globals.Globals;
import simulyn.ui.components.inputModelPanel.InputViewAbstract;


/**
 *
 * @author Kaleb
 */
public class InputViewGridLayoutPanel extends InputViewLayoutPanelAbstract
{
    private GridLayout layout;

    public InputViewGridLayoutPanel(ArrayList<InputViewAbstract> inputPanels)
    {
        this.inputPanels = inputPanels;
        this.layout = new GridLayout(3, 2);
        this.setLayout(layout);

        this.add(inputPanels.get(Globals.W2_PANEL));
        this.add(inputPanels.get(Globals.THETA_PANEL));
        this.add(inputPanels.get(Globals.W1_PANEL));
        this.add(inputPanels.get(Globals.W0_PANEL));
        this.add(inputPanels.get(Globals.TARGET_PANEL));
    }
}
