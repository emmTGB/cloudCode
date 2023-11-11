package Graphic;

import Consts.GUIConsts;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends MyPanel {
    SpringLayout mainMenuLayout = new SpringLayout();
    JRadioButton login = new JRadioButton("Login", true);
    JRadioButton signUp = new JRadioButton("Sign up");
    JRadioButton exit = new JRadioButton("exit");
    ButtonGroup mainMenuGroup = new ButtonGroup();

    public MainMenuPanel() {
        super();

        setLayout(mainMenuLayout);
        mainMenuGroup.add(login);
        mainMenuGroup.add(signUp);
        mainMenuGroup.add(exit);

        add(login);
        add(signUp);
        add(exit);

        mainMenuLayout.putConstraint(SpringLayout.WEST, login, -20, SpringLayout.HORIZONTAL_CENTER, this);
        mainMenuLayout.putConstraint(SpringLayout.WEST, signUp, -20, SpringLayout.HORIZONTAL_CENTER, this);
        mainMenuLayout.putConstraint(SpringLayout.WEST, exit, -20, SpringLayout.HORIZONTAL_CENTER, this);
        mainMenuLayout.putConstraint(SpringLayout.VERTICAL_CENTER, signUp, 0, SpringLayout.VERTICAL_CENTER, this);
        mainMenuLayout.putConstraint(SpringLayout.SOUTH, login, -5, SpringLayout.NORTH, signUp);
        mainMenuLayout.putConstraint(SpringLayout.NORTH, exit, 5, SpringLayout.SOUTH, signUp);
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
