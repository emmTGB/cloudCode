package process;

import client.Client;
import connection.ConnectionSQL;
import consts.CONNECTION_CONST;
import data.Doc;
import exceptions.DataException;
import exceptions.UserException;
import jdk.dynalink.beans.StaticClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DataTruncation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

public class DocProcess {
    private static final ConnectionSQL connectionDoc = new ConnectionSQL(CONNECTION_CONST.DOC_TABLE);

    public static Enumeration<Doc> getAllDocs() {
        ResultSet resultSet;
        try {
            resultSet = connectionDoc.listRows(new String[]{
                    "ID", "filename", "creator", "createTime", "description"
            });
        } catch (SQLException e) {
            // TODO: 11/22/23
            throw new RuntimeException(e);
        }
        Vector<Doc> v = new Vector<>();
        while (true) {
            try {
                // TODO: 0001 12/1 初始化时检查服务端文件是否存在并更新数据库 
                if (!resultSet.next())
                    break;
                String _ID = resultSet.getString("ID");
                String _filename = resultSet.getString("filename");
                String _creator = resultSet.getString("creator");
                Timestamp _createTime = resultSet.getTimestamp("createTime");
                String _description = resultSet.getString("description");
                v.add(new Doc(_ID, _creator, _createTime, _description, _filename));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return v.elements();
    }

    public static int getLengthOfDocList() throws DataException {
        try {
            return connectionDoc.getRow();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataException("Can Not Get Length Of Doc List!");
        }
    }

    public static void downloadDoc(String ID) throws FileNotFoundException {
        ResultSet resultSet;
        try {
            resultSet = connectionDoc.fetchRow(
                    new String[]{
                            "ID", "filename"
                    },
                    "'" + ID + "'",
                    0
            );
        } catch (SQLException e) {
            // TODO: 0029 11/29
            throw new RuntimeException(e);
        }
        try {
            if (resultSet.next()) {
                String _ID = resultSet.getString("ID");
                String _filename = resultSet.getString("filename");
                Client.download(_ID, _filename);
            } else {
                throw new RuntimeException(); // TODO: 0029 11/29
            }
        } catch (SQLException e) {
            // TODO: 0029 11/29
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void uploadDoc(String filePath, String creator, String description) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        File file = new File(filePath);
        String fileName = file.getName();
        String ID = generateID(fileName, timestamp);
        try {
            Client.upload(ID, filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            connectionDoc.insertRow(
                    new String[]{
                            "ID", "filename", "creator", "createTime", "description"
                    },
                    new String[]{
                            "'" + ID + "'", "'" + fileName + "'", "'" + creator + "'", "'" + simpleDateFormat.format(timestamp) + "'", "'" + description + "'"
                    }
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDoc(String ID) {
        try {
            connectionDoc.deleteRow("ID", "'" + ID + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateID(String filename, Timestamp timestamp) {
        return String.valueOf(timestamp.getTime());
    }
}
