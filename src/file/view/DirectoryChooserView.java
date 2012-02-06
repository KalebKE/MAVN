/*
FileChooserView -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package file.view;

import file.controller.DirectoryControllerInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * A special JFileChoser that is integrated into an MVC library
 * that allows files to be parsed with different parsing algorithms
 * that can be dynamically changed at run time. 
 * @author Kaleb
 */
public class DirectoryChooserView extends JFileChooser
{

    private DirectoryControllerInterface directoryController;

    /**
     * Initialize the FileChooserView a FileControllerInterface. This class
     * should be initialized by the implementation of the FileControllerInterface.
     * Example implementation: fcv = new FileChoserView(this);
     * In a standard MVC, the Model would also be passed to this class and implement
     * an Observer Interface. However, this View is only concerned with finding the
     * file where the Model is contained, and not the Model data itself. Thus,
     * it doesn't need to be updated of anything.
     * @param fileController
     */
    public DirectoryChooserView(final DirectoryControllerInterface directoryController)
    {
        // Create a file chooser
        super("C:/");

        this.directoryController = directoryController;

        this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        this.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                File myDirectory = new File(getSelectedFile().getPath());
                File[] files = null;

                if (myDirectory.isDirectory())
                {
                    files = myDirectory.listFiles();

                    DirectoryChooserView.this.directoryController.setModels(files);
                }
            }
        });
    }
}
