package Users;

import Process.DataProcess;

import java.sql.SQLOutput;
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

        String[] npr = new String[3];
        String input = null;
        do {
            System.out.println("Your User type: Administrator");
            System.out.println(
                    """
                            Welcome to Administrator menu
                            \t1.Modify User
                            \t2.Delete User
                            \t3.Add User
                            \t4.List User
                            \t5.Download File
                            \t6.File List
                            \t7.Change Your Password
                            \t8.Exit
                            ***************************************"""
            );
            System.out.println(tip);

            input = DataProcess.scanner.nextLine().trim();
            if (!(input).matches("[12345678]")) {
                System.err.println(tip);
            } else {
                int nextInt = Integer.parseInt(input);
                switch (nextInt) {
                    case 1:
                        System.out.println("Modify User");
                        TipsForInput.tipsForInputUser(npr);
                        if(changeUserInfo(npr[0], npr[1], npr[2]))
                            System.out.println("Succeeded to modify");
                        else
                            System.out.println("Failed to modify");
                        break;
                    case 2:
                        System.out.println("Delete User");
                        System.out.println("Please input user name:");
                        npr[0] = DataProcess.scanner.nextLine().trim();
                        if(delUser(npr[0]))
                            System.out.println("Succeeded to delete");
                        else
                            System.out.println("Failed to delete");
                        break;
                    case 3:
                        System.out.println("Add User");
                        TipsForInput.tipsForInputUser(npr);
                        if(addUser(npr[0], npr[1], npr[2]))
                            System.out.println("Succeeded to add");
                        else
                            System.out.println("Failed to add");
                        break;
                    case 4:
                        System.out.println("List Users");
                        listUser();
                        break;
                    case 5:
                        System.out.println("Download File");
                        //TODO
                        break;
                    case 6:
                        System.out.println("File List");
                        //TODO
                        break;
                    case 7:
                        System.out.println("Change Your Password");
                        System.out.println("Please input new password:");
                        npr[1] = DataProcess.scanner.nextLine().trim();
                        resetPassWord(npr[1]);
                        break;
                    case 8:
                        return;
                    default:
                        break;
                }
                break;
            }
        }while(true);
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
