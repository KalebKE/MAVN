/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetTable;

import javax.swing.Action;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/**
 *
 * @author Kaleb
 */
public class UndoSimTableManager extends UndoManager
{

    protected Action undoAction;
    protected Action redoAction;

    public UndoSimTableManager()
    {
        this.undoAction = new UndoSimTableAction(this);
        this.redoAction = new RedoSimTableAction(this);

        synchronizeActions();           // to set initial names
    }

    public Action getUndoAction()
    {
        return undoAction;
    }

    public Action getRedoAction()
    {
        return redoAction;
    }

    @Override
    public boolean addEdit(UndoableEdit anEdit)
    {
        try
        {
            return super.addEdit(anEdit);
        } finally
        {
            synchronizeActions();
        }
    }

    @Override
    protected void undoTo(UndoableEdit edit) throws CannotUndoException
    {
        try
        {
            super.undoTo(edit);
        } finally
        {
            synchronizeActions();
        }
    }

    @Override
    protected void redoTo(UndoableEdit edit) throws CannotRedoException
    {
        try
        {
            super.redoTo(edit);
        } finally
        {
            synchronizeActions();
        }
    }

    protected void synchronizeActions()
    {
        undoAction.setEnabled(canUndo());
        undoAction.putValue(Action.NAME, getUndoPresentationName());

        redoAction.setEnabled(canRedo());
        redoAction.putValue(Action.NAME, getRedoPresentationName());
    }
}



