/*
DiagnosticSimulationTableWorker -- A class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package mavn.spreadsheet.mediator.worker;


import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import mavn.algorithm.model.point.Point;
import mavn.spreadsheet.mediator.SSMediator;

/**
 * A DiagnosticSimulationTableWorker renders the Spreadsheet View
 * for Diagnostic Simulations. This prevents the UI from hanging while the
 * result renders.
 * @author Kaleb
 */
public class DiagnosticSimulationTableWorker extends SwingWorker
{

    private double[][] modelOutput;
    private SSMediator mediator;
    private Point point;

    /**
     * Initialize a DiagnosticSimulationTableWorker.
     * @param modelOutput the output from the Output Model that will be rendered.
     * @param mediator the Spreadsheet Mediator that the Output Model is being
     * rendered for.
     * @param point the Point from the Target Input Model used in the simluation.
     */
    public DiagnosticSimulationTableWorker(double[][] modelOutput,
            SSMediator mediator, Point point)
    {
        this.modelOutput = modelOutput;
        this.mediator = mediator;
        this.point = point;
    }

    @Override
    protected Object doInBackground() throws Exception
    {
        double[][] data = modelOutput;

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
            columnNames[count] = "Simulation Results";
            count++;
        }

        DefaultTableModel model = new DefaultTableModel(d, columnNames);
        model.setNumRows(15);
        model.setColumnCount(1);
        mediator.setTableModel(model);
        mediator.updateUI();

        return null;
    }
}
