/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mavn.simModel.network.mediator.NetworkMediatorInterface;
import simulyn.output.mediator.OutputMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class NetworkBarAction implements ActionListener
{

    private OutputMediatorInterface outputMediator;
    private NetworkMediatorInterface networkMediator;

    public NetworkBarAction(OutputMediatorInterface outputMediator,
            NetworkMediatorInterface networkMediator)
    {
        this.outputMediator = outputMediator;
        this.networkMediator = networkMediator;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("animateNetworkAction"))
        {
            this.outputMediator.onAnimateNetwork(true);
        }
        if (e.getActionCommand().equals("renderNetworkAction"))
        {
            this.outputMediator.onAnimateNetwork(false);
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
