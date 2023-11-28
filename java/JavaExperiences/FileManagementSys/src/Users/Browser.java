package Users;

import Process.DataProcess;

public class Browser extends User {
    public Browser(String userName, String passWord) {
        super(userName, passWord);
        userRole = "Browser";
    }

    @Override
    public void showMenu() {
        String tip = new String("Select your operation:");
        System.out.println("Welcome! Your User type: Browser");

        String pass;
        String input = null;
        do {
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
                        downloadFile();
                        break;
                    case 2:
                        System.out.println("File List");
                        showFileList();
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
