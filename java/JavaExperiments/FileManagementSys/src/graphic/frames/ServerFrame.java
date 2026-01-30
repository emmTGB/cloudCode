package graphic.frames;

import server.Server;

import javax.swing.*;

public class ServerFrame {
    private final JLabel jLabel = new JLabel();
    public JPanel panel1 = new JPanel();
    private JTextField portTextField = new JTextField();
    private JButton startBtn = new JButton();
    public JList<String> clientList = new JList<>();
    private JScrollPane scrollPanel = new JScrollPane();

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
