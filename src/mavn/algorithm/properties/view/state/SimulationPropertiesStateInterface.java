/*
PropertiesStateInterface -- an interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.properties.view.state;

import javax.swing.SpinnerNumberModel;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;

/**
 * An interface for the applications State Pattern. Implementations define
 * what state should be enabled for the application.
 * @author Kaleb
 */
public interface SimulationPropertiesStateInterface
{

    public SpinnerNumberModel getGridSpinnerModel();

    /**
     * Get the Dart Model.
     * @return SpinnerNumberModel representing the number of darts to be fired
     * during the simulation.
     */
    public SpinnerNumberModel getPointGeneratorModel();

    /**
     * Get the Seed Model.
     * @return SpinnerNumberModel representing the random number seed that should
     * be used during the simulation.
     */
    public SpinnerNumberModel getSeedModel();

    /**
     * Check if CA RNG is desired.
     * @return boolean indicating CA RNG is desired.
     */
    public boolean isCaRng();

    /**
     * Check if CMWC RNG is desired.
     * @return boolean indicating CMWC RNG  is desired.
     */
    public boolean isCmwcRng();

    /**
     * Check to see if the DartGun is desired.
     * @return boolean indicating if a DartGun is desired.
     */
    public boolean isPointGeneratedModel();

    public boolean isGridGeneratedModel();

    /**
     * Check if Mt Rng is desired.
     * @return boolean indicating Mt Rng is desired.
     */
    public boolean isMtRng();

    /**
     * Check if java.util.random RNG is desired.
     * @return boolean indicating java.util.random RNG is desired.
     */
    public boolean isRandomRng();

    /**
     * Check if a random seed is desired.
     * @return boolean indicating if a random seed is desired.
     */
    public boolean isRandomSeed();

    /**
     * Check to see if a single dart target is desired.
     * @return boolean indicating if a single dart target is desired.
     */
    public boolean isTargetModel();

    /**
     * Check if XORRng is desired.
     * @return boolean indicating XORRng is desired.
     */
    public boolean isXORRng();

    /**
     * Use the CA RNG.
     */
    public void onCaRng();

    /**
     * Indicate that mtRNG is desired.
     */
    public void onMtRng();

    /**
     * Use the CMWC RNG.
     */
    public void onCmwcRng();

    public void onPixelGridSimulation();

    /**
     * Indicate that the Target Model should be used instead of the
     * DartGun.
     */
    public void onDiagnosticSimulation();

    /**
     * Indicate that the DartGun should be used instead of the Target Model.
     */
    public void onMonteCarloSimulation();

    /**
     * Indicate that the RNG should use a specific seed.
     */
    public void onSpecifiedRngSeed();

    /**
     * Indicate that the RNG should use a random seed.
     */
    public void onRandomRngSeed();

    /**
     * Inidcate that XORSHIFT RNG should be used.
     */
    public void onXORRng();

     /**
     * Indicate that java.util.random RNG is desired.
     */
    public void randomRng();

    public void setOutputViewState(SimulationViewInputStateInterface outputViewState);
}
