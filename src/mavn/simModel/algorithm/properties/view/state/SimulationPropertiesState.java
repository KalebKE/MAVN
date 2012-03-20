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
    private boolean dart;
    private boolean mtRng;
    private boolean target;
    private boolean randomSeed;
    private boolean randomRng;
    private boolean xORRng;
    private SimulationPropertiesFrame propertiesView;
    private SpinnerNumberModel seedModel;
    private SpinnerNumberModel dartModel;

    /**
     * Initialize the state.
     * @param propertiesView the View this class is reponsible for.
     */
    public SimulationPropertiesState(SimulationPropertiesFrame propertiesView)
    {
        this.propertiesView = propertiesView;
        seedModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        dartModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        propertiesView.getSeedSpinner().setModel(seedModel);
        propertiesView.getNumPointsSpinner().setModel(dartModel);
        randomRng = true;
        this.useTargets();
    }

    /**
     * Use the CA RNG.
     */
    @Override
    public void caRng()
    {
        propertiesView.getCmwcRngRadio().setSelected(false);
        propertiesView.getMTRngRadio().setSelected(false);
        propertiesView.getXORShiftRngRadio().setSelected(false);
        propertiesView.getRandomRadio().setSelected(false);

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
        propertiesView.getXORShiftRngRadio().setSelected(false);
        propertiesView.getMTRngRadio().setSelected(false);
        propertiesView.getRandomRadio().setSelected(false);
        propertiesView.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = false;
        cmwcRng = true;
        mtRng = false;
    }

    /**
     * Get the Dart Model.
     * @return SpinnerNumberModel representing the number of darts to be fired
     * during the simulation.
     */
    @Override
    public SpinnerNumberModel getDartModel()
    {
        return dartModel;
    }

    /**
     * Get the Seed Model.
     * @return SpinnerNumberModel representing the random number seed that should
     * be used during the simulation.
     */
    @Override
    public SpinnerNumberModel getSeedModel()
    {
        return seedModel;
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
    public boolean isDart()
    {
        return dart;
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
    public boolean isTarget()
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
        propertiesView.getCmwcRngRadio().setSelected(false);
        propertiesView.getXORShiftRngRadio().setSelected(false);
        propertiesView.getRandomRadio().setSelected(false);
        propertiesView.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = false;
        cmwcRng = false;
        mtRng = true;
    }

    /**
     * Indicate that java.util.random RNG is desired.
     */
    @Override
    public void randomRng()
    {
        propertiesView.getCmwcRngRadio().setSelected(false);
        propertiesView.getMTRngRadio().setSelected(false);
        propertiesView.getXORShiftRngRadio().setSelected(false);
        propertiesView.getCaRngRadio().setSelected(false);

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
    public void useTargets()
    {
        propertiesView.getUseUniformPointGeneratorCheckBox().setSelected(false);
        propertiesView.getPointGeneratorLabel().setEnabled(false);
        propertiesView.getMTRngRadio().setEnabled(false);
        propertiesView.getInputsLabel().setEnabled(false);
        propertiesView.getRandomRadio().setEnabled(false);
        propertiesView.getNumPointsLabel().setEnabled(false);
        propertiesView.getNumPointsSpinner().setEnabled(false);
        propertiesView.getRandomSeedRadio().setEnabled(false);
        propertiesView.getSeedLabel().setEnabled(false);
        propertiesView.getSeedSpinner().setEnabled(false);
        propertiesView.getSeedSpinnerLabel().setEnabled(false);
        propertiesView.getSpecifiedSeedRadio().setEnabled(false);
        propertiesView.getXORShiftRngRadio().setEnabled(false);
        propertiesView.getCmwcRngRadio().setEnabled(false);
        propertiesView.getCaRngRadio().setEnabled(false);

        target = true;
        dart = false;
    }

    /**
     * Indicate that the DartGun should be used instead of the Target Model.
     */
    @Override
    public void useDarts()
    {
        propertiesView.getTargetCheckBox().setSelected(false);
        propertiesView.getPointGeneratorLabel().setEnabled(true);
        propertiesView.getMTRngRadio().setEnabled(true);
        propertiesView.getInputsLabel().setEnabled(true);
        propertiesView.getRandomRadio().setEnabled(true);
        propertiesView.getNumPointsLabel().setEnabled(true);
        propertiesView.getNumPointsSpinner().setEnabled(true);
        propertiesView.getRandomSeedRadio().setEnabled(true);
        propertiesView.getSeedLabel().setEnabled(true);
        propertiesView.getSeedSpinner().setEnabled(false);
        propertiesView.getSeedSpinnerLabel().setEnabled(false);
        propertiesView.getSpecifiedSeedRadio().setEnabled(true);
        propertiesView.getXORShiftRngRadio().setEnabled(true);
        propertiesView.getCmwcRngRadio().setEnabled(true);
        propertiesView.getCaRngRadio().setEnabled(true);

        target = false;
        dart = true;
    }

    /**
     * Indicate that the RNG should use a specific seed.
     */
    @Override
    public void useSpecifiedSeed()
    {
        propertiesView.getSeedSpinner().setEnabled(true);
        propertiesView.getSeedSpinnerLabel().setEnabled(true);
        propertiesView.getRandomSeedRadio().setSelected(false);

        randomSeed = false;
    }

    /**
     * Indicate that the RNG should use a random seed.
     */
    @Override
    public void useRandomSeed()
    {
        propertiesView.getSeedSpinner().setEnabled(false);
        propertiesView.getSeedSpinnerLabel().setEnabled(false);
        propertiesView.getSpecifiedSeedRadio().setSelected(false);

        randomSeed = true;
    }

    /**
     * Inidcate that XORSHIFT RNG should be used.
     */
    @Override
    public void xORRng()
    {
        propertiesView.getCmwcRngRadio().setSelected(false);
        propertiesView.getMTRngRadio().setSelected(false);
        propertiesView.getRandomRadio().setSelected(false);
        propertiesView.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = true;
        cmwcRng = false;
        mtRng = false;
    }
}
