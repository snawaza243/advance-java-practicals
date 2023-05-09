package com.servlet_mysql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_MySQL")
public class Servlet_MySQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException 
      {  
         PrintWriter out = res.getWriter();  
         res.setContentType("text/html");  
         out.println("<html>"
        		 + "<style>th,tr {border: 1px solid black;width: 100px;padding: 5px 10px;} div{display: flex; justify-content: center; align-items: center;}</style>"
         		+ "<body>");  
         try 
         {  
             Class.forName("com.mysql.jdbc.Driver");  
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/class_db", "root", "mysql1234");  
             
             Statement stmt = con.createStatement();  
             ResultSet rs = stmt.executeQuery("select * from employee"); 
             out.println("<div ><table>");  
             out.println("<tr><th>EmpId</th><th>EmpName</th><th>Gender</th><th>Designation</th><th>Salary</th><tr>");  
             while (rs.next()) 
             {  
                 int n = rs.getInt("id");  
                 String nm = rs.getString("name");
                 String g=rs.getString("gender");
                 String dn=rs.getString("designation");
                 float s = rs.getFloat("salary");   
                 out.println("<tr><td>" + n + "</td><td>" + nm + "</td>  <td>" + g + "</td> <td>" + dn + "</td>  <td>" + s + "</td></tr>");   
             }  
             out.println("</table> </div>");  
             out.println("</html></body>");  
             con.close();  
            }  
             catch (Exception e) 
            {  
            	 e.printStackTrace();
             out.println("error");  
         }  
     }  
 }