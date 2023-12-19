package graphic.frames;

import server.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerFrame {

    public JPanel panel1;
    private JTextField portTextField;
    private JButton startBtn;
    public JList<String> clientList;

    public ServerFrame() {
        portTextField.setText(String.valueOf(Server.SERVER_PORT));
        startBtn.addActionListener(e -> {
            try {
                Server.SERVER_PORT = Integer.parseInt(portTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Wrong port!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Server.start();
        });
    }
}
