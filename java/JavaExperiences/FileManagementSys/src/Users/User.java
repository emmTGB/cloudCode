package Users;

import Process.DataProcess;

public abstract class User {
    protected final String userName;
    protected String passWord;
    protected String role;


    protected User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public abstract void showMenu();

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    protected String getPassWord() {
        return passWord;
    }

    public boolean verifyPassWord(String passWord) {
        return this.passWord.equals(passWord);
    }

    protected void resetPassWord(String passWord){
        this.passWord = passWord;
    }

    //TODO
    protected void showFileList(){}

    protected void downloadFile(){}
}
