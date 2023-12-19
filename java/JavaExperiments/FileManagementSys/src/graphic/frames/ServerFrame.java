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
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Server.start();
            }
        });
    }
}
