package Graphic;

import javax.swing.*;

import Consts.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.lang.System.*;

public class MyFrame extends JFrame {
    JPanel root = new JPanel();
    MainButtonPanel mainButtonPanel = new MainButtonPanel();
    MyPanel currentPanel = null;

    public MyFrame(String title) {
        super(title);
        MyPanel.myFrame = this;
        BorderLayout panelLayout = new BorderLayout();
        setLayout(panelLayout);
        setBounds(0, 0, GUIConsts.WIDTH, GUIConsts.HEIGHT);

        root.setBackground(GUIConsts.BG_COLOR);
        add(root);
        add(mainButtonPanel, BorderLayout.SOUTH);
        currentPanel = new MainMenuPanel();
        add(currentPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void replacePanel(MyPanel newPanel) {
        remove(currentPanel);
        currentPanel = newPanel;
        add(currentPanel);
        SwingUtilities.updateComponentTreeUI(this);  // 更新窗口，似乎优于repaint
    }
}
