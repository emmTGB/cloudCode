package graphic.frames;

import consts.GUI_CONST;
import graphic.panels.MainButtonPanel;
import graphic.panels.MainMenuPanel;
import graphic.panels.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {
    final MainButtonPanel mainButtonPanel = new MainButtonPanel();
    public MyPanel currentPanel;

    public MyFrame(String title) {
        super(title);
        MyPanel.myFrame = this;
        setBounds(0, 0, GUI_CONST.WIDTH, GUI_CONST.HEIGHT);
        setLocationRelativeTo(null);

        add(mainButtonPanel, BorderLayout.SOUTH);
        currentPanel = new MainMenuPanel();
        add(currentPanel, BorderLayout.CENTER);

        getRootPane().setDefaultButton(mainButtonPanel.getConfirmButton());
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainButtonPanel.getCancelButton().doClick();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void replacePanel(MyPanel newPanel) {
        remove(currentPanel);
        newPanel.prePanel = currentPanel;
        currentPanel = newPanel;
        add(currentPanel);
        revalidate();
    }

    public void rollBack() {
        if (currentPanel.prePanel == null) dispose();
        else {
            remove(currentPanel);
            currentPanel = currentPanel.prePanel;
            add(currentPanel);
            repaint();
        }
    }

}
