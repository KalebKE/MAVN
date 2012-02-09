/*
MatrixWizardModelInterface -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package matrixWizard.model;

import javax.swing.SpinnerNumberModel;
import matrixWizard.observer.MatrixWizardObserver;

/**
 * FileModelInterface defines a framework for different types of file
 * parsers to be hooked into the application. In the future,
 * functionality for parsing different file types can be added by implementing
 * setModel(String path). An observer pattern is used so new observers can easily
 * register for Model updates. 
 * @author Kaleb
 */
public interface MatrixWizardModelInterface
{

    /**
     * Implementation of registering observers.
     * @param o
     */
    public void registerMatrixWizardObserver(MatrixWizardObserver o);

    /**
     * Implementation of removing observers.
     * @param o
     */
    public void removeMatrixWizardObserver(MatrixWizardObserver o);

    /**
     * Implementation of notifying the observers.
     */
    public void notifyMatrixWizardObservers(double[][] matrix);

    /**
     * Implementation of how the file is parsed. 
     * @param path
     */
    public void getMatrix(SpinnerNumberModel[][] model);
}
