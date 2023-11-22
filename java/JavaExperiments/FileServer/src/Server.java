import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    private static ObjectOutputStream os;
    private static ObjectInputStream is;
    private static Socket connection;
    private static String messageFromClient;
    private static int counter = 1;

    public static void waitForConnection() throws IOException {
        System.out.println("waiting...");
        connection = server.accept();
        System.out.println("con" + counter + "connected" + connection.getInetAddress());
    }

    public static void getStreams() throws IOException {
        os = new ObjectOutputStream(connection.getOutputStream());
        os.flush();
        is = new ObjectInputStream(connection.getInputStream());
    }

    public static void processConnection() throws IOException, ClassNotFoundException {
        do {
            messageFromClient = (String) is.readObject();

            os.writeObject(messageFromClient);
            os.flush();
        } while (!messageFromClient.equals("Logout"));
        os.writeObject(messageFromClient);
        os.flush();
    }

    private static void closeConnection() {
        try {
            os.close();
            is.close();
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        try {
            server = new ServerSocket(3939);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        try {
            while (true) {
                waitForConnection();
                getStreams();
                processConnection();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        } finally {
            closeConnection();
        }
    }
}
