/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.state.properties;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Kaleb
 */
public interface PropertiesStateInterface
{

    public SpinnerNumberModel getDartModel();

    public SpinnerNumberModel getSeedModel();

    public void gridDistribution();

    public boolean isDart();

    public boolean isGridDistribution();

    public boolean isNormalDistribution();

    public boolean isRandomSeed();

    public boolean isTarget();

    public boolean isUniformDistribution();

    public void normalDistribution();

    public void uniformDistribution();

    public void useTargets();

    public void useDarts();

    public void useSpecifiedSeed();

    public void useRandomSeed();
}
