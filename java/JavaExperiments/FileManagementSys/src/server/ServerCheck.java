package server;

import consts.FILE_CONST;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerCheck implements Runnable {
    final Socket socket;
    final String[] message;
    ObjectOutputStream objectOutputStream;

    public ServerCheck(Socket socket, String[] message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        String threadMessage = "Thread " + Thread.currentThread().getId() + " on " + socket.getInetAddress() + " checking";
        Server.addMessage(Thread.currentThread().getId(), threadMessage);
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
        } finally {
            Server.dropMessage(Thread.currentThread().getId());
        }
    }
}
