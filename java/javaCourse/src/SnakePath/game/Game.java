package SnakePath.game;

import SnakePath.blocks.Snake;
import SnakePath.consts.GamePara;
import SnakePath.field.View;

import javax.swing.*;

public class Game {
    private final View thisView;

    public Game(){
        thisView = new View(new Snake(GamePara.FIELD_WIDTH / 2, GamePara.FIELD_HEIGHT / 2));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("snake_path");
        frame.add(thisView);
        frame.addKeyListener(thisView);
        frame.pack();
        frame.setVisible(true);
    }

    public void start(){
        while (!thisView.update()) {
            try {
                Thread.sleep(GamePara.STEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
