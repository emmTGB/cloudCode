package graphic.panels;

import consts.GUI_CONST;
import users.Browser;
import users.User;

import javax.swing.*;
import java.awt.*;

public class BrowserMenuPanel extends MyPanel {
    final Browser browser;
    final JLabel menuHint1;
    final JLabel menuHint2;
    final Box menuBox;
    final String[] optionList = Browser.OPTION_LIST;
    final JRadioButton[] menuRadioButtons = new JRadioButton[optionList.length];
    final SpringLayout springLayout;

    public BrowserMenuPanel(User browser) {
        super();
        this.browser = (Browser) browser;

        springLayout = new SpringLayout();
        setLayout(springLayout);
        menuHint1 = new JLabel("Welcome to Browser menu!");
        menuHint2 = new JLabel("Select your option");

        add(menuHint1);
        add(menuHint2);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuHint2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.NORTH, menuHint1, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, menuHint2, 5, SpringLayout.SOUTH, menuHint1);

        menuBox = Box.createVerticalBox();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 1; i < menuRadioButtons.length; i++) {
            menuRadioButtons[i] = new JRadioButton(optionList[i]);
            menuBox.add(menuRadioButtons[i]);
            buttonGroup.add(menuRadioButtons[i]);
        }
        menuRadioButtons[1].setSelected(true);
        menuBox.setAutoscrolls(true);
        add(menuBox);

        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuBox, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, menuBox, 0, SpringLayout.VERTICAL_CENTER, this);

        for (Component c : menuBox.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
        }
        for (Component c : this.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
        }
    }

    @Override
    public void confirmTriggered() {
        for (int i = 1; i < menuRadioButtons.length; i++) {
            if (menuRadioButtons[i].isSelected()) {
                switch (i) {
                    case 1 -> myFrame.replacePanel(new ListDocPanel());
                    case 2 -> myFrame.replacePanel(new ChangePassPanel(browser));
                }
            }
        }
    }

}
