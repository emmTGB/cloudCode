package Process;

import Users.*;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

class DataException extends Throwable {
    private String exceptionMsg;

    protected DataException(String exceptionMsg) {
        super();
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return this.exceptionMsg;
    }
}

public class DataProcess {
    private static Hashtable<String, User> userTable;

    public static Scanner scanner = new Scanner(System.in);

    public static void init() {
        userTable = new Hashtable<>();
        try {
            readUsers();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (DataException de) {
            de.printStackTrace();
        }
    }

    private static void readUsers() throws IOException, DataException {
        boolean flag;
        BufferedReader buff = null;
        do {
            try {
                flag = false;
                buff = new BufferedReader(new FileReader("../Data/Users.txt"));
            } catch (FileNotFoundException nf) {
                File file = new File("../Data/User.txt");
                flag = true;
            }
        } while (flag);

        String name, passWord, role;
        while ((name = buff.readLine()) != null) {
            passWord = buff.readLine();
            role = buff.readLine();

            if (passWord == null || role == null) {
                buff.close();
                throw new DataException("Something wrong with file 'user.txt'");
            }

            if (role.equals("Administrator"))
                userTable.put(name, new Administrator(name, passWord));
            else if (role.equals("Operator"))
                userTable.put(name, new Operator(name, passWord));
            else if (role.equals("Browser"))
                userTable.put(name, new Browser(name, passWord));
            else {
                buff.close();
                throw new DataException("Unexpected role");
            }
        }
        buff.close();
    }

    public static void writeUsers() {
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter("../Data/Users.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUserFile() {

    }

    public static User searchUser(String name, String passWord) {
        if (userTable.containsKey(name)) {
            User temp = userTable.get(name);
            if (temp.verifyPassWord(passWord)) {
                return temp;
            }
        }
        return null;
    }

    public static Enumeration<User> getAllUsers() {
        Enumeration<User> e = userTable.elements();
        return e;
    }

    public static boolean updateUser(String name, String passWord, String role) {
        User user;
        if (userTable.containsKey(name)) {
            if (role.equalsIgnoreCase("Administrator"))
                user = new Administrator(name, passWord);
            else if (role.equalsIgnoreCase("Operator"))
                user = new Operator(name, passWord);
            else
                user = new Browser(name, passWord);
            userTable.put(name, user);
            updateUserFile();
            return true;
        } else {
            return false;
        }
    }

    public static boolean insertUser(String name, String passWord, String role) {
        User user;
        if (userTable.containsKey(name))
            return false;
        else {
            if (role.equalsIgnoreCase("Administrator"))
                user = new Administrator(name, passWord);
            else if (role.equalsIgnoreCase("Operator"))
                user = new Operator(name, passWord);
            else
                user = new Browser(name, passWord);
            userTable.put(name, user);
            updateUserFile();
            return true;
        }
    }

    public static boolean deleteUser(String name) {
        if (userTable.containsKey(name)) {
            userTable.remove(name);
            updateUserFile();
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
    }
}
