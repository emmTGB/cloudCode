package client;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Vector;

import connection.ConnectionSQL;
import consts.CONNECTION_CONST;
import consts.FILE_CONST;
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
        ObjectOutputStream os = new ObjectOutputStream(server.getOutputStream());
        os.writeObject(message);
        System.out.println(message);
        os.flush();
    }

    private static String receiveMessage() throws IOException {
        ObjectInputStream is = new ObjectInputStream(server.getInputStream());
        try {
            return (String) is.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void download(String fileID, String fileName) throws IOException {
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
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            InputStream inputStream = server.getInputStream();
            byte[] fileStream = new byte[65560];
            int len;
            while ((len = inputStream.read(fileStream)) != -1) {
                fileOutputStream.write(fileStream, 0, len);
            }
            inputStream.close();
        }

        closeConnection();
    }

    public static void upload(String fileID, String filePath) throws IOException {
        connectToServer();

        double percent = 0;

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
            // TODO: 0029 11/29
        }
        String fileName = file.getName();
        int fileLength = (int) file.length();
        sendMessage("Upload," + fileID + "," + fileName + "," + fileLength);
        FileInputStream fileInputStream = new FileInputStream(file);
        Vector<InputStream> vector = new Vector<>();
        vector.addElement(fileInputStream);
        Enumeration<InputStream> enumeration = vector.elements();
        SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(server.getOutputStream());
        byte[] buff = new byte[65560];
        int len = 0;
        int readSize = 0;
        while ((len = sequenceInputStream.read(buff)) != -1) {
            readSize += len;
            bufferedOutputStream.write(buff, 0, len);
            percent = (double) readSize / fileLength;
        }

        sequenceInputStream.close();
        bufferedOutputStream.close();
        fileInputStream.close();
        closeConnection();
    }
}
