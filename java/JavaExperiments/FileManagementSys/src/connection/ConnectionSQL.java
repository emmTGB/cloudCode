package connection;

import consts.CONNECTION_CONST;

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
        user = CONNECTION_CONST.SQL_USER;
        password = CONNECTION_CONST.SQL_PASS;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" +
                            CONNECTION_CONST.SQL_HOST +
                            ":" +
                            CONNECTION_CONST.SQL_PORT +
                            "/" +
                            CONNECTION_CONST.DATABASE +
                            "?useUnicode=true&characterEncoding=gbk",
                    user, password
            );
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public boolean inTable(String fieldName, String target) throws SQLException {
        String sql = "select " + fieldName + " from " + tableName
                + " where " + fieldName + " = " + target + ";";
        return statement.executeQuery(sql).next();
    }

    public ResultSet fetchRow(String[] fields, String target, int ind) throws SQLException {
        StringBuilder sql = new StringBuilder("select ");
        for (String f : fields) {
            sql.append(f).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ").append(tableName).append(" where ");
        sql.append(fields[ind]).append("=").append(target).append(";");
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

    public void deleteRow(String field, String target) throws SQLException {
        statement.execute("delete from " + tableName + " where " + field + " = " + target + ";");
    }

    public void updateRow(String[] fields, String[] contents, String target, int ind) throws SQLException {
        StringBuilder sql = new StringBuilder("update ");
        sql.append(tableName).append(" set ");
        for (int i = 0; i < fields.length; i++) {
            sql.append(fields[i]).append(" = ").append(contents[i]).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" where ")
                .append(fields[ind]).append(" = ").append(target).append(";");
        statement.execute(sql.toString());
    }
}
