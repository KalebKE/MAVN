/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.plot.mediator;

import javax.swing.JPanel;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Kaleb
 */
public interface PlotMediatorInterface
{

    public JPanel getView();

    public Plot2DPanel getPlot();

    public void onScatterPlot();

    public void onLinePlot();
}
