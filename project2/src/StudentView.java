package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentView extends JFrame {
    private final JTextField idTextField;
    private final JTextField nameTextField;
    private final JTextField emailTextField;
    private final JTextField addressTextField;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    private final JButton searchButton;
    private final JTable studentTable;
    private final DefaultTableModel tableModel;

    // Create a constructor with no parameters
    public StudentView() {
        // Set the title, size, layout, and default close operation of the frame
        setTitle("Student Management Application");
        setSize(800, 600);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the components of the GUI
        // Declare the components of the GUI
        JLabel titleLabel = new JLabel("Student Management Application");
        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel addressLabel = new JLabel("Address:");
        idTextField = new JTextField(10);
        nameTextField = new JTextField(20);
        emailTextField = new JTextField(20);
        addressTextField = new JTextField(20);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        studentTable = new JTable();
        tableModel = new DefaultTableModel();
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Set the font and alignment of the title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Set the column names and the column count of the table model
        tableModel.setColumnIdentifiers(new String[] {"ID", "Name", "Email", "Address"});
        tableModel.setColumnCount(4);

        // Set the table model and the preferred size of the table
        studentTable.setModel(tableModel);
        studentTable.setPreferredScrollableViewportSize(new Dimension(750, 300));

        // Add the components to the frame
        add(titleLabel);
        add(idLabel);
        add(idTextField);
        add(nameLabel);
        add(nameTextField);
        add(emailLabel);
        add(emailTextField);
        add(addressLabel);
        add(addressTextField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(searchButton);
        add(scrollPane);

        // Set the frame to be visible
        setVisible(true);

    }

    // Create getters for the components of the GUI
    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTable getStudentTable() {
        return studentTable;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
