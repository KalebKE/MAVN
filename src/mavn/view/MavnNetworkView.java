/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.view;

import mavn.factory.AbstractSimulationFactory;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Kaleb
 */
public class MavnNetworkView extends NetworkViewAbstract
{
    public MavnNetworkView(AbstractSimulationFactory controller)
    {
        super(controller);
        plot = new Plot2DPanel();
        outputViewScrollPane.setViewportView(plot);
    }
}
