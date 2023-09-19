package SnakePath.blocks;

import SnakePath.consts.Direction;
import SnakePath.consts.FieldPara;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Snake {
    private Direction direction;
    private final Deque<Cell> snakeBody;
    private Cell dot;
    
    public Snake(){
        snakeBody = new LinkedList<>();
        direction = Direction.UP;
        snakeBody.addLast(new Cell(FieldPara.FILED_WIDTH / 2, FieldPara.FILED_HEIGHT / 2, FieldPara.ORANGE));
    }

    public boolean generateDot(){
        if(getLength() >= FieldPara.FILED_WIDTH * FieldPara.FILED_HEIGHT){
            return false;
        }
        Random rand = new Random();
        do{
            dot = new Cell(rand.nextInt(FieldPara.FILED_WIDTH), rand.nextInt(FieldPara.FILED_HEIGHT), FieldPara.GREEN);
        }while(!isInSnake(dot));
        return true;
    }

    public void setDirection(Direction dir){
        direction = dir;
    }
    public boolean moveForward(){
        Cell head = snakeBody.getFirst();
        int row = head.getRow();
        int col = head.getCol();
        switch(direction){
            case UP:
                col -= 1;
            case DOWN:
                col += 1;
            case LEFT:
                row -= 1;
            case RIGHT:
                row += 1;
            default:
                break;
        }
        Cell newCell = new Cell(row, col, FieldPara.ORANGE);
        if(isOutOfField(newCell)){
            snakeBody.getFirst().setColor(FieldPara.GREEN);
            return true;
        }
        boolean ret = false;
        snakeBody.removeLast();
        if(isInSnake(newCell)){
            ret = true;
            newCell.setColor(FieldPara.GREEN);
        }
        snakeBody.addFirst(newCell);
        return ret;
    }
    public boolean isInSnake(Cell e){
        boolean ret = false;
        for(Cell c : snakeBody){
            if(c.getRow() == e.getRow() && c.getCol() == e.getCol()){
                ret = true;
                break;
            }
        }
        return ret;
    }
    public boolean isOutOfField(Cell e){
        return e.getRow() < 0 || e.getRow() >= FieldPara.FILED_WIDTH || e.getCol() < 0 || e.getCol() >= FieldPara.FILED_HEIGHT;
    }
    public int getLength(){
        return snakeBody.size();
    }

    public void draw(Graphics g){
        for(Cell c : snakeBody){
            c.draw(g, c.getCol() * FieldPara.CELL_SIZE, c.getRow() * FieldPara.CELL_SIZE, FieldPara.CELL_SIZE);
        }
        dot.draw(g, dot.getCol() * FieldPara.CELL_SIZE, dot.getRow() * FieldPara.CELL_SIZE, FieldPara.CELL_SIZE);
    }
}
