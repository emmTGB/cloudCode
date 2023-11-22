package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static Socket client;
    private static ObjectOutputStream os;
    private static ObjectInputStream is;
    private static String messageFromServer;

    public static void connectToServer() throws IOException {
        client = new Socket("127.0.0.1", 3939);
        System.out.println(client.getInetAddress().getHostName());
    }

    public static void getStreams() throws IOException {
        os = new ObjectOutputStream(client.getOutputStream());
        os.flush();
        is = new ObjectInputStream(client.getInputStream());
    }

    public static void closeConnection() throws IOException {
        os.close();
        is.close();
        client.close();
    }

    public static void sendMessage(String message) throws IOException {
        os.writeObject(message);
        os.flush();
    }

    public static void receiveMessageFromServer() throws IOException, ClassNotFoundException {
        messageFromServer = (String) is.readObject();
    }
}
