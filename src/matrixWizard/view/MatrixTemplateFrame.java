/*
MatrixTemplatePanel -- a class within the Open Queueing Model (OQM).
Copyright (C) 2011  Kircher Engineering, LLC (http://www.kircherEngineering.com)

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
package matrixWizard.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import matrixWizard.model.MatrixWizardModelInterface;

/**
 *
 * @author Kaleb
 */
public class MatrixTemplateFrame extends JFrame
{

    private JPanel main = new JPanel();
    private JSpinner[][] spinner;
    protected MatrixWizardModelInterface matrixModel;
    private SpinnerNumberModel[][] model;

    /**
     *
     * @param m
     * @param n
     * @param controlFrame
     */
    public MatrixTemplateFrame(int m, int n, MatrixWizardModelInterface matrixModel)
    {
        this.matrixModel = matrixModel;

        setContentPane(main);
        setSize(400, 400);

        spinner = new JSpinner[m][n];
        model = new SpinnerNumberModel[m][n];

        initSpinners(m, n);

        // init grid layout for matrix spinners
        main.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .25;

        main.add(new JLabel("Create Matrix"), c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = .65;

        main.add(initPanel(), c);

        c.gridx = 0;
        c.gridy = 2;
        c.weighty = .1;

        main.add(new MatrixTemplateControlPanel(matrixModel, this, model), c);

        setVisible(true);
    }

    /**
     *
     * @return
     */
    public JPanel initPanel()
    {
        JPanel template = new JPanel(new GridBagLayout());

        template.setSize(spinner.length * 20, spinner[0].length * 20);

        // layered pane
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1 / spinner[0].length + 1; // request any extra vertical space
        c.weighty = 1 / spinner.length + 1; // request any extra vertical space

        for (int i = 0; i < spinner.length + 1; i++)
        {
            for (int j = 0; j < spinner[0].length + 1; j++)
            {
                if (j == 0 && i == 0)
                {
                    c.gridx = i;
                    c.gridy = j;

                    template.add(new JLabel("  "), c);
                }
                if (j == 0 && i != 0)
                {
                    c.gridx = i;
                    c.gridy = j;

                    template.add(new JLabel("M" + (i - 1)), c);
                }

                if (i == 0 && j != 0)
                {
                    c.gridx = i;
                    c.gridy = j;

                    template.add(new JLabel("N" + (j - 1)), c);
                }

                if (i != 0 && j != 0)
                {
                    c.gridx = i;
                    c.gridy = j;
                    c.ipadx = 2;
                    c.ipady = 2;
                    template.add(spinner[i - 1][j - 1], c);
                }
            }
        }
        return template;
    }

    public void initSpinners(int m, int n)
    {
        // init spinners
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                model[i][j] = new SpinnerNumberModel(0.0, -99.0, 99.0, 1.0);
                spinner[i][j] = new JSpinner(model[i][j]);
                ((JSpinner.DefaultEditor) spinner[i][j].getEditor()).getTextField().setColumns(3);
            }
        }
    }

    /**
     *
     * @return
     */
    public void setMatrix(double[][] newMatrix)
    {
        for (int i = 0; i < newMatrix.length; i++)
        {
            for (int j = 0; j < newMatrix[0].length; j++)
            {
                spinner[i][j].getModel().setValue(new Double(newMatrix[i][j]));
            }
        }
    }
}
