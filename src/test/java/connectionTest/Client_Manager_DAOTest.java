package connectionTest;
import connection.Connection_class;
import model.Client;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Client_Manager_DAOTest {
    private static final String URL="jdbc:h2:mem:";
    private static final String dbDrive = "org.h2.Driver";
    private static final ArrayList<Client> clients = new ArrayList<>();

    private static Connection_class h2connection;

    @BeforeClass
    public static void setUp() throws Exception
    {
        h2connection = new Connection_class(dbDrive,URL,null,null);
        initializeClients();
    }

    private static void initializeClients()
    {
        clients.add(new Client("Mike","Johns","12 Naturena st",2523));
        clients.add(new Client("Ken","Mavuso","12 rd",232123));

    }

    @Test
    public void compare_data_in_db() throws Exception
    {
        h2connection.create_client_table();

        h2connection.add_Client(clients);
        Assert.assertEquals(2, h2connection.getClients().size());
        for(int i = 0; i < 2;i++)
        {
            Assert.assertEquals(clients.get(i).getName(), h2connection.getClients().get(i).getName());
            Assert.assertEquals(clients.get(i).getLastname(), h2connection.getClients().get(i).getLastname());
            Assert.assertEquals(clients.get(i).getAddress(), h2connection.getClients().get(i).getAddress());
            Assert.assertEquals(clients.get(i).getAcc_num(), h2connection.getClients().get(i).getAcc_num());

        }
    }

    @Test
    public void verify_table_structure_data() throws Exception
    {
        h2connection.create_client_table();
        PreparedStatement ps = h2connection.getConnection().prepareStatement("SHOW COLUMNS FROM CLIENT_TABLE");
        ResultSet rs = ps.executeQuery();
        List<String> column_names =new ArrayList<>();
        while(rs.next())
        {
            column_names.add(rs.getString("Field"));
        }

        Assert.assertEquals("C_NAME",column_names.get(0));
        Assert.assertEquals("LASTNAME",column_names.get(1));
        Assert.assertEquals("ADDRESS",column_names.get(2));
        Assert.assertEquals("ACC_NUM",column_names.get(3));

    }

    @AfterClass
    public static void close_connection() throws Exception
    {
        h2connection.close();
    }

}
