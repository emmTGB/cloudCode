package SnakePath.blocks;

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

    public void setColor(Color color){
        cellColor = color;
    }

    public void draw(Graphics g, int x, int y, int size){
        g.setColor(cellColor);
        g.fillRect(x, y, size, size);
    }
}
