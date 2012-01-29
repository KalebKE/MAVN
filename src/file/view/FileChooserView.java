/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file.view;

import file.controller.FileControllerInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

/**
 * A special JFileChoser that is integrated into an MVC library
 * that allows files to be parsed with different parsing algorithms
 * that can be dynamically changed at run time. 
 * @author Kaleb
 */
public class FileChooserView extends JFileChooser
{
    private FileControllerInterface fileController;

    /**
     * Initialize the FileChooserView a FileControllerInterface. This class
     * should be initialized by the implementation of the FileControllerInterface.
     * Example implementation: fcv = new FileChoserView(this);
     * In a standard MVC, the model would also be passed to this class and implement
     * an Observer Interface. However, this View is only concerned with finding the
     * file where the model is contained, and not the model itself, it doesn't need to
     * be updated of anything.
     * @param fileController
     */
    public FileChooserView(final FileControllerInterface fileController)
    {
        // Start in the C:/ drive
        super("C:/");

        this.fileController = fileController;

        // Select the file
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                {
                    FileChooserView.this.fileController.setModel(FileChooserView.this.getSelectedFile().getPath());             
                }
            }
        });
    }
}
