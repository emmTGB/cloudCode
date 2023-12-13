package server;

import consts.FILE_CONST;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerCheck implements Runnable {
    Socket socket;
    String[] message;
    ObjectOutputStream objectOutputStream;

    public ServerCheck(Socket socket, String[] message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String ID = message[0];
                String name = message[1];
                String path = FILE_CONST.SERVER_DIR + ID + "_" + name;
                File file = new File(path);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                if (file.exists()) {
                    objectOutputStream.writeObject("E");
                } else {
                    objectOutputStream.writeObject("N");
                }
                objectOutputStream.flush();
                objectOutputStream.close();

                Thread.sleep(200);
                socket.close();
            }
        } catch (SocketException ignored) {
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
