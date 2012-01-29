/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ann.controller;

import ann.model.MatrixModelInterface;
import ann.view.ControlFrame;
import file.controller.FileController;
import file.controller.FileControllerInterface;
import file.model.CSVFileModel;
import file.model.FileModelInterface;
import file.observer.FileObserver;

/**
 *
 * @author Kaleb
 */
public class Controller implements FileObserver, ControllerInterface
{
    private ControlFrame mainView;
    private FileControllerInterface fileController;
    private FileModelInterface fileModel;
    private MatrixModelInterface model;

    public Controller(ControlFrame mainView, MatrixModelInterface model)
    {
        this.mainView = mainView;
        this.model = model;
        fileModel = new CSVFileModel();
        fileModel.registerFileObserver(this);
        fileController = new FileController(fileModel);
    }
    
    public void importMatrix()
    {
        fileController.getFileChooser();
    }

    @Override
    public void updateMatrix(double[][] matrix)
    {
       model.setMatrix(matrix);
    }
}
