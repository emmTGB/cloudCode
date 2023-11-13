package graphic;

import consts.GUI_CONST;
import process.DataProcess;
import process.UserException;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ChangePassPanel extends MyPanel {
    final User user;
    final SpringLayout springLayout = new SpringLayout();
    final JLabel labelCur;
    final JLabel labelNew;
    final JLabel labelRep;
    JLabel labelMsg;
    final JPasswordField textCur;
    final JPasswordField textNew;
    final JPasswordField textRep;
    static final String HINT_CUR = "type current password";
    static final String HINT_NEW = "type new password";
    static final String HINT_REP = "repeat your new password";

    public ChangePassPanel(User user) {
        super();
        this.user = user;
        setLayout(springLayout);
        setPreferredSize(new Dimension(GUI_CONST.WIDTH, GUI_CONST.HEIGHT));

        labelCur = new JLabel("User name:");
        labelNew = new JLabel("Password:");
        labelRep = new JLabel("Role:");

        textCur = new JPasswordField();
        textCur.setColumns(30);
        textCur.setEchoChar('\0');
        textCur.setText(HINT_CUR);
        textCur.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(textCur.getPassword()).equals(HINT_CUR)) {
                    textCur.setEchoChar('*');
                    textCur.setText("");
                    textCur.setForeground(GUI_CONST.FONT_COLOR);
                    textCur.setFont(GUI_CONST.FONT);
                }
                textCur.setBackground(GUI_CONST.ALT_BG_COLOR);
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textCur.getPassword().length == 0) {
                    textCur.setEchoChar('\0');
                    textCur.setText(HINT_CUR);
                    textCur.setForeground(GUI_CONST.ALT_FONT_COLOR);
                    textCur.setFont(GUI_CONST.FONT_ITALIC);
                }
                textCur.setBackground(GUI_CONST.BG_COLOR);
            }
        });
        textNew = new JPasswordField();
        textNew.setColumns(30);
        textNew.setEchoChar('\0');
        textNew.setText(HINT_NEW);
        textNew.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(textNew.getPassword()).equals(HINT_NEW)) {
                    textNew.setEchoChar('*');
                    textNew.setText("");
                    textNew.setForeground(GUI_CONST.FONT_COLOR);
                    textNew.setFont(GUI_CONST.FONT);
                }
                textNew.setBackground(GUI_CONST.ALT_BG_COLOR);
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textNew.getPassword().length == 0) {
                    textNew.setEchoChar('\0');
                    textNew.setText(HINT_NEW);
                    textNew.setForeground(GUI_CONST.ALT_FONT_COLOR);
                    textNew.setFont(GUI_CONST.FONT_ITALIC);
                }
                textNew.setBackground(GUI_CONST.BG_COLOR);
            }
        });
        textRep = new JPasswordField();
        textRep.setColumns(30);
        textRep.setEchoChar('\0');
        textRep.setText(HINT_REP);
        textRep.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(textRep.getPassword()).equals(HINT_REP)) {
                    textRep.setEchoChar('*');
                    textRep.setText("");
                    textRep.setForeground(GUI_CONST.FONT_COLOR);
                    textRep.setFont(GUI_CONST.FONT);
                }
                textRep.setBackground(GUI_CONST.ALT_BG_COLOR);
                labelMsg.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textRep.getPassword().length == 0) {
                    textRep.setEchoChar('\0');
                    textRep.setText(HINT_REP);
                    textRep.setForeground(GUI_CONST.ALT_FONT_COLOR);
                    textRep.setFont(GUI_CONST.FONT_ITALIC);
                }
                textRep.setBackground(GUI_CONST.BG_COLOR);
            }
        });

        JPanel inputPane = new JPanel();
        GroupLayout inputLayout = new GroupLayout(inputPane);
        inputPane.setLayout(inputLayout);
        inputPane.setBackground(GUI_CONST.BG_COLOR);

        GroupLayout.SequentialGroup hGroup = inputLayout.createSequentialGroup();
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelCur).addComponent(labelNew).addComponent(labelRep));
        hGroup.addGap(5);
        hGroup.addGroup(inputLayout.createParallelGroup().addComponent(textCur).addComponent(textNew).addComponent(textRep));
        hGroup.addGap(5);

        inputLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = inputLayout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelCur).addComponent(textCur));
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelNew).addComponent(textNew));
        vGroup.addGap(10);
        vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelRep).addComponent(textRep));
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
            if (c instanceof JPasswordField tf) {
                tf.setForeground(GUI_CONST.ALT_FONT_COLOR);
                tf.setFont(GUI_CONST.FONT_ITALIC);
                tf.setBorder(GUI_CONST.TF_BORDER);
            }
        }
    }

    @Override
    public void confirmTriggered() {  // TODO
        try {
            boolean nameNotTyped = new String(textCur.getPassword()).equals(HINT_CUR) || textCur.getPassword().length == 0;
            boolean passNotTyped = new String(textNew.getPassword()).equals(HINT_NEW) || textNew.getPassword().length == 0;
            boolean roleNotTyped = new String(textRep.getPassword()).equals(HINT_REP) || textRep.getPassword().length == 0;
            if (nameNotTyped || passNotTyped || roleNotTyped) {
                labelMsg.setText(
                        "Please input your"
                                + (nameNotTyped ? " current password" : "")
                                + (nameNotTyped && passNotTyped ? " and" : "")
                                + (passNotTyped ? " new password" : "")
                                + ((nameNotTyped || passNotTyped) && roleNotTyped ? " and" : "")
                                + (roleNotTyped ? " repeat your new pass word" : "")
                );
                labelMsg.setVisible(true);
                return;
            }
            DataProcess.insertUser(textCur.getText().trim(), textNew.getText().trim(), textRep.getText().trim());
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