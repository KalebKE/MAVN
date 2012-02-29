package spreadsheetTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;

class SimTableColumnRenderer
        extends DefaultTableCellRenderer
        implements ListCellRenderer
{

    protected Border noFocusBorder, focusBorder;

    public SimTableColumnRenderer()
    {
        setOpaque(true);
        setBorder(noFocusBorder);
    }

    @Override
    public void updateUI()
    {
        super.updateUI();
        Border cell = UIManager.getBorder("TableHeader.cellBorder");
        Border focus = UIManager.getBorder("Table.focusCellHighlightBorder");

        focusBorder = new BorderUIResource.CompoundBorderUIResource(cell, focus);

        Insets i = focus.getBorderInsets(this);

        noFocusBorder = new BorderUIResource.CompoundBorderUIResource(cell, BorderFactory.createEmptyBorder(i.top, i.left, i.bottom, i.right));

        /* Alternatively, if focus shouldn't be supported:

        focusBorder = noFocusBorder = cell;

         */
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean selected, boolean focused)
    {
        JLabel label = new JLabel();
        label.setOpaque(true);
        if (value != null)
        {
            label.setText("" + value);
        }
        return this;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean selected, boolean focused, int row, int column)
    {
        JLabel label = new JLabel();
        label.setOpaque(true);
        if (value != null)
        {
            label.setText("" + value);
        }

        MatteBorder border = new MatteBorder(0, 0, 1, 1, Color.BLACK);

        label.setBorder(border);
        label.setBackground(Color.WHITE);

        if (focused)
        {
            setBorder(focusBorder);
            label.setBackground(Color.GRAY);
        } else
        {
            setBorder(noFocusBorder);
        }

        if (selected)
        {
            label.setBorder(focusBorder);
            label.setBackground(Color.GRAY);
        } else
        {
            label.setBorder(border);
        }

        return label;
    }
}
