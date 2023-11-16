package graphic.panels;

import consts.GUI_CONST;

import javax.swing.*;
import java.awt.*;

public class MainButtonPanel extends MyPanel {
    final SpringLayout springLayout = new SpringLayout();
    final JButton confirm = new JButton("Confirm");
    final JButton cancel = new JButton("Cancel");
    final JButton exit = new JButton("exit");

    public MainButtonPanel() {
        super();

//        confirm.setBackground(GUI_CONST.BG_COLOR);
        confirm.addActionListener(e -> myFrame.currentPanel.confirmTriggered());
//        cancel.setBackground(GUI_CONST.BG_COLOR);
        cancel.addActionListener(e -> myFrame.currentPanel.cancelTriggered());
//        exit.setBackground(GUI_CONST.BG_COLOR);
        exit.addActionListener(e -> myFrame.dispose());

        setLayout(springLayout);
        add(confirm);
        add(cancel);
        add(exit);

        for (Component c : this.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
        }

        springLayout.putConstraint(SpringLayout.EAST, confirm, -5, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.WEST, cancel, 5, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.EAST, exit, -10, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, confirm, -10, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, exit, -10, SpringLayout.SOUTH, this);

        setPreferredSize(new Dimension(getWidth(), 48));
    }

    public JButton getConfirmButton() {
        return confirm;
    }

    public JButton getCancelButton() {
        return cancel;
    }

    @Override
    public void confirmTriggered() {

    }

    @Override
    public void cancelTriggered() {

    }
}
