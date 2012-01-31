/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixWizard.model;

import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;
import matrixWizard.observer.MatrixWizardObserver;
import mavn.util.Transpose;

/**
 *
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


