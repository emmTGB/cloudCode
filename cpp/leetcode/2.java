import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        line = line.substring(0, line.length() - 1);
        System.out.println(line);
        String ss[] = line.split(" ");


        in.close();
    }
}
