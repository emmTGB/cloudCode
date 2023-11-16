package graphic.panels;

import consts.GUI_CONST;
import exceptions.UserException;
import graphic.frames.UserListFrame;
import graphic.utilities.MyTextField;
import exceptions.DataException;
import exceptions.MyException;
import users.Administrator;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AdminMenuPanel extends MyPanel {
    final Administrator admin;
    final JLabel menuHint1;
    final JLabel menuHint2;
    final Box menuBox;
    final String[] optionList = Administrator.OPTION_LIST;
    final JRadioButton[] menuRadioButtons = new JRadioButton[optionList.length];
    final SpringLayout springLayout;

    public AdminMenuPanel(User admin) {
        super();
        this.admin = (Administrator) admin;

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

        labelMsg = new JLabel();
        labelMsg.setVisible(false);
        labelMsg.setForeground(GUI_CONST.ERR_COLOR);
        add(labelMsg);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelMsg, 0, SpringLayout.HORIZONTAL_CENTER, menuBox);
        springLayout.putConstraint(SpringLayout.NORTH, labelMsg, 10, SpringLayout.SOUTH, menuBox);

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

    private void listUsers() throws DataException, UserException {
        UserListFrame userListFrame = null;
        userListFrame = new UserListFrame(myFrame);
        myFrame.setVisible(false);
        UserListFrame finalUserListFrame = userListFrame;
        userListFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                myFrame.setLocation(finalUserListFrame.getLocationOnScreen());
                myFrame.setVisible(true);
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
                    case 4 -> {
                        try {
                            listUsers();
                        } catch (MyException e) {
                            showMsg(e.getMessage());
                        }
                    }
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
    final Administrator admin;

    public ModifyUserPanel(Administrator admin) {
        super();
        this.admin = admin;
    }

    @Override
    public void confirmTriggered() {

        try {
            {
                boolean nameNotTyped = textName.notTyped();
                boolean passNotTyped = textPass.notTyped();
                boolean roleNotTyped = textRole.notTyped();
                if (nameNotTyped || passNotTyped || roleNotTyped) {
                    showMsg(
                            "Please input your"
                                    + (nameNotTyped ? " user name" : "")
                                    + (nameNotTyped && passNotTyped ? " and" : "")
                                    + (passNotTyped ? " password" : "")
                                    + ((nameNotTyped || passNotTyped) && roleNotTyped ? " and" : "")
                                    + (roleNotTyped ? " role" : "")
                    );
                    return;
                }
                admin.modifyUser(textName.getText().trim(), textPass.getText().trim(), textRole.getText().trim());
                labelMsg.setVisible(false);
                bounceUpMsg("Succeeded!");
                myFrame.rollBack(); //todo
            }
        } catch (MyException e) {
            //todo
            showMsg(e.getMessage());
        }
    }
}

class DeleteUserPanel extends MyPanel {
    final Administrator admin;
    final SpringLayout springLayout = new SpringLayout();
    final JLabel labelName;
    final MyTextField textName;
    static final String HINT_NAME = "type user name";

    public DeleteUserPanel(User admin) {
        super();
        this.admin = (Administrator) admin;
        setLayout(springLayout);
        setPreferredSize(new Dimension(GUI_CONST.WIDTH, GUI_CONST.HEIGHT));

        labelName = new JLabel("User name:");

        textName = new MyTextField(HINT_NAME);

        JPanel inputPane = new JPanel();
        {
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
        }
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
            if (c instanceof JCheckBox cb) {
                cb.setFont(GUI_CONST.FONT_SMALL);
            }
        }
    }

    @Override
    public void confirmTriggered() {
        boolean nameNotTyped = textName.getText().equals(HINT_NAME) || textName.getText().isEmpty();
        if (nameNotTyped) {
            showMsg(
                    "Please input your"
                            + " user name"
            );
            return;
        }
        try {
            admin.deleteUser(textName.getText().trim());
            labelMsg.setVisible(false);
            bounceUpMsg("Succeeded");
            myFrame.rollBack();
        } catch (MyException e) {
            showMsg(e.getMessage());
        }
    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}
