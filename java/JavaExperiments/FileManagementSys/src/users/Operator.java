package users;

public class Operator extends User {
    public static final String[] OPTION_LIST = {
            "Exit",
            "Upload File",
            "File List",
            "Change Your Password"
    };

    public Operator(String userName, String passWord) {
        super(userName, passWord);
        userRole = Role.OPERATOR;
    }
}
