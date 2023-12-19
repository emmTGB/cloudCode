package graphic.panels;

import consts.GUI_CONST;
import exceptions.MyException;
import graphic.utilities.MyPasswordField;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ChangePassPanel extends MyPanel {
    final User user;
    final SpringLayout springLayout = new SpringLayout();
    final JLabel labelCur;
    final JLabel labelNew;
    final JLabel labelRep;
    final MyPasswordField textCur;
    final MyPasswordField textNew;
    final MyPasswordField textRep;
    final JCheckBox checkShowPass;
    static final String HINT_CUR = "type current password";
    static final String HINT_NEW = "type new password";
    static final String HINT_REP = "repeat your new password";

    public ChangePassPanel(User user) {
        super();
        this.user = user;
        setLayout(springLayout);
        setPreferredSize(new Dimension(GUI_CONST.WIDTH, GUI_CONST.HEIGHT));

        labelCur = new JLabel("Current Password:");
        labelNew = new JLabel("New Password:");
        labelRep = new JLabel("Repeat:");

        textCur = new MyPasswordField(HINT_CUR);
        textNew = new MyPasswordField(HINT_NEW);
        textRep = new MyPasswordField(HINT_REP);

        checkShowPass = new JCheckBox("Show Password");

        JPanel inputPane = new JPanel();
        {
            GroupLayout inputLayout = new GroupLayout(inputPane);
            inputPane.setLayout(inputLayout);
            inputPane.setBackground(GUI_CONST.BG_COLOR);

            GroupLayout.SequentialGroup hGroup = inputLayout.createSequentialGroup();
            hGroup.addGap(5);
            hGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelCur).addComponent(labelNew).addComponent(labelRep));
            hGroup.addGap(5);
            hGroup.addGroup(inputLayout.createParallelGroup().addComponent(textCur).addComponent(textNew).addComponent(textRep));
            hGroup.addGap(5);
            hGroup.addGroup(inputLayout.createParallelGroup().addComponent(checkShowPass));
            hGroup.addGap(5);

            inputLayout.setHorizontalGroup(hGroup);

            GroupLayout.SequentialGroup vGroup = inputLayout.createSequentialGroup();
            vGroup.addGap(10);
            vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelCur).addComponent(textCur));
            vGroup.addGap(10);
            vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelNew).addComponent(textNew).addComponent(checkShowPass));
            vGroup.addGap(10);
            vGroup.addGroup(inputLayout.createParallelGroup().addComponent(labelRep).addComponent(textRep));
            vGroup.addGap(10);

            inputLayout.setVerticalGroup(vGroup);
        }
        add(inputPane);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, inputPane, 0, SpringLayout.VERTICAL_CENTER, this);

        checkShowPass.addItemListener(e -> {
            for (Component c : inputPane.getComponents()) {
                if (c instanceof MyPasswordField myPasswordField) {
                    myPasswordField.toggleShowPass(e.getStateChange() == ItemEvent.SELECTED);
                }
            }
        });
        checkShowPass.setFocusTraversalKeysEnabled(false);

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
            if (c instanceof JCheckBox cb) {
                cb.setFont(GUI_CONST.FONT_SMALL);
            }
        }
    }

    @Override
    public void confirmTriggered() {
        try {
            boolean curNotTyped = textCur.notTyped();
            boolean newNotTyped = textNew.notTyped();
            boolean repNotTyped = textRep.notTyped();
            if (curNotTyped || newNotTyped || repNotTyped) {
                showMsg(
                        "Please"
                                + ((curNotTyped || newNotTyped) ? "" : "input your")
                                + (curNotTyped ? " current password" : "")
                                + (curNotTyped && newNotTyped ? " and" : "")
                                + (newNotTyped ? " new password" : "")
                                + ((curNotTyped || newNotTyped) && repNotTyped ? " and" : "")
                                + (repNotTyped ? " repeat your new pass word" : "")
                );
                return;
            }
            String curPass = new String(textCur.getPassword());
            String newPass = new String(textNew.getPassword());
            String repPass = new String(textRep.getPassword());
            if (curPass.equals(newPass)) {
                showMsg("You Did Not Change Your Password!");
                return;
            } else if (!newPass.equals(repPass)) {
                showMsg("You Should Enter Two SAME New Password!");
                return;
            }
            user.resetPassWord(new String(textNew.getPassword()));
            labelMsg.setVisible(false);
            bounceUpMsg("Succeeded!");
            myFrame.rollBack();
        } catch (MyException e) {
            showMsg(e.getMessage());
        }
    }

}