package shapes;

import java.util.Scanner;

public class Circle extends Shape {
    public Circle() {
        type = Type.CIRCLE;
        setEdges();
    }

    protected double radius;

    @Override
    public void setEdges() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Input radius of the Rectangle.\nSeparated by spaces, all zero to exit:");

            try {
                double _radius = scanner.nextDouble();
                if (_radius == 0) {
                    System.out.println("exit!");
                    break;
                }
                radius = _radius;
            } catch (Exception e) {
                System.err.println("Please input your radius correctly!");
                continue;
            }
            System.out.println("Success");
            System.out.println(this);
            break;
        } while (true);
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Type : " + getType() +
                "\nRadius : " + radius +
                "\nArea : " + getArea() +
                "\nCircumference : " + getCircumference();
    }
}
