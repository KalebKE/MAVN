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
package ann.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import ann.util.Transpose;

/**
 *
 * @author Kaleb
 */
public abstract class MatrixTemplatePanel extends JFrame implements ActionListener
{
    protected JPanel main = new JPanel();
    protected JSpinner[][] spinner;
    protected ControlFrame controlFrame;
    protected SpinnerNumberModel[][] model;

    /**
     *
     * @param m
     * @param n
     * @param controlFrame
     */
    public MatrixTemplatePanel(int m, int n, ControlFrame controlFrame)
    {

        this.controlFrame = controlFrame;

        setContentPane(main);
        setSize(400, 400);

        spinner = new JSpinner[m][n];
        model = new SpinnerNumberModel[m][n];

        initSpinners(m,n);

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

        main.add(new MatrixTemplateControlPanel(this, this), c);

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

    /**
     *
     * @return
     */
    public double[][] getMatrix()
    {
        double[][] matrix = new double[spinner.length][spinner[0].length];

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                matrix[i][j] = model[i][j].getNumber().doubleValue();
            }
        }

        Transpose trans = new Transpose();
        double[][] transposeMatrix = trans.tranposeMatrix(matrix);

        return transposeMatrix;
    }

    public abstract void initSpinners(int m, int n);
    /**
     *
     */
    public abstract void updateMatrix();

    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
