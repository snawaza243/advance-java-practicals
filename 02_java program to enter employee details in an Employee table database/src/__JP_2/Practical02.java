package __JP_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Practical02 {
	public static void main(String args[]) {  	 	
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/class_db";
            String username = "root";
            String password = "mysql1234";
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "Insert into employee(id,name,gender,designation,salary)" + "values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter id:");
            int id=sc.nextInt();

            System.out.println("Enter name:");
            String name = sc.next();

            System.out.println("Enter gender:");
            String gender = sc.next();

            System.out.println("Enter designation:");
            String designation = sc.next();

            System.out.println("Enter Salary:");
            float salary = sc.nextFloat();

            // set the values of query
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, designation);
            ps.setFloat(5, salary);
            ps.executeUpdate();
            System.out.println(" insertion Sucessful...");
            con.close();
            ps.close();
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
	}
}
