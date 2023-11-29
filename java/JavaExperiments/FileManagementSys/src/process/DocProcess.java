package process;

import connection.ConnectionSQL;
import consts.CONNECTION_CONST;
import data.Doc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Vector;

public class DocProcess {
    private static final ConnectionSQL connectionDoc = new ConnectionSQL(CONNECTION_CONST.DOC_TABLE);

    public static Enumeration<Doc> getAllDocs() {
        ResultSet resultSet;
        try {
            resultSet = connectionDoc.listRows(new String[] {
                    "ID", "filename", "creator", "createTime", "description"
            });
        } catch (SQLException e) {
            // TODO: 11/22/23
            throw new RuntimeException(e);
        }
        Vector<Doc> v = new Vector<>();
        while (true) {
            try {
                if (!resultSet.next())
                    break;
                String _ID = resultSet.getString("ID");
                String _filename = resultSet.getString("name");
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

}
