package graphic.frames;

import server.Server;

import javax.swing.*;

public class ServerFrame {
    private JLabel jLabel;
    public JPanel panel1;
    private JTextField portTextField;
    private JButton startBtn;
    public JList<String> clientList;
    private JScrollPane scrollPanel;

    public ServerFrame() {
        jLabel.setText("Port:");
        portTextField.setText(String.valueOf(Server.SERVER_PORT));
        startBtn.setText("Start");
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
