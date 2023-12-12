package graphic.frames;

import consts.GUI_CONST;
import graphic.panels.MyPanel;

import javax.swing.*;
import java.awt.*;

public class ProBarFrame extends JFrame {
    private final JProgressBar progressBar;

    public static void main(String[] args) {
        ProBarFrame proBarFrame = new ProBarFrame("111", null);

        for (int i = 0; i < 100; i++) {
            proBarFrame.setProgressValue(i);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ProBarFrame(String title, Component father) {
        super(title);
        setLayout(new BorderLayout());
        setSize(GUI_CONST.WIDTH / 3, GUI_CONST.HEIGHT / 5);

        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setSize(200, 40);

        JPanel jPanel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        jPanel.setLayout(springLayout);
        jPanel.add(progressBar);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, progressBar, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, progressBar, 0, SpringLayout.VERTICAL_CENTER, jPanel);
        add(jPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(father);
        setVisible(true);
    }

    public void setProgressValue(int value) {
        progressBar.setValue(value);
    }
}
