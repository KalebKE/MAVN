/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file.model;

import file.observer.FileObserver;

/**
 *
 * @author Kaleb
 */
public interface FileModelInterface
{
    public void registerFileObserver(FileObserver o);

    public void removeFileObserver(FileObserver o);

    public void notifyFileObservers();

    public void setModel(String path);
}
