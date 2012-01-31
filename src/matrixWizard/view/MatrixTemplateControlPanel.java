/*
 MatrixTemplateControlPanel -- a class within the Open Queueing Model (OQM).
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import matrixWizard.model.MatrixWizardModelInterface;

/**
 *
 * @author Kaleb
 */
public class MatrixTemplateControlPanel extends JPanel implements ActionListener
{

    private JButton create = new JButton("Create!");
    private MatrixWizardModelInterface matrixModel;
    private JFrame parent;
    private SpinnerNumberModel[][] model;


    public MatrixTemplateControlPanel(MatrixWizardModelInterface matrixModel, JFrame parent, SpinnerNumberModel[][] model)
    {
        this.matrixModel = matrixModel;
        this.parent = parent;
        create.addActionListener(this);
        this.model = model;
        add(create);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        parent.dispose();
        matrixModel.getMatrix(model);
    }
}