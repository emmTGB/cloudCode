package SnakePath.blocks;

import java.lang.ref.Cleaner;
import java.util.Deque;

import SnakePath.consts.Direction;
import SnakePath.consts.FieldPara;
import field.Field;
import field.Location;

public class Snake {
    private Direction direction;
    private Deque<Location> snakeBody;
    
    public Snake(){
        direction = Direction.UP;
        snakeBody.addLast(new Location(FieldPara.FILED_WIDTH / 2, FieldPara.FILED_HEIGHT / 2));
    }

    public void setDirection(Direction dir){
        direction = dir;
    }
    public void moveForward(){
        Location head = snakeBody.getFirst();
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
        snakeBody.removeLast();
        snakeBody.addFirst(new Location(row, col));
    }
}
