package Process;

import java.io.IOException;
import java.util.Objects;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void mainMenu() {
        menuLoop:
        do {
            String tip = "Select your operation:";
            System.out.println(
                    """
                            \t1.Login
                            \t2.Signup
                            \t0.Exit"""
            );

            String input;
            input = DataProcess.scanner.nextLine().trim();

            if (!(input).matches("[012]")) {
                System.err.println(tip);
            } else {
                int nextInt = Integer.parseInt(input);
                String name, pass, role;
                switch (nextInt) {
                    case 1:
                        System.out.println("Login");
                        System.out.println("Please input user name:");
                        name = DataProcess.scanner.nextLine().trim();
                        if (!DataProcess.inTable(name)) {
                            System.err.println("User does not exist!");
                            break;
                        }
                        System.out.println("Please input password:");
                        pass = DataProcess.scanner.nextLine().trim();
                        Objects.requireNonNull(DataProcess.fetchUser(name, pass)).showMenu();
                        break;
                    case 2:
                        System.out.println("Signup");
                        System.out.println("Please input user name:");
                        name = DataProcess.scanner.nextLine().trim();
                        if (DataProcess.inTable(name)) {
                            System.out.println("User has already existed!");
                            break;
                        }
                        System.out.println("Please input password:");
                        pass = DataProcess.scanner.nextLine().trim();
                        System.out.println("Please input role:");
                        role = DataProcess.scanner.nextLine().trim();
                        if (DataProcess.insertUser(name, pass, role))
                            System.out.println("Succeeded!");
                        else
                            System.out.println("Failed");
                        break;
                    default:
                        break menuLoop;
                }
            }
        } while (true);
    }

    public static void main(String[] args) {
        try {
            DataProcess.init();
        } catch (IOException e) {
            System.err.println("Failed to initialize the program.");
            return;
        } catch (DataException e) {
            System.err.println(e.getMessage());
            return;
        }
        mainMenu();
        DataProcess.scanner.close();
        try {
            DataProcess.writeUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}