package users;

import consts.Role;
import process.UserException;

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

    public boolean verifyPassWord(String passWord) {
        return this.passWord.equals(passWord);
    }

    public void resetPassWord(String passWord) throws UserException {
        if (passWordNOK(passWord)) {
            throw UserException.PASS_UNSUPPORTED_ERR;
        }
        this.passWord = passWord;
    }

    //TODO
    protected void showFileList() {
    }

    protected void downloadFile() {
    }

    @Override
    public String toString() {
        return userName + "\n" + passWord + "\n" + userRole.toString() + "\n";
    }
}
