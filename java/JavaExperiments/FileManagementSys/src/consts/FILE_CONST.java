package consts;

import java.io.File;

public interface FILE_CONST {
    // String DOWNLOAD_DIR = System.getenv("USERPROFILE") + "\\Downloads\\";
    String DOWNLOAD_DIR = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
    // String SERVER_DIR = "D:/";
    String SERVER_DIR = System.getProperty("user.home") + File.separator;
}
