/*
ModelOutputDefaultLayoutView -- a class within the Machine Artificial Vision Network
(Machine Artificial Vision Network).
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
package mavn.output.view.layout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Model Output Default Layout View provides the layout for the Output View's. 
 * @author Kaleb
 */
public class ModelOutputDefaultLayoutView extends javax.swing.JPanel
{

    private JPanel outputViewRenderer;
    private JPanel plotPanel;
    private JPanel networkPanel;
    private JPanel controlBar;

    /** Creates new form NetworkPanel */
    public ModelOutputDefaultLayoutView(JPanel controlBar,
            JPanel outputViewRenderer,
            JPanel plotPanel, JPanel networkPanel)
    {
        initComponents();
        this.controlBar = controlBar;
        this.outputViewRenderer = outputViewRenderer;
        this.plotPanel = plotPanel;
        this.networkPanel = networkPanel;

        this.controlPanel.setLayout(new BoxLayout(this.controlPanel, BoxLayout.LINE_AXIS));
        this.controlPanel.add(this.controlBar);

        this.outputView.setLayout(new BoxLayout(this.outputView, BoxLayout.LINE_AXIS));
        this.outputView.add(this.outputViewRenderer);

        this.plotView.setLayout(new BoxLayout(this.plotView, BoxLayout.LINE_AXIS));
        this.plotView.add(this.plotPanel);

        this.networkView.setLayout(new BoxLayout(this.networkView, BoxLayout.LINE_AXIS));
        this.networkView.add(this.networkPanel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outputView = new javax.swing.JPanel();
        jLabelResults = new javax.swing.JLabel();
        plotView = new javax.swing.JPanel();
        networkView = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();

        outputView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Simulation Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N
        outputView.setPreferredSize(new java.awt.Dimension(520, 480));

        javax.swing.GroupLayout outputViewLayout = new javax.swing.GroupLayout(outputView);
        outputView.setLayout(outputViewLayout);
        outputViewLayout.setHorizontalGroup(
            outputViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelResults)
                .addContainerGap(776, Short.MAX_VALUE))
        );
        outputViewLayout.setVerticalGroup(
            outputViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputViewLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelResults)
                .addGap(469, 469, 469))
        );

        plotView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N
        plotView.setPreferredSize(new java.awt.Dimension(520, 348));

        javax.swing.GroupLayout plotViewLayout = new javax.swing.GroupLayout(plotView);
        plotView.setLayout(plotViewLayout);
        plotViewLayout.setHorizontalGroup(
            plotViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        plotViewLayout.setVerticalGroup(
            plotViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );

        networkView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Network", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 102, 204))); // NOI18N
        networkView.setPreferredSize(new java.awt.Dimension(1103, 400));

        javax.swing.GroupLayout networkViewLayout = new javax.swing.GroupLayout(networkView);
        networkView.setLayout(networkViewLayout);
        networkViewLayout.setHorizontalGroup(
            networkViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1222, Short.MAX_VALUE)
        );
        networkViewLayout.setVerticalGroup(
            networkViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        controlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(networkView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(plotView, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputView, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(networkView, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(plotView, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(outputView, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel jLabelResults;
    private javax.swing.JPanel networkView;
    protected javax.swing.JPanel outputView;
    private javax.swing.JPanel plotView;
    // End of variables declaration//GEN-END:variables
}