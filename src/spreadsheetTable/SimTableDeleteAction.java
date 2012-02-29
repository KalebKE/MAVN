/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetTable;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kaleb
 */
public class SimTableDeleteAction extends AbstractAction
{

    private JTable table;

    public SimTableDeleteAction(JTable table)
    {
        this.table = table;
        table.getInputMap().put(KeyStroke.getKeyStroke("DELETE"),
                "deleteSelectedCells");

        table.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),
                "deleteSelectedCells");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int[] rows = table.getSelectedRows();

        for (int i = 0; i < rows.length; i++)
        {
            for (int j = 0; j < model.getColumnCount(); j++)
            {
                String value = "";
                model.setValueAt((Object) value, rows[i], j);
            }
        }
    }
}
