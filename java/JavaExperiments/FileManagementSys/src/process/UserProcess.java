package process;

import connection.ConnectionSQL;
import consts.CONNECTION_CONST;
import consts.Role;
import exceptions.DataException;
import exceptions.UserException;
import users.Administrator;
import users.Browser;
import users.Operator;
import users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class UserProcess {
    private static final ConnectionSQL connectionUsers = new ConnectionSQL(CONNECTION_CONST.USER_TABLE);

//    private static void readUsers() throws IOException, DataException, UserException {
//        boolean flag;
//        BufferedReader buff = null;
//        do {
//            try {
//                flag = false;
//                buff = new BufferedReader(new FileReader(FILE_CONST.FILE_PATH));
//            } catch (FileNotFoundException nf) {
//                File file = new File(FILE_CONST.FILE_PATH);
//                file.createNewFile();
//                flag = true;
//            }
//        } while (flag);
//
//        String name, passWord, roleStr;
//        while ((name = buff.readLine()) != null) {
//            passWord = buff.readLine();
//            roleStr = buff.readLine();
//
//            if (passWord == null || roleStr == null) {
//                buff.close();
//                throw DataException.FILE_CONTENT_ERR;
//            }
//
//            User user;
//            switch (Role.getRole(roleStr)) {
//                case ADMINISTRATOR -> user = new Administrator(name, passWord);
//                case OPERATOR -> user = new Operator(name, passWord);
//                case BROWSER -> user = new Browser(name, passWord);
//                default -> throw UserException.ROLE_UNEXPECTED_ERR;
//            }
//            userTable.put(name, user);
//        }
//        buff.close();
//    }
//
//    public static void writeUsers() throws IOException {
//        File userTxt = new File(FILE_CONST.FILE_PATH);
//        if (userTxt.exists())
//            userTxt.delete();
//        userTxt.createNewFile();
//        BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userTxt)));
//
//        Enumeration<User> e = userTable.elements();
//        User user;
//        while (e.hasMoreElements()) {
//            user = e.nextElement();
//            buff.write(user.toString());
//        }
//        buff.close();
//    }

    public static void updateUserFile() {

    }

    //ok
    public static User fetchUser(String name, String passWord) throws DataException, UserException {
        if (inTable(name)) {
            try {
                ResultSet resultSet = connectionUsers.fetchRow(new String[]{"name", "password", "role"}, "'" + name + "'", 0);
                if (!resultSet.next())
                    throw UserException.ROLE_UNEXPECTED_ERR;
                String _pass = resultSet.getString("password");
                String _role = resultSet.getString("role");
                if (_pass.equals(passWord)) {
                    switch (Role.getRole(_role)) {
                        case ADMINISTRATOR -> {
                            return new Administrator(name, passWord);
                        }
                        case OPERATOR -> {
                            return new Operator(name, passWord);
                        }
                        case BROWSER -> {
                            return new Browser(name, passWord);
                        }
                        default -> throw UserException.ROLE_UNEXPECTED_ERR;
                    }
                } else
                    throw UserException.PASS_WRONG_ERR;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new DataException("Can Not Fetch User!");
            }
        }
        throw UserException.USER_NOT_EXIST_ERR;
    }

    //ok
    public static boolean inTable(String name) throws DataException {
        try {
            return connectionUsers.inTable("name", "'" + name + "'");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataException("Can Not Determine The User Exists!");
        }
    }

    //ok
    public static Role checkUserRole(String name) throws UserException, DataException {
        if (inTable(name)) {
            try {
                ResultSet resultSet = connectionUsers.fetchRow(new String[]{"name", "role"}, "'" + name + "'", 0);
                if (resultSet.next()) {
                    String _role = resultSet.getString("role");
                    return Role.getRole(_role);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new DataException("Can Not Check User's Role!");
            }
        }
        throw UserException.USER_NOT_EXIST_ERR;
    }

    //ok
    public static Enumeration<User> getAllUsers() throws DataException, UserException {
        ResultSet resultSet;
        try {
            resultSet = connectionUsers.listRows(new String[]{
                    "name", "password", "role"
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataException("Can Not Fetch All Users!");
        }
        Vector<User> v = new Vector<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                User temp;
                String _name = resultSet.getString("name");
                String _pass = resultSet.getString("password");
                String _role = resultSet.getString("role");
                switch (Role.getRole(_role)) {
                    case ADMINISTRATOR -> temp = new Administrator(_name, _pass);
                    case OPERATOR -> temp = new Operator(_name, _pass);
                    case BROWSER -> temp = new Browser(_name, _pass);
                    default -> throw UserException.ROLE_UNEXPECTED_ERR;
                }
                v.add(temp);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new DataException("Unexpected SQL Data!");
            }
        }
        return v.elements();
    }

    //ok
    public static int getLengthOfUserLists() throws DataException {
        try {
            return connectionUsers.getRow();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataException("Can Not Get Length Of User List!");
        }
    }

    public static void updateUser(String name, String passWord, Role role) throws UserException, DataException {
        if (User.passWordNOK(passWord)) {
            throw UserException.PASS_UNSUPPORTED_ERR;
        }
        if (!inTable(name)) {
            throw UserException.USER_NOT_EXIST_ERR;
        }
        try {
            connectionUsers.updateRow(
                    new String[]{
                            "name", "password", "role"
                    },
                    new String[]{
                            "'" + name + "'", "'" + passWord + "'", "'" + role.toString() + "'",
                    },
                    "'" + name + "'",
                    0
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataException("Can Not Update User!");
        }
    }

    //ok
    public static void insertUser(String name, String passWord, Role role) throws UserException, DataException {
        if (inTable(name)) {
            throw UserException.USER_ALREADY_EXISTS_ERR;
        }
        if (User.passWordNOK(passWord)) {
            throw UserException.PASS_UNSUPPORTED_ERR;
        }
        try {
            connectionUsers.insertRow(
                    new String[]{
                            "name", "password", "role"
                    },
                    new String[]{
                            "'" + name + "'", "'" + passWord + "'", "'" + role.toString() + "'",
                    }
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataException("Can Not Insert User!");
        }
    }

    public static void deleteUser(String name) throws UserException, DataException {
        try {
            connectionUsers.deleteRow("name", "'" + name + "'");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataException("Can Not Delete User!");
        }
    }
}
