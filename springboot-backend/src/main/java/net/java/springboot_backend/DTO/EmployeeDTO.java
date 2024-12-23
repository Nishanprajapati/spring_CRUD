package net.java.springboot_backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

    @NotEmpty
    @Size(min = 2,message ="Firstname must be minimum of 2 character")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Size(min = 2,message ="Lastname must be minimum of 2 character")
    @Column(name = "last_name")
    private String lastName;

    @Email(message ="Email is not valid")
    @Column(name = "email_id")
    private String emailId;

    // Default constructor
    public void EmployeeDTO() {
    }

    // Parameterized constructor
    public void EmployeeDTO(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
