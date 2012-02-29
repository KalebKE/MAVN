/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheetTable;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kaleb
 */
class UndoableSimTableModel extends DefaultTableModel
{
    public UndoableSimTableModel(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }

    public UndoableSimTableModel(int rowCount, int columnCount)
    {
        super(rowCount, columnCount);
    }
    
    @Override
    public Class getColumnClass(int column)
    {
        if (column >= 0 && column < getColumnCount())
            return getValueAt(0, column).getClass();

        return Object.class;
    }



    @Override
    public void setValueAt(Object value, int row, int column)
    {
        setValueAt(value, row, column, true);
    }


    public void setValueAt(Object value, int row, int column, boolean undoable)
    {
        UndoableEditListener listeners[] = getListeners(UndoableEditListener.class);
        if (undoable == false || listeners == null)
        {
            super.setValueAt(value, row, column);
            return;
        }

        Object oldValue = getValueAt(row, column);
        super.setValueAt(value, row, column);
        SimTableCellEditor cellEdit = new SimTableCellEditor(this, oldValue, value, row, column);
        UndoableEditEvent editEvent = new UndoableEditEvent(this, cellEdit);
        for (UndoableEditListener listener : listeners)
            listener.undoableEditHappened(editEvent);
    }


    public void addUndoableEditListener(UndoableEditListener listener)
    {
        listenerList.add(UndoableEditListener.class, listener);
    }
}
