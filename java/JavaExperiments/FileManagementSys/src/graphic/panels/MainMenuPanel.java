package graphic.panels;

import consts.GUI_CONST;
import graphic.utilities.MyTabbedPaneUI;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends MyPanel {
    final JTabbedPane tabbedPane;
    final MyTabbedPaneUI myTabbedPaneUI = new MyTabbedPaneUI();

    public MainMenuPanel() {
        super();
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(GUI_CONST.FONT);
        tabbedPane.setUI(myTabbedPaneUI);
        LoginPanel loginPanel = new LoginPanel();
        loginPanel.setBorder(null);
        tabbedPane.add("Login", loginPanel);
        tabbedPane.add("Sign Up", new CreateUserPanel());
        add(tabbedPane, BorderLayout.CENTER);
    }

    @Override
    public void confirmTriggered() {
        ((MyPanel) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex())).confirmTriggered();
    }
}
