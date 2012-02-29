/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetTable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kaleb
 */
public class SimTableKeyListener implements KeyListener
{

    private JTable table;

    public SimTableKeyListener(JTable table)
    {
        this.table = table;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int[] rows = table.getSelectedRows();
        switch (e.getKeyChar())
        {
            case '\u0008':
                for (int i = 0; i < rows.length; i++)
                {
                    model.removeRow(rows[i] - i);
                }
                break;

            case '\u007F':
                for (int i = 0; i < rows.length; i++)
                {
                    model.removeRow(rows[i] - i);
                }
                break;

            default:

                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        e.consume();
    }
}
