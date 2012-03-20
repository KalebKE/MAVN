/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.network.view;

/**
 *
 * @author Kaleb
 */
public interface JUNGPanelAdapterInterface
{
    public void fireNodes(double[] result);
    public void setNetwork(double[][] w2, double[][] w1, double[][] w0);
}
