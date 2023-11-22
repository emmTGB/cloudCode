package server;

import com.sun.jdi.connect.spi.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.DataFormatException;

public class Server {
    private static ServerSocket server;
    private static ObjectOutputStream os;
    private static ObjectInputStream is;
    private static Socket socket;
    private static String messageFromClient;
    private static int counter = 1;

    public static void waitForConnection() throws IOException {
        System.out.println("waiting...");
        socket = server.accept();
        System.out.println("con" + counter + "connected" + socket.getInetAddress());
    }

    public static void getStreams() throws IOException {
        os = new ObjectOutputStream(socket.getOutputStream());
        os.flush();
        is = new ObjectInputStream(socket.getInputStream());
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
            socket.close();
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
                byte[] buf = new byte[1024];
                int len = is.read(buf);
                String tmp = new String(buf, 0, len);
                String[] msg = tmp.split(",");
                String ID = msg[0];
                String name = msg[1];
                String path = "D:/" + ID + "_" + name;
                File file = new File(path);
                if (!file.exists()) {
                    throw new FileNotFoundException();
                }
                FileInputStream fileInputStream;
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                Vector<InputStream> vector = new Vector<>();
                vector.addElement(fileInputStream);
                Enumeration<InputStream> e = vector.elements();
                SequenceInputStream sequenceInputStream = new SequenceInputStream(e);

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                buf = new byte[1024];
                while ((len = sequenceInputStream.read(buf)) != -1) {
                    bufferedOutputStream.write(buf, 0, len);
                }
                bufferedOutputStream.close();
                sequenceInputStream.close();
                fileInputStream.close();
                Thread.sleep(200);
                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }
}
