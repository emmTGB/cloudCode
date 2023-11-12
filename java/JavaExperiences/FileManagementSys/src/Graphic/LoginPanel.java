package Graphic;

import Consts.GUIConsts;
import Process.DataProcess;
import Users.*;
import Process.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginPanel extends MyPanel {
    SpringLayout loginLayout = new SpringLayout();
    JLabel labelName, labelPass;
    JTextField textName, textPass;
    JLabel labelMsg;
    static final String HINT_NAME = "type user name";
    static final String HINT_PASS = "type user pass";

    public LoginPanel() {
        super();
        setLayout(loginLayout);
        setPreferredSize(new Dimension(GUIConsts.WIDTH, GUIConsts.HEIGHT));

        labelName = new JLabel("User name:");
        labelPass = new JLabel("Password:");

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

        JPanel inputPane = new JPanel();
        GroupLayout inputLayout = new GroupLayout(inputPane);
        inputPane.setLayout(inputLayout);
        inputPane.setBackground(GUIConsts.BG_COLOR);

        GroupLayout.SequentialGroup hGroup = inputLayout.createSequentialGroup();
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelName).addComponent(labelPass));
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(textName).addComponent(textPass));
        hGroup.addGap(5);

        inputLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = inputLayout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelName).addComponent(textName));
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelPass).addComponent(textPass));
        vGroup.addGap(10);

        inputLayout.setVerticalGroup(vGroup);

        add(inputPane);

        loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
        loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, inputPane, 0, SpringLayout.VERTICAL_CENTER, this);

        labelMsg = new JLabel();
        add(labelMsg);
        loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelMsg, 0, SpringLayout.HORIZONTAL_CENTER, this);
        loginLayout.putConstraint(SpringLayout.NORTH, labelMsg, 10, SpringLayout.SOUTH, inputPane);
        labelMsg.setVisible(false);
        labelMsg.setForeground(Color.RED);
    }

    @Override
    public void confirmTriggered() {
        try {
            boolean nameNotTyped = textName.getText().equals(HINT_NAME);
            boolean passNotTyped = textPass.getText().equals(HINT_PASS);
            if (nameNotTyped || passNotTyped) {
                labelMsg.setText(
                        "Please input your"
                                + (nameNotTyped ? " user name" : "")
                                + (nameNotTyped && passNotTyped ? " and" : "")
                                + (passNotTyped ? " password" : "")
                );
                labelMsg.setVisible(true);
                return;
            }
            User user = DataProcess.fetchUser(textName.getText().trim(), textPass.getText().trim());
            String role = user.getUserRole();

            switch (role) {
                case "Administrator":
                    myFrame.replacePanel(new AdminMenuPanel(user));
                    break;
                case "Operator":
                case "Browser":
                default:
                    break;
            }
        } catch (UserException e) {
            // TODO
            labelMsg.setText(e.getMessage());
            labelMsg.setVisible(true);
        }


    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}
