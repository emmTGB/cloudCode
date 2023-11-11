package Graphic;

import Consts.GUIConsts;
import Process.DataProcess;
import Users.*;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.LinkedTransferQueue;

public class LoginPanel extends MyPanel {
    SpringLayout loginLayout = new SpringLayout();
    JLabel labelName, labelPass;
    JTextField textName, textPass;

    public LoginPanel() {
        super();
        setLayout(loginLayout);
        setPreferredSize(new Dimension(GUIConsts.WIDTH, GUIConsts.HEIGHT));

        labelName = new JLabel("User name:");
        labelPass = new JLabel("Password:");

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
    }

    @Override
    public void confirmTriggered() {
        User user = DataProcess.fetchUser(textName.getText().trim(), textPass.getText().trim());
        String role = "";
        if (user != null) {
            role = user.getUserRole();
        } else {
            //TODO
            System.out.println("wrong");
        }
        switch (role) {
            case "Administrator":
                myFrame.replacePanel(new AdminMenuPanel(user));
                break;
            case "Operator":
            case "Browser":
            default:
                break;
        }

    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}
