/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheetTable;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;

/**
 *
 * @author Kaleb
 */
class SimTableCellEditor extends AbstractUndoableEdit
{
    protected UndoableSimTableModel tableModel;
    protected Object oldValue;
    protected Object newValue;
    protected int row;
    protected int column;

    public SimTableCellEditor(UndoableSimTableModel tableModel, Object oldValue, Object newValue, int row, int column)
    {
        this.tableModel = tableModel;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.row = row;
        this.column = column;
    }

    @Override
    public String getPresentationName()
    {
        return "Cell Edit";
    }

    @Override
    public void undo() throws CannotUndoException
    {
        super.undo();

        tableModel.setValueAt(oldValue, row, column, false);
    }

    @Override
    public void redo() throws CannotUndoException
    {
        super.redo();

        tableModel.setValueAt(newValue, row, column, false);
    }
}
