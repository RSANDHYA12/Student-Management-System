package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Declare the SQL statements for the CRUD operations
    private static final String INSERT_STUDENT = "INSERT INTO student (name, email, address) VALUES (?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
    private static final String UPDATE_STUDENT = "UPDATE student SET name = ?, email = ?, address = ? WHERE id = ?";
    private static final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
    private Database DatabaseHelper;

    // Create a method to insert a student into the database
    public boolean insertStudent(Student student) {
        // Get the connection object
        Connection conn = Database.getConnection();
        // Initialize the result flag
        boolean result = false;
        try {
            // Create a prepared statement with the SQL statement
            PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT);
            // Set the parameters for the statement
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getAddress());
            // Execute the statement and get the number of affected rows
            int rows = ps.executeUpdate();
            // Check if the insertion was successful
            if (rows > 0) {
                result = true;
            }
            // Close the statement and the connection
            ps.close();
            conn.close();
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }
        // Return the result flag
        return result;
    }

    // Create a method to select a student by id from the database
    public Student selectStudentById(int id) {
        // Get the connection object
        Connection conn = Database.getConnection();
        // Initialize the student object
        Student student = null;
        try {
            // Create a prepared statement with the SQL statement
            PreparedStatement ps = conn.prepareStatement(SELECT_STUDENT_BY_ID);
            // Set the parameter for the statement
            ps.setInt(1, id);
            // Execute the statement and get the result set
            ResultSet rs = ps.executeQuery();
            // Check if the result set is not empty
            if (rs.next()) {
                // Get the values from the result set
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                // Create a new student object with the values
                student = new Student(id, name, email, address);
            }
            // Close the result set, the statement, and the connection
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }
        // Return the student object
        return student;
    }

    // Create a method to select all students from the database
    public List<Student> selectAllStudents() {
        // Get the connection object
        Connection conn = Database.getConnection();
        // Initialize the student list
        List<Student> students = new ArrayList<>();
        try {
            // Create a statement with the SQL statement
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_STUDENTS);
            // Execute the statement and get the result set
            ResultSet rs = ps.executeQuery();
            // Loop through the result set
            while (rs.next()) {
                // Get the values from the result set
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                // Create a new student object with the values
                Student student = new Student(id, name, email, address);
                // Add the student object to the list
                students.add(student);
            }
            // Close the result set, the statement, and the connection
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }
        // Return the student list
        return students;
    }

    // Create a method to update a student in the database
    public boolean updateStudent(Student student) {
        // Get the connection object
        Connection conn = Database.getConnection();
        // Initialize the result flag
        boolean result = false;
        try {
            // Create a prepared statement with the SQL statement
            PreparedStatement ps = conn.prepareStatement(UPDATE_STUDENT);
            // Set the parameters for the statement
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getAddress());
            ps.setInt(4, student.getId());
            // Execute the statement and get the number of affected rows
            int rows = ps.executeUpdate();
            // Check if the update was successful
            if (rows > 0) {
                result = true;
            }
            // Close the statement and the connection
            ps.close();
            conn.close();
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }
        // Return the result flag
        return result;
    }

    // Create a method to delete a student from the database
    public boolean deleteStudent(int id) {
        // Get the connection object
        Connection conn = Database.getConnection();
        // Initialize the result flag
        boolean result = false;
        try {
            // Create a prepared statement with the SQL statement
            PreparedStatement ps = conn.prepareStatement(DELETE_STUDENT);
            // Set the parameter for the statement
            ps.setInt(1, id);
            // Execute the statement and get the number of affected rows
            int rows = ps.executeUpdate();
            // Check if the deletion was successful
            if (rows > 0) {
                result = true;
            }
            // Close the statement and the connection
            ps.close();
            conn.close();
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }
        // Return the result flag
        return result;
    }

    public Student searchStudent(int idNum) {
        // Declare a student object to store the result
        Student student = null;
        // Declare a connection object to connect to the database
        Connection connection;
        // Declare a prepared statement object to execute the query
        PreparedStatement statement;
        // Declare a result set object to store the query result
        ResultSet resultSet;
        try {
            // Get the connection from the database helper class
            connection = DatabaseHelper.getConnection();
            // Create the query string to select a student by id
            String query = "SELECT * FROM students WHERE id = ?";
            // Create the prepared statement with the query and the id parameter
            statement = connection.prepareStatement(query);
            statement.setInt(1, idNum);
            // Execute the query and get the result set
            resultSet = statement.executeQuery();
            // Check if the result set has any data
            if (resultSet.next()) {
                // Get the student details from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                // Create a new student object with the details
                student = new Student(id, name, email, address);
            }
        } catch (SQLException e) {
            // Handle the SQL exception
            e.printStackTrace();
        }
        // Return the student object
        return student;
    }

}