/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.view.input;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import mavn.globals.Globals;

/**
 *
 * @author Kaleb
 */
public class ModelInputControlPanel extends ModelInputControlPanelAbstract
{

    public ModelInputControlPanel(ArrayList<ModelInputPanelAbstract> inputPanels)
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
