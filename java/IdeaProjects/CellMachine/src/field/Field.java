package field;

import cell.Cell;

import java.util.ArrayList;

public class Field {
    private final int width;
    private final int height;
    private Cell[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[height][width];
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}

    public Cell place(int row, int col, Cell c){
        Cell ret = field[row][col];
        field[row][col] = c;
        return ret;
    }
    public void setField(){
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                field[row][col] = new Cell();
            }
        }
    }
    public void setField(double prob){
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                field[row][col] = new Cell();
                if(Math.random() < prob){
                    field[row][col].reborn();
                }
            }
        }
    }
    public Cell get(int row, int col){
        return field[row][col];
    }
    public Cell[] getNeighbor(int row, int col){
        ArrayList<Cell> list = new ArrayList<>();
        for(int i = row - 1; i < row + 2; i++){
            for(int j = col - 1; j < col + 2; j++){
                if(i > -1 && i < height && j > -1 && j < height && !(i == row && j == col)){
                    list.add(field[i][j]);
                }
            }
        }
        return list.toArray(new Cell[list.size()]);
    }
    public boolean getAlive(int row, int col){
        return field[row][col].isAlive();
    }

    public void clear(){
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                field[row][col] = null;
            }
        }
    }
}
