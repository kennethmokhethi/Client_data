package controller;

import model.Client;
import dao.Client_DAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Main")
public class Main extends HttpServlet {
  private Client_DAO client_dao;

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
         client_dao =new Client_DAO();

//         doPost(request,response);
         response.setContentType("text/html");

         String Name = request.getParameter("cName");
         String LastName = request.getParameter("cLastName");
         String Address = request.getParameter("cAddress");
         String strAcc_num = request.getParameter("cAcc_num");


         Client  obj_client = new Client(Name,LastName,Address,Integer.parseInt(strAcc_num));

         try
         {
             //create the table
             client_dao.create_client_table();

             //add client data
           boolean validate_entry= client_dao.add_Client(obj_client);

           if(validate_entry)
           {
               System.out.println("Data saved");
           }else{
               System.out.println("Something gone wrong!!");
           }


         }catch(Exception ex)
         {
             ex.printStackTrace();
         }
         PrintWriter output = response.getWriter();
         output.println("<h2>" + obj_client.getName()+"</h2>");

     }


}
