package client;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import consts.FILE_CONST;

public class Client {
    private static Socket server;
    private static ObjectOutputStream os;
    private static ObjectInputStream is;
    private static String messageFromServer;

    public static void connectToServer() throws IOException {
        server = new Socket("127.0.0.1", 3939);
        System.out.println(server.getInetAddress().getHostName());
    }

    public static void getStreams() throws IOException {
        os = new ObjectOutputStream(server.getOutputStream());
        os.flush();
        is = new ObjectInputStream(server.getInputStream());
    }

    public static void closeConnection() throws IOException {
        os.close();
        is.close();
        server.close();
    }

    public static void sendMessage(String message) throws IOException {
        os.writeObject(message);
        os.flush();
    }

    public static void receiveMessageFromServer() throws IOException, ClassNotFoundException {
        messageFromServer = (String) is.readObject();
    }

    public static void download(String fileID, String fileName) throws IOException {
        sendMessage("Download," + fileID + "," + fileName);

        String filePath = FILE_CONST.DOWNLOAD_DIR + fileName;
        FileOutputStream fileOutputStream = new FileOutputStream(filePath); // todo

        InputStream inputStream = server.getInputStream();
        byte[] fileStream = new byte[65560];
        int len = 0;
        while ((len = inputStream.read(fileStream)) != -1) {
            fileOutputStream.write(fileStream, 0, len);
        }

    }
}
