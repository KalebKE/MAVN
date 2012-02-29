/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetTable;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * A special JScrollPane/JTable combo that mimics standard spreadsheet
 * editors. SimTable currently supports column and row headers, custom
 * cell renderers, drag-to-select, undo/redo and copy/paste.
 * @author Kaleb
 */
public class SimTable extends JScrollPane
{

    private DefaultTableModel headerData;
    private double[][] defaultModel;
    private JTable table;
    private JTable tableRowHeader;
    private JTableHeader corner;
    private SimTableCopyActionListener copyAction;
    private UndoSimTableManager undoManager;
    private UndoableSimTableModel data;

    /**
     * Initialize a new SimTable.
     */
    public SimTable()
    {
        super();

        initTableDataModel();
        initTableData();
        initTableRowHeader();

        this.setViewportView(table);

        this.setRowHeaderView(tableRowHeader);

        corner = tableRowHeader.getTableHeader();
        corner.setReorderingAllowed(false);
        corner.setResizingAllowed(false);

        this.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);

        new JScrollPaneAdjuster(this);

        new JTableRowHeaderResizer(this).setEnabled(true);
    }

    public double[][] getModel()
    {
        if (table.getCellEditor() != null)
        {
            table.getCellEditor().stopCellEditing();
        }
        
        int numRows = 0;
        int numCols = 0;
        for (int i = 0; i < table.getRowCount(); i++)
        {
            for (int j = 0; j < table.getColumnCount(); j++)
            {
                String cell = String.valueOf(table.getValueAt(i, j));
                if (cell.length() != 0)
                {
                    if (j + 1 > numCols)
                    {
                        numCols = j + 1;
                    }
                    if (i + 1 > numRows)
                    {
                        numRows = i + 1;
                    }
                }
            }
        }

        double[][] model = new double[numRows][numCols];

        for (int i = 0; i < model.length; i++)
        {
            for (int j = 0; j < model[i].length; j++)
            {
                String cell = String.valueOf(table.getValueAt(i, j));
                model[i][j] = Double.valueOf(cell);
            }
        }

        return model;
    }

    /**
     * Set a new input model.
     * @param model the desired input model.
     */
    public void setModel(double[][] model)
    {
        // Put the model values into the SimTable.
        for (int i = 0; i
                < model.length; i++)
        {
            for (int j = 0; j
                    < model[i].length; j++)
            {
                data.setValueAt((Double)model[i][j], i, j);
            }
        }

        // Set the table model and the column renderer.
        table.setModel(data);
        table.setDefaultRenderer(Object.class, new SimTableColumnRenderer());

        // Set the table row header table model.
        tableRowHeader.setModel(headerData);

        // Make the current look and feel is being used by the table row header.
        LookAndFeel.installColorsAndFont(tableRowHeader,
                "TableHeader.background",
                "TableHeader.foreground", "TableHeader.font");

        // Set up the table row header.
        tableRowHeader.setIntercellSpacing(
                new Dimension(0, 0));
        Dimension d = tableRowHeader.getPreferredScrollableViewportSize();
        d.width = tableRowHeader.getPreferredSize().width;
        tableRowHeader.setPreferredScrollableViewportSize(d);
        tableRowHeader.setRowHeight(table.getRowHeight());
        // Add the table to the row header.
        tableRowHeader.setDefaultRenderer(Object.class, new RowHeaderRenderer(table));
        // Set the table and the row header to the scroll panes view port.
        this.setViewportView(table);
        this.setRowHeaderView(
                tableRowHeader);

        // Size everything correctly.
        corner = tableRowHeader.getTableHeader();
        corner.setReorderingAllowed(
                false);
        corner.setResizingAllowed(
                false);

        this.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
        new JScrollPaneAdjuster(
                this);
        new JTableRowHeaderResizer(
                this).setEnabled(true);
    }

    private void initTableDataModel()
    {
        headerData = new DefaultTableModel();
        data = new UndoableSimTableModel(0, 0);
        defaultModel = new double[15][12];

        undoManager = new UndoSimTableManager();
        data.addUndoableEditListener(undoManager);

        Vector names = new Vector();
        for (int i = 0; i < defaultModel[0].length; i++)
        {
            names.add("n" + i);
        }

        headerData.addColumn("M-by-N");

        for (int i = 0; i < defaultModel.length; i++)
        {
            headerData.addRow(new Object[]
                    {
                        "m" + i
                    });

            Vector v = new Vector();

            for (int j = 0; j < defaultModel[i].length; j++)
            {
                v.add("");
            }
            data.setColumnIdentifiers(names);
            data.addRow(v);
        }
    }

    private void initTableData()
    {
        table = new JTable(data);
        table.setDefaultRenderer(Object.class, new SimTableColumnRenderer());
        table.setDragEnabled(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);

        copyAction = new SimTableCopyActionListener(table);

        KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
        // Identifying the copy KeyStroke user can modify this
        // to copy on some other Key combination.
        KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK, false);
        // Identifying the Paste KeyStroke user can modify this
        //to copy on some other Key combination.
        this.table.registerKeyboardAction(copyAction, "Copy", copy, JComponent.WHEN_FOCUSED);
        this.table.registerKeyboardAction(copyAction, "Paste", paste, JComponent.WHEN_FOCUSED);

        // Action to remove the values in the selected cells
        Action deleteSelectedCells = new SimTableDeleteAction(table);

        table.getActionMap().put("deleteSelectedCells",
                deleteSelectedCells);

        table.getActionMap().put("undoAction", undoManager.getUndoAction());
        table.getActionMap().put("redoAction", undoManager.getRedoAction());

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),
                "undoAction");

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),
                "redoAction");
    }

    private void initTableRowHeader()
    {
        tableRowHeader = new JTable(headerData);

        LookAndFeel.installColorsAndFont(tableRowHeader, "TableHeader.background",
                "TableHeader.foreground", "TableHeader.font");

        tableRowHeader.setIntercellSpacing(new Dimension(0, 0));
        Dimension d = tableRowHeader.getPreferredScrollableViewportSize();
        d.width = tableRowHeader.getPreferredSize().width;
        tableRowHeader.setPreferredScrollableViewportSize(d);
        tableRowHeader.setRowHeight(table.getRowHeight());
        tableRowHeader.setDefaultRenderer(Object.class, new RowHeaderRenderer(table));
    }
}


