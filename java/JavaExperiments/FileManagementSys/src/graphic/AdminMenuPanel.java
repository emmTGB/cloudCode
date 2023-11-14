package graphic;

import consts.GUI_CONST;
import consts.Role;
import process.DataProcess;
import process.UserException;
import users.Administrator;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AdminMenuPanel extends MyPanel {
    final User admin;
    final JLabel menuHint1;
    final JLabel menuHint2;
    final Box menuBox;
    final String[] optionList = Administrator.OPTION_LIST;
    final JRadioButton[] menuRadioButtons = new JRadioButton[optionList.length];
    final SpringLayout springLayout;

    public AdminMenuPanel(User admin) {
        super();
        this.admin = admin;

        springLayout = new SpringLayout();
        setLayout(springLayout);
        menuHint1 = new JLabel("Welcome to Administrator menu!");
        menuHint2 = new JLabel("Select your option");

        add(menuHint1);
        add(menuHint2);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.NORTH, menuHint1, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, menuHint2, 5, SpringLayout.SOUTH, menuHint1);

        menuBox = Box.createVerticalBox();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < menuRadioButtons.length; i++) {
            menuRadioButtons[i] = new JRadioButton(optionList[i]);
            menuBox.add(menuRadioButtons[i]);
            buttonGroup.add(menuRadioButtons[i]);
        }
        menuBox.add(menuRadioButtons[0]);
        menuBox.setAutoscrolls(true);
        add(menuBox);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuBox, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, menuBox, 0, SpringLayout.VERTICAL_CENTER, this);

        for (Component c : menuBox.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
        }
        for (Component c : this.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
        }
    }

    private void listUsers() {
        UserListFrame userListFrame = new UserListFrame(myFrame);
        myFrame.setVisible(false);
        userListFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                myFrame.setLocation(userListFrame.getLocationOnScreen());
                myFrame.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    @Override
    public void confirmTriggered() {
        for (int i = 0; i < menuRadioButtons.length; i++) {
            if (menuRadioButtons[i].isSelected()) {
                switch (i) {
                    case 1 -> myFrame.replacePanel(new ModifyUserPanel(admin));
                    case 2 -> myFrame.replacePanel(new DeleteUserPanel(admin));
                    case 3 -> myFrame.replacePanel(new CreateUserPanel());
                    case 4 -> listUsers();
                    case 7 -> myFrame.replacePanel(new ChangePassPanel(admin));
                    case 0 -> myFrame.rollBack();
                }
            }
        }
    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}

class ModifyUserPanel extends CreateUserPanel {
    final User user;

    public ModifyUserPanel(User user) {
        super();
        this.user = user;
    }

    @Override
    public void confirmTriggered() {

        try {
            {
                boolean nameNotTyped = textName.getText().equals(HINT_NAME);
                boolean passNotTyped = textPass.getText().equals(HINT_PASS);
                boolean roleNotTyped = textRole.getText().equals(HINT_ROLE);
                if (nameNotTyped || passNotTyped || roleNotTyped) {
                    labelMsg.setText(
                            "Please input your"
                                    + (nameNotTyped ? " user name" : "")
                                    + (nameNotTyped && passNotTyped ? " and" : "")
                                    + (passNotTyped ? " password" : "")
                                    + ((nameNotTyped || passNotTyped) && roleNotTyped ? " and" : "")
                                    + (roleNotTyped ? " role" : "")
                    );
                    labelMsg.setVisible(true);
                    return;
                }
                DataProcess.updateUser(textName.getText().trim(), textPass.getText().trim(), textRole.getText().trim());
                bounceUpMsg("Succeeded!");
                myFrame.rollBack(); //todo
            }
        } catch (UserException e) {
            //todo
            labelMsg.setText(e.getMessage());
            labelMsg.setVisible(true);
        }
    }
}

class DeleteUserPanel extends MyPanel {
    final User user;
    final SpringLayout springLayout = new SpringLayout();
    final JLabel labelName;
    final JTextField textName;
    static final String HINT_NAME = "type user name";
    JLabel labelMsg;

    public DeleteUserPanel(User user) {
        super();
        this.user = user;
        setLayout(springLayout);
        setPreferredSize(new Dimension(GUI_CONST.WIDTH, GUI_CONST.HEIGHT));

        labelName = new JLabel("User name:");

        textName = new JTextField();
        textName.setColumns(30);
        textName.setText(HINT_NAME);
        textName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textName.getText().equals(HINT_NAME)) {
                    textName.setText("");
                    textName.setForeground(GUI_CONST.FONT_COLOR);
                    textName.setFont(GUI_CONST.FONT);
                }
                textName.setBackground(GUI_CONST.ALT_BG_COLOR);
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textName.getText().isEmpty()) {
                    textName.setText(HINT_NAME);
                    textName.setForeground(GUI_CONST.ALT_FONT_COLOR);
                    textName.setFont(GUI_CONST.FONT_ITALIC);
                }
                textName.setBackground(GUI_CONST.BG_COLOR);
            }
        });

        JPanel inputPane = new JPanel();
        GroupLayout inputLayout = new GroupLayout(inputPane);
        inputPane.setLayout(inputLayout);
        inputPane.setBackground(GUI_CONST.BG_COLOR);

        GroupLayout.SequentialGroup hGroup = inputLayout.createSequentialGroup();
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelName));
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(textName));
        hGroup.addGap(5);

        inputLayout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup = inputLayout.createParallelGroup();
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelName).addComponent(textName));
        vGroup.addGap(10);

        inputLayout.setVerticalGroup(vGroup);

        add(inputPane);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, inputPane, 0, SpringLayout.VERTICAL_CENTER, this);

        labelMsg = new JLabel();
        add(labelMsg);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelMsg, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.NORTH, labelMsg, 10, SpringLayout.SOUTH, inputPane);
        labelMsg.setForeground(GUI_CONST.ERR_COLOR);
        labelMsg.setVisible(false);

        for (Component c : inputPane.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
            if (c instanceof JTextField tf) {
                tf.setForeground(GUI_CONST.ALT_FONT_COLOR);
                tf.setFont(GUI_CONST.FONT_ITALIC);
                tf.setBorder(GUI_CONST.TF_BORDER);
            }
        }
    }

    @Override
    public void confirmTriggered() {
        boolean nameNotTyped = textName.getText().equals(HINT_NAME) || textName.getText().isEmpty();
        if (nameNotTyped) {
            labelMsg.setText(
                    "Please input your"
                            + " user name"
            );
            labelMsg.setVisible(true);
            return;
        }
        if (textName.getText().trim().equals(user.getUserName())) {
            labelMsg.setText(
                    "You Cannot Delete Your Self"
            );
            labelMsg.setVisible(true);
            return;
        }
        try {
            DataProcess.deleteUser(textName.getText().trim());
            bounceUpMsg("Succeeded");
            myFrame.rollBack();
        } catch (UserException e) {
            labelMsg.setText(e.getMessage());
            labelMsg.setVisible(true);
        }
    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}
