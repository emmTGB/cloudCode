package cellMachine;

import cell.Cell;
import field.Field;
import field.View;

import javax.swing.*;

public class CellMachine {
    public static void main(String[] args){
        Field field = new Field(50, 50);
        field.setField(0.3);

        View view = new View(field);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cell Machine");
        frame.add(view);
        frame.pack();
        frame.setVisible(true);

        while(true){
            int[][] numOfAlive = new int[field.getHeight()][field.getWidth()];
            for(int row = 0; row < field.getHeight(); row++){
                for(int col = 0; col < field.getHeight(); col++){
                    Cell cell = field.get(row, col);
                    Cell[] neighbor = field.getNeighbor(row, col);
                    for(Cell c : neighbor){
                        if(c.isAlive()){
                            numOfAlive[row][col]++;
                        }
                    }
                }
            }
            for(int row = 0; row < field.getHeight(); row++){
                for(int col = 0; col < field.getHeight(); col++){
                    if(numOfAlive[row][col] != 2) {
                        if(numOfAlive[row][col] == 3){
                            field.get(row, col).reborn();
                        }else if(field.getAlive(row, col)){
                            field.get(row, col).die();
                        }
                    }
                }
            }
            frame.repaint();
            try{
                Thread.sleep(25);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
