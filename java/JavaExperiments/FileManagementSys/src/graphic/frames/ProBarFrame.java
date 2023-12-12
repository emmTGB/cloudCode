package graphic.frames;

import consts.GUI_CONST;

import javax.swing.*;
import java.awt.*;

public class ProBarFrame extends JFrame {
    private final JProgressBar progressBar;
    private int progressValue;

    public ProBarFrame(String title, Component father) {
        super(title);
        setLayout(new FlowLayout());
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        progressBar.setSize(100, 20);
        add(progressBar);
        setSize(GUI_CONST.WIDTH / 2, GUI_CONST.HEIGHT / 5);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(father);
        setVisible(true);
        ProgressThread progressThread = new ProgressThread();
//        progressThread.start();
        for (int i = 0; i < 100; i++) {
            progressBar.setValue(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setProgressValue(int value) {
        progressValue = value;
    }

    class ProgressThread extends Thread {
        @Override
        public void run() {
            while (progressValue < 100) {
                progressBar.setValue(progressValue);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
