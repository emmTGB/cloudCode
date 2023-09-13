package Animal;

import cell.Cell;

import javax.lang.model.element.AnnotationMirror;
import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Fox extends Animal implements Cell {//实现Cell接口，并要求override接口中的函数
    public Fox(){
        super(20, 4);
    }

    @Override
    public void draw(Graphics g, int x, int y, int size){
        int alpha = (int)((1 - getAgePercent()) * 255);
        g.setColor(new Color(32, 74, 173, alpha));
        g.fillRect(x, y, size, size);
    }
    @Override
    public Animal breed(){
        Animal ret = null;
        if(isBreedable() && Math.random() < 0.05){
            ret = new Fox();
        }
        return ret;
    }
    @Override
    public Animal feed(ArrayList<Animal> neighbor){
        Animal ret = null;
        if(Math.random() < 0.3){
            ret = neighbor.get((int)(Math.random() * neighbor.size()));
            longerLife(2);
        }
        return ret;
    }
    @Override
    public String toString(){
        return "Fox:" + super.toString();
    }
}
