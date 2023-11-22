package graphic.panels;

import consts.GUI_CONST;
import graphic.frames.MyFrame;
import graphic.utilities.MessageSlot;

import javax.swing.*;

public abstract class MyPanel extends JPanel implements MessageSlot {
    public static MyFrame myFrame;
    public MyPanel prePanel;
    protected JLabel labelMsg;

    protected void showMsg(String msg) {
        labelMsg.setText(msg);
        labelMsg.setVisible(true);
    }

    protected void bounceUpMsg(String msg) {
        JOptionPane.showMessageDialog(myFrame, msg);
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
