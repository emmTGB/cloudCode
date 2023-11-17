package connection;

import java.sql.*;

public class ConnectionSQL {
    static Connection connection;
    static Statement statement;
    public static String user, password;
    final String tableName;

    public ConnectionSQL(String tableName) {
        this.tableName = tableName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        user = "root";
        password = "123456";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forJavaExperiment?useUnicode=true&characterEncoding=gbk", user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public int getRow() throws SQLException {
        String sql = "select count(*) as countRow from " + tableName + ";";
        ResultSet res = statement.executeQuery(sql);
        if (res.next()) {
            return res.getInt("countRow");
        } else {
            return 0;
        }
    }

    //ok
    public boolean inTable(String fieldName, String content) throws SQLException {
        String sql = "select " + fieldName + " from " + tableName
                + " where " + fieldName + " = " + content + ";";
        return statement.executeQuery(sql).next();
    }

    public ResultSet fetchRow(String[] fields, int ind, String content) throws SQLException {
        StringBuilder sql = new StringBuilder("select ");
        for (String f : fields) {
            sql.append(f).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ").append(tableName).append(" where ");
        sql.append(fields[ind]).append("=").append(content).append(";");
        return statement.executeQuery(sql.toString());
    }

    public ResultSet listRows(String[] fields) throws SQLException {
        StringBuilder sql = new StringBuilder("select ");
        for (String f : fields) {
            sql.append(f).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ").append(tableName).append(";");
        return statement.executeQuery(sql.toString());
    }

    //ok
    public void insertRow(String[] fields, String[] contents) throws SQLException {
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(tableName).append("(");
        for (String f : fields) {
            sql.append(f).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(") values (");
        for (String c : contents) {
            sql.append(c).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(");");
        statement.execute(sql.toString());
    }
}
