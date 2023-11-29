package graphic.panels;

import consts.GUI_CONST;
import data.Doc;
import exceptions.DataException;
import process.DocProcess;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Enumeration;

public class ListDocPanel extends MyPanel {
    final Enumeration<Doc> listDocs;
    static final String[] columnNames = {
            "file name",
            "creator",
            "create time",
            "description",
            "id"
    };
    final String[][] contents;
    final JTable tableDocs;
    final JScrollPane scrollPane;

    public ListDocPanel() {
        super();
        try {
            contents = new String[DocProcess.getLengthOfDocList()][columnNames.length];
            listDocs = DocProcess.getAllDocs();
        } catch (DataException e) {
            bounceUpMsg(e.getMessage()); // TODO: 0029 11/29
            throw new RuntimeException(e);
        }

        int i = 0;
        while (listDocs.hasMoreElements()) {
            Doc doc = listDocs.nextElement();
            contents[i][0] = doc.getFileName();
            contents[i][1] = doc.getCreator();
            contents[i][2] = doc.getTimestamp().toString();
            contents[i][3] = doc.getDescription();
            contents[i][4] = doc.getID();
            i++;
        }

        tableDocs = new JTable(contents, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableDocs.setBackground(GUI_CONST.BG_COLOR);
        tableDocs.setForeground(GUI_CONST.FONT_COLOR);
        tableDocs.setFont(GUI_CONST.FONT);

        JTableHeader tableHeaderDocs = tableDocs.getTableHeader();
        tableHeaderDocs.setBackground(GUI_CONST.ALT_BG_COLOR);
        tableHeaderDocs.setForeground(GUI_CONST.FONT_COLOR);
        tableDocs.setRowHeight(GUI_CONST.FONT_SIZE * 5 / 4);
        tableHeaderDocs.setFont(GUI_CONST.FONT);
        tableHeaderDocs.setBorder(GUI_CONST.TF_BORDER);

        scrollPane = new JScrollPane(tableDocs);
        scrollPane.setBackground(GUI_CONST.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        setLayout(new BorderLayout());
        add(scrollPane);
        for (Component c : scrollPane.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
        }

        setVisible(true);
    }

    @Override
    public void confirmTriggered() {

    }
}
