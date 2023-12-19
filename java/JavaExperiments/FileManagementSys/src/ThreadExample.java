import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

public class ThreadExample {
    private JList<String> messageList;
    private DefaultListModel<String> listModel;

    public ThreadExample() {
        // 创建GUI
        JFrame frame = new JFrame("Thread Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        messageList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(messageList);
        JButton startButton = new JButton("Start Thread");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startThread();
            }
        });

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(startButton, "South");
        frame.pack();
        frame.setVisible(true);
    }

    private void startThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 在线程中执行任务
                String threadMessage = "Thread " + Thread.currentThread().getId() + " started";
                updateMessageList(threadMessage);

                // 模拟一些工作
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 任务完成后更新UI并移除行
                threadMessage = "Thread " + Thread.currentThread().getId() + " completed";
                updateMessageList(threadMessage);
            }
        });

        thread.start();
    }

    private void updateMessageList(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                listModel.addElement(message);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ThreadExample();
            }
        });
    }
}