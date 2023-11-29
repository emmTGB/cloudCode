import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test {
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
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getPath();
                    textField.setText(selectedFilePath);
                }
            }
        });

        panel.add(textField);
        panel.add(chooseButton);
        frame.add(panel);

        frame.setVisible(true);
    }
}
