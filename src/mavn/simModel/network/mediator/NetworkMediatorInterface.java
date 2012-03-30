/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.network.mediator;

import javax.swing.JPanel;

/**
 *
 * @author Kaleb
 */
public interface NetworkMediatorInterface
{
    public JPanel getView();
    
    public void setNetwork(double[][] w2, double[][] w1, double[][] w0);

    public void resetNetwork();
}
