/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.network.mediator.NetworkMediatorInterface;
import simulyn.mediator.SimulationMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class NetworkBarAction implements ActionListener
{

    private NetworkMediatorInterface networkMediator;

    public NetworkBarAction(NetworkMediatorInterface networkMediator)
    {
        this.networkMediator = networkMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("animateNetworkAction"))
        {
            this.networkMediator.animateNetwork(true);
        }
        if (e.getActionCommand().equals("renderNetworkAction"))
        {
            this.networkMediator.animateNetwork(false);
        }
        if (e.getActionCommand().equals("clearNetworkAction"))
        {
            double[][] w2 = new double[0][0];
            double[][] w1 = new double[0][0];
            double[][] w0 = new double[0][0];

            networkMediator.setNetwork(w2, w1, w0);
        }
    }
}
