<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.*"%>


	<%
	// Retrive data from form and post to the database
	%>
	<%
	String emp_id = request.getParameter("id");
	String emp_name = request.getParameter("name");
	String emp_gender = request.getParameter("gender");
	String emp_des = request.getParameter("designation");
	String emp_salary = request.getParameter("salary");
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlDatabase","root","mysqlDatabasePassword");
		String query = "insert into employee (id, name, gender, designation, salary) values(?, ?, ?, ?,?)";
		
		PreparedStatement pstm = con.prepareStatement(query);
		pstm.setString(1,emp_id);
		pstm.setString(2, emp_name);
		pstm.setString(3, emp_gender);
		pstm.setString(4, emp_des);
		pstm.setString(5, emp_salary);
		
		pstm.executeUpdate();
		out.println("<!DOCTYPE html>"
		+"<html lang='en'><head><title>Status Report</title>"
		+"<style>div {display: flex;justify-content: center;align-items: center;text-align: left;}" 
		+ "th,td,tr {padding: 5px 10px;} "
		+".status-out{background-color: rgb(20, 226, 137);color: white;font-weight: 600;border-radius: 10px;}"
		+"</style>"
		+"<body><div><table>"
		+"<tr><th>Status: </th><td class='status-out' >Success! </td></tr>"
		+"<tr><th>Action: </th><td>Adding New Employee</td></tr>" 
		+"<tr><td> &emsp;</td></tr> "
		+"<tr><th>Data:</th></tr> "
		+"<tr><th>Employee Name: </th> <td>"+emp_name+"</td></tr> "
		+"<tr><th>Employee ID: </th> <td>"+emp_id+"</td></tr>"
		+"<tr><th>Employee Gender: </th> <td>"+emp_gender+"</td></tr>"
		+"<tr><th>Employee Designation: </th> <td>"+emp_des+"</td></tr>"
		+"<tr><th>Employee Salary: </th> <td>"+emp_salary+"</td></tr>"
		+"</table></div></body></html>");
		pstm.close();
		con.close();
	}
	catch(Exception e){
		out.println("<!DOCTYPE html>"
				+"<html lang='en'><head><title>Status Report</title>"
				+"<style>div {display: flex;justify-content: center;align-items: center;text-align: left;}" 
				+ "th,td,tr {padding: 5px 10px;} "
				+".status-out-2{background-color: rgb(226, 20, 30);color: white;font-weight: 600;border-radius: 10px;}"
				+"</style>"
				+"<body><div><table>"
				+"<tr><th>Status: </th><td class='status-out-2' >Fail! </td></tr>"
				+"<tr><th>Action: </th><td>Adding New Employee</td></tr>" 
				+"<tr><td> &emsp;</td></tr> "
				+"<tr><th>Data:</th></tr> "
				+"<tr><th>Error: </th> <td>"+e.getMessage()+"</td></tr> "
				+"</table></div></body></html>");
		System.out.println("Error: " + e.getMessage());
	}
	
	
	%>

</body>
</html>