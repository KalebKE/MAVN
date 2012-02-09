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
package mavn.state;

import java.util.ArrayList;
import mavn.view.MavnView;
import util.components.BlinkerButton;

/**
 * A class that maintains all of the state required for the simulations output.
 * This class is an implementation of the applications State Pattern.
 * @author Kaleb
 */
public class ResultsState implements OutputStateInterface, RngStateInterface
{

    private MavnView view;
    private boolean randomRng;
    private boolean caRng;
    private boolean cmwcRng;
    private boolean mtRng;
    private boolean xORRng;
    private boolean dartGunState;
    private boolean propertiesLoaded;
    private boolean simulationLoaded;
    private boolean run;
    private ArrayList<double[][]> hit;
    private ArrayList<double[][]> miss;

    /**
     * Initialize the state.
     * @param view the view the class manages the state for.
     */
    public ResultsState(MavnView view)
    {
        this.view = view;
        propertiesLoaded = false;
        hit = new ArrayList<double[][]>();
        miss = new ArrayList<double[][]>();

        randomRng = false;
        caRng = false;
        cmwcRng = false;
        mtRng = false;
        xORRng = false;
    }

    /**
     * Get the number and points of darts that hit a shape in the image
     * during the simulation.
     * @return the points of darts that hit a shape.
     */
    @Override
    public ArrayList<double[][]> getHit()
    {
        return hit;
    }

    /**
     * Get the number and points of darts that missed a shape in the image
     * during the simulation.
     * @return the points of darts that missed a shape.
     */
    @Override
    public ArrayList<double[][]> getMiss()
    {
        return miss;
    }

    /**
     * Check if the simulation has been run.
     * @return boolean indicating a simulation has been run.
     */
    @Override
    public boolean hasRun()
    {
        return run;
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
        run = false;
    }

    /**
     * Indicate that the simulation has been run.
     */
    @Override
    public void run()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(true);
        ((BlinkerButton) view.getRunButton()).setBlink(true);
        view.getViewResultsButton().setEnabled(true);
        ((BlinkerButton) view.getViewResultsButton()).setBlink(true);
        view.getSaveResultsButton().setEnabled(true);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(true);
        view.getClearResultsMatrixButton().setEnabled(true);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(true);
        run = true;
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
     * Set the points of darts that hit a shape in the image during the
     * simulation.
     * @param hit the points of darts that hit shapes.
     */
    @Override
    public void setHit(ArrayList<double[][]> hit)
    {
        this.hit = hit;
    }

    /**
     * Set the points of darts that missed shapes in the image during the simulation.
     * @param miss the points of darts that missed shapes.
     */
    @Override
    public void setMiss(ArrayList<double[][]> miss)
    {
        this.miss = miss;
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
    }

    /**
     * Indicate that a simulation has not been loaded.
     */
    @Override
    public void simulationUnloaded()
    {
        // Disable these buttons
        view.getPropertiesButton().setEnabled(false);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getViewResultsButton().setEnabled(false);
        ((BlinkerButton) view.getViewResultsButton()).setBlink(false);
        view.getSaveResultsButton().setEnabled(false);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(false);
        view.getClearResultsMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(false);
        simulationLoaded = false;
    }
}
