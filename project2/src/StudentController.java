package controller;

import model.Student;
import model.StudentDAO;
import view.StudentView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentController {
    // Declare the view and the model objects
    private  StudentView view;
    private  StudentDAO model;

    //main method
    public static void main(String[] args) {
        // Create the view object
        StudentView view = new StudentView();
        // Create the model object
        StudentDAO model = new StudentDAO();
        // Create the controller object with the view and the model
        StudentController controller = new StudentController(view, model);
        // Set the view to be visible
        view.setVisible(true);
    }

    // Create a constructor with parameters
    public StudentController(StudentView view, StudentDAO model) {
        // Initialize the view and the model objects
        this.view = view;
        this.model = model;
        // Add the action listeners to the buttons
        view.getAddButton().addActionListener(new AddButtonListener());
        view.getUpdateButton().addActionListener(new UpdateButtonListener());
        view.getDeleteButton().addActionListener(new DeleteButtonListener());
        view.getSearchButton().addActionListener(new SearchButtonListener());
        // Add the list selection listener to the table
        view.getStudentTable().getSelectionModel().addListSelectionListener(new TableSelectionListener());
        // Populate the table with the initial data
        populateTable();
    }

    // Create a method to populate the table with the data from the database
    public void populateTable() {
        // Clear the table model
        view.getTableModel().setRowCount(0);
        // Get the list of all students from the database
        List<Student> students = model.selectAllStudents();
        // Loop through the list and add each student as a row to the table model
        for (Student student : students) {
            view.getTableModel().addRow(new Object[] {student.getId(), student.getName(), student.getEmail(), student.getAddress()});
        }
    }

    // Create an inner class for the add button listener
    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the values from the text fields
            String name = view.getNameTextField().getText();
            String email = view.getEmailTextField().getText();
            String address = view.getAddressTextField().getText();

            // Validate the input values
            if (name.isEmpty()) {
                // Show an error message if the name field is empty
                JOptionPane.showMessageDialog(view, "Please enter the name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (email.isEmpty()) {
                // Show an error message if the email field is empty
                JOptionPane.showMessageDialog(view, "Please enter the email", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (address.isEmpty()) {
                // Show an error message if the address field is empty
                JOptionPane.showMessageDialog(view, "Please enter the address", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate the name
            if (!name.matches("[a-zA-Z]+")) {
                // Show an error message if the name is not valid
                JOptionPane.showMessageDialog(view, "The name must contain only letters", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate the email
            if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
                // Show an error message if the email is not valid
                JOptionPane.showMessageDialog(view, "The email must be valid", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate the address
            if (!address.matches("[a-zA-Z0-9\\s]+")) {
                // Show an error message if the address is not valid
                JOptionPane.showMessageDialog(view, "The address must contain only letters, numbers, and spaces", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert the student into the database
            boolean result = model.insertStudent(new Student(0, name, email, address));

            // Check the result of the insertion
            if (result) {
                // Show a success message if the insertion was successful
                JOptionPane.showMessageDialog(view, "Student added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Clear the text fields
                view.getIdTextField().setText("");
                view.getNameTextField().setText("");
                view.getEmailTextField().setText("");
                view.getAddressTextField().setText("");
                // Repopulate the table with the updated data
                populateTable();
            } else {
                // Show an error message if the insertion failed
                JOptionPane.showMessageDialog(view, "Student could not be added", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Create an inner class for the update button listener
    class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the values from the text fields
            String id = view.getIdTextField().getText();
            String name = view.getNameTextField().getText();
            String email = view.getEmailTextField().getText();
            String address = view.getAddressTextField().getText();
            // Validate the input values
            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty()) {
                // Show an error message if any value is empty
                JOptionPane.showMessageDialog(view, "Please enter all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Parse the id value to an integer
                int idNum = Integer.parseInt(id);
                // Create a new student object with the values
                Student student = new Student(idNum, name, email, address);
                // Update the student in the database
                boolean result = model.updateStudent(student);
                // Check the result of the update
                if (result) {
                    // Show a success message if the update was successful
                    JOptionPane.showMessageDialog(view, "Student updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Clear the text fields
                    view.getIdTextField().setText("");
                    view.getNameTextField().setText("");
                    view.getEmailTextField().setText("");
                    view.getAddressTextField().setText("");
                    // Repopulate the table with the updated data
                    populateTable();
                } else {
                    // Show an error message if the update failed
                    JOptionPane.showMessageDialog(view, "Student could not be updated", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Create an inner class for the delete button listener

    // Create an inner class for the delete button listener
    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the value from the id text field
            String id = view.getIdTextField().getText();
            // Validate the input value
            if (id.isEmpty()) {
                // Show an error message if the value is empty
                JOptionPane.showMessageDialog(view, "Please enter the id", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Parse the id value to an integer
                int idNum = Integer.parseInt(id);
                // Delete the student from the database
                boolean result = model.deleteStudent(idNum);
                // Check the result of the deletion
                if (result) {
                    // Show a success message if the deletion was successful
                    JOptionPane.showMessageDialog(view, "Student deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Clear the text fields
                    view.getIdTextField().setText("");
                    view.getNameTextField().setText("");
                    view.getEmailTextField().setText("");
                    view.getAddressTextField().setText("");
                    // Repopulate the table with the updated data
                    populateTable();
                } else {
                    // Show an error message if the deletion failed
                    JOptionPane.showMessageDialog(view, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Create an inner class for the search button listener
    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the value from the id text field
            String id = view.getIdTextField().getText();
            // Validate the input value
            if (id.isEmpty()) {
                // Show an error message if the value is empty
                JOptionPane.showMessageDialog(view, "Please enter the id", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Parse the id value to an integer
                int idNum = Integer.parseInt(id);
                // Search the student from the database
                Student student = model.searchStudent(idNum);
                // Check the result of the search
                if (student != null) {
                    // Display the student details in the text fields
                    view.getNameTextField().setText(student.getName());
                    view.getEmailTextField().setText(student.getEmail());
                    view.getAddressTextField().setText(student.getAddress());
                } else {
                    // Show an error message if the student was not found
                    JOptionPane.showMessageDialog(view, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
                    // Clear the text fields
                    view.getNameTextField().setText("");
                    view.getEmailTextField().setText("");
                    view.getAddressTextField().setText("");
                }
            }
        }
    }
    // Create an inner class for the table selection listener
    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // Check if the selection is not adjusting
            if (!e.getValueIsAdjusting()) {
                // Get the selected row index from the table
                int rowIndex = view.getStudentTable().getSelectedRow();
                // Check if the row index is valid
                if (rowIndex != -1) {
                    // Get the student id from the table model
                    int id = (int) view.getTableModel().getValueAt(rowIndex, 0);
                    // Search the student from the database
                    Student student = model.searchStudent(id);
                    // Display the student details in the text fields
                    view.getIdTextField().setText(String.valueOf(student.getId()));
                    view.getNameTextField().setText(student.getName());
                    view.getEmailTextField().setText(student.getEmail());
                    view.getAddressTextField().setText(student.getAddress());
                }
            }
        }
    }
}



