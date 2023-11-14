package process;

import graphic.frames.MyFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {

    public static void main(String[] args) {
        try {
            DataProcess.init();
        } catch (IOException e) {
            System.err.println("Failed to initialize the program.");
            return;
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            return;
        }
        MyFrame mainFrame = new MyFrame("FileManagementSys");
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosing(e);
                try {
                    DataProcess.writeUsers();
                } catch (IOException ex) {
                    //todo
                    throw new RuntimeException(ex);
                }
            }
        });

        DataProcess.scanner.close();
    }
}