package Process;

import Users.*;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

class DataException extends Throwable {
    private final String exceptionMsg;

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

    public static void init() throws IOException, DataException {
        userTable = new Hashtable<>();
        readUsers();
    }

    private static void readUsers() throws IOException, DataException {
        boolean flag;
        BufferedReader buff = null;
        do {
            try {
                flag = false;
                buff = new BufferedReader(new FileReader("src/Data/Users.txt"));
            } catch (FileNotFoundException nf) {
                File file = new File("src/Data/Users.txt");
                file.createNewFile();
                flag = true;
            }
        } while (flag);

        String name, passWord, role;
        while ((name = buff.readLine()) != null) {
            passWord = buff.readLine();
            role = buff.readLine();

            if (passWord == null || role == null) {
                buff.close();
                throw new DataException("Something wrong with file 'Users.txt'");
            }

            switch (role) {
                case "Administrator" -> userTable.put(name, new Administrator(name, passWord));
                case "Operator" -> userTable.put(name, new Operator(name, passWord));
                case "Browser" -> userTable.put(name, new Browser(name, passWord));
                default -> {
                    buff.close();
                    throw new DataException("Unexpected role");
                }
            }
        }
        buff.close();
    }

    public static void writeUsers() throws IOException {
        File userTxt = new File("src/Data/Users.txt");
        if (userTxt.exists())
            userTxt.delete();
        userTxt.createNewFile();
        BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userTxt)));

        Enumeration<User> e = userTable.elements();
        User user;
        while (e.hasMoreElements()) {
            user = e.nextElement();
            buff.write(user.toString());
        }
        buff.close();
    }

    public static void updateUserFile() {

    }

    public static User fetchUser(String name, String passWord) {
        if (userTable.containsKey(name)) {
            User temp = userTable.get(name);
            if (temp.verifyPassWord(passWord)) {
                return temp;
            }
        }
        return null;
    }

    public static boolean inTable(String name) {
        return userTable.containsKey(name);
    }

    public static String checkUserRole(String name) {
        return userTable.get(name).getUserRole();
    }

    public static Enumeration<User> getAllUsers() {
        return userTable.elements();
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
        if (userTable.containsKey(name)) {
            System.err.println("User Already Exists!");
            return false;
        } else if (User.passWordNOK(passWord)) {
            System.err.println("Unsupported Password!");
            return false;
        } else {
            if (role.equalsIgnoreCase("Administrator"))
                user = new Administrator(name, passWord);
            else if (role.equalsIgnoreCase("Operator"))
                user = new Operator(name, passWord);
            else if (role.equalsIgnoreCase("Browser"))
                user = new Browser(name, passWord);
            else {
                System.err.println("Wrong Role!");
                return false;
            }
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