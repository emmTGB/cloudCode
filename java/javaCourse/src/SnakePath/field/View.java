package SnakePath.field;

import SnakePath.blocks.Cell;
import SnakePath.blocks.Snake;
import SnakePath.consts.FieldPara;

import java.awt.*;
import java.lang.reflect.Field;

import javax.swing.JPanel;

public class View extends JPanel {
    private final Snake thisSnake;
    
    public View(Snake snake){
        thisSnake = snake;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(39, 39, 39));
        g.fillRect(0, 0, FieldPara.FILED_WIDTH * FieldPara.CELL_SIZE + 1, FieldPara.FILED_HEIGHT * FieldPara.CELL_SIZE + 1);
        g.setColor(new Color(63, 63, 63));
        for(int row = 0; row < FieldPara.FILED_HEIGHT; row++){
            g.drawLine(0, row * FieldPara.CELL_SIZE, FieldPara.FILED_WIDTH * FieldPara.CELL_SIZE + 1, row * FieldPara.CELL_SIZE);
        }
        for(int col = 0; col < FieldPara.FILED_WIDTH; col++){
            g.drawLine(col * FieldPara.CELL_SIZE, 0, col * FieldPara.CELL_SIZE, FieldPara.FILED_HEIGHT * FieldPara.CELL_SIZE + 1);
        }

    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(FieldPara.FILED_WIDTH * FieldPara.CELL_SIZE + 1, FieldPara.FILED_HEIGHT * FieldPara.CELL_SIZE + 1);
    }
}
