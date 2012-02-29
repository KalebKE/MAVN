/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetTable;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Kaleb
 */
class RedoSimTableAction extends AbstractAction
{

    protected final UndoManager manager;

    public RedoSimTableAction(UndoManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            manager.redo();
        } catch (CannotRedoException ex)
        {
            ex.printStackTrace();
        }
    }
}

