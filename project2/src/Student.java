package model;

public class Student {
    // Declare the attributes of the student entity
    private int id;
    private String name;
    private String email;
    private String address;

    // Create a constructor with parameters
    public Student(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Create getters and setters for the attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Override the toString method to display the student information
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
    }
}
