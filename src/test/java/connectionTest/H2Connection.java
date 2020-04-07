package connectionTest;

import dao.Client_DAO;


public final class H2Connection extends Client_DAO {


    public H2Connection(String dbDrive,String URL, String USER, String PASSWORD) throws Exception {
        super(dbDrive,URL, USER, PASSWORD);
    }

    @Override
    public void close() throws Exception
    {
        conn.createStatement().execute("DROP ALL OBJECTS");
    }

}
