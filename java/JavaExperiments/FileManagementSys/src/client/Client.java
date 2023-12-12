package client;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.List;

import connection.ConnectionSQL;
import consts.CONNECTION_CONST;
import consts.FILE_CONST;
import graphic.frames.ProBarFrame;
import process.DocProcess;

public class Client {
    private static Socket server;
    public static Component fatherComponent = null;

    public static void connectToServer() throws IOException {
        server = new Socket(CONNECTION_CONST.SERVER_HOST, CONNECTION_CONST.SERVER_PORT);
        System.out.println(server.getInetAddress().getHostName());
    }

    public static void closeConnection() throws IOException {
        server.close();
    }

    public static void sendMessage(String message) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(server.getOutputStream());) {
            os.writeObject(message);
            System.out.println(message);
            os.flush();
        }
    }

    private static String receiveMessage() throws IOException {
        try (ObjectInputStream is = new ObjectInputStream(server.getInputStream());) {
            return (String) is.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void download(String fileID, String fileName) throws IOException {
        // TODO: 12/12/23 stream try with resources
        connectToServer();
        sendMessage("Download," + fileID + "," + fileName);

        String response = receiveMessage();
        System.out.println(response);
        if (CONNECTION_CONST.ERR_FILE_NOT_FOUND.equals(response)) {
            System.err.println("fnf");
            closeConnection();
            DocProcess.deleteDoc(fileID);
            // TODO: 0001 12/1
            throw new FileNotFoundException();
        } else if (CONNECTION_CONST.MSG_READY_TO_DOWNLOAD.equals(response)) {
            System.out.println("ready");
        } else {
            System.err.println("wtf");
            closeConnection();
            return;
            // TODO: 0001 12/1
        }

        String filePath = FILE_CONST.DOWNLOAD_DIR + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        // todo
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             InputStream inputStream = server.getInputStream();
        ) {
            byte[] fileStream = new byte[65560];
            int len;
            while ((len = inputStream.read(fileStream)) != -1) {
                fileOutputStream.write(fileStream, 0, len);
            }
        } finally {
            closeConnection();
        }
    }

    public static void upload(String fileID, String filePath) throws IOException {
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
            int readSize = 0;
            ProBarFrame proBarFrame = new ProBarFrame("Uploading", fatherComponent);

            try {
                int len;
                double percent;
                while ((len = sequenceInputStream.read(buff)) != -1) {
                    readSize += len;
                    bufferedOutputStream.write(buff, 0, len);
                    percent = (double) readSize / fileLength;
                    proBarFrame.setProgressValue((int) (percent * 100));
                }
            } catch (IOException e) {
                proBarFrame.exit();
                throw e;
            }
        } finally {
            closeConnection();
        }
    }
}
