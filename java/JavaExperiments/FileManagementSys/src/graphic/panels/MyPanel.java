package graphic.panels;

import consts.GUI_CONST;
import graphic.frames.MyFrame;
import graphic.utilities.MessageSlot;

import javax.swing.*;
import java.awt.*;

public abstract class MyPanel extends JPanel implements MessageSlot {
    public static MyFrame myFrame;
    public MyPanel prePanel;
    protected JLabel labelMsg;

    protected void showMsg(String msg) {
        labelMsg.setText(msg);
        labelMsg.setVisible(true);
    }

    protected void bounceUpMsg(String msg) {
        staticBounceUpMsg(myFrame, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void staticBounceUpMsg(Component fatherComponent, String msg, String title, int type) {
//        UIManager.put("OptionPane.background", GUI_CONST.BG_COLOR);
//        UIManager.put("Panel.background", GUI_CONST.BG_COLOR);
//        JLabel jLabel = new JLabel(msg);
//        jLabel.setForeground(GUI_CONST.FONT_COLOR);
        JOptionPane.showMessageDialog(fatherComponent, msg, title, type);
    }

    protected MyPanel() {
        setBackground(GUI_CONST.BG_COLOR);
        prePanel = null;
    }

    @Override
    public void cancelTriggered() {
        myFrame.rollBack();
    }
}
