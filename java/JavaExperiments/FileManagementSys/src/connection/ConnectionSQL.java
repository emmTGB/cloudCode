package connection;

import javax.sound.midi.Soundbank;
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
            System.out.println("success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //todo
        }
        user = "root";
        password = "123456";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forJavaExperiment?useUnicode=true&characterEncoding=gbk", user, password);
            System.out.println("success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public int getRow() {
        String sql = "select count(*) as countRow from " + tableName + ";";
        try {
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return res.getInt("countRow");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ok
    public boolean inTable(String fieldName, String content) {
        String sql = "select " + fieldName + " from " + tableName
                + " where " + fieldName + " = " + content + ";";
        try {
            return statement.executeQuery(sql).next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet fetchRow(String[] fields, int ind, String content) {
        StringBuilder sql = new StringBuilder("select ");
        for (String f : fields) {
            sql.append(f).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ").append(tableName).append(" where ");
        sql.append(fields[ind]).append("=").append(content).append(";");
        try {
            return statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listRows(String[] fields) {
        StringBuilder sql = new StringBuilder("select ");
        for (String f : fields) {
            sql.append(f).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ").append(tableName).append(";");
        try {
            return statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ok
    public void insertRow(String[] fields, String[] contents) {
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
        try {
            statement.execute(sql.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConnectionSQL con = new ConnectionSQL("users");
        Connection connection1 = con.getConnection();
        try {
            Statement statement = connection1.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "select name from users where name = 'byeol';";
        try {
            System.out.println(
                    statement.executeQuery(sql).getRow());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
