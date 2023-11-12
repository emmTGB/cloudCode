package Users;

import Process.*;

import java.util.Enumeration;

public class Administrator extends User {
    public static final String[] OPTION_LIST = {
            "Exit",
            "Modify User",
            "Delete User",
            "Add User",
            "List User",
            "Download File",
            "File List",
            "Change Your Password"
    };

    public Administrator(String userName, String passWord) {
        super(userName, passWord);
        userRole = "Administrator";
    }

    @Override
    public void showMenu() {
        String tip = "Select your operation:";

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
                            \t0.Exit
                            *******************************"""
            );
            System.out.println(tip);
        } while (true);
    }

    public void addUser(String name, String passWord, String role) {
        try {
            DataProcess.insertUser(name, passWord, role);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        return;
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
