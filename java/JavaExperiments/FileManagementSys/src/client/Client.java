package client;

import consts.CONNECTION_CONST;
import consts.FILE_CONST;
import graphic.frames.ProBarFrame;
import graphic.panels.MyPanel;
import process.DocProcess;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class Client {
    private Socket server;
    public static Component fatherComponent = null;

    public void connectToServer() throws IOException {
        server = new Socket(CONNECTION_CONST.SERVER_HOST, CONNECTION_CONST.CLIENT_PORT);
//        System.out.println(server.getInetAddress().getHostName());
    }

    public void closeConnection() throws IOException {
        server.close();
    }

    public void sendMessage(String message) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(server.getOutputStream());
        os.writeObject(message);
        System.out.println(message);
        os.flush();
    }

    private String receiveMessage() throws IOException {
        ObjectInputStream is = new ObjectInputStream(server.getInputStream());
        try {
            return (String) is.readObject();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Thread getDownloading(String fileName, int[] progress, long length) {
        Thread downloading = new Thread(() -> {
            try {
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
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     InputStream inputStream = server.getInputStream()
                ) {
                    byte[] fileStream = new byte[65560];
                    int len;
                    long readSize = 0;
                    while ((len = inputStream.read(fileStream)) != -1) {
                        if (Thread.interrupted()) {
                            if (file.exists())
                                file.delete();
                            break;
                        }
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
        });
        downloading.start();
        return downloading;
    }

    public void download(String fileID, String fileName) throws IOException {
        connectToServer();
        sendMessage("Download," + fileID + "," + fileName);

        String[] message = receiveMessage().split(",");
        String response = message[0];
        long length;
        System.out.println(response);
        if (CONNECTION_CONST.ERR_FILE_NOT_FOUND.equals(response)) {
            closeConnection();
            DocProcess.deleteDoc(fileID);
            throw new FileNotFoundException();
        } else if (CONNECTION_CONST.MSG_READY_TO_DOWNLOAD.equals(response)) {
            System.out.println("ready");
            length = Long.parseLong(message[1]);
        } else {
            closeConnection();
            throw new ConnectException("Some Thing Wrong!");
        }

        int[] progress = {0};
        Thread downloading = getDownloading(fileName, progress, length);

        new Thread(() -> {
            ProBarFrame proBarFrame = new ProBarFrame("Downloading " + fileID, fatherComponent, downloading);

            while (progress[0] < 100) {
                if (!proBarFrame.isVisible())
                    return;
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

    private Thread getUploading(File file, int[] progress, int fileLength) {
        Thread uploading = new Thread(() -> {
            try {
                try (FileInputStream fileInputStream = new FileInputStream(file);
                     SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(List.of(fileInputStream)));
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(server.getOutputStream())
                ) {
                    byte[] buff = new byte[65560];
                    long readSize = 0;
                    int len;
                    while ((len = sequenceInputStream.read(buff)) != -1) {
                        if (Thread.interrupted()) {
                            break;
                        }
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
        });
        uploading.start();
        return uploading;
    }

    public void upload(String fileID, String filePath) throws IOException {
        connectToServer();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File Not Found!");
        }
        String fileName = file.getName();
        int fileLength = (int) file.length();
        sendMessage("Upload," + fileID + "," + fileName + "," + fileLength);

        int[] progress = {0};
        Thread uploading = getUploading(file, progress, fileLength);

        new Thread(() -> {
            ProBarFrame proBarFrame = new ProBarFrame("Uploading " + fileID, fatherComponent, uploading);

            while (progress[0] < 100) {
                if (!proBarFrame.isVisible())
                    return;
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

    public boolean checkOnServer(String ID, String fileName) throws IOException {
        connectToServer();
        sendMessage("Check," + ID + "," + fileName);

        String s = receiveMessage();
        return s.equals("E");
    }
}
