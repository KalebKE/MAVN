/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.state.properties.state;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Kaleb
 */
public interface PropertiesStateInterface
{

    public SpinnerNumberModel getDartModel();

    public SpinnerNumberModel getSeedModel();

    public void caRng();

    public void cmwcRng();

    public boolean isDart();

    public boolean isCaRng();

    public boolean isCmwcRng();

    public boolean isMtRng();

    public boolean isRandomRng();

    public boolean isRandomSeed();

    public boolean isTarget();

    public boolean isXORRng();

    public void mtRng();

    public void randomRng();

    public void xORRng();

    public void useTargets();

    public void useDarts();

    public void useSpecifiedSeed();

    public void useRandomSeed();
}
