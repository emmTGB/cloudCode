package Graphic;

import Consts.GUI_CONST;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonPanel extends MyPanel {
    SpringLayout springLayout = new SpringLayout();
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    JButton exit = new JButton("exit");

    public MainButtonPanel() {
        super();

//        confirm.setBackground(GUI_CONST.BG_COLOR);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.currentPanel.confirmTriggered();
            }
        });
//        cancel.setBackground(GUI_CONST.BG_COLOR);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.currentPanel.cancelTriggered();
            }
        });
//        exit.setBackground(GUI_CONST.BG_COLOR);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.dispose();
            }
        });

        setLayout(springLayout);
        add(confirm);
        add(cancel);
        add(exit);

        for (Component c : this.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
        }

        springLayout.putConstraint(SpringLayout.EAST, confirm, -5, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.WEST, cancel, 5, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.EAST, exit, -10, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, confirm, -10, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, exit, -10, SpringLayout.SOUTH, this);

        setPreferredSize(new Dimension(getWidth(), 48));
    }

    @Override
    public void confirmTriggered() {

    }

    @Override
    public void cancelTriggered() {

    }
}
