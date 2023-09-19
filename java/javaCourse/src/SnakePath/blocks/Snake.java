package SnakePath.blocks;

import SnakePath.consts.Direction;
import SnakePath.consts.GamePara;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Snake {
    private Direction direction;
    private final Deque<Cell> snakeBody;
    private Cell dot;
    private boolean gameOver;

    public Snake(int startRow, int startCol){
        snakeBody = new LinkedList<>();
        direction = Direction.UP;
        snakeBody.addLast(new Cell(startRow, startCol, GamePara.ORANGE));
        snakeBody.addLast(new Cell(startRow + 1, startCol, GamePara.ORANGE));
        generateDot();
        gameOver = false;
    }

    public void generateDot(){
        if(getLength() >= GamePara.FIELD_WIDTH * GamePara.FIELD_HEIGHT){
            return;
        }
        Random rand = new Random();
        do{
            dot = new Cell(rand.nextInt(GamePara.FIELD_HEIGHT), rand.nextInt(GamePara.FIELD_WIDTH), GamePara.RED);
        }while(isInSnake(dot));
    }

    public void setDirection(Direction dir){
        if(dir.ordinal() + direction.ordinal() == 3){
            return;
        }
        direction = dir;
    }
    public void moveForward(){
        Cell head = snakeBody.getFirst();
        int row = head.getRow();
        int col = head.getCol();
        switch(direction){
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            default:
                break;
        }
        
        Cell newCell = generateHead(row, col);
        
        if(newCell.getRow() == dot.getRow() && newCell.getCol() == dot.getCol()){
            snakeBody.addFirst(newCell);
            generateDot();
            return;
        }
        snakeBody.removeLast();
        if(isInSnake(newCell)){
            gameOver = true;
            newCell.setCellColor(GamePara.GREEN);
        }
        snakeBody.addFirst(newCell);
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
    public Cell generateHead(int row, int col){
        if(row < 0){
            row = GamePara.FIELD_HEIGHT - 1;
        }else if(row >= GamePara.FIELD_HEIGHT){
            row = 0;
        }else if(col < 0){
            col = GamePara.FIELD_WIDTH - 1;
        }else if(col >= GamePara.FIELD_WIDTH){
            col = 0;
        }
        return new Cell(row, col, GamePara.ORANGE);
    }
    public int getLength(){
        return snakeBody.size();
    }

    public boolean isGameOver(){return gameOver;}

    public void draw(Graphics g){
        for (Cell c : snakeBody) {
            c.draw(g);
        }
        dot.draw(g);
        if(gameOver){
            snakeBody.getFirst().draw(g);
        }
    }
}
