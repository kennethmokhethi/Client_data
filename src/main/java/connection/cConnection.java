package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class cConnection {

    private static final String URL="jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER="root";
    private  static final String PASSWORD="##1235%Asdfrty";


    public static Connection getConnection() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        return conn;
    }
}
