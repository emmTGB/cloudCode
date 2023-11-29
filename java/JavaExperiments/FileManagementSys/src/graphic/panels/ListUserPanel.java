package graphic.panels;

import consts.GUI_CONST;
import exceptions.DataException;
import exceptions.UserException;
import graphic.utilities.DIYScrollBar;
import process.UserProcess;
import users.User;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Enumeration;

public class ListUserPanel extends MyPanel {
    final Enumeration<User> listUsers;
    static final String[] columnNames = {"user name", "password", "role"};
    final String[][] contents;
    final JTable tableUsers;
    final JScrollPane scrollPane;

    public ListUserPanel() {
        super();
        try {
            contents = new String[UserProcess.getLengthOfUserList()][columnNames.length];
            listUsers = UserProcess.getAllUsers();
        } catch (DataException | UserException e) {
            bounceUpMsg(e.getMessage()); // TODO: 0029 11/29
            throw new RuntimeException(e);
        }

        int i = 0;
        while (listUsers.hasMoreElements()) {
            User user = listUsers.nextElement();
            contents[i][0] = user.getUserName();
            contents[i][1] = user.getPassWord();
            contents[i][2] = user.getUserRole().toString();
            i++;
        }

        tableUsers = new JTable(contents, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableUsers.setBackground(GUI_CONST.BG_COLOR);
        tableUsers.setForeground(GUI_CONST.FONT_COLOR);
        tableUsers.setRowHeight(GUI_CONST.FONT_SIZE * 5 / 4);
        tableUsers.setFont(GUI_CONST.FONT);

        JTableHeader tableHeaderUsers = tableUsers.getTableHeader();
        tableHeaderUsers.setBackground(GUI_CONST.ALT_BG_COLOR);
        tableHeaderUsers.setForeground(GUI_CONST.FONT_COLOR);
        tableUsers.setRowHeight(GUI_CONST.FONT_SIZE * 5 / 4);
        tableHeaderUsers.setFont(GUI_CONST.FONT);
        tableHeaderUsers.setBorder(GUI_CONST.TF_BORDER);

        tableUsers.getColumn(columnNames[0]).setMinWidth(100);
        tableUsers.getColumn(columnNames[1]).setMinWidth(100);
        tableUsers.getColumn(columnNames[2]).setMinWidth(100);

        scrollPane = new JScrollPane(tableUsers);
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
        cancelTriggered();
    }
}
