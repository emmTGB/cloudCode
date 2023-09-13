import java.io.FileOutputStream;
import java.io.IOException;

public class FileStream {//FileIn/OutputStream在实际工程中已经较为少见
    public static void main(String[] args){
        System.out.println("Hello World!");
        byte[] buff = new byte[10];
        for(int i = 0; i < buff.length; i++){
            buff[i] = (byte) i;
        }

        try {
            FileOutputStream out = new FileOutputStream("a.dat");
            out.write(buff);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//不常用，已被数据库等工具替代
