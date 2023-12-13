package client;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import consts.CONNECTION_CONST;
import consts.FILE_CONST;
import graphic.frames.ProBarFrame;
import graphic.panels.MyPanel;
import process.DocProcess;

import javax.swing.*;

public class Client {
    private static Socket server;
    public static Component fatherComponent = null;

    public static void connectToServer() throws IOException {
        server = new Socket(CONNECTION_CONST.SERVER_HOST, CONNECTION_CONST.SERVER_PORT);
//        System.out.println(server.getInetAddress().getHostName());
    }

    public static void closeConnection() throws IOException {
        server.close();
    }

    public static void sendMessage(String message) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(server.getOutputStream());
        os.writeObject(message);
        System.out.println(message);
        os.flush();
    }

    private static String receiveMessage() throws IOException {
        ObjectInputStream is = new ObjectInputStream(server.getInputStream());
        try {
            return (String) is.readObject();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void download(String fileID, String fileName) throws IOException {
        int[] progress = {0};
        new Thread(() -> {
            try {// TODO: 12/12/23 stream try with resources
                connectToServer();
                sendMessage("Download," + fileID + "," + fileName);

                String[] message = receiveMessage().split(",");
                String response = message[0];
                long length = 0;
                System.out.println(response);
                if (CONNECTION_CONST.ERR_FILE_NOT_FOUND.equals(response)) {
                    System.err.println("fnf");
                    closeConnection();
                    DocProcess.deleteDoc(fileID);
                    // TODO: 0001 12/1
                    throw new FileNotFoundException();
                } else if (CONNECTION_CONST.MSG_READY_TO_DOWNLOAD.equals(response)) {
                    System.out.println("ready");
                    length = Long.parseLong(message[1]);
                } else {
                    System.err.println("wtf");
                    closeConnection();
                    return;
                    // TODO: 0001 12/1
                }

                File dir = new File(FILE_CONST.DOWNLOAD_DIR);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                String filePath = FILE_CONST.DOWNLOAD_DIR + fileName;
                File file = new File(filePath);
                System.out.println(filePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                // todo
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     InputStream inputStream = server.getInputStream();
                ) {
                    byte[] fileStream = new byte[65560];
                    int len;
                    long readSize = 0;
                    while ((len = inputStream.read(fileStream)) != -1) {
                        readSize += len;
                        fileOutputStream.write(fileStream, 0, len);
                        progress[0] = (int) (readSize * 100 / length);
                    }
                } finally {
                    closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            ProBarFrame proBarFrame = new ProBarFrame("Uploading", fatherComponent);
            proBarFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // TODO: 0012 12/12
                }
            });

            while (progress[0] < 100) {
                proBarFrame.setProgressValue(progress[0]);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            proBarFrame.setProgressValue(100);
            MyPanel.staticBounceUpMsg(proBarFrame, "Success!", "Upload finish", JOptionPane.INFORMATION_MESSAGE);
            proBarFrame.dispose();
        }).start();
    }

    public static void upload(String fileID, String filePath) {
        int[] progress = {0};
        new Thread(() -> {
            try {
                connectToServer();

                File file = new File(filePath);
                if (!file.exists()) {
                    throw new FileNotFoundException();
                    // TODO: 0029 11/29
                }
                String fileName = file.getName();
                int fileLength = (int) file.length();
                sendMessage("Upload," + fileID + "," + fileName + "," + fileLength);
                try (FileInputStream fileInputStream = new FileInputStream(file);
                     SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(List.of(fileInputStream)));
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(server.getOutputStream());
                ) {
                    byte[] buff = new byte[65560];
                    long readSize = 0;
                    int len;
                    while ((len = sequenceInputStream.read(buff)) != -1) {
                        readSize += len;
                        bufferedOutputStream.write(buff, 0, len);
                        progress[0] = (int) (readSize * 100 / fileLength);
                    }
                } finally {
                    closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            ProBarFrame proBarFrame = new ProBarFrame("Downloading", fatherComponent);
            proBarFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // TODO: 0012 12/12
                }
            });

            while (progress[0] < 100) {
                proBarFrame.setProgressValue(progress[0]);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            proBarFrame.setProgressValue(100);
            MyPanel.staticBounceUpMsg(proBarFrame, "Success!", "Upload finish", JOptionPane.INFORMATION_MESSAGE);
            proBarFrame.dispose();
        }).start();
    }

    public static boolean checkOnServer(String ID, String fileName) throws IOException {
        connectToServer();
        sendMessage("Check," + ID + "," + fileName);

        String s = receiveMessage();
        return s.equals("E");
    }
}
