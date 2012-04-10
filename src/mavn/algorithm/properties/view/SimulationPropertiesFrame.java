/*
PropertiesFrame --
A class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.properties.view;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import mavn.globals.Globals;
import mavn.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.algorithm.properties.view.state.PointGeneratorStateInterface;
import mavn.simulation.view.state.input.SimulationViewInputStateInterface;

/**
 * A special JFrame used to manage the Simulation Properties State of the
 * MAVN application.
 * @author Kaleb
 */
public class SimulationPropertiesFrame extends javax.swing.JFrame
{

    private SimulationViewInputStateInterface outputState;
    private PointGeneratorStateInterface pointGeneratorState;
    private SimulationPropertiesStateInterface simulationState;

    /**
     * Initialize the state.
     * @param view the View this class is responsible for.
     */
    public SimulationPropertiesFrame(
            PointGeneratorStateInterface pointGeneratorState,
            SimulationPropertiesStateInterface simulationState)
    {
        initComponents();
        this.simulationState = simulationState;
        this.pointGeneratorState = pointGeneratorState;
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        initToolTips();
    }

    public JLabel getResolutionLabel()
    {
        return resolutionLabel;
    }

    public JSpinner getResolutionSpinner()
    {
        return resolutionSpinner;
    }

    /**
     * Get the useUniformPointGeneratorCheckBox. This check box indicates if the
     * Uniformly Distributed Multiple Point Simulation is desired. If this
     * check box is selected, the user can select from a number of different
     * Uniform Point Generators to use with the simulation.
     * @return the useUniformPointGeneratorCheckBox.
     */
    public JCheckBox getUseUniformPointGeneratorCheckBox()
    {
        return useUniformPointGeneratorCheckBox;
    }
    
    /**
     * Get the NumPointsLabel.
     * @return the NumPointsLabel.
     */
    public JLabel getNumPointsLabel()
    {
        return numPointsLabel;
    }

    /**
     * Get the NumPoints JSpinner. It allows the user to indicate
     * how many Points should be fired during the Multiple Point Simulation.
     * @return the NumDarts JSpinner.
     */
    public JSpinner getNumPointsSpinner()
    {
        return numPointsSpinner;
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

    public JCheckBox getGridPointCheckBox()
    {
        return gridPointCheckBox;
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

    public void setOutputState(SimulationViewInputStateInterface outputState)
    {
        this.outputState = outputState;
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
        propertiesHeaderLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        targetCheckBox = new javax.swing.JCheckBox();
        useUniformPointGeneratorCheckBox = new javax.swing.JCheckBox();
        gridPointCheckBox = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        xORShiftRngRadio = new javax.swing.JRadioButton();
        caRngRadio = new javax.swing.JRadioButton();
        randomRadio = new javax.swing.JRadioButton();
        cmwcRngRadio = new javax.swing.JRadioButton();
        mtRngRadio = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        randomSeedRadio = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        seedSpinner = new javax.swing.JSpinner();
        seedSpinnerLabel = new javax.swing.JLabel();
        specifiedSeedRadio = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        numPointsLabel = new javax.swing.JLabel();
        numPointsSpinner = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        acceptButton = new javax.swing.JButton();
        resolutionPanel = new javax.swing.JPanel();
        resolutionLabel = new javax.swing.JLabel();
        resolutionSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        propertiesHeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        propertiesHeaderLabel.setForeground(new java.awt.Color(0, 102, 204));
        propertiesHeaderLabel.setText("MAVN Simulation Properties:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Simulation Model", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        targetCheckBox.setText("Single Point (Test Point)");
        targetCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetCheckBoxActionPerformed(evt);
            }
        });

        useUniformPointGeneratorCheckBox.setSelected(true);
        useUniformPointGeneratorCheckBox.setText("Random Point (Monte Carlo)");
        useUniformPointGeneratorCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useUniformPointGeneratorCheckBoxActionPerformed(evt);
            }
        });

        gridPointCheckBox.setText("Grid Point (Resolution)");
        gridPointCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridPointCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(useUniformPointGeneratorCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gridPointCheckBox))
                    .addComponent(targetCheckBox))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useUniformPointGeneratorCheckBox)
                    .addComponent(gridPointCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(targetCheckBox))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Point Generator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(347, 81));

        xORShiftRngRadio.setText("XORShiftRNG");
        xORShiftRngRadio.setEnabled(false);
        xORShiftRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xORShiftRngRadioActionPerformed(evt);
            }
        });

        caRngRadio.setText("CellularAutomatonRNG");
        caRngRadio.setEnabled(false);
        caRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caRngRadioActionPerformed(evt);
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

        cmwcRngRadio.setText("CMWC4096RNG");
        cmwcRngRadio.setEnabled(false);
        cmwcRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmwcRngRadioActionPerformed(evt);
            }
        });

        mtRngRadio.setText("MersenneTwisterRNG");
        mtRngRadio.setEnabled(false);
        mtRngRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mtRngRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmwcRngRadio)
                    .addComponent(randomRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(mtRngRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xORShiftRngRadio))
                    .addComponent(caRngRadio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(randomRadio)
                        .addGap(2, 2, 2)
                        .addComponent(cmwcRngRadio))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mtRngRadio)
                            .addComponent(xORShiftRngRadio))
                        .addGap(2, 2, 2)
                        .addComponent(caRngRadio)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Point Generator Seed", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        randomSeedRadio.setSelected(true);
        randomSeedRadio.setText("Use Random Seed");
        randomSeedRadio.setEnabled(false);
        randomSeedRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomSeedRadioActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        seedSpinner.setEnabled(false);

        seedSpinnerLabel.setText("Desired Seed:");
        seedSpinnerLabel.setEnabled(false);

        specifiedSeedRadio.setText("Use Specified Seed");
        specifiedSeedRadio.setEnabled(false);
        specifiedSeedRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specifiedSeedRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(specifiedSeedRadio, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seedSpinnerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(specifiedSeedRadio)
                    .addComponent(seedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seedSpinnerLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(randomSeedRadio))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(randomSeedRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Number of Points", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        numPointsLabel.setText("How many points should be fired?");
        numPointsLabel.setEnabled(false);

        numPointsSpinner.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numPointsLabel)
                .addGap(18, 18, 18)
                .addComponent(numPointsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numPointsLabel)
                    .addComponent(numPointsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(acceptButton)
                .addGap(59, 59, 59)
                .addComponent(cancelButton)
                .addGap(95, 95, 95))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {acceptButton, cancelButton});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cancelButton)
                .addComponent(acceptButton))
        );

        resolutionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Resolution", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N

        resolutionLabel.setText("How many points per grid?");
        resolutionLabel.setEnabled(false);

        resolutionSpinner.setEnabled(false);

        javax.swing.GroupLayout resolutionPanelLayout = new javax.swing.GroupLayout(resolutionPanel);
        resolutionPanel.setLayout(resolutionPanelLayout);
        resolutionPanelLayout.setHorizontalGroup(
            resolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolutionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resolutionLabel)
                .addGap(18, 18, 18)
                .addComponent(resolutionSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        resolutionPanelLayout.setVerticalGroup(
            resolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolutionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resolutionLabel)
                    .addComponent(resolutionSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(propertiesHeaderLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(resolutionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propertiesHeaderLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resolutionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            this.simulationState.onDiagnosticSimulation();
        }
    }//GEN-LAST:event_targetCheckBoxActionPerformed

    private void useUniformPointGeneratorCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_useUniformPointGeneratorCheckBoxActionPerformed
    {//GEN-HEADEREND:event_useUniformPointGeneratorCheckBoxActionPerformed
        AbstractButton abstractButton = (AbstractButton) evt.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        if (selected)
        {
            this.simulationState.onMonteCarloSimulation();
        }
    }//GEN-LAST:event_useUniformPointGeneratorCheckBoxActionPerformed

    private void randomRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_randomRadioActionPerformed
    {//GEN-HEADEREND:event_randomRadioActionPerformed
        if (randomRadio.isSelected())
        {
            this.simulationState.randomRng();
        }
    }//GEN-LAST:event_randomRadioActionPerformed

    private void mtRngRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mtRngRadioActionPerformed
    {//GEN-HEADEREND:event_mtRngRadioActionPerformed
        if (mtRngRadio.isSelected())
        {
            this.simulationState.onMtRng();
        }
    }//GEN-LAST:event_mtRngRadioActionPerformed

    private void xORShiftRngRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_xORShiftRngRadioActionPerformed
    {//GEN-HEADEREND:event_xORShiftRngRadioActionPerformed
        if (xORShiftRngRadio.isSelected())
        {
            this.simulationState.onXORRng();
        }
    }//GEN-LAST:event_xORShiftRngRadioActionPerformed

    private void randomSeedRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_randomSeedRadioActionPerformed
    {//GEN-HEADEREND:event_randomSeedRadioActionPerformed
        if (randomSeedRadio.isSelected())
        {
            this.simulationState.onRandomRngSeed();
        }
    }//GEN-LAST:event_randomSeedRadioActionPerformed

    private void specifiedSeedRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_specifiedSeedRadioActionPerformed
    {//GEN-HEADEREND:event_specifiedSeedRadioActionPerformed
        if (specifiedSeedRadio.isSelected())
        {
            this.simulationState.onSpecifiedRngSeed();
        }
    }//GEN-LAST:event_specifiedSeedRadioActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_acceptButtonActionPerformed
    {//GEN-HEADEREND:event_acceptButtonActionPerformed
        if (this.simulationState.isPointGeneratedModel())
        {
            this.pointGeneratorState.setDartGunState(true);
            Globals.SEED = ((Double) this.simulationState.getSeedModel().getValue());
            double darts = (Double) this.simulationState.getPointGeneratorModel().getValue();
            Globals.NUM_DARTS = (int) darts;
        }

        if (this.simulationState.isGridGeneratedModel())
        {
            this.pointGeneratorState.setDartGunState(false);
            Globals.RESOLUTION = ((Integer) this.simulationState.getGridSpinnerModel().getValue());
        }

        if (this.simulationState.isTargetModel())
        {
            this.pointGeneratorState.setDartGunState(false);
        }

        if (this.simulationState.isCaRng())
        {
            this.pointGeneratorState.setCaRng(true);
            this.pointGeneratorState.setCmwcRng(false);
            this.pointGeneratorState.setMtRng(false);
            this.pointGeneratorState.setRandomRng(false);
            this.pointGeneratorState.setxORRng(false);
        }

        if (this.simulationState.isCmwcRng())
        {
            this.pointGeneratorState.setCaRng(false);
            this.pointGeneratorState.setCmwcRng(true);
            this.pointGeneratorState.setMtRng(false);
            this.pointGeneratorState.setRandomRng(false);
            this.pointGeneratorState.setxORRng(false);
        }

        if (this.simulationState.isMtRng())
        {
            this.pointGeneratorState.setCaRng(false);
            this.pointGeneratorState.setCmwcRng(false);
            this.pointGeneratorState.setMtRng(true);
            this.pointGeneratorState.setRandomRng(false);
            this.pointGeneratorState.setxORRng(false);
        }

        if (this.simulationState.isRandomRng())
        {
            this.pointGeneratorState.setCaRng(false);
            this.pointGeneratorState.setCmwcRng(false);
            this.pointGeneratorState.setMtRng(false);
            this.pointGeneratorState.setRandomRng(true);
            this.pointGeneratorState.setxORRng(false);
        }

        if (this.simulationState.isXORRng())
        {
            this.pointGeneratorState.setCaRng(false);
            this.pointGeneratorState.setCmwcRng(false);
            this.pointGeneratorState.setMtRng(false);
            this.pointGeneratorState.setRandomRng(false);
            this.pointGeneratorState.setxORRng(true);
        }

        outputState.onPropertiesLoaded();
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
            this.simulationState.onCmwcRng();
        }
    }//GEN-LAST:event_cmwcRngRadioActionPerformed

    private void caRngRadioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_caRngRadioActionPerformed
    {//GEN-HEADEREND:event_caRngRadioActionPerformed
        if (caRngRadio.isSelected())
        {
            this.simulationState.onCaRng();
        }
    }//GEN-LAST:event_caRngRadioActionPerformed

    private void gridPointCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gridPointCheckBoxActionPerformed
    {//GEN-HEADEREND:event_gridPointCheckBoxActionPerformed
        AbstractButton abstractButton = (AbstractButton) evt.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        if (selected)
        {
            this.simulationState.onPixelGridSimulation();
        }
    }//GEN-LAST:event_gridPointCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JRadioButton caRngRadio;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton cmwcRngRadio;
    private javax.swing.JCheckBox gridPointCheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton mtRngRadio;
    private javax.swing.JLabel numPointsLabel;
    private javax.swing.JSpinner numPointsSpinner;
    private javax.swing.JLabel propertiesHeaderLabel;
    private javax.swing.JRadioButton randomRadio;
    private javax.swing.JRadioButton randomSeedRadio;
    private javax.swing.JLabel resolutionLabel;
    private javax.swing.JPanel resolutionPanel;
    private javax.swing.JSpinner resolutionSpinner;
    private javax.swing.JSpinner seedSpinner;
    private javax.swing.JLabel seedSpinnerLabel;
    private javax.swing.JRadioButton specifiedSeedRadio;
    private javax.swing.JCheckBox targetCheckBox;
    private javax.swing.JCheckBox useUniformPointGeneratorCheckBox;
    private javax.swing.JRadioButton xORShiftRngRadio;
    // End of variables declaration//GEN-END:variables
}
