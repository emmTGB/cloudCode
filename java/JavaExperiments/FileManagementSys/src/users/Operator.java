package users;

import consts.Role;
import process.DataProcess;

public class Operator extends User {
    public static final String[] OPTION_LIST = {
            "Exit",
            "Download File",
            "Upload File",
            "File List",
            "Change Your Password"
    };

    public Operator(String userName, String passWord) {
        super(userName, passWord);
        userRole = Role.OPERATOR;
    }

    //TODO
    private boolean uploadFile() {
        return false;
    }
}
