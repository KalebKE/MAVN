/*
MatrixWizardModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package matrixWizard.model;

import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;
import matrixWizard.observer.MatrixWizardObserver;
import mavn.util.math.Transpose;

/**
 * An implementation of MatrixWizardModelInterface that allows the user
 * to create a new matrix with an array of JSpinners. The class uses an
 * Observer pattern to allow observers to be notified of updates. 
 * @author Kaleb
 */
public class MatrixWizardModel implements MatrixWizardModelInterface
{

    private ArrayList<MatrixWizardObserver> observers;

    public MatrixWizardModel()
    {
        observers = new ArrayList<MatrixWizardObserver>();
    }

    @Override
    public void registerMatrixWizardObserver(MatrixWizardObserver o)
    {
        observers.add(o);
    }

    @Override
    public void removeMatrixWizardObserver(MatrixWizardObserver o)
    {
        int i = observers.indexOf(o);
        if (i >= 0)
        {
            observers.remove(i);
        }
    }

    @Override
    public void notifyMatrixWizardObservers(double[][] matrix)
    {
        for (int i = 0; i < observers.size(); i++)
        {
            MatrixWizardObserver observer = (MatrixWizardObserver) observers.get(i);
            observer.updateMatrix(matrix);
        }
    }

    @Override
    public void getMatrix(SpinnerNumberModel[][] model)
    {
        double[][] matrix = new double[model.length][model[0].length];

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                matrix[i][j] = model[i][j].getNumber().doubleValue();
            }
        }

        Transpose trans = new Transpose();
        double[][] transposeMatrix = trans.tranposeMatrix(matrix);

        notifyMatrixWizardObservers(transposeMatrix);
    }
}


