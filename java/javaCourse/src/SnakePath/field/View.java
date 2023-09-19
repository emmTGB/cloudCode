package SnakePath.field;

import SnakePath.blocks.Snake;
import SnakePath.consts.Direction;
import SnakePath.consts.GamePara;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class View extends JPanel implements KeyListener {
    private final Snake thisSnake;
    Direction tmpDir;
    
    public View(Snake snake){
        thisSnake = snake;
        tmpDir = Direction.UP;
    }

    public boolean update(){
        thisSnake.setDirection(tmpDir);
        thisSnake.moveForward();
        this.repaint();
        return thisSnake.isGameOver();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(39, 39, 39));
        g.fillRect(0, 0, GamePara.FIELD_WIDTH * GamePara.CELL_SIZE + 1, GamePara.FIELD_HEIGHT * GamePara.CELL_SIZE + 1);
        g.setColor(new Color(63, 63, 63));
        for(int row = 0; row < GamePara.FIELD_HEIGHT; row++){
            g.drawLine(0, row * GamePara.CELL_SIZE, GamePara.FIELD_WIDTH * GamePara.CELL_SIZE + 1, row * GamePara.CELL_SIZE);
        }
        for(int col = 0; col < GamePara.FIELD_WIDTH; col++){
            g.drawLine(col * GamePara.CELL_SIZE, 0, col * GamePara.CELL_SIZE, GamePara.FIELD_HEIGHT * GamePara.CELL_SIZE + 1);
        }
        thisSnake.draw(g);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(GamePara.FIELD_WIDTH * GamePara.CELL_SIZE + 1, GamePara.FIELD_HEIGHT * GamePara.CELL_SIZE + 1);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            tmpDir = Direction.UP;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            tmpDir = Direction.DOWN;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            tmpDir = Direction.LEFT;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            tmpDir = Direction.RIGHT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
