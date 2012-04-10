/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.input.view.layout;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import mavn.globals.Globals;
import simulyn.ui.components.inputModelPanel.InputViewAbstract;

/**
 *
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
