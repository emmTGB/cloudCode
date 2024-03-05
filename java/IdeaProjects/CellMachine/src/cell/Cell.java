package cell;

import java.awt.*;

public class Cell {
    private boolean isAlive = false;

    public void die(){
        isAlive = false;
    }
    public void reborn(){
        isAlive = true;
    }
    public boolean isAlive(){
        return isAlive;
    }

    public void draw(Graphics g, int x, int y, int size){
        g.drawRect(x, y, size, size);
        if(isAlive){
            g.fillRect(x, y, size, size);
        }
    }
}
