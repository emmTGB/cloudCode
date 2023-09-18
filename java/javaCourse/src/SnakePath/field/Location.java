package SnakePath.field;

public abstract class Location {
    protected final int row, col;

    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){return row;}
    public int getCol(){return col;}
}
