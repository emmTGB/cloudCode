import java.io.*;
import java.nio.charset.StandardCharsets;

public class ChineseCode {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("utf8.txt"), StandardCharsets.UTF_8//此处为InputStreamReader第二个参数，用于确定打开文件的编码
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
