package process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
    Connection connection;
    public static String user, password;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //todo
        }
        user = "root";
        password = "root";//todo
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/name?useUnicode=true&characterEncoding=gbk", user, password);
            System.out.println("success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
