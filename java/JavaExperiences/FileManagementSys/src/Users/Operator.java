package Users;

import Process.DataProcess;

import java.io.*;

public class Operator extends User {
    public Operator(String userName, String passWord) {
        super(userName, passWord);
        userRole = "Operator";
    }

    @Override
    public void showMenu() {
        String tip = new String("Select your operation:");
        System.out.println("Welcome! Your User type: Operator");

        String pass;
        String input = null;
        do {
            System.out.println(
                    """
                            ******Operator menu******
                            \t1.Download File
                            \t2.Upload File
                            \t3.File List
                            \t4.Change Your Password
                            \t5.Exit
                            *******************************"""
            );
            System.out.println(tip);

            input = DataProcess.scanner.nextLine().trim();
            if (!(input).matches("[12345]")) {
                System.err.println("wrong option!");
            } else {
                int nextInt = Integer.parseInt(input);
                switch (nextInt) {
                    case 1:
                        System.out.println("Download File");
                        downloadFile();
                        break;
                    case 2:
                        System.out.println("Upload File");
                        uploadFile();
                        break;
                    case 3:
                        System.out.println("File List");
                        showFileList();
                        break;
                    case 4:
                        System.out.println("Change Your Password");
                        System.out.println("Please input new password:");
                        pass = DataProcess.scanner.nextLine().trim();
                        resetPassWord(pass);
                        break;
                    case 5:
                        return;
                    default:
                        break;
                }
            }
        } while (true);
    }

    public void uploadFile() {
        System.out.println("请输入源文件名（包含路径）");
        String sourcefile = DataProcess.scanner.nextLine().trim();
        System.out.println("请输入档案号：");
        String ID = DataProcess.scanner.nextLine().trim();
        System.out.println("请输入档案描述：");
        String description = DataProcess.scanner.nextLine().trim();
        boolean upSucceed = false;

        File tempFile = new File(sourcefile.trim());
        String filename = tempFile.getName();

        int lastIndexOf = filename.lastIndexOf(".");
        if (lastIndexOf == -1) {
            filename = DataProcess.generateRandomFilename();
        } else {
            filename = DataProcess.generateRandomFilename() + filename.substring(lastIndexOf);
        }

        try {
            byte[] buffer = new byte[1024];
            BufferedInputStream infile = new BufferedInputStream(new FileInputStream((tempFile)));
            BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream("D:/" + filename));
            while (true) {
                int byteRead = infile.read(buffer);
                if (byteRead == -1)
                    break;
                targetfile.write(buffer, 0, byteRead);
            }
            infile.close();
            targetfile.close();

            String creator = this.getUserName();
            long timestamp = System.currentTimeMillis();
            upSucceed = DataProcess.insertDoc(ID, creator, timestamp, description, filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件未找到！");
        } catch (IOException e) {
            System.out.println("文件上传过程出错！");
        } finally {
            if (upSucceed)
                System.out.println("文件上传成功!");
            else
                System.out.println("文件上传失败!");
        }
    }
}
