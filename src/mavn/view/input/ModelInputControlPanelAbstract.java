/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InputControlPanel.java
 *
 * Created on Feb 25, 2012, 9:27:57 AM
 */
package mavn.view.input;

import java.awt.GridLayout;
import java.util.ArrayList;

/**
 *
 * @author Kaleb
 */
public abstract class ModelInputControlPanelAbstract extends javax.swing.JPanel
{
    protected ArrayList<ModelInputPanelAbstract> inputPanels;
    protected GridLayout layout;

    /** Creates new form InputControlPanel */
    public ModelInputControlPanelAbstract()
    {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 833, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
