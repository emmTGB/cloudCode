package Users;

import Process.DataProcess;

import java.util.Enumeration;

public class Administrator extends User {
    public Administrator(String userName, String passWord) {
        super(userName, passWord);
        userRole = "Administrator";
    }

    @Override
    public void showMenu() {
        String tip = "Select your operation:";

        String name, pass, role;
        String input = null;
        System.out.println("Welcome! Your User type: Administrator");
        do {
            System.out.println(
                    """
                            ******Administrator menu******
                            \t1.Modify User
                            \t2.Delete User
                            \t3.Add User
                            \t4.List User
                            \t5.Download File
                            \t6.File List
                            \t7.Change Your Password
                            \t8.Exit
                            *******************************"""
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
                        System.out.println("Please input user name:");
                        name = DataProcess.scanner.nextLine().trim();
                        if (!DataProcess.inTable(name)) {
                            System.err.println("User does not exist!");
                            break;
                        } else if (DataProcess.checkUserRole(name).equalsIgnoreCase("Administrator")) {
                            System.err.println("You can not modify an Admin");
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
                        if (changeUserInfo(name, pass, role))
                            System.out.println("Succeeded to modify");
                        else
                            System.out.println("Failed to modify");
                        break;
                    case 2:
                        System.out.println("Delete User");
                        System.out.println("Please input user name:");
                        name = DataProcess.scanner.nextLine().trim();
                        if (delUser(name))
                            System.out.println("Succeeded to delete");
                        else
                            System.err.println("Failed to delete");
                        break;
                    case 3:
                        System.out.println("Add User");
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
                        if (addUser(name, pass, role))
                            System.out.println("Succeeded to add");
                        else
                            System.out.println("Failed to add");
                        break;
                    case 4:
                        System.out.println("----List Users----");
                        listUser();
                        System.out.println("----List Ending---");
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
                        pass = DataProcess.scanner.nextLine().trim();
                        resetPassWord(pass);
                        break;
                    case 8:
                        return;
                    default:
                        break;
                }
            }
        } while (true);
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
                    "Name: " + user.getUserName() + "\t Password: " + user.getPassWord() + "\t Role: " + user.getUserRole()
            );
        }
    }
}
