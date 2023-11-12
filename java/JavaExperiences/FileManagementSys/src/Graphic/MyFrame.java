package Graphic;

import Consts.GUI_CONST;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MyFrame extends JFrame {
    JPanel root = new JPanel();
    MainButtonPanel mainButtonPanel = new MainButtonPanel();
    MyPanel currentPanel = null;

    public MyFrame(String title) {
        super(title);
        MyPanel.myFrame = this;
        BorderLayout panelLayout = new BorderLayout();
        setLayout(panelLayout);
        setBounds(0, 0, GUI_CONST.WIDTH, GUI_CONST.HEIGHT);

        root.setBackground(GUI_CONST.BG_COLOR);
        add(root);
        add(mainButtonPanel, BorderLayout.SOUTH);
        currentPanel = new MainMenuPanel();
        add(currentPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void replacePanel(MyPanel newPanel) {
        remove(currentPanel);
        newPanel.prePanel = currentPanel;
        currentPanel = newPanel;
        add(currentPanel);
        SwingUtilities.updateComponentTreeUI(this);  // 更新窗口，似乎优于repaint
    }

    public void rollBack() {
        if (currentPanel.prePanel == null) dispose();
        else {
            remove(currentPanel);
            currentPanel = currentPanel.prePanel;
            add(currentPanel);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

}
