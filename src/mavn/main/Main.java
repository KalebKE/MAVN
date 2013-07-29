/*
 Main -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.main;

import java.awt.Color;
import javax.swing.plaf.ColorUIResource;
import mavn.factory.MavnSimulationFactory;
import mavn.simulation.view.SimControlView;

/**
 * The main class for the MAVN application.
 *
 * @author Kaleb
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                    javax.swing.UIManager.put("control", new Color(85, 85, 85));
                    javax.swing.UIManager.put("nimbusBase", new Color(51, 51, 51));
                    javax.swing.UIManager.put("nimbusFocus", new Color(51, 51, 51));
                    javax.swing.UIManager.put("nimbusLightBackground", new Color(153, 153, 153));
                    javax.swing.UIManager.put("nimbusSelectionBackground", new Color(90, 130, 195));
                    javax.swing.UIManager.put("text", new Color(238, 238, 238));
                    break;
                }

            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(SimControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SimControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SimControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SimControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                // Create an instance of the mavn controller
                // to run the application. 
                MavnSimulationFactory mavn = new MavnSimulationFactory();
            }
        });
    }
}
