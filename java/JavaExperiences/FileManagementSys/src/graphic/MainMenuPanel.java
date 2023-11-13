package graphic;

import consts.GUI_CONST;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends MyPanel {
    final SpringLayout springLayout = new SpringLayout();
    final JRadioButton login = new JRadioButton("Login", true);
    final JRadioButton signUp = new JRadioButton("Sign up");
    final JRadioButton exit = new JRadioButton("exit");
    final ButtonGroup mainMenuGroup = new ButtonGroup();

    public MainMenuPanel() {
        super();

        setLayout(springLayout);
        mainMenuGroup.add(login);
        mainMenuGroup.add(signUp);
        mainMenuGroup.add(exit);

        Box menuBox = Box.createVerticalBox();
        menuBox.add(login);
        menuBox.add(signUp);
        menuBox.add(exit);
        add(menuBox);

        for (Component c : menuBox.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
        }

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuBox, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, menuBox, 0, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void confirmTriggered() {
        if (login.isSelected()) {
            myFrame.replacePanel(new LoginPanel());
        } else if (signUp.isSelected()) {
            myFrame.replacePanel(new CreateUserPanel());
        } else if (exit.isSelected()) {
            myFrame.dispose();
        }
    }

    @Override
    public void cancelTriggered() {
        myFrame.dispose();
    }
}
