package field;

import cell.Cell;

import java.util.ArrayList;

public class Field {
    private final int width;
    private final int height;
    private Cell[][] field;
    private static final Location[] adjacent = {
            new Location(-1, -1), new Location(-1, 0), new Location(-1, 1),
            new Location(0, -1), new Location(0, 0), new Location(0, 1),
            new Location(1, -1), new Location(1, 0),new Location(1, 1),
    };

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[height][width];
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}

    public Cell place(int row, int col, Cell c){
        field[row][col] = c;
        Cell ret = field[row][col];
        return ret;
    }
    public boolean placeRandomAdj(int row, int col, Cell cell){
        boolean ret = false;
        Location[] freeAdj = getFreeNeighbor(row, col);
        if(freeAdj.length > 0){
            int idx = (int)(Math.random() * freeAdj.length);
            field[freeAdj[idx].getRow()][freeAdj[idx].getCol()] = cell;
            ret = true;
        }
        return ret;
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
    public Location[] getFreeNeighbor(int row, int col){
        ArrayList<Location> list = new ArrayList<>();
        for(Location loc : adjacent){
            int r = row + loc.getRow();
            int c = col + loc.getCol();
            if(r > -1 && r < height && c > -1 && c < width && field[r][c] == null){
                list.add(new Location(r, c));
            }
        }
        return list.toArray(new Location[list.size()]);
    }

    public void clear(){
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                field[row][col] = null;
            }
        }
    }
    public void remove(Cell fed){
        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if(field[row][col] == fed){
                    field[row][col] = null;
                    break;
                }
            }
        }
    }
    public Cell remove(int row, int col){
        Cell ret = field[row][col];
        field[row][col] = null;
        return ret;
    }
    public void move(int row, int col, Location loc){
        field[loc.getRow()][loc.getCol()] = field[row][col];
        remove(row, col);
    }
}
