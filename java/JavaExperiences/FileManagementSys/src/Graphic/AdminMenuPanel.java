package Graphic;

import Users.User;

import javax.swing.*;

public class AdminMenuPanel extends MyPanel {
    User admin;
    JLabel menuHint1, menuHint2;

    public AdminMenuPanel(User admin) {
        super();
        this.admin = admin;


    }

    @Override
    public void confirmTriggered() {

    }

    @Override
    public void cancelTriggered() {

    }
}
