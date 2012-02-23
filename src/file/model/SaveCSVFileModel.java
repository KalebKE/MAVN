/*
CSVFileModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package file.model;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * An implementation of FileModelInterface. CSVFileModel knows how to
 * parse CSV files into the correct data structures so it can be used by the rest of the
 * application. It implements an Observer pattern so any observers will
 * be notified and updated with a new Model whenever a new CSV file is parsed.
 * @author Kaleb
 */
public class SaveCSVFileModel implements SaveCsvFileModelInterface
{

    /**
     * Set the path to the file that defines the new Model.
     * @param path to the file containing the model data
     */
    @Override
    public void setModel(String path, double[][] matrix)
    {
        int n = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to save to this location?",
                "Wait a second!",
                JOptionPane.YES_NO_OPTION);

        if (n == 0)
        {
            try
            {
                FileWriter writer = new FileWriter(path + ".csv");
                for (int i = 0; i < matrix.length; i++)
                {
                    for (int j = 0; j < matrix[i].length; j++)
                    {
                        writer.append(Double.toString(matrix[i][j]) + ",");
                    }
                    writer.append("\n");
                    writer.flush();
                }
                System.out.println("File Written!");
                writer.close();
            } catch (IOException error)
            {
                error.printStackTrace();
            }
        }
    }
}
