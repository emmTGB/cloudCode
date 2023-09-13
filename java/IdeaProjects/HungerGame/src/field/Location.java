package field;

public class Location {
    private final int row, col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow(){return row;}
    public int getCol(){return col;}
}
