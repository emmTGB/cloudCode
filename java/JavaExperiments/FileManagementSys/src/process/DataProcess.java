package process;

import consts.FILE_CONST;
import consts.Role;
import users.Administrator;
import users.Browser;
import users.Operator;
import users.User;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class DataProcess {
    private static Hashtable<String, User> userTable;

    public static final Scanner scanner = new Scanner(System.in);

    public static void init() throws IOException, DataException, UserException {
        userTable = new Hashtable<>();
        readUsers();
    }

    private static void readUsers() throws IOException, DataException, UserException {
        boolean flag;
        BufferedReader buff = null;
        do {
            try {
                flag = false;
                buff = new BufferedReader(new FileReader(FILE_CONST.FILE_PATH));
            } catch (FileNotFoundException nf) {
                File file = new File(FILE_CONST.FILE_PATH);
                file.createNewFile();
                flag = true;
            }
        } while (flag);

        String name, passWord, roleStr;
        while ((name = buff.readLine()) != null) {
            passWord = buff.readLine();
            roleStr = buff.readLine();

            if (passWord == null || roleStr == null) {
                buff.close();
                throw DataException.FILE_CONTENT_ERR;
            }

            User user;
            switch (Role.getRole(roleStr)) {
                case ADMINISTRATOR -> user = new Administrator(name, passWord);
                case OPERATOR -> user = new Operator(name, passWord);
                case BROWSER -> user = new Browser(name, passWord);
                default -> throw UserException.ROLE_UNEXPECTED_ERR;
            }
            userTable.put(name, user);
        }
        buff.close();
    }

    public static void writeUsers() throws IOException {
        File userTxt = new File(FILE_CONST.FILE_PATH);
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

    public static User fetchUser(String name, String passWord) throws UserException {
        if (userTable.containsKey(name)) {
            User temp = userTable.get(name);
            if (temp.verifyPassWord(passWord)) {
                return temp;
            } else {
                throw UserException.PASS_WRONG_ERR;
            }
        }
        throw UserException.USER_NOT_EXIST_ERR;
    }

    public static boolean inTable(String name) {
        return userTable.containsKey(name);
    }

    public static Role checkUserRole(String name) throws UserException {
        if (userTable.containsKey(name))
            return userTable.get(name).getUserRole();
        throw UserException.USER_NOT_EXIST_ERR;
    }

    public static Enumeration<User> getAllUsers() {
        return userTable.elements();
    }

    public static int getLengthOfUserLists() {
        return userTable.size();
    }

    public static void updateUser(String name, String passWord, Role role) throws UserException {
        User user;
        if (User.passWordNOK(passWord)) {
            throw UserException.PASS_UNSUPPORTED_ERR;
        } else if (userTable.containsKey(name)) {
            switch (role) {
                case ADMINISTRATOR -> user = new Administrator(name, passWord);
                case OPERATOR -> user = new Operator(name, passWord);
                case BROWSER -> user = new Browser(name, passWord);
                default -> throw UserException.PASS_UNSUPPORTED_ERR;
            }
            userTable.put(name, user);
            updateUserFile();
        } else {
            throw UserException.USER_NOT_EXIST_ERR;
        }
    }

    public static void insertUser(String name, String passWord, Role role) throws UserException {
        User user;
        if (userTable.containsKey(name)) {
            throw UserException.USER_ALREADY_EXISTS_ERR;
        } else if (User.passWordNOK(passWord)) {
            throw UserException.PASS_UNSUPPORTED_ERR;
        } else {
            switch (role) {
                case ADMINISTRATOR -> user = new Administrator(name, passWord);
                case OPERATOR -> user = new Operator(name, passWord);
                case BROWSER -> user = new Browser(name, passWord);
                default -> throw UserException.ROLE_UNEXPECTED_ERR;
            }
            userTable.put(name, user);
            updateUserFile();
        }
    }

    public static void deleteUser(String name) throws UserException {
        if (userTable.containsKey(name)) {
            userTable.remove(name);
            updateUserFile();
        } else {
            throw UserException.USER_NOT_EXIST_ERR;
        }
    }
}
