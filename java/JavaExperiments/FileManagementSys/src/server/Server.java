package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class Server {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(3939);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        try {
            while (true) {
                Socket socket = server.accept();
                Runnable runnable = getRunnable(socket);
                if (runnable != null) {
                    Thread thread = new Thread(runnable);
                    new Thread(thread).start();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static Runnable getRunnable(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = is.read(buf);
        String messageFromClient = new String(buf, 0, len);
        String[] message = messageFromClient.split(",");
        String[] subMessage = Arrays.copyOfRange(message, 1, message.length);
        Runnable runnable;
        switch (message[0]) {
            case "Download" -> {
                runnable = new ServerDownload(socket, subMessage);
            }
            default -> {
                runnable = null;
            }
        }
        return runnable;
    }
}
