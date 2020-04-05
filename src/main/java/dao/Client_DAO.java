package dao;

import connection.cConnection;
import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Client_DAO {

    private ArrayList<Client> clients = new ArrayList<>();
    private Client obj_client;
    private Connection conn;
    private Statement create_stmt;
    private Statement retrieve_data_from_db;
    private ResultSet output_from_db;

    public void create_client_table()
    {
        try
        {
            conn = cConnection.getConnection();
            create_stmt = conn.createStatement();
            String  create_client_table_sql ="CREATE TABLE IF NOT EXISTS CLIENT_TABLE(C_Name text,LastName text,Address text,Acc_num int primary key)";
            create_stmt.executeUpdate(create_client_table_sql);

            System.out.println("Created the client table");

        }catch(Exception ex)
        {
            System.out.println("Error on createe table2 "+ex);
        }finally {
            close(conn,create_stmt,null);
        }
    }


    public boolean add_Client(Client obj_client)
    {
        try
        {
            conn = cConnection.getConnection();
            String add_client_data ="INSERT INTO CLIENT_TABLE(C_Name,LastName,Address,Acc_num) VALUES(?,?,?,?)";
            PreparedStatement client_ps = conn.prepareStatement(add_client_data);
            client_ps.setString(1,obj_client.getName());
            client_ps.setString(2,obj_client.getLastname());
            client_ps.setString(3,obj_client.getAddress());
            client_ps.setInt(4,obj_client.getAcc_num());
            client_ps.executeUpdate();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }finally {
            //close connection
            close(conn,null,null);
        }

        return true;
    }

    public ArrayList<Client> getClients()
    {
        try
        {
            conn = cConnection.getConnection();
            retrieve_data_from_db=conn.createStatement();
            output_from_db=retrieve_data_from_db.executeQuery("SELECT * FROM CLIENT_TABLE");

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

        }finally {
            //close connection
            close(conn,retrieve_data_from_db,output_from_db);

        }


        return clients;

    }



    private void close(Connection conn,Statement stmt,ResultSet rs)
    {
        try
        {
            if(conn != null)
            {
                conn.close();
            }

            if(stmt != null)
            {
                stmt.close();
            }
            if(rs != null)
            {
                rs.close();
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



}
