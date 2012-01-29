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

package ann.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Kaleb
 */
public class MatrixTemplateControlPanel extends JPanel implements ActionListener
{

    private JButton create = new JButton("Create!");
    private MatrixTemplatePanel matrix;
    private JFrame parent;


    public MatrixTemplateControlPanel(MatrixTemplatePanel matrix, JFrame parent)
    {
        this.matrix = matrix;
        this.parent = parent;
        create.addActionListener(this);
        add(create);
    }

    public void actionPerformed(ActionEvent e)
    {
        parent.dispose();
        matrix.updateMatrix();
    }
}
