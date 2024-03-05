// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。

import shapes.*;

import java.net.spi.URLStreamHandlerProvider;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        for (int i = 0; i < shapes.length; i++) {
            System.out.println("Input the type of your shape:");
            try {
                Scanner scanner = new Scanner(System.in);
                Shape shape = null;
                switch (Type.getType(scanner.next())) {
                    case TRIANGLE -> shape = new Triangle();
                    case RECTANGLE -> shape = new Rectangle();
                    case CIRCLE -> shape = new Circle();
                }
                shapes[i] = shape;

            } catch (ClassFormatError e) {
                System.err.println("Input your type correctly!");
                i--;
                continue;
            }
        }

        System.out.println("area compare");
        switch (shapes[0].compareArea(shapes[1])) {
            case 1 -> System.out.println("first > sec");
            case 0 -> System.out.println("first == sec");
            case -1 -> System.out.println("first < sec");
        }
        System.out.println("circumference compare");
        switch (shapes[0].compareCircumference(shapes[1])) {
            case 1 -> System.out.println("first > sec");
            case 0 -> System.out.println("first == sec");
            case -1 -> System.out.println("first < sec");
        }
    }
}