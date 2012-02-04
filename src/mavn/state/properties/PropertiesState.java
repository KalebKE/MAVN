/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.state.properties;

import javax.swing.SpinnerNumberModel;
import mavn.view.PropertiesFrame;

/**
 *
 * @author Kaleb
 */
public class PropertiesState implements PropertiesStateInterface
{

    private PropertiesFrame propertiesView;
    private SpinnerNumberModel seedModel;
    private SpinnerNumberModel dartModel;
    private boolean target;
    private boolean dart;
    private boolean randomSeed;
    private boolean uniformDistribution;
    private boolean normalDistribution;
    private boolean gridDistribution;

    public PropertiesState(PropertiesFrame propertiesView)
    {
        this.propertiesView = propertiesView;
        seedModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        dartModel = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);
        propertiesView.getSeedSpinner().setModel(seedModel);
        propertiesView.getNumDartsSpinner().setModel(dartModel);
    }

    @Override
    public SpinnerNumberModel getDartModel()
    {
        return dartModel;
    }

    @Override
    public SpinnerNumberModel getSeedModel()
    {
        return seedModel;
    }

    @Override
    public boolean isDart()
    {
        return dart;
    }

    @Override
    public boolean isGridDistribution()
    {
        return gridDistribution;
    }

    @Override
    public boolean isNormalDistribution()
    {
        return normalDistribution;
    }

    @Override
    public boolean isRandomSeed()
    {
        return randomSeed;
    }

    @Override
    public boolean isTarget()
    {
        return target;
    }

    @Override
    public boolean isUniformDistribution()
    {
        return uniformDistribution;
    }

    @Override
    public void useTargets()
    {
        propertiesView.getDartGunCheckBox().setSelected(false);
        propertiesView.getDartGunLabel().setEnabled(false);
        propertiesView.getGridRadio().setEnabled(false);
        propertiesView.getInputsLabel().setEnabled(false);
        propertiesView.getNormalRadio().setEnabled(false);
        propertiesView.getNumDartsLabel().setEnabled(false);
        propertiesView.getNumDartsSpinner().setEnabled(false);
        propertiesView.getRandomSeedRadio().setEnabled(false);
        propertiesView.getSeedLabel().setEnabled(false);
        propertiesView.getSeedSpinner().setEnabled(false);
        propertiesView.getSeedSpinnerLabel().setEnabled(false);
        propertiesView.getSpecifiedSeedRadio().setEnabled(false);
        propertiesView.getUniformRadio().setEnabled(false);

        target = true;
        dart = false;
    }

    @Override
    public void useDarts()
    {
        propertiesView.getTargetCheckBox().setSelected(false);
        propertiesView.getDartGunLabel().setEnabled(true);
        propertiesView.getGridRadio().setEnabled(true);
        propertiesView.getInputsLabel().setEnabled(true);
        propertiesView.getNormalRadio().setEnabled(true);
        propertiesView.getNumDartsLabel().setEnabled(true);
        propertiesView.getNumDartsSpinner().setEnabled(true);
        propertiesView.getRandomSeedRadio().setEnabled(true);
        propertiesView.getSeedLabel().setEnabled(true);
        propertiesView.getSpecifiedSeedRadio().setEnabled(true);
        propertiesView.getUniformRadio().setEnabled(true);

        target = false;
        dart = true;
    }

    @Override
    public void useSpecifiedSeed()
    {
        propertiesView.getSeedSpinner().setEnabled(true);
        propertiesView.getSeedSpinnerLabel().setEnabled(true);
        propertiesView.getRandomSeedRadio().setSelected(false);

        randomSeed = false;
    }

    @Override
    public void useRandomSeed()
    {
        propertiesView.getSeedSpinner().setEnabled(false);
        propertiesView.getSeedSpinnerLabel().setEnabled(false);
        propertiesView.getSpecifiedSeedRadio().setSelected(false);

        randomSeed = true;
    }

    @Override
    public void gridDistribution()
    {
        propertiesView.getUniformRadio().setSelected(false);
        propertiesView.getNormalRadio().setSelected(false);

        gridDistribution = true;
        normalDistribution = false;
        uniformDistribution = false;
    }

    @Override
    public void normalDistribution()
    {
        propertiesView.getUniformRadio().setSelected(false);
        propertiesView.getGridRadio().setSelected(false);

        gridDistribution = false;
        normalDistribution = true;
        uniformDistribution = false;
    }

    @Override
    public void uniformDistribution()
    {
        propertiesView.getNormalRadio().setSelected(false);
        propertiesView.getGridRadio().setSelected(false);

        gridDistribution = false;
        normalDistribution = false;
        uniformDistribution = true;
    }
}
