/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.state.properties.state;

import javax.swing.SpinnerNumberModel;
import mavn.state.properties.view.PropertiesFrame;

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
    private boolean xORRng;
    private boolean randomRng;
    private boolean caRng;
    private boolean cmwcRng;
    private boolean mtRng;

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
    public boolean isCaRng()
    {
        return caRng;
    }

    @Override
    public boolean isCmwcRng()
    {
        return cmwcRng;
    }

    @Override
    public boolean isMtRng()
    {
        return mtRng;
    }

    @Override
    public boolean isRandomRng()
    {
        return randomRng;
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
    public boolean isXORRng()
    {
        return xORRng;
    }

    @Override
    public void useTargets()
    {
        propertiesView.getDartGunCheckBox().setSelected(false);
        propertiesView.getDartGunLabel().setEnabled(false);
        propertiesView.getMTRngRadio().setEnabled(false);
        propertiesView.getInputsLabel().setEnabled(false);
        propertiesView.getRandomRadio().setEnabled(false);
        propertiesView.getNumDartsLabel().setEnabled(false);
        propertiesView.getNumDartsSpinner().setEnabled(false);
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

    @Override
    public void useDarts()
    {
        propertiesView.getTargetCheckBox().setSelected(false);
        propertiesView.getDartGunLabel().setEnabled(true);
        propertiesView.getMTRngRadio().setEnabled(true);
        propertiesView.getInputsLabel().setEnabled(true);
        propertiesView.getRandomRadio().setEnabled(true);
        propertiesView.getNumDartsLabel().setEnabled(true);
        propertiesView.getNumDartsSpinner().setEnabled(true);
        propertiesView.getRandomSeedRadio().setEnabled(true);
        propertiesView.getSeedLabel().setEnabled(true);
        propertiesView.getSeedSpinner().setEnabled(true);
        propertiesView.getSeedSpinnerLabel().setEnabled(true);
        propertiesView.getSpecifiedSeedRadio().setEnabled(true);
        propertiesView.getXORShiftRngRadio().setEnabled(true);
        propertiesView.getCmwcRngRadio().setEnabled(true);
        propertiesView.getCaRngRadio().setEnabled(true);

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
}
