package client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileTransferServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("File Transfer Server");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());

            JProgressBar progressBar = new JProgressBar();
            panel.add(progressBar, BorderLayout.CENTER);

            JButton startButton = new JButton("Start Server");
            startButton.addActionListener(e -> {
                startServer(progressBar);
                startButton.setEnabled(false);
            });
            panel.add(startButton, BorderLayout.SOUTH);

            frame.add(panel);
            frame.setSize(300, 100);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void startServer(JProgressBar progressBar) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server is listening on port " + PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                    receiveFile(clientSocket, progressBar);

                    System.out.println("File received successfully.");

                    SwingUtilities.invokeLater(() -> progressBar.setValue(0)); // 重置进度条
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void receiveFile(Socket clientSocket, JProgressBar progressBar) {
        try (DataInputStream dis = new DataInputStream(clientSocket.getInputStream())) {
            String fileName = dis.readUTF();
            long fileSize = dis.readLong();

            Path filePath = Path.of(fileName);
            try (OutputStream fos = Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                long totalBytesRead = 0;

                while ((bytesRead = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;

                    final long finalTotalBytesRead = totalBytesRead;
                    SwingUtilities.invokeLater(() -> {
                        int progress = (int) ((finalTotalBytesRead * 100) / fileSize);
                        progressBar.setValue(progress);
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
