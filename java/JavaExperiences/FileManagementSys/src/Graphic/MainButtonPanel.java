package Graphic;

import Consts.GUIConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.out;

public class MainButtonPanel extends MyPanel {
    SpringLayout buttonLayout = new SpringLayout();
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    JButton exit = new JButton("exit");

    public MainButtonPanel() {
        super();

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.currentPanel.confirmTriggered();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.currentPanel.cancelTriggered();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.dispose();
            }
        });

        setLayout(buttonLayout);
        add(confirm);
        add(cancel);
        add(exit);

        buttonLayout.putConstraint(SpringLayout.EAST, confirm, -5, SpringLayout.HORIZONTAL_CENTER, this);
        buttonLayout.putConstraint(SpringLayout.WEST, cancel, 5, SpringLayout.HORIZONTAL_CENTER, this);
        buttonLayout.putConstraint(SpringLayout.EAST, exit, -10, SpringLayout.EAST, this);
        buttonLayout.putConstraint(SpringLayout.SOUTH, confirm, -10, SpringLayout.SOUTH, this);
        buttonLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, this);
        buttonLayout.putConstraint(SpringLayout.SOUTH, exit, -10, SpringLayout.SOUTH, this);

        setPreferredSize(new Dimension(getWidth(), 48));
    }

    @Override
    public void confirmTriggered() {

    }

    @Override
    public void cancelTriggered() {

    }
}
