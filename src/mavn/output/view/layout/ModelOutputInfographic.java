/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.output.view.layout;

import java.text.DecimalFormat;

/**
 *
 * @author Kaleb
 */
public class ModelOutputInfographic extends javax.swing.JPanel
{

   private DecimalFormat df = new DecimalFormat("#.####");
    
    /**
     * Creates new form ModelOutputInfographic
     */
    public ModelOutputInfographic()
    {
        initComponents();
    }
    
    public void setHitValue(double hit)
    {
        valueHits.setText(String.valueOf(hit));
    }
    
    public void setPointsValue(int points)
    {
        valueFired.setText(String.valueOf(points));
    }
    
    public void setMissValue(double miss)
    {
        valueMisses.setText(String.valueOf(miss));
    }
    
    public void setRatioValue(double ratio)
    {
        valueHitMiss.setText(df.format(ratio));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        labelFired = new javax.swing.JLabel();
        valueFired = new javax.swing.JLabel();
        labelHits = new javax.swing.JLabel();
        valueHits = new javax.swing.JLabel();
        labelHit = new javax.swing.JLabel();
        valueMisses = new javax.swing.JLabel();
        labelHitMiss = new javax.swing.JLabel();
        valueHitMiss = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Progress", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N
        setMaximumSize(new java.awt.Dimension(400, 200));
        setPreferredSize(new java.awt.Dimension(400, 100));
        setLayout(new java.awt.GridBagLayout());

        labelFired.setText("Fired:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(labelFired, gridBagConstraints);

        valueFired.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(valueFired, gridBagConstraints);

        labelHits.setText("Hits:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(labelHits, gridBagConstraints);

        valueHits.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(valueHits, gridBagConstraints);

        labelHit.setText("Misses:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(labelHit, gridBagConstraints);

        valueMisses.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(valueMisses, gridBagConstraints);

        labelHitMiss.setText("Hit/Miss:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(labelHitMiss, gridBagConstraints);

        valueHitMiss.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.25;
        add(valueHitMiss, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelFired;
    private javax.swing.JLabel labelHit;
    private javax.swing.JLabel labelHitMiss;
    private javax.swing.JLabel labelHits;
    private javax.swing.JLabel valueFired;
    private javax.swing.JLabel valueHitMiss;
    private javax.swing.JLabel valueHits;
    private javax.swing.JLabel valueMisses;
    // End of variables declaration//GEN-END:variables
}