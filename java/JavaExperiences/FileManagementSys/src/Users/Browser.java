package Users;

import Process.DataProcess;

public class Browser extends User {
    public Browser(String userName, String passWord) {
        super(userName, passWord);
    }

    @Override
    public void showMenu() {
        String tip = new String("Select your operation:");

        String[] npr = new String[3];
        String input = null;
        do {
            System.out.println("Your User type: Browser");
            System.out.println(
                    """
                            Welcome to Browser menu
                            \t1.Download File
                            \t2.File List
                            \t3.Change Your Password
                            \t4.Exit
                            ***************************************"""
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
                        npr[1] = DataProcess.scanner.nextLine().trim();
                        resetPassWord(npr[1]);
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
                break;
            }
        }while(true);
    }
}
