package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Declare the connection URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/studentdata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "tiger";

    // Create a method to get the connection object
    public static Connection getConnection() {
        Connection conn;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection to the database
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // Return the connection object
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            // Handle the exceptions
            e.printStackTrace();
        }
        // Return null if the connection failed
        return null;
    }
}
