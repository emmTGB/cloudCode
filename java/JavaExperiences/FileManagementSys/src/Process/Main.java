package Process;

import Graphic.MyFrame;
import Users.User;

import java.io.IOException;
import java.util.Objects;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void mainMenu() {
        try {
            DataProcess.init();
        } catch (IOException e) {
            System.err.println("wrong with init");
            ;
        } catch (DataException e) {
            System.err.println(e.getMessage());
        }

        menuLoop:
        do {
            System.out.println(
                    """
                            ------Menu------
                            \t1.Login
                            \t2.Signup
                            \t0.Exit
                            ----------------"""
            );
            System.out.print("Select your operation: ");

            String input;
            input = DataProcess.scanner.nextLine().trim();

            if ((input).matches("[012]")) {
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
                        User _u = DataProcess.fetchUser(name, pass);
                        if (_u == null) {
                            System.err.println("wrong key");
                            break;
                        }
                        _u.showMenu();
                        break;
                    case 2:
                        System.out.println("Signup");
                        System.out.println("Please input user name:");
                        name = DataProcess.scanner.nextLine().trim();
                        if (DataProcess.inTable(name)) {
                            System.err.println("User has already existed!");
                            break;
                        }
                        System.out.println("Please input password:");
                        pass = DataProcess.scanner.nextLine().trim();
                        if (User.passWordNOK(pass)) {
                            System.err.println("Unsupported Password!");
                            break;
                        }
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
            } else {
                System.err.println("Wrong Number Typed!");
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
            DataProcess.writeDocs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}