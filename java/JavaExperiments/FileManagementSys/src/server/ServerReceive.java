package server;

import consts.FILE_CONST;
import consts.GUI_CONST;

import java.io.*;
import java.net.Socket;

public class ServerReceive implements Runnable {
    Socket socket;
    InputStream is;
    FileInputStream fileInputStream;
    BufferedOutputStream bufferedOutputStream;
    String[] message;
    String path;

    ServerReceive(Socket socket, String[] message) {
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

                InputStream inputStream = socket.getInputStream();
                FileOutputStream fileOutputStream = getFileOutputStream();
                byte[] fileStream = new byte[65560];
                int len = 0;
                while ((len = inputStream.read(fileStream)) != -1) {
                    fileOutputStream.write(fileStream, 0, len);
                }

                bufferedOutputStream.close();
                fileInputStream.close();

                Thread.sleep(200);
                socket.close();

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // TODO: 0029 11/29
        } catch (IOException e) {
            throw new RuntimeException(e);  // TODO: 0029 11/29
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        return fileOutputStream;
    }
}
