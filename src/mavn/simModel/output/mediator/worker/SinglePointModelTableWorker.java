/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.mediator.worker;

import java.awt.Color;
import java.awt.Point;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import mavn.simModel.output.mediator.OutputMediator;

/**
 *
 * @author Kaleb
 */
public class SinglePointModelTableWorker extends SwingWorker
{

    private double[][] modelResult;
    private OutputMediator controller;
    private Point point;

    public SinglePointModelTableWorker(double[][] modelResult, OutputMediator controller, Point point)
    {
        this.modelResult = modelResult;
        this.controller = controller;
        this.point = point;
    }

    @Override
    protected Object doInBackground() throws Exception
    {
        double[][] data = modelResult;

        Object[][] d = new Object[data.length][];

        for (int i = 0; i < data.length; i++)
        {
            d[i] = new Object[data[i].length];

            for (int j = 0; j < data[i].length; j++)
            {

                if (data[i][j] == 1)
                {
                    d[i][j] = "Dart Hit Image: " + data[i][j] + " @ " + "X = " + point.getX()
                            + " Y = " + point.getY();
                }
                if (data[i][j] == -1)
                {
                    d[i][j] = "Dart Missed Image: " + data[i][j] + " @ " + "X = " + point.getX()
                            + " Y = " + point.getY();
                }
            }
        }
        String[] columnNames;
        columnNames = new String[data[0].length];

        int count = 0;
        for (String s : columnNames)
        {
            columnNames[count] = "" + count;
            count++;
        }

        DefaultTableModel model = new DefaultTableModel(d, columnNames);
        model.setNumRows(15);
        model.setColumnCount(1);
        controller.setTableModel(model);
        controller.updateUI();

        return null;
    }
}
