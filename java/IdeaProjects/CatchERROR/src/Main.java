// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        // 当文本光标位于高亮显示的文本处时按 Alt+Enter，
        // 可查看 IntelliJ IDEA 对于如何修正该问题的建议。
        System.out.printf("Hello and welcome!");

        try{//此时尝试try语体中的语句
            int[] a = new int[10];
             System.out.println("1");
            a[10] = 10;
            System.out.println("2");
        }catch(ArrayIndexOutOfBoundsException e){//若try语体崩溃，则跳过异常并跳出，则catch错误信息e，若捕捉到则执行catch语体语句
            System.out.println("caught");
            System.out.println(e.getMessage());
            System.out.println(e);
            System.out.println(e.toString());
            e.printStackTrace();//输出红字，调用栈追踪

            System.err.println("An exception is thrown");//在错误部分输出
            throw e;//抛出异常并进入下一步的try-catch处理
        }catch(StackOverflowError stackOverflowError){//可以catch多种异常信息
            System.out.println("another");
        }
        //完成异常捕捉后，程序能正常继续运行

        // 按 F10 或点击间距中的绿色箭头按钮以运行脚本。
        for (int i = 1; i <= 5; i++) {

            // 按 Shift+F9 开始调试代码。我们已为您设置了一个断点，
            // 但您始终可以通过按 Ctrl+F8 添加更多断点。
            System.out.println("i = " + i);
        }
    }
}