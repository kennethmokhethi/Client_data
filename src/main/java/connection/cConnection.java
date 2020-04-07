package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class cConnection {

    public static final String dbDrive="com.mysql.cj.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/StudentDB";
    public static final String USER="root";
    public  static final String PASSWORD="##1235%Asdfrty";


    public static Connection getConnection(String dbDrive,String URL,String USER,String PASSWORD) throws Exception
    {

        Class.forName(dbDrive);
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        return conn;
    }
}
