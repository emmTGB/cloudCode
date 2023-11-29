package server;

import consts.FILE_CONST;

import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class ServerDownload implements Runnable {
    Socket socket;
    InputStream is;
    FileInputStream fileInputStream;
    SequenceInputStream sequenceInputStream;
    BufferedOutputStream bufferedOutputStream;
    String[] message;
    String path;

    ServerDownload(Socket socket, String[] message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            while (true) {
                {
                    String ID = message[0];
                    String name = message[1];
                    path = FILE_CONST.SERVER_DIR + ID + "_" + name;
                }
                File file = new File(path);
                if (!file.exists()) {
                    throw new FileNotFoundException();
                }
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e) {
                    throw new RuntimeException();
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

                Thread.sleep(200);
                socket.close();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
