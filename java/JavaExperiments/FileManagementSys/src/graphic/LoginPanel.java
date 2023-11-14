package graphic;

import consts.GUI_CONST;
import process.DataProcess;
import process.UserException;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginPanel extends MyPanel {
    final SpringLayout springLayout = new SpringLayout();
    final JLabel labelName;
    final JLabel labelPass;
    final JTextField textName;
    final JPasswordField textPass;
    static final String HINT_NAME = "type user name";
    static final String HINT_PASS = "type user pass";

    public LoginPanel() {
        super();
        setLayout(springLayout);
        setPreferredSize(new Dimension(GUI_CONST.WIDTH, GUI_CONST.HEIGHT));

        labelName = new JLabel("User name:");
        labelPass = new JLabel("Password:");

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
        textPass = new JPasswordField();
        textPass.setColumns(30);
        textPass.setEchoChar('\0');
        textPass.setText(HINT_PASS);
        textPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(textPass.getPassword()).equals(HINT_PASS)) {
                    textPass.setEchoChar('*');
                    textPass.setText("");
                    textPass.setForeground(GUI_CONST.FONT_COLOR);
                    textPass.setFont(GUI_CONST.FONT);
                }
                textPass.setBackground(GUI_CONST.ALT_BG_COLOR);
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textPass.getPassword().length == 0) {
                    textPass.setEchoChar('\0');
                    textPass.setText(HINT_PASS);
                    textPass.setForeground(GUI_CONST.ALT_FONT_COLOR);
                    textPass.setFont(GUI_CONST.FONT_ITALIC);
                }
                textPass.setBackground(GUI_CONST.BG_COLOR);
            }
        });

        JPanel inputPane = new JPanel();
        GroupLayout inputLayout = new GroupLayout(inputPane);
        inputPane.setLayout(inputLayout);
        inputPane.setBackground(GUI_CONST.BG_COLOR);

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

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, inputPane, 0, SpringLayout.VERTICAL_CENTER, this);

        labelMsg = new JLabel();
        add(labelMsg);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelMsg, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.NORTH, labelMsg, 10, SpringLayout.SOUTH, inputPane);
        labelMsg.setVisible(false);
        labelMsg.setForeground(GUI_CONST.ERR_COLOR);

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
        try {
            boolean nameNotTyped = textName.getText().equals(HINT_NAME) || textName.getText().isEmpty();
            boolean passNotTyped = (new String(textPass.getPassword()).equals(HINT_PASS)) || textPass.getPassword().length == 0;
            if (nameNotTyped || passNotTyped) {
                showMsg(
                        "Please input your"
                                + (nameNotTyped ? " user name" : "")
                                + (nameNotTyped && passNotTyped ? " and" : "")
                                + (passNotTyped ? " password" : "")
                );
                return;
            }
            User user = DataProcess.fetchUser(textName.getText().trim(), new String(textPass.getPassword()));
            textPass.setText("");

            switch (user.getUserRole()) {
                case ADMINISTRATOR -> myFrame.replacePanel(new AdminMenuPanel(user));
                case OPERATOR -> myFrame.replacePanel(new OperatorMenuPanel(user));
                case BROWSER -> myFrame.replacePanel(new BrowserMenuPanel(user));
            }
        } catch (UserException e) {
            // TODO
            showMsg(e.getMessage());
        }


    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}
