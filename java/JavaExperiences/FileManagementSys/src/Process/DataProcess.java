package Process;

import Users.*;

import java.io.*;
import java.util.*;

class DataException extends Throwable {
    private final String exceptionMsg;

    protected DataException(String exceptionMsg) {
        super();
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return this.exceptionMsg;
    }
}

public class DataProcess {
    private static Hashtable<String, User> userTable;
    private static Hashtable<String, Doc> fileTable;
    public static final String USER_FILE = "src/data/Users.txt";
    public static final String DOC_FILE = "src/data/File.txt";

    public static Scanner scanner = new Scanner(System.in);

    public static void init() throws IOException, DataException {
        userTable = new Hashtable<>();
        fileTable = new Hashtable<>();
        readUsers();
        readDoc();
    }

    private static void readUsers() throws IOException, DataException {
        boolean flag;
        BufferedReader buff = null;
        do {
            try {
                flag = false;
                buff = new BufferedReader(new FileReader(USER_FILE));
            } catch (FileNotFoundException nf) {
                File file = new File(USER_FILE);
                file.createNewFile();
                flag = true;
            }
        } while (flag);

        String name, passWord, role;
        while ((name = buff.readLine()) != null) {
            passWord = buff.readLine();
            role = buff.readLine();

            if (passWord == null || role == null) {
                buff.close();
                throw new DataException("Something wrong with file 'Users.txt'");
            }

            switch (role) {
                case "Administrator" -> userTable.put(name, new Administrator(name, passWord));
                case "Operator" -> userTable.put(name, new Operator(name, passWord));
                case "Browser" -> userTable.put(name, new Browser(name, passWord));
                default -> {
                    buff.close();
                    throw new DataException("Unexpected role");
                }
            }
        }
        buff.close();
    }

    public static void readDoc() throws IOException, DataException {
        String idFile, idUser, timeStamp, fileDisc, fileName;

        boolean flag;
        BufferedReader buff = null;
        do {
            try {
                flag = false;
                buff = new BufferedReader(new FileReader(DOC_FILE));
            } catch (FileNotFoundException nf) {
                File file = new File(DOC_FILE);
                file.createNewFile();
                flag = true;
            }
        } while (flag);

        while ((idFile = buff.readLine()) != null) {
            idUser = buff.readLine();
            timeStamp = buff.readLine();
            fileDisc = buff.readLine();
            fileName = buff.readLine();


            if (idUser == null || timeStamp == null || fileDisc == null || fileName == null) {
                buff.close();
                throw new DataException("wrong doc file!");
            }

            long ts = Long.parseLong(timeStamp);
            fileTable.put(idFile, new Doc(idFile, idUser, ts, fileDisc, fileName));
        }
        buff.close();
    }

    public static void writeUsers() throws IOException {
        File userTxt = new File(USER_FILE);
        if (userTxt.exists())
            userTxt.delete();
        userTxt.createNewFile();
        BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userTxt)));

        Enumeration<User> e = userTable.elements();
        User user;
        while (e.hasMoreElements()) {
            user = e.nextElement();
            buff.write(user.toString());
        }
        buff.close();
    }

    public static void writeDocs() throws IOException {
        File docTxt = new File(DOC_FILE);
        if (docTxt.exists())
            docTxt.delete();
        docTxt.createNewFile();
        BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docTxt)));

        Enumeration<Doc> e = fileTable.elements();
        Doc doc;
        while (e.hasMoreElements()) {
            doc = e.nextElement();
            buff.write(doc.toString());
        }
        buff.close();
    }

    public static void updateUserFile() {

    }

    public static User fetchUser(String name, String passWord) {
        if (userTable.containsKey(name)) {
            User temp = userTable.get(name);
            if (temp.verifyPassWord(passWord)) {
                return temp;
            }
        }
        return null;
    }

    public static boolean inTable(String name) {
        return userTable.containsKey(name);
    }

    public static String checkUserRole(String name) {
        return userTable.get(name).getUserRole();
    }

    public static Enumeration<User> getAllUsers() {
        return userTable.elements();
    }

    public static boolean updateUser(String name, String passWord, String role) {
        User user;
        if (userTable.containsKey(name)) {
            if (role.equalsIgnoreCase("Administrator"))
                user = new Administrator(name, passWord);
            else if (role.equalsIgnoreCase("Operator"))
                user = new Operator(name, passWord);
            else
                user = new Browser(name, passWord);
            userTable.put(name, user);
            updateUserFile();
            return true;
        } else {
            return false;
        }
    }

    public static boolean insertUser(String name, String passWord, String role) {
        User user;
        if (userTable.containsKey(name)) {
            System.err.println("User Already Exists!");
            return false;
        } else if (User.passWordNOK(passWord)) {
            System.err.println("Unsupported Password!");
            return false;
        } else {
            if (role.equalsIgnoreCase("Administrator"))
                user = new Administrator(name, passWord);
            else if (role.equalsIgnoreCase("Operator"))
                user = new Operator(name, passWord);
            else if (role.equalsIgnoreCase("Browser"))
                user = new Browser(name, passWord);
            else {
                System.err.println("Wrong Role!");
                return false;
            }
            userTable.put(name, user);
            updateUserFile();
            return true;
        }
    }

    public static boolean deleteUser(String name) {
        if (userTable.containsKey(name)) {
            userTable.remove(name);
            updateUserFile();
            return true;
        } else {
            return false;
        }
    }

    public static Doc searchDoc(String ID) {
        if (fileTable.containsKey(ID)) {
            return fileTable.get(ID);
        }
        return null;
    }

    public static Enumeration<Doc> getAllDocs() {
        return fileTable.elements();
    }

    public static void main(String[] args) {
    }

    public static boolean insertDoc(String ID, String creator, long timestamp, String description, String filename) throws IOException {
        Doc doc;

        if (fileTable.containsKey(ID))
            return false;
        else {
            doc = new Doc(ID, creator, timestamp, description, filename);
            fileTable.put(ID, doc);
            writeDocs();
            return true;
        }
    }

    public static String generateRandomFilename() {
        String RandomFilename = "";
        Random rand = new Random();
        int random = rand.nextInt();

        Calendar calCurrent = Calendar.getInstance();
        int day = calCurrent.get(Calendar.DATE);
        int month = calCurrent.get(Calendar.MONTH) + 1;
        int year = calCurrent.get(Calendar.YEAR);
        String now = String.valueOf(year) + "_" + String.valueOf(month) + "_" + String.valueOf(day) + "_";

        RandomFilename = now + (random > 0 ? random : (-1) * random);
        return RandomFilename;
    }
}
