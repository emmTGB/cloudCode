package server;

import consts.CONNECTION_CONST;
import consts.FILE_CONST;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class ServerDownload implements Runnable {
    final Socket socket;
    ObjectOutputStream objectOutputStream;
    final String[] message;
    String path;

    ServerDownload(Socket socket, String[] message) {
        System.out.println("start down");
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        String threadMessage = "Thread " + Thread.currentThread().getId() + " on " + socket.getInetAddress() + " downloading";
        Server.addMessage(Thread.currentThread().getId(), threadMessage);
        try {
            {
                String ID = message[0];
                String name = message[1];
                path = FILE_CONST.SERVER_DIR + ID + "_" + name;
            }
            File file = new File(path);
            if (!file.exists()) {
                sendErrorMessage(CONNECTION_CONST.ERR_FILE_NOT_FOUND);
                return;
            }
            sendSuccessMessage(CONNECTION_CONST.MSG_READY_TO_DOWNLOAD + "," + file.length());

            try (FileInputStream fileInputStream = new FileInputStream(file);
                 SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(List.of(fileInputStream)));
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream())
            ) {
                byte[] buf = new byte[65560];
                int len;
                while ((len = sequenceInputStream.read(buf)) != -1) {
                    bufferedOutputStream.write(buf, 0, len);
                }
            } finally {
                socket.close();
            }
        } catch (SocketException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO: 0020 12/20
        } finally {
            Server.dropMessage(Thread.currentThread().getId());
        }
    }

    private void sendErrorMessage(String errorMessage) throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(errorMessage);
    }

    private void sendSuccessMessage(String successMessage) throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(successMessage);
    }
}
