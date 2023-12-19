package server;

import consts.FILE_CONST;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ServerReceive implements Runnable {
    Socket socket;
    String[] message;
    String path;

    ServerReceive(Socket socket, String[] message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        String threadMessage = "Thread " + Thread.currentThread().getId() + " on " + socket.getInetAddress() + " uploading";
        Server.addMessage(Thread.currentThread().getId(), threadMessage);
        try {
            while (true) {
                {
                    String ID = message[0];
                    String name = message[1];
                    path = FILE_CONST.SERVER_DIR + ID + "_" + name;
                    int fileLength = Integer.parseInt(message[2]);
                }

                InputStream inputStream = socket.getInputStream();
                FileOutputStream fileOutputStream = getFileOutputStream();
                byte[] fileStream = new byte[65560];
                int len = 0;
                while ((len = inputStream.read(fileStream)) != -1) {
                    fileOutputStream.write(fileStream, 0, len);
                }
                inputStream.close();
                fileOutputStream.close();
                Thread.sleep(200);
                socket.close();
            }
        } catch (SocketException ignored) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(); // TODO: 0029 11/29
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(); // TODO: 0029 11/29
        } finally {
            Server.dropMessage(Thread.currentThread().getId());
        }
    }

    private FileOutputStream getFileOutputStream() throws FileNotFoundException {
        File fileDir = new File(FILE_CONST.SERVER_DIR);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);  // TODO: 0029 11/29
            }
        }
        return new FileOutputStream(file);
    }
}
