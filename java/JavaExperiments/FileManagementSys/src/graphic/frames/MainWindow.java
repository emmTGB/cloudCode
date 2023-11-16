package graphic.frames;

import graphic.panels.CreateUserPanel;
import graphic.panels.LoginPanel;

import javax.swing.*;

public class MainWindow {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTable table1;
    private JPanel JPanel1;

    public MainWindow() {
        JPanel1 = new LoginPanel();


    }

    public static void main(String[] args) {
        MainWindow m = new MainWindow();
    }
}
