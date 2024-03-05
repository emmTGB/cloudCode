package Animal;

import cell.Cell;

import java.awt.*;

public class Rabbit extends Animal implements Cell {
    public Rabbit() {
        super(10, 2);
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        int alpha = (int)((1 - getAgePercent()) * 255);
        g.setColor(new Color(221, 134, 130, alpha));
        g.fillRect(x, y, size, size);
    }
    @Override
    public Animal breed() {
        Animal ret = null;
        if (isBreedable() && Math.random() < 0.15) {
            ret = new Rabbit();
        }
        return ret;
    }
    @Override
    public String toString() {
        return "Rabbit" + super.toString();
    }
}