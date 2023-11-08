package Users;

import Process.DataProcess;

import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User {
    public Administrator(String userName, String passWord) {
        super(userName, passWord);
        role = "Administrator";
    }

    @Override
    public void showMenu() {
        String tip = new String("Select your operation:");

        System.out.println("Your User type: Administrator");
        System.out.println(
                "Welcome to Administrator menu\n" +
                        "\t1.Modify User\n" +
                        "\t2.Delete User\n" +
                        "\t3.Add User\n" +
                        "\t4.List User\n" +
                        "\t5.Download File\n" +
                        "\t6.Doc List\n" +
                        "\t7.Change Your Password\n" +
                        "\t8.Exit\n" +
                        "***************************************"
        );
        System.out.println(tip);

        String[] npr = new String[3];
        String input = null;
        while (true) {
            input = DataProcess.scanner.nextLine().trim();
            if (!(input).matches("[12345678]")) {
                System.err.println(tip);
            } else {
                int nextInt = Integer.parseInt(input);
                switch (nextInt) {
                    case 1:
                        break;
                    case 2:
                }
            }
        }
    }

    public boolean changeUserInfo(String name, String passWord, String role) {
        return DataProcess.updateUser(name, passWord, role);
    }

    public boolean delUser(String name) {
        return DataProcess.deleteUser(name);
    }

    public boolean addUser(String name, String passWord, String role) {
        return DataProcess.insertUser(name, passWord, role);
    }

    public void listUser() {
        Enumeration<User> e = DataProcess.getAllUsers();
        User user;
        while (e.hasMoreElements()) {
            user = e.nextElement();
            System.out.println(
                    "Name: " + user.getUserName() + "\t Password: " + user.getPassWord() + "\t Role: " + user.getRole()
            );
        }
    }
}
