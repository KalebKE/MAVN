/*
 FileObserver -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package file.observer;

/**
 * An interface that allows FileObservers to hook into
 * FileObservees. This interface should be impelmented by
 * any class wanting to observe a class implementing FileModelInterface.
 * @author Kaleb
 */
public interface FileObserver
{
    /**
     * Implementation of updating the matrix defining the model.
     * This method could be called by View classes to update text
     * areas, by other parts of the Model that validate data or by whatever
     * you want really. 
     * @param matrix the matrix defining the model
     */
    public void updateMatrix(double[][] matrix);
}
