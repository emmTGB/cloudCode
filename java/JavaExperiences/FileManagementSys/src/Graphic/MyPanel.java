package Graphic;

import Consts.GUIConsts;

import javax.swing.*;

public abstract class MyPanel extends JPanel implements MessageSlot {
    static MyFrame myFrame;
    public MyPanel prePanel;

    protected MyPanel() {
        setBackground(GUIConsts.BG_COLOR);
    }
}
