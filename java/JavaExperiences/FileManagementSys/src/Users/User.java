package Users;

import Process.*;

import java.io.*;
import java.util.Enumeration;

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

    protected String getPassWord() {
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

    public void showFileList() {
        Enumeration<Doc> e = DataProcess.getAllDocs();
        Doc doc;
        while (e.hasMoreElements()) {
            doc = e.nextElement();
            System.out.println("ID:" + doc.getID() + "\t Creator:" + doc.getCreator() + "\t Time:" + doc.getTimestamp() + "\t Filename" + doc.getFilename());
            System.out.println("Description: " + doc.getDescription());
        }
    }

    public boolean downloadFile() {
        System.out.println("input id:");
        String field = DataProcess.scanner.nextLine().trim();

        Doc doc = DataProcess.searchDoc(field);

        if (doc == null) {
            System.out.println("id [" + field + "] not found");
            System.out.println("failed to download");
            return false;
        }

        byte[] buffer = new byte[1024];
        String severPath = "D:/";
        File tempFile = new File(severPath + doc.getFilename());

        BufferedInputStream infile = null;
        BufferedOutputStream targetfile = null;
        try {
            infile = new BufferedInputStream(new FileInputStream(tempFile));
            targetfile = new BufferedOutputStream(new FileOutputStream(severPath));
            while (true) {
                int byteRead = 0;
                byteRead = infile.read(buffer);
                if (byteRead == -1)
                    break;
                targetfile.write(buffer, 0, byteRead);
            }
            infile.close();
            targetfile.close();
            System.out.println("success");
        } catch (FileNotFoundException e) {
            System.out.println("file: " + doc.getFilename() + " not exists!");
            System.out.println("failed to download");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("wrong with data process");
            System.out.println("failed to download");
            //e.printStackTrace();
        }

        return true;
    }

    @Override
    public String toString() {
        return userName + "\n" + passWord + "\n" + userRole + "\n";
    }
}
