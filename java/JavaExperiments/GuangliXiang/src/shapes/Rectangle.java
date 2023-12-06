package shapes;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Rectangle extends Shape {
    protected double length;
    protected double width;

    public Rectangle() {
        type = Type.RECTANGLE;
        setEdges();
    }


    @Override
    public void setEdges() {
        do {
            System.out.println("Input the length and width of the Rectangle.\nSeparated by spaces, all zero to exit:");

            try {
                Scanner scanner = new Scanner(System.in);
                double _length = scanner.nextDouble();
                double _width = scanner.nextDouble();
                if (_length == 0 && _width == 0) {
                    System.out.println("exit!");
                    break;
                }
                length = _length;
                width = _width;
            } catch (Exception e) {
                System.err.println("Please input your edges correctly!");
                continue;
            }
            System.out.println("Success");
            System.out.println(this);
            break;
        } while (true);
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getCircumference() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "Type : " + getType() +
                "\nLength : " + length + " Width : " + width +
                "\nArea : " + getArea() +
                "\nCircumference : " + getCircumference();
    }
}
