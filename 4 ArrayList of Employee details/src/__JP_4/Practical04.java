package __JP_4;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Practical04 {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/class_db";
	static final String USER = "root";
	static final String PASS = "mysql1234";

	public static void main(String[] args) {
		Connection conn = null;
		java.sql.Statement stmt = null;
		ArrayList<Employee> employeeList = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT id, name,gender,designation, salary FROM employee";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String designation = rs.getString("designation");
				float salary = rs.getFloat("salary");

				Employee employee = new Employee(id, name, gender, designation, salary);
				employeeList.add(employee);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		for (Employee e : employeeList) {
			System.out.println("ID: " + e.getId() + ", Name: " + e.getName() + ", Gender: " + e.getGender()
					+ ", Designation: " + e.getDesignation() + ", Salary: " + e.getSalary());
		}
	}
}

class Employee {
	private int id;
	private String name;
	private String gender;
	private String designation;
	private float salary;

	public Employee(int id, String name, String gender, String designation, float salary) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.designation = designation;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getGender() {
		return gender;
	}

	public String getDesignation() {
		return designation;
	}

	public String getName() {
		return name;
	}

	public float getSalary() {
		return salary;
	}
}
