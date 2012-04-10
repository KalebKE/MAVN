/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.network.mediator;

import javax.swing.JPanel;

/**
 *
 * @author Kaleb
 */
public interface NetworkMediatorInterface
{

    public JPanel getView();

    public void animateNetwork(boolean animate);

    public boolean isAnimateNetwork();

    public void setNetwork(double[][] w2, double[][] w1, double[][] w0);

    public void resetNetwork();
}
