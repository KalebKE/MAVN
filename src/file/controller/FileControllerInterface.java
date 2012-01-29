/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file.controller;

/**
 *
 * @author Kaleb
 */
public interface FileControllerInterface
{

    /**
     * The desired View for finding files should be initialized here.
     * Example of a JFileChooser that only finds directories:
     * {
     * final JFileChooser fc = new JFileChooser("C:/");
     * fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     * }
     * Implementations of JFileChooser, or any other View,
     * should call setFile(String file).
     * Example:
     * {
     * implementation.setFile(FileChooserView.this.getSelectedFile().getPath());
     * {
     *
     */
    public void getFileChooser();

    /**
     * The desired implemenation of FileModelInterface should be initialized here.
     * Example:
     * {
     * implemenation.setModel(path);
     * {
     * This will forward the file path to the Model that knows how to
     * parse the file type. Any classes that implement FileObserver and
     * and have registered as Observers of the Model will be notified of the
     * new state.
     * @param path the path of the file
     */
    public void setModel(String path);
}
