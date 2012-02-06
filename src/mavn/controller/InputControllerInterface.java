/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.controller;

import file.model.FileModelInterface;

/**
 *
 * @author Kaleb
 */
public interface InputControllerInterface
{
    public void importMatrix();
    public FileModelInterface getFileModel();
    public void editMatrix(double[][] matrix);
    public void newMatrix();
}
