package Graphic;

import Consts.GUIConsts;
import Users.Administrator;
import Users.User;
import Process.*;
import com.sun.source.tree.LabeledStatementTree;
import jdk.jfr.DataAmount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AdminMenuPanel extends MyPanel {
    User admin;
    JLabel menuHint1, menuHint2;
    Box menuBox;
    String[] optionList = Administrator.OPTION_LIST;
    JRadioButton[] menuRadioButtons = new JRadioButton[optionList.length];
    SpringLayout menuLayout;

    public AdminMenuPanel(User admin) {
        super();
        this.admin = admin;

        menuLayout = new SpringLayout();
        setLayout(menuLayout);
        menuHint1 = new JLabel("Welcome to Administrator menu!");
        menuHint2 = new JLabel("Select your option");

        add(menuHint1);
        add(menuHint2);

        menuLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        menuLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        menuLayout.putConstraint(SpringLayout.NORTH, menuHint1, 10, SpringLayout.NORTH, this);
        menuLayout.putConstraint(SpringLayout.NORTH, menuHint2, 5, SpringLayout.SOUTH, menuHint1);

        menuBox = Box.createVerticalBox();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < menuRadioButtons.length; i++) {
            menuRadioButtons[i] = new JRadioButton(Administrator.OPTION_LIST[i]);
            menuBox.add(menuRadioButtons[i]);
            buttonGroup.add(menuRadioButtons[i]);
        }
        add(menuBox);

        menuLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuBox, 0, SpringLayout.HORIZONTAL_CENTER, this);
        menuLayout.putConstraint(SpringLayout.VERTICAL_CENTER, menuBox, 0, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void confirmTriggered() {
        for (int i = 0; i < menuRadioButtons.length; i++) {
            if (menuRadioButtons[i].isSelected()) {
                switch (i) {
                    case 1 -> myFrame.replacePanel(new ModifyUserPanel(admin));
                    case 2 -> myFrame.replacePanel(new DeleteUserPanel(admin));
                    case 3 -> myFrame.replacePanel(new CreateUserPanel());
                }
            }
        }
    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}

class ModifyUserPanel extends MyPanel {
    User admin;
    SpringLayout createUserLayout = new SpringLayout();
    JLabel labelName, labelPass, labelRole;
    JTextField textName, textPass, textRole;
    static final String HINT_NAME = "type user name";
    static final String HINT_PASS = "type user pass";
    static final String HINT_ROLE = "type user Role";
    JLabel labelMsg;

    public ModifyUserPanel(User admin) {
        super();
        this.admin = admin;
        setLayout(createUserLayout);
        setPreferredSize(new Dimension(GUIConsts.WIDTH, GUIConsts.HEIGHT));

        labelName = new JLabel("User name:");
        labelPass = new JLabel("Password:");
        labelRole = new JLabel("Role:");

        textName = new JTextField();
        textName.setColumns(30);
        textName.setText(HINT_NAME);
        textName.setForeground(Color.gray);
        textName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textName.getText().equals(HINT_NAME)) {
                    textName.setText("");
                    textName.setForeground(Color.BLACK);
                }
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textName.getText().isEmpty()) {
                    textName.setText(HINT_NAME);
                    textName.setForeground(Color.gray);
                }
            }
        });
        textPass = new JTextField();
        textPass.setColumns(30);
        textPass.setText(HINT_PASS);
        textPass.setForeground(Color.gray);
        textPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textPass.getText().equals(HINT_PASS)) {
                    textPass.setText("");
                    textPass.setForeground(Color.BLACK);
                }
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textPass.getText().isEmpty()) {
                    textPass.setText(HINT_PASS);
                    textPass.setForeground(Color.gray);
                }
            }
        });
        textRole = new JTextField();
        textRole.setColumns(30);
        textRole.setText(HINT_ROLE);
        textRole.setForeground(Color.gray);
        textRole.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textRole.getText().equals(HINT_ROLE)) {
                    textRole.setText("");
                    textRole.setForeground(Color.BLACK);
                }
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textRole.getText().isEmpty()) {
                    textRole.setText(HINT_ROLE);
                    textRole.setForeground(Color.gray);
                }
            }
        });

        JPanel inputPane = new JPanel();
        GroupLayout inputLayout = new GroupLayout(inputPane);
        inputPane.setLayout(inputLayout);
        inputPane.setBackground(GUIConsts.BG_COLOR);

        GroupLayout.SequentialGroup hGroup = inputLayout.createSequentialGroup();
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelName).addComponent(labelPass).addComponent(labelRole));
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(textName).addComponent(textPass).addComponent(textRole));
        hGroup.addGap(5);

        inputLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = inputLayout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelName).addComponent(textName));
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelPass).addComponent(textPass));
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelRole).addComponent(textRole));
        vGroup.addGap(10);

        inputLayout.setVerticalGroup(vGroup);

        add(inputPane);

        createUserLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
        createUserLayout.putConstraint(SpringLayout.VERTICAL_CENTER, inputPane, 0, SpringLayout.VERTICAL_CENTER, this);

        labelMsg = new JLabel();
        add(labelMsg);
        createUserLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelMsg, 0, SpringLayout.HORIZONTAL_CENTER, this);
        createUserLayout.putConstraint(SpringLayout.NORTH, labelMsg, 10, SpringLayout.SOUTH, inputPane);
        labelMsg.setForeground(Color.RED);
        labelMsg.setVisible(false);
    }

    @Override
    public void confirmTriggered() {
        String name = textName.getText().trim(), pass = textPass.getText().trim(), role = textRole.getText().trim();
        if (role.equalsIgnoreCase("Administrator")) {
            labelMsg.setText("Admin Cannot Be Modified!");
            labelMsg.setVisible(true);
            return;
        } else
            try {
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
            } catch (UserException e) {
                //todo
                labelMsg.setText(e.getMessage());
                labelMsg.setVisible(true);
            }
    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}

class DeleteUserPanel extends MyPanel {
    User admin;
    SpringLayout deleteUserLayout = new SpringLayout();
    JLabel labelName;
    JTextField textName;
    static final String HINT_NAME = "type user name";
    JLabel labelMsg;

    public DeleteUserPanel(User admin) {
        super();
        this.admin = admin;
        setLayout(deleteUserLayout);
        setPreferredSize(new Dimension(GUIConsts.WIDTH, GUIConsts.HEIGHT));

        labelName = new JLabel("User name:");
        textName = new JTextField();
        textName.setColumns(30);
        textName.setText(HINT_NAME);
        textName.setForeground(Color.GRAY);
        textName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textName.getText().equals(HINT_NAME)) {
                    textName.setText("");
                    textName.setForeground(Color.BLACK);
                }
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textName.getText().isEmpty()) {
                    textName.setText(HINT_NAME);
                    textName.setForeground(Color.gray);
                }
            }
        });

        JPanel inputPane = new JPanel();
        GroupLayout inputLayout = new GroupLayout(inputPane);
        inputPane.setLayout(inputLayout);
        inputPane.setBackground(GUIConsts.BG_COLOR);

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
        deleteUserLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
        deleteUserLayout.putConstraint(SpringLayout.VERTICAL_CENTER, inputPane, 0, SpringLayout.VERTICAL_CENTER, this);

        labelMsg = new JLabel();
        add(labelMsg);
        deleteUserLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelMsg, 0, SpringLayout.HORIZONTAL_CENTER, this);
        deleteUserLayout.putConstraint(SpringLayout.NORTH, labelMsg, 10, SpringLayout.SOUTH, inputPane);
        labelMsg.setForeground(Color.RED);
        labelMsg.setVisible(false);
    }

    @Override
    public void confirmTriggered() {
        boolean nameNotTyped = textName.getText().equals(HINT_NAME);
        if (nameNotTyped) {
            labelMsg.setText(
                    "Please input your"
                            + " user name"
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
