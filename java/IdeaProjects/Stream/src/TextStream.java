import java.io.*;

public class TextStream {//文本的输入输出用reader/writer，Input/OutputStream则是二进制数据
    public static void main(String[] args){
        try {
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream("abc.txt")//在流上建立文本处理
                            )
                    )
            );
            int i = 123456;
            out.println(i);
            out.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("src/TextStream.java")
                    )
            );
            String line;
            while((line = in.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
