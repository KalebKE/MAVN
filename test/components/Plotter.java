/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package components;

import java.awt.Color;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Kaleb
 */
public class Plotter {

    public static void main(String[] args)
    {
        Plot2DPanel plot = new Plot2DPanel();

        double[][] hit =
            {
                {
                    1
                },
                {
                    5
                }
            };

        double[][] miss =
            {
                {
                    5
                },
                {
                    1
                }
            };

        plot.addScatterPlot("", Color.RED, hit);
        plot.addScatterPlot("", Color.BLACK, miss);

        JFrame frame = new JFrame();
        frame.add(plot);
        frame.pack();
        frame.setVisible(true);
    }
}
