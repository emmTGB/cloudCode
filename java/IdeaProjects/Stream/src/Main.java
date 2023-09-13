import java.io.*;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        // 当文本光标位于高亮显示的文本处时按 Alt+Enter，
        // 可查看 IntelliJ IDEA 对于如何修正该问题的建议。
        System.out.println("Hello and welcome!");

        byte[] buf = new byte[10];
        for(int i = 0; i < buf.length; i++){
            buf[i] = (byte) i;
        }
        try{//流操作总是伴随着风险，需要时时使用try-catch
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("a.dat")));//dataOutputStream建立在fileOutputStream基础上
            //将上述构造理解为为FileOutputStream套了两层流过滤器
//            out.write(buf);
            int i = 0xcaffeb;
            out.writeInt(i);
            out.close();

            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("a.dat")
                    )
            );
            int j = in.readInt();
            System.out.println(j);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}