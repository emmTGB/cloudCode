package SnakePath.blocks;

import java.awt.Color;

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

    public void setColor(Color color){
        cellColor = color;
    }
}
