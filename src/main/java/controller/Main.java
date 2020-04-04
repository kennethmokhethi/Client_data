package controller;

import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Main")
public class Main extends HttpServlet {
  private Client client;

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
//         doPost(request,response);
         response.setContentType("text/html");

         String Name = request.getParameter("cName");
         String LastName = request.getParameter("cLastName");
         String Address = request.getParameter("cAddress");
         String strAcc_num = request.getParameter("cAcc_num");


         client = new Client(Name,LastName,Address,Integer.parseInt(strAcc_num));




         PrintWriter output = response.getWriter();
         output.println("<h2>" + client.getName()+"</h2>");

     }


}
