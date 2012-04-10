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
package mavn.algorithm.properties.view.state;

import javax.swing.SpinnerNumberModel;
import mavn.algorithm.properties.view.SimulationPropertiesFrame;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;

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
    private SimulationPropertiesFrame propertiesFrameview;
    private SpinnerNumberModel seedSpinnerModel;
    private SpinnerNumberModel pointSpinnerModel;
    private SpinnerNumberModel gridSpinnerModel;
    private SimulationViewInputStateInterface outputViewState;

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
    public void onCaRng()
    {
        propertiesFrameview.getCmwcRngRadio().setSelected(false);
        propertiesFrameview.getMTRngRadio().setSelected(false);
        propertiesFrameview.getXORShiftRngRadio().setSelected(false);
        propertiesFrameview.getRandomRadio().setSelected(false);

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
    public void onCmwcRng()
    {
        propertiesFrameview.getXORShiftRngRadio().setSelected(false);
        propertiesFrameview.getMTRngRadio().setSelected(false);
        propertiesFrameview.getRandomRadio().setSelected(false);
        propertiesFrameview.getCaRngRadio().setSelected(false);

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
    public void onMtRng()
    {
        propertiesFrameview.getCmwcRngRadio().setSelected(false);
        propertiesFrameview.getXORShiftRngRadio().setSelected(false);
        propertiesFrameview.getRandomRadio().setSelected(false);
        propertiesFrameview.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = false;
        cmwcRng = false;
        mtRng = true;
    }

    @Override
    public void setOutputViewState(SimulationViewInputStateInterface outputViewState)
    {
        this.outputViewState = outputViewState;
    }

    public void setView(SimulationPropertiesFrame propertiesView)
    {
        this.propertiesFrameview = propertiesView;
        seedSpinnerModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        pointSpinnerModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        gridSpinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        propertiesFrameview.getSeedSpinner().setModel(seedSpinnerModel);
        propertiesFrameview.getNumPointsSpinner().setModel(pointSpinnerModel);
        propertiesFrameview.getResolutionSpinner().setModel(gridSpinnerModel);
        randomRng = true;
    }

    /**
     * Indicate that java.util.random RNG is desired.
     */
    @Override
    public void randomRng()
    {
        propertiesFrameview.getCmwcRngRadio().setSelected(false);
        propertiesFrameview.getMTRngRadio().setSelected(false);
        propertiesFrameview.getXORShiftRngRadio().setSelected(false);
        propertiesFrameview.getCaRngRadio().setSelected(false);

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
    public void onDiagnosticSimulation()
    {
        propertiesFrameview.getTargetCheckBox().setSelected(true);
        propertiesFrameview.getUseUniformPointGeneratorCheckBox().setSelected(false);
        propertiesFrameview.getGridPointCheckBox().setSelected(false);
        propertiesFrameview.getMTRngRadio().setEnabled(false);
        propertiesFrameview.getRandomRadio().setEnabled(false);
        propertiesFrameview.getNumPointsLabel().setEnabled(false);
        propertiesFrameview.getNumPointsSpinner().setEnabled(false);
        propertiesFrameview.getRandomSeedRadio().setEnabled(false);
        propertiesFrameview.getSeedSpinner().setEnabled(false);
        propertiesFrameview.getSeedSpinnerLabel().setEnabled(false);
        propertiesFrameview.getSpecifiedSeedRadio().setEnabled(false);
        propertiesFrameview.getXORShiftRngRadio().setEnabled(false);
        propertiesFrameview.getCmwcRngRadio().setEnabled(false);
        propertiesFrameview.getCaRngRadio().setEnabled(false);

        propertiesFrameview.getResolutionSpinner().setEnabled(false);
        propertiesFrameview.getResolutionLabel().setEnabled(false);

        target = true;
        point = false;
        grid = false;
    }

    /**
     * Indicate that the DartGun should be used instead of the Target Model.
     */
    @Override
    public void onMonteCarloSimulation()
    {
        propertiesFrameview.getUseUniformPointGeneratorCheckBox().setSelected(true);
        propertiesFrameview.getTargetCheckBox().setSelected(false);
        propertiesFrameview.getGridPointCheckBox().setSelected(false);
        propertiesFrameview.getMTRngRadio().setEnabled(true);
        propertiesFrameview.getRandomRadio().setEnabled(true);
        propertiesFrameview.getNumPointsLabel().setEnabled(true);
        propertiesFrameview.getNumPointsSpinner().setEnabled(true);
        propertiesFrameview.getRandomSeedRadio().setEnabled(true);
        propertiesFrameview.getSeedSpinner().setEnabled(false);
        propertiesFrameview.getSeedSpinnerLabel().setEnabled(false);
        propertiesFrameview.getSpecifiedSeedRadio().setEnabled(true);
        propertiesFrameview.getXORShiftRngRadio().setEnabled(true);
        propertiesFrameview.getCmwcRngRadio().setEnabled(true);
        propertiesFrameview.getCaRngRadio().setEnabled(true);

        propertiesFrameview.getResolutionSpinner().setEnabled(false);
        propertiesFrameview.getResolutionLabel().setEnabled(false);

        target = false;
        point = true;
        grid = false;
    }

    /**
     * Indicate that the RNG should use a specific seed.
     */
    @Override
    public void onSpecifiedRngSeed()
    {
        propertiesFrameview.getSeedSpinner().setEnabled(true);
        propertiesFrameview.getSeedSpinnerLabel().setEnabled(true);
        propertiesFrameview.getRandomSeedRadio().setSelected(false);

        randomSeed = false;
    }

    /**
     * Indicate that the RNG should use a random seed.
     */
    @Override
    public void onRandomRngSeed()
    {
        propertiesFrameview.getSeedSpinner().setEnabled(false);
        propertiesFrameview.getSeedSpinnerLabel().setEnabled(false);
        propertiesFrameview.getSpecifiedSeedRadio().setSelected(false);

        randomSeed = true;
    }

    /**
     * Inidcate that XORSHIFT RNG should be used.
     */
    @Override
    public void onXORRng()
    {
        propertiesFrameview.getCmwcRngRadio().setSelected(false);
        propertiesFrameview.getMTRngRadio().setSelected(false);
        propertiesFrameview.getRandomRadio().setSelected(false);
        propertiesFrameview.getCaRngRadio().setSelected(false);

        caRng = false;
        randomRng = false;
        xORRng = true;
        cmwcRng = false;
        mtRng = false;
    }

    @Override
    public void onPixelGridSimulation()
    {
        propertiesFrameview.getResolutionSpinner().setEnabled(true);
        propertiesFrameview.getResolutionLabel().setEnabled(true);

        propertiesFrameview.getGridPointCheckBox().setSelected(true);
        propertiesFrameview.getUseUniformPointGeneratorCheckBox().setSelected(false);
        propertiesFrameview.getTargetCheckBox().setSelected(false);
        propertiesFrameview.getMTRngRadio().setEnabled(false);
        propertiesFrameview.getRandomRadio().setEnabled(false);
        propertiesFrameview.getNumPointsLabel().setEnabled(false);
        propertiesFrameview.getNumPointsSpinner().setEnabled(false);
        propertiesFrameview.getRandomSeedRadio().setEnabled(false);
        propertiesFrameview.getSeedSpinner().setEnabled(false);
        propertiesFrameview.getSeedSpinnerLabel().setEnabled(false);
        propertiesFrameview.getSpecifiedSeedRadio().setEnabled(false);
        propertiesFrameview.getXORShiftRngRadio().setEnabled(false);
        propertiesFrameview.getCmwcRngRadio().setEnabled(false);
        propertiesFrameview.getCaRngRadio().setEnabled(false);

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
