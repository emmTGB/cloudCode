// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));
        // 当文本光标位于高亮显示的文本处时按 Alt+Enter，
        // 可查看 IntelliJ IDEA 对于如何修正该问题的建议。
        int[] a = {1,2,3,4,5,6,7,8};
        int[] b = new int[a.length];
        for(int j = 0; j < a.length; j++){
            b[j] = a[a.length - j -1];
        }
        for(int i : b){
            System.out.print(i + '\t');
        }
        System.out.println(a.length);

        String s = "hello";
        System.out.println(s == "hello");//这里比较的是二者的指针
        System.out.println(s.equals("hello"));//这里比较的是二者的内容，一般用string的equals()方法
        System.out.println(s.compareTo("Hello"));//compareTo(str)返回1 0 -1分别代表本字符串大于/等于/小于参数字符串
        System.out.println(s.charAt(3));//charAt(index)返回字符串在index位置的字符，0<=index<s.length
        //字符串无法使用foreach循环
        System.out.println(s.substring(1));//substring(n)返回string中n位置及其以后的子串
        System.out.println(s.substring(1,3));//substring(begin,end)返回从begin到end的子串，且不包括end，即左闭右开
        System.out.println(s.indexOf('l') + " " + s.indexOf("lo") + " " + s.indexOf('x'));//index(ch)判断string中是否存在目标字符/字符串，若找到则返回其第一次出现的index，否则返回-1
        System.out.println(s.indexOf('l', 4));//indexOf(ch, loc)表示从loc位置开始查找
        System.out.println(s.lastIndexOf('l'));//lastIndexOf(ch)为indexOf的逆序版
        //trim() end/startWith(c) replace(c1, c2) toLower/UpperCase()这些中对string进行修改的函数仅是生成并返回一个新的string而不会更改原string本身



        System.out.println("a\bcd");// \b表示输出位置向前回退一格继续输出，相当于键盘左箭头后按ins
        System.out.println(Character.isDigit('1'));
        System.out.println(Character.toLowerCase('A'));

        System.out.println(Math.abs(-12));//abs(int)输出绝对值
        System.out.println(Math.round(1.234));//round(float)将浮点数四舍五入
        System.out.println(Math.random());//random()输出[1,0)的浮点数
        System.out.println(Math.pow(12,3.4));//pow(a,b)输出a^b

        int number = (int)(Math.random() * 100);//输出0到100，random输出为0到1左闭右开
        System.out.println(number);

        System.out.println(2 + 3 + (2 + 3) + " = 5" + " = " + (2 + 3));
        final int amount = 100;
        int it = in.nextInt();
        System.out.println(amount + " - " + it + " = " + (amount - it));
        System.out.println(12 + 34 + "ab" + 56 + 78);

        // 按 Shift+F10 或点击间距中的绿色箭头按钮以运行脚本。
        for (int i = 1; i <= 5; i++) {

            // 按 Shift+F9 开始调试代码。我们已为您设置了一个断点，
            // 但您始终可以通过按 Ctrl+F8 添加更多断点。
            System.out.println("i = " + i);
        }
    }
}