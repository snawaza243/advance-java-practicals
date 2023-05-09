<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.*"%>

<%
String emp_id = request.getParameter("id");

try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlDatabase", "root", "mysqlDatabasePassword");

	String query0 = "SELECT * FROM employee WHERE id = ?";
	PreparedStatement psmt0 = con.prepareStatement(query0);
	psmt0.setString(1, emp_id);
	ResultSet delete_emp_data0 = psmt0.executeQuery();

	String query1 = "SELECT * FROM employee WHERE id = ?";
	PreparedStatement pstm1 = con.prepareStatement(query1);
	pstm1.setString(1, emp_id);
	ResultSet delete_emp_data = pstm1.executeQuery();

	String query2 = "DELETE FROM EMPLOYEE WHERE id = ?";
	PreparedStatement pstm2 = con.prepareStatement(query2);
	pstm2.setString(1, emp_id);

	try {

		if (delete_emp_data.next()) {
	out.println("<!DOCTYPE html>" + "<html lang='en'><head><title>Status Report</title>"
			+ "<style>div {display: flex;justify-content: center;align-items: center;text-align: left;}"
			+ "th,td,tr {padding: 5px 10px;} "
			+ ".status-out{background-color: rgb(20, 226, 137);color: white;font-weight: 600;border-radius: 10px;}"
			+ "</style>" + "<body><div><table>"
			+ "<tr><th>Status: </th><td class='status-out' >Success! </td></tr>"
			+ "<tr><th>Action: </th><td>Delete Employee</td></tr>" + "<tr><td> &emsp;</td></tr> "
			+ "<tr><th>Data:</th></tr> " + "<tr><th>Employee Name: </th> <td>"
			+ delete_emp_data.getString("name") + "</td></tr> " + "<tr><th>Employee ID: </th> <td>"
			+ delete_emp_data.getString("id") + "</td></tr>" + "<tr><th>Employee Gender: </th> <td>"
			+ delete_emp_data.getString("gender") + "</td></tr>" + "<tr><th>Employee Designation: </th> <td>"
			+ delete_emp_data.getString("designation") + "</td></tr>" + "<tr><th>Employee Salary: </th> <td>"
			+ delete_emp_data.getString("salary") + "</td></tr>" + "</table></div></body></html>");
		}

	} catch (Exception e) {
		out.println("<!DOCTYPE html>" + "<html lang='en'><head><title>Status Report</title>"
		+ "<style>div {display: flex;justify-content: center;align-items: center;text-align: left;}"
		+ "th,td,tr {padding: 5px 10px;} "
		+ ".status-out-2{background-color: rgb(226, 20, 30);color: white;font-weight: 600;border-radius: 10px;}"
		+ "</style>" + "<body><div><table>" + "<tr><th>Status: </th><td class='status-out-2' >Fail! </td></tr>"
		+ "<tr><th>Action: </th><td>Delete Employee</td></tr>" + "<tr><td> &emsp;</td></tr> "
		+ "<tr><th>Data:</th></tr> " + "<tr><th>Error: </th> <td> Eployee not exist and " + e.getMessage()
		+ "</td></tr> " + "</table></div></body></html>");
		System.out.println("Error: " + e.getMessage());
	}

	int x = pstm2.executeUpdate();
	if (x > 0)
		System.out.println("Data Deleted Successfully");
	else
		System.out.println("Data Not Present");

	pstm2.executeQuery();
}

catch (Exception e) {
	e.printStackTrace();
}
%>

</body>
</html>