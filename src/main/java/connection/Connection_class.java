package connection;
import dao.Client_Manager_DAO;
import java.sql.Connection;


public final class Connection_class extends Client_Manager_DAO {

    public Connection_class(String dbDrive, String URL, String USER, String PASSWORD) throws Exception {
        super(dbDrive,URL, USER, PASSWORD);
    }

    @Override
    public void close() throws Exception
    {
        conn.createStatement().execute("DROP ALL OBJECTS");
    }

    public Connection getConnection()
    {
        return conn;
    }

}
