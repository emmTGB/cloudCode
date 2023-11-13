package users;

public abstract class User {
    protected final String userName;
    protected String passWord;
    protected String userRole;


    protected User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public static boolean passWordNOK(String passWord) {
        return passWord.length() > 24 || passWord.length() < 4;
    }

    public abstract void showMenu();

    public String getUserName() {
        return userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean verifyPassWord(String passWord) {
        return this.passWord.equals(passWord);
    }

    protected void resetPassWord(String passWord) {
        if (passWordNOK(passWord)) {
            System.err.println("Unsupported Password!");
            return;
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
        return userName + "\n" + passWord + "\n" + userRole + "\n";
    }
}
