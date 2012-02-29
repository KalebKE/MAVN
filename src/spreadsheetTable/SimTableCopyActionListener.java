/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetTable;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Kaleb
 */
public class SimTableCopyActionListener implements ActionListener
{
    private JTable table;
    private String rowstring, value;
    private Clipboard system;
    private StringSelection stsel;

    public SimTableCopyActionListener(JTable table)
    {
        this.table = table;

       

        system = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    /**
     * This method is activated on the Keystrokes we are listening to
     * in this implementation. Here it listens for Copy and Paste ActionCommands.
     * Selections comprising non-adjacent cells result in invalid selection and
     * then copy action cannot be performed.
     * Paste is done by aligning the upper left corner of the selection with the
     * 1st element in the current selection of the JTable.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().compareTo("Copy") == 0)
        {
            StringBuffer sbf = new StringBuffer();
            // Check to ensure we have selected only a contiguous block of
            // cells
            int numcols = table.getSelectedColumnCount();
            int numrows = table.getSelectedRowCount();
            int[] rowsselected = table.getSelectedRows();
            int[] colsselected = table.getSelectedColumns();
            if (!((numrows - 1 == rowsselected[rowsselected.length - 1] - rowsselected[0]
                    && numrows == rowsselected.length)
                    && (numcols - 1 == colsselected[colsselected.length - 1] - colsselected[0]
                    && numcols == colsselected.length)))
            {
                JOptionPane.showMessageDialog(null, "Invalid Copy Selection",
                        "Invalid Copy Selection",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (int i = 0; i < numrows; i++)
            {
                for (int j = 0; j < numcols; j++)
                {
                    sbf.append(table.getValueAt(rowsselected[i], colsselected[j]));
                    if (j < numcols - 1)
                    {
                        sbf.append("\t");
                    }
                }
                sbf.append("\n");
            }
            stsel = new StringSelection(sbf.toString());
            system = Toolkit.getDefaultToolkit().getSystemClipboard();
            system.setContents(stsel, stsel);
        }
        if (e.getActionCommand().compareTo("Paste") == 0)
        {
            System.out.println("Trying to Paste");
            int startRow = (table.getSelectedRows())[0];
            int startCol = (table.getSelectedColumns())[0];
            try
            {
                String trstring = (String) (system.getContents(this).getTransferData(DataFlavor.stringFlavor));
                //System.out.println("String is:" + trstring);
                StringTokenizer st1 = new StringTokenizer(trstring, "\n");
                for (int i = 0; st1.hasMoreTokens(); i++)
                {
                    rowstring = st1.nextToken();
                    StringTokenizer st2 = new StringTokenizer(rowstring, "\t");
                    for (int j = 0; st2.hasMoreTokens(); j++)
                    {
                        value = (String) st2.nextToken();
                        if (startRow + i < table.getRowCount()
                                && startCol + j < table.getColumnCount())
                        {
                            table.setValueAt(value, startRow + i, startCol + j);
                        }
                        //System.out.println("Putting " + value +"at row = "+ startRow+i+ "column ="+ startCol+j);
                    }
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
