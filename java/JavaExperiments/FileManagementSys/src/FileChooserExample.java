import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooserExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("文件选择器示例");
        frame.setSize(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JTextField textField = new JTextField(20);

        // 创建一个按钮并设置图标为文件夹图标
        JButton chooseButton = new JButton();
        chooseButton.setIcon(new ImageIcon(FileChooserExample.class.getResource("/1691318963824.png")));
        chooseButton.setPreferredSize(new java.awt.Dimension(20, 20));

        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser(textField);
            }
        });

        // 将按钮添加到文本框中
        textField.setLayout(new java.awt.BorderLayout());
        textField.add(chooseButton, java.awt.BorderLayout.EAST);

        panel.add(textField);
        frame.add(panel);

        frame.setVisible(true);
    }

    private static void openFileChooser(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textField.setText(selectedFile.getAbsolutePath());
        }
    }
}
