package controller;

import connection.Connection_class;
import connection.cConnection;
import model.Client;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/Main")
public class Main extends HttpServlet {

    private Connection_class client_Manager_dao;

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
         ArrayList<Client> client_list = new ArrayList<>();
         response.setContentType("text/html");

         String Name = request.getParameter("cName");
         String LastName = request.getParameter("cLastName");
         String Address = request.getParameter("cAddress");
         String strAcc_num = request.getParameter("cAcc_num");

         Client  obj_client = new Client(Name,LastName,Address,Integer.parseInt(strAcc_num));
         client_list.add(new Client(Name,LastName,Address,Integer.parseInt(strAcc_num)));

         try
         {
             client_Manager_dao =new Connection_class(cConnection.dbDrive,cConnection.URL,cConnection.USER,cConnection.PASSWORD);
             //create the table
             client_Manager_dao.create_client_table();

             //add client data
              client_Manager_dao.add_Client(client_list);

         }catch(Exception ex)
         {
             ex.printStackTrace();
         }
         PrintWriter output = response.getWriter();
         output.println("<h2>" + obj_client.getName()+"</h2>");
     }

}
