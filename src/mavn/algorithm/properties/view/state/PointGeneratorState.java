/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.algorithm.properties.view.state;

/**
 *
 * @author Kaleb
 */
public class PointGeneratorState implements PointGeneratorStateInterface
{

    private boolean randomRng;
    private boolean caRng;
    private boolean cmwcRng;
    private boolean mtRng;
    private boolean xORRng;
    private boolean dartGunState;

    public PointGeneratorState()
    {
        randomRng = false;
        caRng = false;
        cmwcRng = false;
        mtRng = false;
        xORRng = false;
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
     * Check to see if the java.util.random RNG is desired.
     * @return boolean indicating java.util.random RNG is desired.
     */
    @Override
    public boolean isRandomRng()
    {
        return randomRng;
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
}
