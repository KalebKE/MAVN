/*
PropertiesFrame -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

/*
 * ResultsFrame.java
 *
 * Created on Feb 3, 2012, 11:34:37 AM
 */
package mavn.simModel.properties.view;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import mavn.factory.AbstractSimulationFactory;
import mavn.globals.Globals;
import mavn.simModel.result.view.state.RngStateInterface;
import mavn.simModel.properties.state.PropertiesState;

/**
 * A special JFrame used to manage the properties state of the application.
 * @author Kaleb
 */
public class PropertiesFrame extends javax.swing.JFrame
{
    private AbstractSimulationFactory controller;

    /**
     * Initialize the state.
     * @param view the View this class is responsible for.
     */
    public PropertiesFrame(AbstractSimulationFactory controller)
    {
        initComponents();
        this.controller = controller;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        initToolTips();
    }

    /**
     * Get the DartGunCheckBox. This check box indicates if the
     * DartGun is desired.
     * @return the DartGunCheckBox.
     */
    public JCheckBox getDartGunCheckBox()
    {
        return dartGunCheckBox;
    }

    /**
     * Get the DartGunLabel.
     * @return the DartGunLabel.
     */
    public JLabel getDartGunLabel()
    {
        return dartGunLabel;
    }

    /**
     * Get the InputsLabel.
     * @return the InputsLabel.
     */
    public JLabel getInputsLabel()
    {
        return inputsLabel;
    }

    /**
     * Get the NumDartsLabel.
     * @return the NumDartsLabel.
     */
    public JLabel getNumDartsLabel()
    {
        return numDartsLabel;
    }

    /**
     * Get the NumDarts JSpinner. It allows the user to indicate
     * how many darts should be fired during the DartGun simulation.
     * @return the NumDarts JSpinner.
     */
    public JSpinner getNumDartsSpinner()
    {
        return numDartsSpinner;
    }

    /**
     * Get the PropertiesHeaderLabel.
     * @return the PropertiesHeaderLabel.
     */
    public JLabel getPropertiesHeaderLabel()
    {
        return propertiesHeaderLabel;
    }

    /**
     * Get the RandomSeed Randio Button. It allows the user to
     * indicate if they want to use a randomly generated seed for the RNG.
     * @return the RandomSeed Randio Button.
     */
    public JRadioButton getRandomSeedRadio()
    {
        return randomSeedRadio;
    }

    /**
     * Get the SeedLabel.
     * @return the SeedLabel.
     */
    public JLabel getSeedLabel()
    {
        return seedLabel;
    }

    /**
     * Get the Seed JSpinner. It allows the user to specify a seed for the RNG.
     * @return Get the Seed JSpinner.
     */
    public JSpinner getSeedSpinner()
    {
        return seedSpinner;
    }

    /**
     * Get the Seed JSpinner Label.
     * @return the Seed JSpinner JLabel.
     */
    public JLabel getSeedSpinnerLabel()
    {
        return seedSpinnerLabel;
    }

    /**
     * Get the SpecifiedSeed Radio Button. It allows the user to
     * indicate that they want to use a specified seed for the RNG.
     * @return the SpecifiedSeed Radio Button.
     */
    public JRadioButton getSpecifiedSeedRadio()
    {
        return specifiedSeedRadio;
    }

    /**
     * Get the Target JCheckBox. It allows the user to indicate that
     * they want to use the Target Model instead of the DartGun.
     * @return
     */
    public JCheckBox getTargetCheckBox()
    {
        return targetCheckBox;
    }

    /**
     * Get the Random Radio Button. It allows the user to indicate that
     * they want to use java.random.util as the DartGun's RNG.
     * @return the Random Radio Button.
     */
    public JRadioButton getRandomRadio()
    {
        return randomRadio;
    }

    /**
     * Get the CA RNG Radio Button. Indicates that the user wants to
     * use the CellularAutomatonRNG for the DartGun RNG.
     * @return the CA RNG Radio Button.
     */
    public JRadioButton getCaRngRadio()
    {
        return caRngRadio;
    }

    /**
     * Get the CMWC RNG Radio Button. It indicates that the user wants to
     * use the CMWC4096 RNG for the DartGun's RNG.
     * @return the CMWC RNG Radio Button.
     */
    public JRadioButton getCmwcRngRadio()
    {
        return cmwcRngRadio;
    }

    /**
     * Get the MT RNG Radio Button. It indicates that the user wants to use the
     * Mersenne Twister RNG for the DartGun's RNG.
     * @return the MT RNG Radio Button.
     */
    public JRadioButton getMTRngRadio()
    {
        return mtRngRadio;
    }

    /**
     * Get the XORSSHIFT RNG Radio Button. Inicates if the user
     * wants to use the XORSHIFT RNG for the DartGun.
     * @return the XORSSHIFT RNG Radio Button.
     */
    public JRadioButton getXORShiftRngRadio()
    {
        return xORShiftRngRadio;
    }

    private void initToolTips()
    {
        randomRadio.setToolTipText("<html>Use java.util.Random: For non-critical random<br>"
                + " numbers, e.g. adding some unpredictability to a game, <br>"
                + "it’s fine. It’s even pretty quick. Unfortunately, it’s not <br>"
                + "random enough – not by the standards required for more serious<br> "
                + "applications. Probably the best known test suite for random<br>"
                + " number generators is George Marsaglia’s Diehard Battery of <br>"
                + "Tests of Randomness. Diehard says that java.util.Random is<br>"
                + " not sufficiently random. It is fast, however.</html>");

        mtRngRadio.setToolTipText("<html>A Java port of the fast and reliable Mersenne<br> "
                + "Twister RNG originally developed by Makoto Matsumoto and Takuji<br> "
                + "Nishimura. It is faster than java.util.Random, does not have the<br> "
                + "same statistical flaws as that RNG and also has a long period<br> "
                + "(2^19937). The Mersenne Twister is an excellent general purpose RNG.</html>");

        xORShiftRngRadio.setToolTipText("<html>A Java implementation of the very fast<br>"
                + " PRNG described by George Marsaglia. It has a period of about 2^160,<br>"
                + " which although much shorter than the Mersenne Twister's, <br>"
                + "is still significantly longer than that of java.util.Random. <br>"
                + "This is the RNG to use when performance is the primary concern. <br>"
                + "It can be up to twice as fast as the Mersenne Twister.</html>");

        cmwcRngRadio.setToolTipText("<html>A Java implementation of a Complementary-Multiply-With-Carry <br>"
                + "(CMWC) RNG as described by George Marsaglia. It has an extremely <br>"
                + "long period (2^131104) and performance comparable to the Mersenne <br>"
                + "Twister (though the Mersenne Twister has the advantage of only requiring <br>"
                + "16 bytes of seed data rather than the 16 kilobytes required by the CMWC RNG).</html>");

        caRngRadio.setToolTipText("<html>A Java port of Tony Pasqualoni's fast Cellular <br>"
                + "Automaton RNG. It uses a 256-cell automaton to generate random values.</html>");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        randomRadio = new javax.swing.JRadioButton();
        mtRngRadio = new javax.swing.JRadioButton();
        xORShiftRngRadio = new javax.swing.JRadioButton();
        targetCheckBox = new javax.swing.JCheckBox();
        dartGunCheckBox = new javax.swing.JCheckBox();
        propertiesHeaderLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        inputsLabel = new javax.swing.JLabel();
        dartGunLabel = new javax.swing.JLabel();
        randomSeedRadio = new javax.swing.JRadioButton();
        specifiedSeedRadio = new javax.swing.JRadioButton();
        seedLabel = new javax.swing.JLabel();
        seedSpinner = new javax.swing.JSpinner();
        seedSpinnerLabel = new javax.swing.JLabel();
        numDartsLabel = new javax.swing.JLabel();
        numDartsSpinner = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        acceptButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        cmwcRngRadio = new javax.swing.JRadioButton();
        caRngRadio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        randomRadio.setSelected(true);
        randomRadio.setText("java.util.Random");
        randomRadio.setEnabled(false);
        randomRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomRadioActionPerformed(evt);
            }
        });

        mtRngRadio.setText("MersenneTwisterRNG");
        mtRngRadio.setEnabled(false);
        mtRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mtRngRadioActionPerformed(evt);
            }
        });

        xORShiftRngRadio.setText("XORShiftRNG");
        xORShiftRngRadio.setEnabled(false);
        xORShiftRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xORShiftRngRadioActionPerformed(evt);
            }
        });

        targetCheckBox.setSelected(true);
        targetCheckBox.setText("Use Target Matrix");
        targetCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetCheckBoxActionPerformed(evt);
            }
        });

        dartGunCheckBox.setText("Use Dart Gun");
        dartGunCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dartGunCheckBoxActionPerformed(evt);
            }
        });

        propertiesHeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        propertiesHeaderLabel.setText("Set MAVN Simulation Run Properties:");

        inputsLabel.setText("What inputs should the simulation use?");

        dartGunLabel.setText("What random number generator (Dart Gun) is desired?");
        dartGunLabel.setEnabled(false);

        randomSeedRadio.setSelected(true);
        randomSeedRadio.setText("Use Random Seed");
        randomSeedRadio.setEnabled(false);
        randomSeedRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomSeedRadioActionPerformed(evt);
            }
        });

        specifiedSeedRadio.setText("Use Specified Seed");
        specifiedSeedRadio.setEnabled(false);
        specifiedSeedRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specifiedSeedRadioActionPerformed(evt);
            }
        });

        seedLabel.setText("Do you want a seeded distribution?");
        seedLabel.setEnabled(false);

        seedSpinner.setEnabled(false);

        seedSpinnerLabel.setText("Desired Seed:");
        seedSpinnerLabel.setEnabled(false);

        numDartsLabel.setText("How many darts should be used?");
        numDartsLabel.setEnabled(false);

        numDartsSpinner.setEnabled(false);

        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        cmwcRngRadio.setText("CMWC4096RNG");
        cmwcRngRadio.setEnabled(false);
        cmwcRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmwcRngRadioActionPerformed(evt);
            }
        });

        caRngRadio.setText("CellularAutomatonRNG");
        caRngRadio.setEnabled(false);
        caRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caRngRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(propertiesHeaderLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(acceptButton)
                        .addGap(51, 51, 51)
                        .addComponent(cancelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(randomRadio)
                                    .addComponent(cmwcRngRadio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(caRngRadio)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(mtRngRadio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(xORShiftRngRadio)))
                                .addGap(41, 41, 41))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(inputsLabel)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dartGunCheckBox)
                                    .addComponent(targetCheckBox)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(seedSpinnerLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(seedSpinner))
                                        .addComponent(seedLabel, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(numDartsLabel))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(specifiedSeedRadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(randomSeedRadio))
                                        .addGap(169, 169, 169))
                                    .addComponent(numDartsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dartGunLabel))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {acceptButton, cancelButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(propertiesHeaderLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputsLabel)
                    .addComponent(targetCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dartGunCheckBox)
                .addGap(13, 13, 13)
                .addComponent(dartGunLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(randomRadio)
                    .addComponent(mtRngRadio)
                    .addComponent(xORShiftRngRadio))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmwcRngRadio)
                    .addComponent(caRngRadio))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seedLabel)
                    .addComponent(randomSeedRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(specifiedSeedRadio)
                    .addComponent(seedSpinnerLabel))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numDartsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numDartsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptButton)
                    .addComponent(cancelButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_formPropertyChange
    {//GEN-HEADEREND:event_formPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_formPropertyChange

    private void targetCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_targetCheckBoxActionPerformed
    {//GEN-HEADEREND:event_targetCheckBoxActionPerformed
        AbstractButton abstractButton = (AbstractButton) evt.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        if (selected)
        {
            this.controller.getPropertiesState().useTargets();
        }
    }//GEN-LAST:event_targetCheckBoxActionPerformed

    private void dartGunCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dartGunCheckBoxActionPerformed
    {//GEN-HEADEREND:event_dartGunCheckBoxActionPerformed
        AbstractButton abstractButton = (AbstractButton) evt.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        if (selected)
        {
            this.controller.getPropertiesState().useDarts();
        }
    }//GEN-LAST:event_dartGunCheckBoxActionPerformed

    private void randomRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_randomRadioActionPerformed
    {//GEN-HEADEREND:event_randomRadioActionPerformed
        if (randomRadio.isSelected())
        {
            this.controller.getPropertiesState().randomRng();
        }
    }//GEN-LAST:event_randomRadioActionPerformed

    private void mtRngRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mtRngRadioActionPerformed
    {//GEN-HEADEREND:event_mtRngRadioActionPerformed
        if (mtRngRadio.isSelected())
        {
            this.controller.getPropertiesState().mtRng();
        }
    }//GEN-LAST:event_mtRngRadioActionPerformed

    private void xORShiftRngRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_xORShiftRngRadioActionPerformed
    {//GEN-HEADEREND:event_xORShiftRngRadioActionPerformed
        if (xORShiftRngRadio.isSelected())
        {
            this.controller.getPropertiesState().xORRng();
        }
    }//GEN-LAST:event_xORShiftRngRadioActionPerformed

    private void randomSeedRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_randomSeedRadioActionPerformed
    {//GEN-HEADEREND:event_randomSeedRadioActionPerformed
        if (randomSeedRadio.isSelected())
        {
            this.controller.getPropertiesState().useRandomSeed();
        }
    }//GEN-LAST:event_randomSeedRadioActionPerformed

    private void specifiedSeedRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_specifiedSeedRadioActionPerformed
    {//GEN-HEADEREND:event_specifiedSeedRadioActionPerformed
        if (specifiedSeedRadio.isSelected())
        {
            this.controller.getPropertiesState().useSpecifiedSeed();
        }
    }//GEN-LAST:event_specifiedSeedRadioActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_acceptButtonActionPerformed
    {//GEN-HEADEREND:event_acceptButtonActionPerformed
        if (this.controller.getPropertiesState().isDart())
        {
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setDartGunState(true);
            Globals.SEED = ((Double) this.controller.getPropertiesState().getSeedModel().getValue());
            double darts = (Double) this.controller.getPropertiesState().getDartModel().getValue();
            Globals.NUM_DARTS = (int) darts;
        }

        if (this.controller.getPropertiesState().isTarget())
        {
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setDartGunState(false);
        }

        if (this.controller.getPropertiesState().isCaRng())
        {
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCaRng(true);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCmwcRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setMtRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setRandomRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setxORRng(false);
        }

        if (this.controller.getPropertiesState().isCmwcRng())
        {
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCaRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCmwcRng(true);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setMtRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setRandomRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setxORRng(false);
        }

        if (this.controller.getPropertiesState().isMtRng())
        {
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCaRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCmwcRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setMtRng(true);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setRandomRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setxORRng(false);
        }

        if (this.controller.getPropertiesState().isRandomRng())
        {
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCaRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCmwcRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setMtRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setRandomRng(true);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setxORRng(false);
        }

        if (this.controller.getPropertiesState().isXORRng())
        {
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCaRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setCmwcRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setMtRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setRandomRng(false);
            ((RngStateInterface) controller.getModelResultStates().get(Globals.RESULTS_STATE)).setxORRng(true);
        }

        controller.getModelResultStates().get(Globals.RESULTS_STATE).propertiesLoaded();
        this.dispose();
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
    {//GEN-HEADEREND:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void cmwcRngRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmwcRngRadioActionPerformed
    {//GEN-HEADEREND:event_cmwcRngRadioActionPerformed
        if (cmwcRngRadio.isSelected())
        {
            this.controller.getPropertiesState().cmwcRng();
        }
    }//GEN-LAST:event_cmwcRngRadioActionPerformed

    private void caRngRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_caRngRadioActionPerformed
    {//GEN-HEADEREND:event_caRngRadioActionPerformed
        if (caRngRadio.isSelected())
        {
            this.controller.getPropertiesState().caRng();
        }
    }//GEN-LAST:event_caRngRadioActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JRadioButton caRngRadio;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton cmwcRngRadio;
    private javax.swing.JCheckBox dartGunCheckBox;
    private javax.swing.JLabel dartGunLabel;
    private javax.swing.JLabel inputsLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton mtRngRadio;
    private javax.swing.JLabel numDartsLabel;
    private javax.swing.JSpinner numDartsSpinner;
    private javax.swing.JLabel propertiesHeaderLabel;
    private javax.swing.JRadioButton randomRadio;
    private javax.swing.JRadioButton randomSeedRadio;
    private javax.swing.JLabel seedLabel;
    private javax.swing.JSpinner seedSpinner;
    private javax.swing.JLabel seedSpinnerLabel;
    private javax.swing.JRadioButton specifiedSeedRadio;
    private javax.swing.JCheckBox targetCheckBox;
    private javax.swing.JRadioButton xORShiftRngRadio;
    // End of variables declaration//GEN-END:variables
}
