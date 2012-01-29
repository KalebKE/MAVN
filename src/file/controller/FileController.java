/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file.controller;

import file.model.FileModelInterface;
import file.view.FileChooserView;

/**
 * An implementation of FileControllerInterface that will load a file.
 * @author Kaleb
 */
public class FileController implements FileControllerInterface
{

    FileModelInterface fileModel;

    public FileController(FileModelInterface fileModel)
    {
        this.fileModel = fileModel;
    }

    /**
     * Get a file navigator that selects a file.
     */
    @Override
    public void getFileChooser()
    {
        // Create a file chooser
        final FileChooserView fc = new FileChooserView(this);

        int returnVal = fc.showOpenDialog(fc);
    }

    @Override
    public void setModel(String path)
    {
        fileModel.setModel(path);
    }
}
