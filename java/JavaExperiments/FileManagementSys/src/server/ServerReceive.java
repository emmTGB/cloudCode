package server;

import consts.FILE_CONST;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ServerReceive implements Runnable {
    final Socket socket;
    final String[] message;
    String path;
    long fileLength;

    ServerReceive(Socket socket, String[] message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        String threadMessage = "Thread " + Thread.currentThread().getId() + " uploading";
        Server.addMessage(Thread.currentThread().getId(), threadMessage);
        try {
            {
                String ID = message[0];
                String name = message[1];
                path = FILE_CONST.SERVER_DIR + ID + "_" + name;
                fileLength = Integer.parseInt(message[2]);
            }


            try (InputStream inputStream = socket.getInputStream();
                 FileOutputStream fileOutputStream = getFileOutputStream()) {
                byte[] fileStream = new byte[65560];
                int len;
                while ((len = inputStream.read(fileStream)) != -1) {
                    fileOutputStream.write(fileStream, 0, len);
                }
            } finally {
                socket.close();
            }
        } catch (SocketException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(); // TODO: 0029 11/29
        } finally {
            if (!finished()) {
                deleteFile();
            }
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

    private void deleteFile() {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    private boolean finished() {
        File file = new File(path);
        if (file.exists()) {
            return file.length() == fileLength;
        }
        return false;
    }
}
