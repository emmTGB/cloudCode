package process;

import client.Client;
import connection.ConnectionSQL;
import consts.CONNECTION_CONST;
import data.Doc;
import exceptions.DataException;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class DocProcess {
    private static final ConnectionSQL connectionDoc = new ConnectionSQL(CONNECTION_CONST.DOC_TABLE);

    public static Enumeration<Doc> getAllDocs() throws SQLException, IOException {
        ResultSet resultSet;
        resultSet = connectionDoc.listRows(new String[]{
                "ID", "filename", "creator", "createTime", "description"
        });
        Vector<Doc> v = new Vector<>();
        Vector<String> stringVector = new Vector<>();
        while (true) {
            if (!resultSet.next())
                break;
            String _ID = resultSet.getString("ID");
            String _filename = resultSet.getString("filename");
            String _creator = resultSet.getString("creator");
            Timestamp _createTime = resultSet.getTimestamp("createTime");
            String _description = resultSet.getString("description");

            if (!new Client().checkOnServer(_ID, _filename)) {
                stringVector.add(_ID);
                continue;
            }
            v.add(new Doc(_ID, _creator, _createTime, _description, _filename));
        }
        for (String id : stringVector) {
            deleteDoc(id);
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

    public static void downloadDoc(String ID) throws IOException, SQLException, DataException {
        ResultSet resultSet;
        resultSet = connectionDoc.fetchRow(
                new String[]{
                        "ID", "filename"
                },
                "'" + ID + "'",
                0
        );

        if (resultSet.next()) {
            String _ID = resultSet.getString("ID");
            String _filename = resultSet.getString("filename");
            new Client().download(_ID, _filename);
        } else {
            throw new DataException("Record Not Found!");
        }
    }

    public static void uploadDoc(String filePath, String creator, String description) throws IOException, SQLException {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        File file = new File(filePath);
        String fileName = file.getName();
        String ID = generateID(timestamp);
        new Client().upload(ID, filePath);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        connectionDoc.insertRow(
                new String[]{
                        "ID", "filename", "creator", "createTime", "description"
                },
                new String[]{
                        "'" + ID + "'", "'" + fileName + "'", "'" + creator + "'", "'" + simpleDateFormat.format(timestamp) + "'", "'" + description + "'"
                }
        );
    }

    public static void deleteDoc(String ID) {
        try {
            connectionDoc.deleteRow("ID", "'" + ID + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateID(Timestamp timestamp) {
        return String.valueOf(timestamp.getTime());
    }
}
