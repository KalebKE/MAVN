/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetTable;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Kaleb
 */
public class UndoSimTableAction extends AbstractAction
{
    protected final UndoManager manager;

    public UndoSimTableAction(UndoManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            manager.undo();
        } catch (CannotUndoException ex)
        {
            ex.printStackTrace();
        }
    }
}
