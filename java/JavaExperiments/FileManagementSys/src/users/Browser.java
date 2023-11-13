package users;

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
        userRole = "Browser";
    }

    @Override
    public void showMenu() {
        String tip = "Select your operation:";

        String pass;
        String input;
        do {
            System.out.println("Welcome! Your User type: Browser");
            System.out.println(
                    """
                            *******Browser menu******
                            \t1.Download File
                            \t2.File List
                            \t3.Change Your Password
                            \t4.Exit
                            *******************************"""
            );
            System.out.println(tip);

            input = DataProcess.scanner.nextLine().trim();
            if (!(input).matches("[1234]")) {
                System.err.println(tip);
            } else {
                int nextInt = Integer.parseInt(input);
                switch (nextInt) {
                    case 1:
                        System.out.println("Download File");
                        //TODO
                        break;
                    case 2:
                        System.out.println("File List");
                        //TODO
                        break;
                    case 3:
                        System.out.println("Change Your Password");
                        System.out.println("Please input new password:");
                        pass = DataProcess.scanner.nextLine().trim();
                        resetPassWord(pass);
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
            }
        } while (true);
    }
}
