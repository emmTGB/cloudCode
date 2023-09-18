package SnakePath.field;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class View extends JPanel {
    
    public View(){

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(39, 39, 39));
    }
}
