/*
RngStateInterface-- an interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

/**
 * An interface used to define a properties framework for the applications
 * State Pattern.
 * @author Kaleb
 */
public interface RngStateInterface
{

    /**
     * Check if the CaRng is the desired DartGun.
     * @return boolean indicating CaRng is desired.
     */
    public boolean isCaRng();

    /**
     * Check if the CmwcRng is the desired DartGun.
     * @return boolean indicating CmwcRng is desired.
     */
    public boolean isCmwcRng();

    /**
     * Check if the DartGun should be used for the simlulation.
     * @return boolean indicating DartGun is desired.
     */
    public boolean isDartGunState();

    /**
     * Check if the MtRNG is the desired DartGun.
     * @return boolean indicating MtRng is desired.
     */
    public boolean isMtRng();

    /**
     * Check if java.util.random RNG is the desired DartGun.
     * @return boolean indicating java.util.random RNG is desired.
     */
    public boolean isRandomRng();

    /**
     * Check if the xORRng is the desired DartGun.
     * @return boolean indicating xORRng is desired.
     */
    public boolean isxORRng();

    /**
     * Indicate that CaRng is the desired DartGun.
     * @param caRng boolean indicating that CaRng is the desired DartGun
     */
    public void setCaRng(boolean caRng);

    /**
     * Indicate that CmwcRng is the desired DartGun.
     * @param caRng boolean indicating that CmwcRng is the desired DartGun
     */
    public void setCmwcRng(boolean cmwcRng);

    /**
     * Indicate that the DartGun is desired.
     * @param cmwcRng boolean indicating that the DartGun is desired.
     */
    public void setDartGunState(boolean dartGunState);

    /**
     * Indicate that MtRng is the desired DartGun.
     * @param dartGunState boolean indicating that MtRng is the desired DartGun
     */
    public void setMtRng(boolean mtRng);

    /**
     * Indicate that java.util.random RNG is the desired DartGun.
     * @param mtRng boolean indicating that java.util.random RNG is the desired DartGun
     */
    public void setRandomRng(boolean randomRng);

    /**
     * Indicate that xORRng is the desired DartGun.
     * @param xORRng boolean indicating that xORRngis the desired DartGun
     */
    public void setxORRng(boolean xORRng);
}
