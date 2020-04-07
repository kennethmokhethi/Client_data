package controller;

import connection.cConnection;
import dao.Client_DAO;
import model.Client;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Retrieve_Client_data extends HttpServlet {

    public ArrayList<Client> client_list = new ArrayList<>();
    private Client_DAO client_dao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html");
        try
        {
            client_dao = new Client_DAO(cConnection.dbDrive,cConnection.URL,cConnection.USER,cConnection.PASSWORD);
            client_list = client_dao.getClients();
            display_clients(response);

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }



    private void display_clients(HttpServletResponse response)
    {
        int client_count = 0;
        try {
            PrintWriter output = response.getWriter();
            output.print("<html><body>");
            for(Client client : client_list)
            {
                ++client_count;
                output.println("<h2> The following is the client information for client :"+client_count+"</h2>");
                output.println("Name :" + client.getName());
                output.println("Lastname :"+ client.getLastname());
                output.println("Address :" + client.getAddress());
                output.println("Account number :" + client.getAcc_num());
            }

            output.print("</body></html>");
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
