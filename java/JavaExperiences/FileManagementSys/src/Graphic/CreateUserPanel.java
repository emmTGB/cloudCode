package Graphic;

import Consts.GUIConsts;
import Process.*;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CreateUserPanel extends MyPanel {
    SpringLayout createUserLayout = new SpringLayout();
    JLabel labelName, labelPass, labelRole;
    JTextField textName, textPass, textRole;

    public CreateUserPanel() {
        super();
        setLayout(createUserLayout);
        setPreferredSize(new Dimension(GUIConsts.WIDTH, GUIConsts.HEIGHT));

        labelName = new JLabel("User name:");
        labelPass = new JLabel("Password:");
        labelRole = new JLabel("Role:");

        textName = new JTextField();
        textName.setColumns(30);
        String nameHint = "type user name";
        textName.setText(nameHint);
        textName.setForeground(Color.gray);
        textName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textName.getText().equals(nameHint)) {
                    textName.setText("");
                    textName.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textName.getText().isEmpty()) {
                    textName.setText(nameHint);
                    textName.setForeground(Color.gray);
                }
            }
        });
        textPass = new JTextField();
        textPass.setColumns(30);
        String passHint = "type user pass";
        textPass.setText(passHint);
        textPass.setForeground(Color.gray);
        textPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textPass.getText().equals(passHint)) {
                    textPass.setText("");
                    textPass.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textPass.getText().isEmpty()) {
                    textPass.setText(passHint);
                    textPass.setForeground(Color.gray);
                }
            }
        });
        textRole = new JTextField();
        textRole.setColumns(30);
        String roleHint = "type user Role";
        textRole.setText(roleHint);
        textRole.setForeground(Color.gray);
        textRole.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textRole.getText().equals(roleHint)) {
                    textRole.setText("");
                    textRole.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textRole.getText().isEmpty()) {
                    textRole.setText(roleHint);
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
    }

    @Override
    public void confirmTriggered() {
        try {
            DataProcess.insertUser(textName.getText().trim(), textPass.getText().trim(), textRole.getText().trim());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        myFrame.rollBack();
    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}