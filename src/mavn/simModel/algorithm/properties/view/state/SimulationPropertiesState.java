/*
PropertiesState -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.algorithm.properties.view.state;

import javax.swing.SpinnerNumberModel;
import mavn.simModel.algorithm.properties.view.SimulationPropertiesFrame;

/**
 * A implentation of the applications State pattern. This class is responsible
 * for managing the state of the applications properties. It helps make the
 * GUI more intuitive and helps other classes in the application decide what to do.
 * @author Kaleb
 */
public class SimulationPropertiesState implements SimulationPropertiesStateInterface
{
    private boolean caRng;
    private boolean cmwcRng;
    private boolean point;
    private boolean grid;
    private boolean mtRng;
    private boolean target;
    private boolean randomSeed;
    private boolean randomRng;
    private boolean xORRng;
    private SimulationPropertiesFrame view;
    private SpinnerNumberModel seedSpinnerModel;
    private SpinnerNumberModel pointSpinnerModel;
    private SpinnerNumberModel gridSpinnerModel;

    /**
     * Initialize the state.
     * @param propertiesView the View this class is reponsible for.
     */
    public SimulationPropertiesState()
    {
        super();
    }

    /**
     * Use the CA RNG.
     */
    @Override
    public void caRng()
    {
        view.getCmwcRngRadio().setSelected(false);
        view.getMTRngRadio().setSelected(false);
        view.getXORShiftRngRadio().setSelected(false);
        view.getRandomRadio().setSelected(false);

        caRng = true;
        randomRng = false;
        xORRng = false;
        cmwcRng = false;
        mtRng = false;
    }

    /**
     * Use the CMWC RNG.
     */
    @Override
    public void cmwcRng()
    {
        view.getXORShiftRngRadio().setSelected(false);
        view.getMTRngRadio().setSelected(false);
        view.getRandomRadio().setSelected(false);
        view.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = false;
        cmwcRng = true;
        mtRng = false;
    }

    @Override
    public SpinnerNumberModel getGridSpinnerModel()
    {
        return gridSpinnerModel;
    }
    
    /**
     * Get the Dart Model.
     * @return SpinnerNumberModel representing the number of darts to be fired
     * during the simulation.
     */
    @Override
    public SpinnerNumberModel getPointGeneratorModel()
    {
        return pointSpinnerModel;
    }

    /**
     * Get the Seed Model.
     * @return SpinnerNumberModel representing the random number seed that should
     * be used during the simulation.
     */
    @Override
    public SpinnerNumberModel getSeedModel()
    {
        return seedSpinnerModel;
    }

    /**
     * Check if CA RNG is desired.
     * @return boolean indicating CA RNG is desired.
     */
    @Override
    public boolean isCaRng()
    {
        return caRng;
    }

    /**
     * Check if CMWC RNG is desired.
     * @return boolean indicating CMWC RNG  is desired.
     */
    @Override
    public boolean isCmwcRng()
    {
        return cmwcRng;
    }

    /**
     * Check to see if the DartGun is desired.
     * @return boolean indicating if a DartGun is desired.
     */
    @Override
    public boolean isPointGeneratedModel()
    {
        return point;
    }

    /**
     * Check if Mt Rng is desired.
     * @return boolean indicating Mt Rng is desired.
     */
    @Override
    public boolean isMtRng()
    {
        return mtRng;
    }

    /**
     * Check if java.util.random RNG is desired.
     * @return boolean indicating java.util.random RNG is desired.
     */
    @Override
    public boolean isRandomRng()
    {
        return randomRng;
    }

    /**
     * Check if a random seed is desired.
     * @return boolean indicating if a random seed is desired.
     */
    @Override
    public boolean isRandomSeed()
    {
        return randomSeed;
    }

    /**
     * Check to see if a single dart target is desired.
     * @return boolean indicating if a single dart target is desired.
     */
    @Override
    public boolean isTargetModel()
    {
        return target;
    }

    /**
     * Check if XORRng is desired.
     * @return boolean indicating XORRng is desired.
     */
    @Override
    public boolean isXORRng()
    {
        return xORRng;
    }

    /**
     * Indicate that mtRNG is desired.
     */
    @Override
    public void mtRng()
    {
        view.getCmwcRngRadio().setSelected(false);
        view.getXORShiftRngRadio().setSelected(false);
        view.getRandomRadio().setSelected(false);
        view.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = false;
        cmwcRng = false;
        mtRng = true;
    }

    public void setView(SimulationPropertiesFrame propertiesView)
    {
        this.view = propertiesView;
        seedSpinnerModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        pointSpinnerModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        gridSpinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        view.getSeedSpinner().setModel(seedSpinnerModel);
        view.getNumPointsSpinner().setModel(pointSpinnerModel);
        view.getResolutionSpinner().setModel(gridSpinnerModel);
        randomRng = true;
        this.usePointGeneratorModel();
    }

    /**
     * Indicate that java.util.random RNG is desired.
     */
    @Override
    public void randomRng()
    {
        view.getCmwcRngRadio().setSelected(false);
        view.getMTRngRadio().setSelected(false);
        view.getXORShiftRngRadio().setSelected(false);
        view.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = true;
        xORRng = false;
        mtRng = false;
    }

    /**
     * Indicate that the Target Model should be used instead of the
     * DartGun.
     */
    @Override
    public void useTargetModel()
    {
        view.getTargetCheckBox().setSelected(true);
        view.getUseUniformPointGeneratorCheckBox().setSelected(false);
        view.getGridPointCheckBox().setSelected(false);
        view.getMTRngRadio().setEnabled(false);
        view.getRandomRadio().setEnabled(false);
        view.getNumPointsLabel().setEnabled(false);
        view.getNumPointsSpinner().setEnabled(false);
        view.getRandomSeedRadio().setEnabled(false);
        view.getSeedSpinner().setEnabled(false);
        view.getSeedSpinnerLabel().setEnabled(false);
        view.getSpecifiedSeedRadio().setEnabled(false);
        view.getXORShiftRngRadio().setEnabled(false);
        view.getCmwcRngRadio().setEnabled(false);
        view.getCaRngRadio().setEnabled(false);

        view.getResolutionSpinner().setEnabled(false);
        view.getResolutionLabel().setEnabled(false);

        target = true;
        point = false;
        grid = false;
    }

    /**
     * Indicate that the DartGun should be used instead of the Target Model.
     */
    @Override
    public void usePointGeneratorModel()
    {
        view.getUseUniformPointGeneratorCheckBox().setSelected(true);
        view.getTargetCheckBox().setSelected(false);
        view.getGridPointCheckBox().setSelected(false);
        view.getMTRngRadio().setEnabled(true);
        view.getRandomRadio().setEnabled(true);
        view.getNumPointsLabel().setEnabled(true);
        view.getNumPointsSpinner().setEnabled(true);
        view.getRandomSeedRadio().setEnabled(true);
        view.getSeedSpinner().setEnabled(false);
        view.getSeedSpinnerLabel().setEnabled(false);
        view.getSpecifiedSeedRadio().setEnabled(true);
        view.getXORShiftRngRadio().setEnabled(true);
        view.getCmwcRngRadio().setEnabled(true);
        view.getCaRngRadio().setEnabled(true);

        view.getResolutionSpinner().setEnabled(false);
        view.getResolutionLabel().setEnabled(false);

        target = false;
        point = true;
        grid = false;
    }

    /**
     * Indicate that the RNG should use a specific seed.
     */
    @Override
    public void useSpecifiedSeed()
    {
        view.getSeedSpinner().setEnabled(true);
        view.getSeedSpinnerLabel().setEnabled(true);
        view.getRandomSeedRadio().setSelected(false);

        randomSeed = false;
    }

    /**
     * Indicate that the RNG should use a random seed.
     */
    @Override
    public void useRandomSeed()
    {
        view.getSeedSpinner().setEnabled(false);
        view.getSeedSpinnerLabel().setEnabled(false);
        view.getSpecifiedSeedRadio().setSelected(false);

        randomSeed = true;
    }

    /**
     * Inidcate that XORSHIFT RNG should be used.
     */
    @Override
    public void xORRng()
    {
        view.getCmwcRngRadio().setSelected(false);
        view.getMTRngRadio().setSelected(false);
        view.getRandomRadio().setSelected(false);
        view.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = true;
        cmwcRng = false;
        mtRng = false;
    }

    @Override
    public void useGridGeneratedModel()
    {
        view.getResolutionSpinner().setEnabled(true);
        view.getResolutionLabel().setEnabled(true);

        view.getGridPointCheckBox().setSelected(true);
        view.getUseUniformPointGeneratorCheckBox().setSelected(false);
        view.getTargetCheckBox().setSelected(false);
        view.getMTRngRadio().setEnabled(false);
        view.getRandomRadio().setEnabled(false);
        view.getNumPointsLabel().setEnabled(false);
        view.getNumPointsSpinner().setEnabled(false);
        view.getRandomSeedRadio().setEnabled(false);
        view.getSeedSpinner().setEnabled(false);
        view.getSeedSpinnerLabel().setEnabled(false);
        view.getSpecifiedSeedRadio().setEnabled(false);
        view.getXORShiftRngRadio().setEnabled(false);
        view.getCmwcRngRadio().setEnabled(false);
        view.getCaRngRadio().setEnabled(false);

        target = false;
        point = false;
        grid = true;
    }

    @Override
    public boolean isGridGeneratedModel()
    {
        return grid;
    }
}
