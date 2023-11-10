package Graphic;

import javax.swing.*;

import Consts.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.lang.System.*;

public class MyFrame extends JFrame {
    public MyFrame(String title) {
        super(title);
        BorderLayout panelLayout = new BorderLayout();
        setLayout(panelLayout);
        setBounds(0, 0, GUIConsts.WIDTH, GUIConsts.HEIGHT);

        JPanel root = new JPanel();
        root.setBackground(GUIConsts.BG_COLOR);
        add(root);

        JPanel panelOfButtons = new JPanel();
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        JButton exit = new JButton("exit");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("action caught!");
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Canceled");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("exit!");
                dispose();
            }
        });
        SpringLayout buttonLayout = new SpringLayout();
        panelOfButtons.setLayout(buttonLayout);
        panelOfButtons.setBackground(GUIConsts.BG_COLOR);
        panelOfButtons.add(confirm);
        panelOfButtons.add(cancel);
        panelOfButtons.add(exit);

        buttonLayout.putConstraint(SpringLayout.EAST, confirm, -5, SpringLayout.HORIZONTAL_CENTER, panelOfButtons);
        buttonLayout.putConstraint(SpringLayout.WEST, cancel, 5, SpringLayout.HORIZONTAL_CENTER, panelOfButtons);
        buttonLayout.putConstraint(SpringLayout.EAST, exit, -10, SpringLayout.EAST, panelOfButtons);
        buttonLayout.putConstraint(SpringLayout.SOUTH, confirm, -10, SpringLayout.SOUTH, panelOfButtons);
        buttonLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, panelOfButtons);
        buttonLayout.putConstraint(SpringLayout.SOUTH, exit, -10, SpringLayout.SOUTH, panelOfButtons);

        panelOfButtons.setPreferredSize(new Dimension(getWidth(), 48));

        add(panelOfButtons, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
