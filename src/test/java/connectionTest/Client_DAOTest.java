package connectionTest;
import dao.Client_DAO;
import model.Client;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class Client_DAOTest {
    private static final String URL="jdbc:h2:mem:";
    private static final String dbDrive = "org.h2.Driver";
    private static final ArrayList<Client> clients = new ArrayList<>();

    private static H2Connection h2Connection;

    @BeforeClass
    public static void setUp() throws Exception
    {
        h2Connection = new H2Connection(dbDrive,URL,null,null);
        initializeClients();
    }

    private static void initializeClients()
    {
        clients.add(new Client("Ken","Mavuso","12 rd",232123));
      //  clients.add(new Client("Mike","Johns","12 Naturena st",2523));
    }


    @Test
    public void data_inserted() throws Exception
    {
        h2Connection.create_client_table();

        h2Connection.add_Client(clients.get(0));
        Assert.assertEquals(clients.get(0).getName(),h2Connection.getClients().get(0).getName());
        
    }


    @AfterClass
    public static void close_connection() throws Exception
    {
      h2Connection.close();
    }




}
