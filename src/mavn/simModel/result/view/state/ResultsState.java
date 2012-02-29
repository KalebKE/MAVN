/*
ResultsState -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.result.view.state;

import mavn.view.result.NetworkViewAbstract;
import util.components.BlinkerButton;

/**
 * A class that maintains all of the state required for the simulations output.
 * This class is an implementation of the applications State Pattern.
 * @author Kaleb
 */
public class ResultsState implements OutputStateInterface, RngStateInterface
{
    private NetworkViewAbstract view;
    private boolean animated;
    private boolean randomRng;
    private boolean caRng;
    private boolean cmwcRng;
    private boolean mtRng;
    private boolean xORRng;
    private boolean dartGunState;
    private boolean propertiesLoaded;
    private boolean simulationLoaded;
    private boolean resultAvailable;

    /**
     * Initialize the state.
     * @param view the view the class manages the state for.
     */
    public ResultsState(NetworkViewAbstract view)
    {
        this.view = view;
        propertiesLoaded = false;
        randomRng = false;
        caRng = false;
        cmwcRng = false;
        mtRng = false;
        xORRng = false;

        this.propertiesUnloaded();
        this.simulationUnloaded();
    }

    /**
     * Check if the simulation has been run.
     * @return boolean indicating a simulation has been run.
     */
    @Override
    public boolean isResultAvailable()
    {
        return resultAvailable;
    }

    /**
     * Indicate if CARNG is the desired DartGun.
     * @return boolean indicating CARNG DartGun.
     */
    @Override
    public boolean isCaRng()
    {
        return caRng;
    }

    /**
     * Indicate if CMWCRNG is the desired DartGun.
     * @return boolean indicating CMWCRNG DartGun.
     */
    @Override
    public boolean isCmwcRng()
    {
        return cmwcRng;
    }

    /**
     * Check if the user wants to use the Dart Gun for the simulation.
     * @return boolean indicating the Dart Gun is desired.
     */
    @Override
    public boolean isDartGunState()
    {
        return dartGunState;
    }

    /**
     * Check if MTRNG is the desired dart gun.
     * @return boolean indicating MTRNG is desired.
     */
    @Override
    public boolean isMtRng()
    {
        return mtRng;
    }

    /**
     * Check to see if the simulation properties have been loaded.
     * @return boolean indicating loaded simulation properties.
     */
    @Override
    public boolean isPropertiesLoaded()
    {
        return propertiesLoaded;
    }

    /**
     * Check to see if the java.util.random RNG is desired.
     * @return boolean indicating java.util.random RNG is desired.
     */
    @Override
    public boolean isRandomRng()
    {
        return randomRng;
    }

    /**
     * Check to see if a simulation has been loaded.
     * @return boolean indicating a simulation has been loaded.
     */
    @Override
    public boolean isSimulationLoaded()
    {
        return simulationLoaded;
    }

    /**
     * Indicate if XORSHIFT is the desired DartGun.
     * @return boolean indicating XORSHIFT DartGun.
     */
    @Override
    public boolean isxORRng()
    {
        return xORRng;
    }

    /**
     * Inidcate that the simulation properties have been loaded.
     */
    @Override
    public void propertiesLoaded()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(true);
        ((BlinkerButton) view.getRunButton()).setBlink(true);
        propertiesLoaded = true;
    }

    /**
     * Inidicate that the simulation properties have not been loaded.
     */
    @Override
    public void propertiesUnloaded()
    {
        view.getPropertiesButton().setEnabled(true);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(true);
        // Disable these buttons
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getSaveResultsButton().setEnabled(false);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(false);
        view.getClearResultsMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(false);
        propertiesLoaded = false;
        resultAvailable = false;
    }

    /**
     * Indicate that the simulation has been run.
     */
    @Override
    public void resultAvailable()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(true);
        ((BlinkerButton) view.getRunButton()).setBlink(true);
        view.getSaveResultsButton().setEnabled(true);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(true);
        view.getClearResultsMatrixButton().setEnabled(true);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(true);
        resultAvailable = true;
    }

    /**
     * Indicate XORSHIFT is the desired DartGun.
     * @param xORRng boolean indicating XORSHIFT DartGun.
     */
    @Override
    public void setxORRng(boolean xORRng)
    {
        this.xORRng = xORRng;
    }

    /**
     * Indicate CARNG is the desired DartGun.
     * @param caRng boolean indicating CARNG DargGun.
     */
    @Override
    public void setCaRng(boolean caRng)
    {
        this.caRng = caRng;
    }

    /**
     * Indicate CMWCRNG is the desired DartGun.
     * @param cmwcRng boolean indicating CMWCRNG DartGun.
     */
    @Override
    public void setCmwcRng(boolean cmwcRng)
    {
        this.cmwcRng = cmwcRng;
    }

    /**
     * Indicate that the simulation should use the DartGun.
     * @param dartGunState boolean indicating the DartGun should be used for
     * the simulation.
     */
    @Override
    public void setDartGunState(boolean dartGunState)
    {
        this.dartGunState = dartGunState;
    }

    /**
     * Indicate if MTRNG is the desired DartGun.
     * @param mtRng boolean indicating MTRNG is desired.
     */
    @Override
    public void setMtRng(boolean mtRng)
    {
        this.mtRng = mtRng;
    }

    /**
     * Indicate that java.util.random is the desired DartGun.
     * @param randomRng boolean indicating java.util.random is the
     * desired DartGun.
     */
    @Override
    public void setRandomRng(boolean randomRng)
    {
        this.randomRng = randomRng;
    }

    /**
     * Indicate that a simulation has been loaded.
     */
    @Override
    public void simulationLoaded()
    {
        // Disable these buttons
        view.getPropertiesButton().setEnabled(true);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(true);
        view.getLoadModelButton().setEnabled(true);
        ((BlinkerButton) view.getLoadModelButton()).setBlink(false);
    }

    /**
     * Indicate that a simulation has not been loaded.
     */
    @Override
    public void simulationUnloaded()
    {
        // Disable these buttons
        view.getLoadModelButton().setEnabled(true);
        ((BlinkerButton) view.getLoadModelButton()).setBlink(true);
        view.getPropertiesButton().setEnabled(false);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getSaveResultsButton().setEnabled(false);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(false);
        view.getClearResultsMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(false);
        simulationLoaded = false;
    }

    @Override
    public void animate(boolean animate)
    {
        this.animated = animate;
    }

    @Override
    public boolean isAnimated()
    {
        return animated;
    }
}
