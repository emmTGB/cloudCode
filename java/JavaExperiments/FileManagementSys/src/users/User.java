package users;

import exceptions.MyException;
import exceptions.UserException;

public abstract class User {
    protected final String userName;
    protected String passWord;
    protected Role userRole;

    protected User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public static boolean passWordNOK(String passWord) {
        return passWord.length() > 24 || passWord.length() < 4;
    }

    public String getUserName() {
        return userName;
    }

    public Role getUserRole() {
        return userRole;
    }

    public String getPassWord() {
        return passWord;
    }

    public void resetPassWord(String passWord) throws MyException {
        if (passWordNOK(passWord)) {
            throw UserException.PASS_UNSUPPORTED_ERR;
        }
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return userName + "\n" + passWord + "\n" + userRole.toString() + "\n";
    }
}
