package server;

import consts.CONNECTION_CONST;
import consts.FILE_CONST;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;

public class ServerDownload implements Runnable {
    Socket socket;
    FileInputStream fileInputStream;
    SequenceInputStream sequenceInputStream;
    BufferedOutputStream bufferedOutputStream;
    ObjectOutputStream objectOutputStream;
    String[] message;
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
            while (true) {
                {
                    String ID = message[0];
                    String name = message[1];
                    path = FILE_CONST.SERVER_DIR + ID + "_" + name;
                }
                File file = new File(path);
                if (!file.exists()) {
                    sendErrorMessage(CONNECTION_CONST.ERR_FILE_NOT_FOUND);
                    throw new FileNotFoundException();  // TODO: 0029 11/29
                }
                sendSuccessMessage(CONNECTION_CONST.MSG_READY_TO_DOWNLOAD + "," + file.length());
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e) {
                    throw new RuntimeException();  // TODO: 0029 11/29  
                }

                Vector<InputStream> vector = new Vector<>();
                vector.addElement(fileInputStream);
                Enumeration<InputStream> enumeration = vector.elements();
                sequenceInputStream = new SequenceInputStream(enumeration);

                {
                    bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[65560];
                    int len;
                    while ((len = sequenceInputStream.read(buf)) != -1) {
                        bufferedOutputStream.write(buf, 0, len);
                    }
                }
                bufferedOutputStream.close();
                sequenceInputStream.close();
                fileInputStream.close();

                // TODO: 0029 11/29检查文件完整性

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

    private void sendErrorMessage(String errorMessage) throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(errorMessage);
    }

    private void sendSuccessMessage(String successMessage) throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(successMessage);
    }
}
