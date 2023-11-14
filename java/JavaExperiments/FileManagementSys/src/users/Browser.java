package users;

import consts.Role;
import process.DataProcess;

public class Browser extends User {
    public static final String[] OPTION_LIST = {
            "Exit",
            "Download File",
            "File List",
            "Change Your Password"
    };

    public Browser(String userName, String passWord) {
        super(userName, passWord);
        userRole = Role.BROWSER;
    }
}
