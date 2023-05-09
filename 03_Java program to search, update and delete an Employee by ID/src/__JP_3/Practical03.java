package __JP_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Practical03 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/class_db";
        String user = "root";
        String password = "mysql1234";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
            int choice;
            do {
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addEmployee(connection);
                        break;
                    case 2:
                        searchEmployee(connection);
                        break;
                    case 3:
                        updateEmployee(connection);
                        break;
                    case 4:
                        deleteEmployee(connection);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 5);
            connection.close();
            System.out.println("Disconnected from the database!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addEmployee(Connection connection) { 
    Scanner scanner=new Scanner(System.in); Scanner sc=new Scanner(System.in); 
    try { 
    System.out.print("Enter employee ID: "); 
    int id = sc.nextInt(); 
    System.out.print("Enter employee name: "); 
    String name = scanner.nextLine(); 
    System.out.println("Enter employee gender"); 
    String gender=scanner.nextLine(); 
    System.out.println("Enter employee designation"); 
    String designation=scanner.nextLine(); System.out.print("Enter employee salary: "); float salary = sc.nextFloat(); 
    PreparedStatement preparedStatement = 
    connection.prepareStatement("INSERT INTO employee() VALUES (?, ?, ?, ?, ?)"); 
    preparedStatement.setInt(1, id); preparedStatement.setString(2, name); preparedStatement.setString(3,gender); preparedStatement.setString(4,designation); preparedStatement.setDouble(5, salary); 
    int rowsInserted = preparedStatement.executeUpdate(); if(rowsInserted > 0) { 
    System.out.println("Employee added successfully!"); 
    } 
    } catch (SQLException throwables) { throwables.printStackTrace(); 
    } }

    public static void searchEmployee(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter employee ID to search: ");
            int id = scanner.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Employee found:");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Designation: " + resultSet.getString("designation"));
                System.out.println("Salary: " + resultSet.getFloat("salary"));
            } 
            else {
                System.out.println("Employee not found!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateEmployee(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter employee ID to update: ");
            int id = scanner.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.print("Enter new name for the employee: ");
                String name = scanner.next();
                System.out.print("Enter new salary for the employee: ");
                float salary = scanner.nextFloat();
                preparedStatement = connection
                        .prepareStatement("UPDATE employee SET name = ?, salary = ? WHERE id = ?");
                preparedStatement.setString(1, name);
                preparedStatement.setFloat(2, salary);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteEmployee(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter employee ID to delete: ");
            int id = scanner.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}