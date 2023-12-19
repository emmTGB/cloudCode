package server;

import consts.CONNECTION_CONST;
import graphic.frames.ServerFrame;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.function.ObjIntConsumer;

public class Server {
    static ServerFrame serverFrame;
    static DefaultListModel<String> listModel;
    static Hashtable<Long, String> hashList;
    public static int SERVER_PORT = 3939;
    static Thread serverMain = new Thread(() -> {
        ServerSocket server;
        try {
            server = new ServerSocket(SERVER_PORT);
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    });

    public static void main(String[] args) {
        JFrame frame = new JFrame("ServerFrame");
        serverFrame = new ServerFrame();
        listModel = new DefaultListModel<>();
        hashList = new Hashtable<>();
        serverFrame.clientList.setModel(listModel);
//        hashList.put(1L, "test");

        frame.setContentPane(serverFrame.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void addMessage(long id, String msg) {
        SwingUtilities.invokeLater(() -> {
            hashList.put(id, msg);
            updateList();
        });
    }

    public static void dropMessage(long id) {
        SwingUtilities.invokeLater(() -> {
            hashList.remove(id);
            updateList();
        });
    }

    private static void updateList() {
        SwingUtilities.invokeLater(() -> {
            listModel.clear();
            for (String value : hashList.values()) {
                listModel.addElement(value);
            }
        });
    }

    public static void start() {
        serverMain.start();
    }

    private static Runnable getRunnable(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
//        byte[] buf = new byte[2048];
//        int len = is.read(buf);
        String messageFromClient = (String) is.readObject();
        String[] message = messageFromClient.split(",");
        String[] subMessage = Arrays.copyOfRange(message, 1, message.length);
        Runnable runnable;
        switch (message[0]) {
            case "Download" -> {
                runnable = new ServerDownload(socket, subMessage);
            }
            case "Upload" -> {
                runnable = new ServerReceive(socket, subMessage);
            }
            case "Check" -> {
                runnable = new ServerCheck(socket, subMessage);
            }
            default -> {
                runnable = null;
            }
        }
        return runnable;
    }
}
