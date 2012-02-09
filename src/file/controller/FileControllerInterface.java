/*
FileControllerInterface -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package file.controller;

/**
 * Implementations of FileControllerInterface define what File Chooser to use
 * (JFileChooser would be a typically choice) and how to parse the file with a
 * file parser Model. The parser models are classes that know how to parse
 * different file types into a data type that the application can use. 
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
     * implementation.setFile(JFileChooserView.this.getSelectedFile().getPath());
     * {
     *
     */
    public void getOpenFileChooser();

    public void getSaveFileChooser(double[][] matrix);

    public void getSaveFileChooser(String results);

    public void setModel(String path, String results);

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

    public void setModel(String path, double[][] matrix);
}
