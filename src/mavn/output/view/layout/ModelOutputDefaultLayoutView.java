/*
 ModelOutputDefaultLayoutView -- A class within the Machine Artificial Vision Network
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

import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Model Output Default Layout View provides the layout for the Output View's.
 *
 * @author Kaleb
 */
public class ModelOutputDefaultLayoutView extends javax.swing.JPanel
{

    private JPanel imageRatioViewRenderer;
    private JPanel segmentRatioViewRenderer;
    private JPanel histogramViewRenderer;
    private JPanel plotPanel;
    private JPanel networkPanel;
    private JPanel controlBar;
    private JPanel subControlBar;
    private JPanel propertiesControlBar;
    private JPanel modelOutputInfographic;

    /**
     * Creates new form NetworkPanel
     */
    public ModelOutputDefaultLayoutView(JPanel controlBar, JPanel subControlBar, JPanel propertiesControlBar, JPanel modelOutputInfographic,
            JPanel histogramViewRenderer, JPanel segmentRatioViewRenderer, JPanel imageRatioViewRenderer,
            JPanel plotPanel, JPanel networkPanel)
    {
        initComponents();
        this.controlBar = controlBar;
        this.histogramViewRenderer = histogramViewRenderer;
        this.plotPanel = plotPanel;
        this.networkPanel = networkPanel;
        this.subControlBar = subControlBar;
        this.propertiesControlBar = propertiesControlBar;
        this.modelOutputInfographic = modelOutputInfographic;
        this.segmentRatioViewRenderer = segmentRatioViewRenderer;
        this.imageRatioViewRenderer = imageRatioViewRenderer;

        this.controlPanel.setLayout(new BoxLayout(this.controlPanel, BoxLayout.LINE_AXIS));
        this.controlPanel.add(this.controlBar);

        this.outputHistogramView.setLayout(new BoxLayout(this.outputHistogramView, BoxLayout.Y_AXIS));
        this.outputHistogramView.add(this.histogramViewRenderer);

        this.outputSegmentRatioView.setLayout(new BoxLayout(this.outputSegmentRatioView, BoxLayout.Y_AXIS));
        this.outputSegmentRatioView.add(this.segmentRatioViewRenderer);

        this.outputImageRatioView.setLayout(new BoxLayout(this.outputImageRatioView, BoxLayout.Y_AXIS));
        this.outputImageRatioView.add(this.imageRatioViewRenderer);

        this.plotView.setLayout(new BoxLayout(this.plotView, BoxLayout.Y_AXIS));
        this.plotView.add(this.plotPanel);

        this.networkView.setLayout(new BoxLayout(this.networkView, BoxLayout.LINE_AXIS));
        this.networkView.add(this.networkPanel);

        this.controlView.setLayout(new BoxLayout(this.controlView, BoxLayout.Y_AXIS));
        this.subControlPanel.setLayout(new BoxLayout(this.subControlPanel, BoxLayout.LINE_AXIS));
        this.subControlPanel.add(this.subControlBar);

        this.propertiesControlPanel.setLayout(new BoxLayout(this.propertiesControlPanel, BoxLayout.LINE_AXIS));
        this.propertiesControlPanel.add(this.propertiesControlBar);

        this.infograhpicControlPanel.setLayout(new BoxLayout(this.infograhpicControlPanel, BoxLayout.LINE_AXIS));
        this.infograhpicControlPanel.add(this.modelOutputInfographic);

        this.logo.setIcon(getIcon("/icons/mavn_logo.png"));

    }

    /**
     * Get an Image Icon from the path.
     *
     * @param iconPath the path of the desired image.
     * @return an Image Icon from the path.
     */
    public ImageIcon getIcon(String iconPath)
    {
        // Set Icon for button
        ImageIcon icon = createImageIcon(iconPath, "");
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(225, 225, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg, "");

        return newIcon;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    private ImageIcon createImageIcon(String path,
            String description)
    {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null)
        {
            return new ImageIcon(imgURL, description);
        } else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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

        controlPanel = new javax.swing.JPanel();
        inputView = new javax.swing.JPanel();
        networkView = new javax.swing.JPanel();
        plotView = new javax.swing.JPanel();
        controlView = new javax.swing.JPanel();
        subControlPanel = new javax.swing.JPanel();
        propertiesControlPanel = new javax.swing.JPanel();
        infograhpicControlPanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        outputView = new javax.swing.JPanel();
        outputHistogramView = new javax.swing.JPanel();
        jLabelResults = new javax.swing.JLabel();
        outputSegmentRatioView = new javax.swing.JPanel();
        outputImageRatioView = new javax.swing.JPanel();

        controlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );

        inputView.setLayout(new java.awt.GridBagLayout());

        networkView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Network", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N
        networkView.setMaximumSize(new java.awt.Dimension(800, 500));
        networkView.setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.GroupLayout networkViewLayout = new javax.swing.GroupLayout(networkView);
        networkView.setLayout(networkViewLayout);
        networkViewLayout.setHorizontalGroup(
            networkViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );
        networkViewLayout.setVerticalGroup(
            networkViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        inputView.add(networkView, gridBagConstraints);

        plotView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N
        plotView.setMaximumSize(new java.awt.Dimension(800, 500));
        plotView.setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.GroupLayout plotViewLayout = new javax.swing.GroupLayout(plotView);
        plotView.setLayout(plotViewLayout);
        plotViewLayout.setHorizontalGroup(
            plotViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );
        plotViewLayout.setVerticalGroup(
            plotViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        inputView.add(plotView, gridBagConstraints);

        controlView.setPreferredSize(new java.awt.Dimension(285, 500));
        controlView.setVerifyInputWhenFocusTarget(false);
        controlView.setLayout(new java.awt.GridBagLayout());

        subControlPanel.setAutoscrolls(true);
        subControlPanel.setMaximumSize(new java.awt.Dimension(700, 100));
        subControlPanel.setPreferredSize(new java.awt.Dimension(285, 100));

        javax.swing.GroupLayout subControlPanelLayout = new javax.swing.GroupLayout(subControlPanel);
        subControlPanel.setLayout(subControlPanelLayout);
        subControlPanelLayout.setHorizontalGroup(
            subControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        subControlPanelLayout.setVerticalGroup(
            subControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        controlView.add(subControlPanel, gridBagConstraints);

        propertiesControlPanel.setAutoscrolls(true);
        propertiesControlPanel.setMaximumSize(new java.awt.Dimension(700, 100));
        propertiesControlPanel.setPreferredSize(new java.awt.Dimension(285, 100));
        propertiesControlPanel.setRequestFocusEnabled(false);

        javax.swing.GroupLayout propertiesControlPanelLayout = new javax.swing.GroupLayout(propertiesControlPanel);
        propertiesControlPanel.setLayout(propertiesControlPanelLayout);
        propertiesControlPanelLayout.setHorizontalGroup(
            propertiesControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        propertiesControlPanelLayout.setVerticalGroup(
            propertiesControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        controlView.add(propertiesControlPanel, gridBagConstraints);

        infograhpicControlPanel.setMaximumSize(new java.awt.Dimension(32767, 50));
        infograhpicControlPanel.setMinimumSize(new java.awt.Dimension(100, 50));

        javax.swing.GroupLayout infograhpicControlPanelLayout = new javax.swing.GroupLayout(infograhpicControlPanel);
        infograhpicControlPanel.setLayout(infograhpicControlPanelLayout);
        infograhpicControlPanelLayout.setHorizontalGroup(
            infograhpicControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );
        infograhpicControlPanelLayout.setVerticalGroup(
            infograhpicControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        controlView.add(infograhpicControlPanel, gridBagConstraints);

        logoPanel.setPreferredSize(new java.awt.Dimension(300, 300));
        logoPanel.setLayout(new java.awt.GridBagLayout());

        logo.setIconTextGap(0);
        logo.setMaximumSize(new java.awt.Dimension(300, 300));
        logo.setMinimumSize(new java.awt.Dimension(200, 200));
        logo.setPreferredSize(new java.awt.Dimension(300, 300));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        logoPanel.add(logo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        controlView.add(logoPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        inputView.add(controlView, gridBagConstraints);

        outputView.setLayout(new java.awt.GridBagLayout());

        outputHistogramView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Distribution Histogram", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N
        outputHistogramView.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout outputHistogramViewLayout = new javax.swing.GroupLayout(outputHistogramView);
        outputHistogramView.setLayout(outputHistogramViewLayout);
        outputHistogramViewLayout.setHorizontalGroup(
            outputHistogramViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputHistogramViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelResults)
                .addContainerGap(380, Short.MAX_VALUE))
        );
        outputHistogramViewLayout.setVerticalGroup(
            outputHistogramViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputHistogramViewLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelResults)
                .addGap(469, 469, 469))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.333;
        gridBagConstraints.weighty = 1.0;
        outputView.add(outputHistogramView, gridBagConstraints);

        outputSegmentRatioView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Segment Hit Ratio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N
        outputSegmentRatioView.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout outputSegmentRatioViewLayout = new javax.swing.GroupLayout(outputSegmentRatioView);
        outputSegmentRatioView.setLayout(outputSegmentRatioViewLayout);
        outputSegmentRatioViewLayout.setHorizontalGroup(
            outputSegmentRatioViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        outputSegmentRatioViewLayout.setVerticalGroup(
            outputSegmentRatioViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.weighty = 1.0;
        outputView.add(outputSegmentRatioView, gridBagConstraints);

        outputImageRatioView.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)), "Image Hit Ratio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Cn", 1, 12), new java.awt.Color(238, 238, 238))); // NOI18N
        outputImageRatioView.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout outputImageRatioViewLayout = new javax.swing.GroupLayout(outputImageRatioView);
        outputImageRatioView.setLayout(outputImageRatioViewLayout);
        outputImageRatioViewLayout.setHorizontalGroup(
            outputImageRatioViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        outputImageRatioViewLayout.setVerticalGroup(
            outputImageRatioViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.333;
        gridBagConstraints.weighty = 1.0;
        outputView.add(outputImageRatioView, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(inputView, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(outputView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputView, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel controlView;
    private javax.swing.JPanel infograhpicControlPanel;
    private javax.swing.JPanel inputView;
    private javax.swing.JLabel jLabelResults;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel networkView;
    private javax.swing.JPanel outputHistogramView;
    private javax.swing.JPanel outputImageRatioView;
    private javax.swing.JPanel outputSegmentRatioView;
    private javax.swing.JPanel outputView;
    private javax.swing.JPanel plotView;
    private javax.swing.JPanel propertiesControlPanel;
    private javax.swing.JPanel subControlPanel;
    // End of variables declaration//GEN-END:variables
}
