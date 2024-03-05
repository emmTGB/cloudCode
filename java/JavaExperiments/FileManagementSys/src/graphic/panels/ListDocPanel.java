package graphic.panels;

import consts.GUI_CONST;
import data.Doc;
import exceptions.DataException;
import graphic.utilities.DIYScrollBar;
import process.DocProcess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

public class ListDocPanel extends MyPanel {
    final Enumeration<Doc> listDocs;
    static final String[] columnNames = {
            "operation",
            "file name",
            "creator",
            "create time",
            "id",
            "description"
    };
    final Object[][] contents;
    final JTable tableDocs;
    final JScrollPane scrollPane;

    public ListDocPanel() {
        super();
        try {
            listDocs = DocProcess.getAllDocs();
            contents = new Object[DocProcess.getLengthOfDocList()][columnNames.length];
        } catch (IOException | SQLException | DataException e) {
            bounceUpMsg(e.getClass() + e.getMessage());
            myFrame.rollBack();
            throw new RuntimeException(e);
        }

        int i = 0;
        while (listDocs.hasMoreElements()) {
            Doc doc = listDocs.nextElement();
            contents[i][0] = "Download";
            contents[i][1] = doc.fileName();
            contents[i][2] = doc.creator();
            contents[i][3] = doc.timestamp().toString();
            contents[i][4] = doc.ID();
            contents[i][5] = doc.description();
            i++;
        }

        tableDocs = new JTable(new DefaultTableModel(contents, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        tableDocs.setBackground(GUI_CONST.BG_COLOR);
        tableDocs.setForeground(GUI_CONST.FONT_COLOR);
        tableDocs.setFont(GUI_CONST.FONT);


        tableDocs.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer());
        tableDocs.getColumnModel().getColumn(0).setCellEditor(new ButtonEditor(new JTextField(), tableDocs));

        JTableHeader tableHeaderDocs = tableDocs.getTableHeader();
        tableHeaderDocs.setBackground(GUI_CONST.ALT_BG_COLOR);
        tableHeaderDocs.setForeground(GUI_CONST.FONT_COLOR);
        tableDocs.setRowHeight(GUI_CONST.FONT_SIZE * 5 / 4);
        tableHeaderDocs.setFont(GUI_CONST.FONT);
        tableHeaderDocs.setBorder(GUI_CONST.TF_BORDER);

        scrollPane = new JScrollPane(tableDocs);
        scrollPane.setBackground(GUI_CONST.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.getVerticalScrollBar().setUI(new DIYScrollBar());

        setLayout(new BorderLayout());
        add(scrollPane);

        for (Component c : scrollPane.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
        }
        setVisible(true);
    }

    @Override
    public void confirmTriggered() {
        int[] select = tableDocs.getSelectedRows();
        for (int i : select) {
            String ID = tableDocs.getValueAt(i, tableDocs.getColumn("id").getModelIndex()).toString();
            try {
                DocProcess.downloadDoc(ID);
            } catch (Throwable e) {
                showMsg(e.getMessage());
            }
        }
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
//            setBackground(UIManager.getColor("Button.background"));
            setBackground(table.getBackground());
        }

        setBorder(null);
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private String label;
    private int row;
    private final JTable jTable;

    public ButtonEditor(JTextField textField, JTable jTable) {
        super(textField);
        this.jTable = jTable;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            fireEditingStopped();
            handleButtonClick();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }

        label = (value == null) ? "" : value.toString();
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    private void handleButtonClick() {
        String columnName = "id";
        String IDinRow = jTable.getValueAt(row, jTable.getColumn(columnName).getModelIndex()).toString();
        int result = JOptionPane.showConfirmDialog(this.getComponent(), "Confirm to download");
        if (result != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            DocProcess.downloadDoc(IDinRow);
        } catch (FileNotFoundException | DataException e) {
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.removeRow(row);
            JOptionPane.showMessageDialog(this.getComponent(), "File Not Exist On Server!");
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(this.getComponent(), e.getMessage());
        }
    }
}
