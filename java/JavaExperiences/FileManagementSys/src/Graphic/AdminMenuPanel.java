package Graphic;

import Consts.GUIConsts;
import Users.Administrator;
import Users.User;

import javax.swing.*;
import java.awt.*;

public class AdminMenuPanel extends MyPanel {
    User admin;
    JLabel menuHint1, menuHint2;
    JButton[] menuButtons = new JButton[Administrator.OPTION_LIST.length];
    SpringLayout menuLayout;

    public AdminMenuPanel(User admin) {
        super();
        this.admin = admin;

        menuLayout = new SpringLayout();
        setLayout(menuLayout);
        menuHint1 = new JLabel("Welcome to Administrator menu!");
        menuHint2 = new JLabel("Select your option");

        add(menuHint1);
        add(menuHint2);

        menuLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        menuLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        menuLayout.putConstraint(SpringLayout.NORTH, menuHint1, 10, SpringLayout.NORTH, this);
        menuLayout.putConstraint(SpringLayout.NORTH, menuHint2, 5, SpringLayout.SOUTH, menuHint1);

        Box menuBox = Box.createVerticalBox();
        for (int i = 1; i < menuButtons.length; i++) {
            menuButtons[i] = new JButton(Administrator.OPTION_LIST[i]);
            menuBox.add(menuButtons[i]);
        }
        add(menuBox);

        menuLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuBox, 0, SpringLayout.HORIZONTAL_CENTER, this);
        menuLayout.putConstraint(SpringLayout.VERTICAL_CENTER, menuBox, 0, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void confirmTriggered() {

    }

    @Override
    public void cancelTriggered() {

    }
}
