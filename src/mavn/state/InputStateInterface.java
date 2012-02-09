/*
InputStateInterface -- an interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

package mavn.state;

/**
 * Defines a input framework to manage the applications state using a State Pattern.
 * Implementations will handle the state related to
 * the loading and unloading of matricies into the application.
 * @author Kaleb
 */
public interface InputStateInterface
{
    /**
     * Check to see if a matrix is loaded.
     * @return indicate if a loaded has been loaded
     */
    public boolean isMatrixLoaded();
    /**
     * What state should be enabled when a matrix is loaded.
     */
    public void matrixLoaded();

    /**
     * What state should be enabled when a matrix is unloaded.
     */
    public void matrixUnloaded();
    
}
