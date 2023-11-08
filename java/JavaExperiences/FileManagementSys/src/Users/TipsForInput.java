package Users;

import Process.DataProcess;

public interface TipsForInput {
    static void tipsForInputUser(String[] npr) {
        System.out.println("Please input user name:");
        npr[0] = DataProcess.scanner.nextLine().trim();
        System.out.println("Please input password:");
        npr[1] = DataProcess.scanner.nextLine().trim();
        System.out.println("Please input role:");
        npr[2] = DataProcess.scanner.nextLine().trim();
    }
}
