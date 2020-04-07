package dao;

import connection.cConnection;
import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Client_Manager_DAO {

    private ArrayList<Client> clients = new ArrayList<>();
    protected Connection conn;
    private static final String CREATE_CLIENT_TABLE="CREATE TABLE IF NOT EXISTS CLIENT_TABLE(C_Name text,LastName text,Address text,Acc_num int primary key)";
    private static final String INSERT_CLIENT="INSERT INTO CLIENT_TABLE(C_Name,LastName,Address,Acc_num) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_CLIENTS="SELECT * FROM CLIENT_TABLE";

    public Client_Manager_DAO(String dbDrive, String URL, String USER, String PASSWORD) throws Exception
    {
        conn = cConnection.getConnection(dbDrive,URL,USER,PASSWORD);
    }

    public boolean create_client_table() throws Exception
    {
         Statement create_stmt = conn.createStatement();
        return  create_stmt.execute(CREATE_CLIENT_TABLE);
    }

    public void add_Client(ArrayList<Client> obj_client_list)
    {
        try
        {
            PreparedStatement client_ps = conn.prepareStatement(INSERT_CLIENT);
            for(int i=0;i < obj_client_list.size();i++)
            {
                client_ps.setString(1,obj_client_list.get(i).getName());
                client_ps.setString(2,obj_client_list.get(i).getLastname());
                client_ps.setString(3,obj_client_list.get(i).getAddress());
                client_ps.setInt(4,obj_client_list.get(i).getAcc_num());
                client_ps.executeUpdate();
            }


        }catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public ArrayList<Client> getClients()
    {
        try
        {
             Statement retrieve_data_from_db=conn.createStatement();
             ResultSet output_from_db=retrieve_data_from_db.executeQuery(SELECT_ALL_CLIENTS);

            while(output_from_db.next())
            {
                String Name= output_from_db.getString(1);
                String Lastname= output_from_db.getString(2);
                String Address  = output_from_db.getString(3);
                int Acc_num= output_from_db.getInt(4);
                clients.add(new Client(Name,Lastname,Address,Acc_num));
            }

        }catch(Exception ex)
        {
               ex.printStackTrace();
        }

        return clients;
    }

    public void close() throws Exception
    {
       conn.close();
    }

}
