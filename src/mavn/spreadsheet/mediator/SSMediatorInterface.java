/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.spreadsheet.mediator;

import javax.swing.JPanel;

/**
 *
 * @author Kaleb
 */
public interface SSMediatorInterface
{

    public JPanel getView();

    public void onClearModelResult();

    /**
     * Implementations should initialize the classes
     * that will save the results data to an external file.
     * This can be accomplished by accessing a file controller
     * and requesting a Save File Chooser (usually a JFileChooser)
     * and passing it the results.
     * Example:
     * {
     * fileController.getSaveFileChooser(results);
     * }
     */
    public void onSaveSimulationOutput();
}
