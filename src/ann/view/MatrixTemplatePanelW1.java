/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ann.view;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Kaleb
 */
public class MatrixTemplatePanelW1 extends MatrixTemplatePanel
{
    public MatrixTemplatePanelW1(int m, int n, ControlFrame controlFrame)
    {
        super(m, n, controlFrame);
    }

    @Override
     public void updateMatrix()
    {
        double[][] matrix = getMatrix();

        controlFrame.setCurrentMatrixW1(matrix);
        controlFrame.updateW1Matrix(matrix);
    }

     @Override
    public void initSpinners(int m, int n)
    {
        // init spinners
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                model[i][j] = new SpinnerNumberModel(0.0, -1.0, 1.0, 1.0);
                spinner[i][j] = new JSpinner(model[i][j]);
                ((JSpinner.DefaultEditor) spinner[i][j].getEditor()).getTextField().setColumns(3);
            }
        }
    }
}
