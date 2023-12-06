package shapes;

import java.util.Scanner;

public class Triangle extends Shape {
    protected double fir;
    protected double sec;
    protected double thr;

    public Triangle() {
        type = Type.TRIANGLE;
        setEdges();
    }


    @Override
    public void setEdges() {
        do {
            System.out.println("Input the three edges of the Triangle.\nSeparated by spaces, all zero to exit:");

            try {
                Scanner scanner = new Scanner(System.in);
                double _fir = scanner.nextDouble();
                double _sec = scanner.nextDouble();
                double _thr = scanner.nextDouble();
                if (_fir == 0 && _sec == 0 && _thr == 0) {
                    System.out.println("exit!");
                    return;
                } else if (!(_fir + _sec > _thr && _sec + _thr > _fir && _fir + _thr > _sec)) {
                    throw new Exception();
                }
                fir = _fir;
                sec = _sec;
                thr = _thr;
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
        double q = (fir + sec + thr) / 2;
        return Math.sqrt(q * (q - fir) * (q - sec) * (q - thr));
    }

    @Override
    public double getCircumference() {
        return fir + sec + thr;
    }

    @Override
    public String toString() {
        return "Type : " + getType() +
                "\nEdges : " + fir + "," + sec + "," + thr +
                "\nArea : " + getArea() +
                "\nCircumference : " + getCircumference();
    }
}
