package Graphic;

import Consts.GUIConsts;
import Process.DataProcess;
import Users.*;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

        add(labelName);
        add(labelPass);
        add(textName);
        add(textPass);

        loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textName, 0, SpringLayout.HORIZONTAL_CENTER, this);
        loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textPass, 0, SpringLayout.HORIZONTAL_CENTER, this);
        loginLayout.putConstraint(SpringLayout.EAST, labelName, -5, SpringLayout.WEST, textName);
        loginLayout.putConstraint(SpringLayout.EAST, labelPass, -5, SpringLayout.WEST, textPass);

        loginLayout.putConstraint(SpringLayout.SOUTH, textName, -5, SpringLayout.VERTICAL_CENTER, this);
        loginLayout.putConstraint(SpringLayout.NORTH, textPass, 5, SpringLayout.VERTICAL_CENTER, this);
        loginLayout.putConstraint(SpringLayout.SOUTH, labelName, 0, SpringLayout.SOUTH, textName);
        loginLayout.putConstraint(SpringLayout.SOUTH, labelPass, 0, SpringLayout.SOUTH, textPass);
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
        myFrame.replacePanel(new MainMenuPanel());
    }
}
