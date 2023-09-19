package SnakePath.blocks;

import SnakePath.consts.GamePara;

import java.awt.*;

public class Cell {
    protected final int row, col;
    private Color cellColor;

    public Cell(int row, int col, Color cellColor){
        this.row = row;
        this.col = col;
        this.cellColor = cellColor;
    }

    public int getRow(){return row;}
    public int getCol(){return col;}

    public void setCellColor(Color color){
        cellColor = color;
    }

    public void draw(Graphics g){
        g.setColor(cellColor);
        g.fillRect(col * GamePara.CELL_SIZE, row * GamePara.CELL_SIZE, GamePara.CELL_SIZE, GamePara.CELL_SIZE);
    }
}
