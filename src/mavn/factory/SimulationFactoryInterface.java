/*
MavnControllerInterface -- an interface within the Machine Artificial Vision Network(Machine Artificial Vision Network).
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
package mavn.factory;

/**
 * An interface for MavnControllers. MavnControllers are responsible for
 * initializing all of the state required for the application. The MAVN application
 * is based on a model-view-controller (MVC) architecture. The different modules of the
 * architecture generally communicate via their own Observers. This allows different
 * modules to be "pluged-in" to the application.
 * The MVC modules are sub-classified into Input and Output. Input Modules are
 * concerned with managing state that will be used as an input (like matricies) in the application.
 * Output Modules are concered with managing state that will be used as an output
 * (like the results of a simulation) in the application. It is up to the implementations
 * to decide exactly what qualifies as Input and Output. 
 *
 * Implementing MavnControllers allows for a custom implementation of how all
 * of the modules are "pluged-in". 
 * @author Kaleb Kircher
 */
public interface SimulationFactoryInterface
{
    /**
     * Implementation of DartGuns used by the application. DartGun's
     * use random number generators to produce two points for a two
     * dimensional plane. They will generally be implementations of
     * DartModelInterface. 
     */
    public void initModelAlgorithmDartGuns();

    /**
     * Initialize Input Controller Modules (ICM). Generally, ICM's
     * would implement InputControllerInterface. 
     */
    public void initModelInputControllers();

    /**
     * Initialize Input Model Modules (IMM). Gernally, IMM's would
     * implement InputModelInterface.
     */
    public void initInputModels();

    /**
     * Initialize Input State Modules (ISM). Generally, ISM's would implement
     * InputStateInterface.
     */
    public void initModelInputStates();


    /**
     * Initialize Output State Modules (OSM). Generally, OSM's would implement
     * OutputStateInterface.
     */
    public void initModelResultStates();

    /**
     * Initialize the main View that the user will see when they first
     * see the application.
     */
    public void initModelViews();
}
