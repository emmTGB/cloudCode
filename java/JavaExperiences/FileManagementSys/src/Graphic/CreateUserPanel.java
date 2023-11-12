package Graphic;

import Consts.GUIConsts;
import Process.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CreateUserPanel extends MyPanel {
    SpringLayout createUserLayout = new SpringLayout();
    JLabel labelName, labelPass, labelRole;
    JLabel labelMsg;
    JTextField textName, textPass, textRole;
    static final String HINT_NAME = "type user name";
    static final String HINT_PASS = "type user pass";
    static final String HINT_ROLE = "type user Role";

    public CreateUserPanel() {
        super();
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
        labelMsg.setVisible(false);
        labelMsg.setForeground(Color.RED);
    }

    @Override
    public void confirmTriggered() {
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
            DataProcess.insertUser(textName.getText().trim(), textPass.getText().trim(), textRole.getText().trim());
            bounceUpMsg("Succeeded!");
            myFrame.rollBack();  //todo
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