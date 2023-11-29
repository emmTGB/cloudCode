import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("文件选择器示例");
        frame.setSize(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JTextField textField = new JTextField(20);
        JButton chooseButton = new JButton("选择文件");

        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openFileChooser(textField);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(textField);
        panel.add(chooseButton);
        frame.add(panel);

        frame.setVisible(true);
    }

    private static void openFileChooser(JTextField textField) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File selectedFile = showFileChooser();
            if (selectedFile != null) {
                textField.setText(selectedFile.getPath());
            }
        } else {
            // Desktop is not supported on this platform
            // You can fall back to JFileChooser or provide an error message
            System.out.println("Desktop is not supported on this platform");
        }
    }

    private static File showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}
