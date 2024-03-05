package scheduleTable;

import javax.swing.*;
import java.awt.*;

public class ScheduleTable {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JTable table = new JTable(new TableData());
        JScrollPane pane = new JScrollPane(table);//创建基于table的滚表
        frame.add(pane);
        frame.setSize(630, 190);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
