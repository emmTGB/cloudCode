package SnakePath.blocks;

import java.lang.ref.Cleaner;
import java.util.Deque;

import SnakePath.consts.Direction;
import SnakePath.consts.FieldPara;
import SnakePath.blocks.Cell;

public class Snake {
    private Direction direction;
    private Deque<Cell> snakeBody;
    
    public Snake(){
        direction = Direction.UP;
        snakeBody.addLast(new Cell(FieldPara.FILED_WIDTH / 2, FieldPara.FILED_HEIGHT / 2, FieldPara.ORANGE));
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
            }
        }
        return ret;
    }
    public boolean isOutOfField(Cell e){
        return e.getRow() < 0 || e.getRow() >= FieldPara.FILED_WIDTH || e.getCol() < 0 || e.getCol() >= FieldPara.FILED_HEIGHT;
    }
}
