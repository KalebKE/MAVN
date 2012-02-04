/*
TransitionMatrixWizardFrame -- a class within the Open Queueing Model (OQM).
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
package matrixWizard.view;

import javax.swing.SpinnerNumberModel;
import matrixWizard.model.MatrixWizardModelInterface;

/**
 * A special JFrame that creates a Transition Matrix Wizard GUI.
 * It allows the user to select the size of matrix they would like
 * to generate with another type of special JFrame matrix template.
 * @author Kaleb
 */
public class CreateMatrixWizardFrame extends javax.swing.JFrame
{
    // Get an instance of the controller

    MatrixWizardModelInterface matrixModel;
    private SpinnerNumberModel mSpinnerModel;
    private SpinnerNumberModel nSpinnerModel;
    private String mSpinnerToolTipText = "Use this spinner box to enter the "
            + "desired dimension of the matrix.";
    private String nSpinnerToolTipText = "Use this spinner box to enter the "
            + "desired dimension of the matrix.";

    /** 
     * Creates new form TransitionMatrixWizardFrame
     */
    public CreateMatrixWizardFrame(MatrixWizardModelInterface matrixModel)
    {
        this.matrixModel = matrixModel;
        initComponents();

        mSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        nSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        mSpinner.setModel(mSpinnerModel);
        nSpinner.setModel(nSpinnerModel);
        mSpinner.setName("mSpinner");
        nSpinner.setName("nSpinner");
        mSpinner.setToolTipText(mSpinnerToolTipText);
        nSpinner.setToolTipText(nSpinnerToolTipText);

        // make sure the JFrame only closes itself
        // Overrides default behavior. Must be called after initComponents.
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        matrixDimControlPanel = new javax.swing.JPanel();
        mxnlabel = new javax.swing.JLabel();
        generateMatrixLabel = new javax.swing.JLabel();
        generateMatrixButton = new javax.swing.JButton();
        defineDimLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        nSpinner = new javax.swing.JSpinner();
        mSpinner = new javax.swing.JSpinner();
        jMenuBar1 = new javax.swing.JMenuBar();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Neuropol", 0, 14));
        jLabel1.setText("Create new Transition Matrix Wizard");

        jLabel2.setText("Please select the desired dimensions of the matrix you want to create");

        mxnlabel.setText("       M    x    N");

        generateMatrixLabel.setFont(new java.awt.Font("Tahoma", 3, 11));
        generateMatrixLabel.setText("Generate New Matrix:");

        generateMatrixButton.setText("Generate Matrix Template");
        generateMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMatrixButtonActionPerformed(evt);
            }
        });

        defineDimLabel.setFont(new java.awt.Font("Tahoma", 2, 11));
        defineDimLabel.setText("Define Matrix Dimensions");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(nSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout matrixDimControlPanelLayout = new javax.swing.GroupLayout(matrixDimControlPanel);
        matrixDimControlPanel.setLayout(matrixDimControlPanelLayout);
        matrixDimControlPanelLayout.setHorizontalGroup(
            matrixDimControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matrixDimControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(matrixDimControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generateMatrixButton)
                    .addGroup(matrixDimControlPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(matrixDimControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(generateMatrixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(defineDimLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(matrixDimControlPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(mxnlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, matrixDimControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        matrixDimControlPanelLayout.setVerticalGroup(
            matrixDimControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matrixDimControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generateMatrixLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defineDimLabel)
                .addGap(2, 2, 2)
                .addComponent(mxnlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generateMatrixButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(matrixDimControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matrixDimControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpMenu.setText("Help");
        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_generateMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_generateMatrixButtonActionPerformed
        new MatrixTemplateFrame((((SpinnerNumberModel) mSpinner.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) nSpinner.getModel()).getNumber()).intValue(), matrixModel);
        this.dispose();
}//GEN-LAST:event_generateMatrixButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel defineDimLabel;
    private javax.swing.JButton generateMatrixButton;
    private javax.swing.JLabel generateMatrixLabel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSpinner mSpinner;
    private javax.swing.JPanel matrixDimControlPanel;
    private javax.swing.JLabel mxnlabel;
    private javax.swing.JSpinner nSpinner;
    // End of variables declaration//GEN-END:variables
}
