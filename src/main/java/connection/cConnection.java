package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class cConnection {

    public static Connection getConnection() throws Exception
    {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test","client","client123")
;        return conn;
    }
}
