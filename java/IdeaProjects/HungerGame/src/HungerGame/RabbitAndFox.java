package HungerGame;

import Animal.Animal;
import Animal.Fox;
import Animal.Rabbit;
import cell.Cell;
import field.Field;
import field.Location;
import field.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RabbitAndFox {
    private Field thisField;
    private View thisView;
    private JFrame frame;
    private static final long STEP_GAP = 50;

    public RabbitAndFox(int size) {
        thisField = new Field(size, size);
        for (int row = 0; row < thisField.getHeight(); row++) {
            for (int col = 0; col < thisField.getWidth(); col++) {
                double probability = Math.random();
                if (probability < 0.05) {
                    thisField.place(row, col, new Fox());
                } else if (probability < 0.15) {
                    thisField.place(row, col, new Rabbit());
                }
            }
        }
        thisView = new View(thisField);
        frame = new JFrame();//新建容器
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Rabbit And Fox");
        frame.add(thisView);//添加部件theView
        JButton btnStep = new JButton("Next step");
        frame.add(btnStep, BorderLayout.NORTH);//将btnStep安放在容器的北部，BorderLayout是一种布局管理器，默认为BorderLayout.CENTER
        btnStep.addActionListener(new ActionListener() {//在构造函数后使用大括号定义一个新无名类，用以实现其接口，称为匿名类，在编写方便的同时省去了起名的麻烦
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                step();
                frame.repaint();
            }
        });//此处在RabbitAndFox内的函数中定义了一个新的实现ActionListener的类，称之为内部类，具有作为内部成员的权利，可以访问内部成员
        // 此处为函数的内部类，可以访问函数的为final的本地变量
        frame.pack();
        frame.setVisible(true);
    }

    public void start(long steps){
        for(long i = 0; i < steps; i++){
            step();
            thisView.repaint();
            try{
                Thread.sleep(STEP_GAP);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void start(){
        while(true){
            step();
            thisView.repaint();
            try{
                Thread.sleep(STEP_GAP);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void step(){
        for (int row = 0; row < thisField.getHeight(); row++) {
            for (int col = 0; col < thisField.getWidth(); col++) {
                Cell cell = thisField.get(row, col);
                if(cell != null){
                    Animal animal = (Animal)cell;
                    animal.grow();
                    if(animal.isAlive()){
                        Location loc = animal.move(thisField.getFreeNeighbor(row, col));
                        if(loc != null){
                            thisField.move(row, col, loc);
                        }

                        if(animal instanceof Fox){
                            Cell[] neighbor = thisField.getNeighbor(row, col);
                            ArrayList<Animal> listR = new ArrayList<>();
                            for(Cell ani : neighbor){
                                if(ani instanceof Rabbit){//判断是否为rabbit类的对象
                                    listR.add((Rabbit)ani);
                                }
                            }
                            if(!listR.isEmpty()){
                                Animal fed = animal.feed(listR);
                                if(fed != null){
                                    thisField.remove((Cell)fed);
                                }
                            }
                        }

                        Animal baby = animal.breed();
                        if(baby != null){
                            thisField.placeRandomAdj(row, col, (Cell)baby);
                        }
                    }else{
                        thisField.remove(row, col);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        RabbitAndFox rabbitAndFox = new RabbitAndFox(50);
        rabbitAndFox.start();
    }
}
