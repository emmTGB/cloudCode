package users;

import consts.Role;
import exceptions.DataException;
import exceptions.UserException;
import process.DataProcess;

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
        userRole = Role.ADMINISTRATOR;
    }

    public void modifyUser(String name, String pass, String roleStr) throws UserException, DataException {
        if (!DataProcess.inTable(name)) throw UserException.USER_NOT_EXIST_ERR;
        if (name.equals(this.userName)) throw new UserException("You Can Not Modify Yourself!");
        if (passWordNOK(pass)) throw UserException.PASS_UNSUPPORTED_ERR;
        Role role = Role.getRole(roleStr);
        if (DataProcess.checkUserRole(name).equals(Role.ADMINISTRATOR))
            throw new UserException("You Can Not Modify An Admin!");
        DataProcess.updateUser(name, pass, role);
    }

    public void deleteUser(String name) throws UserException, DataException {
        if (!DataProcess.inTable(name)) throw UserException.USER_NOT_EXIST_ERR;
        if (name.equals(this.userName)) throw new UserException("You Can Not Delete Yourself!");
        if (DataProcess.checkUserRole(name).equals(Role.ADMINISTRATOR))
            throw new UserException("You Can Not Delete An Admin");

        DataProcess.deleteUser(name);
    }

    public static void addUser(String name, String pass, String roleStr) throws UserException, DataException {
        if (DataProcess.inTable(name)) throw UserException.USER_ALREADY_EXISTS_ERR;
        if (passWordNOK(pass)) throw UserException.PASS_UNSUPPORTED_ERR;
        Role role = Role.getRole(roleStr);

        DataProcess.insertUser(name, pass, role);
    }
}
