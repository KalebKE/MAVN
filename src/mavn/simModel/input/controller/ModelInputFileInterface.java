/*
InputControllerInterface -- an interface within the Simulyn Framework.
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
package mavn.simModel.input.controller;

import mavn.simModel.input.model.ModelInputInterface;

/**
 * ModelInputFileInterface is designed as a Controller in a
 * Model-View-Controller architecture for the Simulyn Framework.
 * Each instance is intended to control and interface one, and only one,
 * instance of an ModelInput and an ModelView, respectively.
 * Typically, you will need an ModelInput (Model), ViewInput (View), and a
 * ModelInputFileInterface (Controller) for each input your simulation
 * model requires.
 * For example, if your simulation's model requires only
 * a single matrix that is persisted in an external file, you will need one
 * of each to use the files model input data locally (within your simulation
 * application):
 * - A ModelInput that stores and manages a local copy of the external
 * files data. ModelInput represents the Model in the MVC architecture and
 * can be implemented using the ModelInputInterface.
 * - A ViewInput that is responsible for rendering the ModelInput so
 * the user can interact with it in a graphical environment. ViewInput
 * represents the View in the MVC architecture and can be implemented
 * using the ViewInputInterface.
 * - ModelInputFile is intended to control and  interface one, and only one,
 * instance of an ModelInput and an ModelView, respectively. ModelInputFile
 * represents the Controller in the MVC architecture and can be implemented
 * using the ModelInputFileInterface.
 * 
 * @author Kaleb
 */
public interface ModelInputFileInterface
{

    /**
     * Implementation of how the simulation model input data that is
     * stored locally (typically with a ModelInput) will be written out to an
     * external persisted file.
     */
    public void exportModelInput();

    /**
     * Return the ModelInputInterface.
     * @return
     */
    public ModelInputInterface getModelInput();

    /**
     * Implementation of how the imulation model input data that is stored
     * in an external persisted file will be read into and stored in the
     * simulation application (typically with a ModelInput) .
     */
    public void importModelInput();
}
