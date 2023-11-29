package client;

import java.io.*;
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
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        // todo
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            InputStream inputStream = server.getInputStream();
            byte[] fileStream = new byte[65560];
            int len = 0;
            while ((len = inputStream.read(fileStream)) != -1) {
                fileOutputStream.write(fileStream, 0, len);
            }
            inputStream.close();
        }
    }

    public static void upload(String fileID, String filePath) throws IOException {

        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException();
            // TODO: 0029 11/29
        }

        String fileName = file.getName();
        sendMessage("Upload," + fileID + "," + fileName);//todo
    }
}
