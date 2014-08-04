package main.java.co.uk.myhandicap.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * User Registration Dto
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/08/2014
 * @project MyHandicapApp
 */
public class UserRegistrationDto {

    @NotBlank(message = "Username must have a value.")
    @Length(min = 4, max = 15, message = "Username must be a minimum of 4 and maximum of 15 characters.")
    private String username;
    @NotBlank(message = "FirstName must have a value.")
    @Length(min = 2, max = 15, message = "FirstName must be a minimum of 2 and maximum of 15 characters.")
    @Pattern(regexp="[a-zA-Z]+", message = "FirstName must only contain characters.")
    private String firstName;
    @NotBlank(message = "LastName must have a value.")
    @Pattern(regexp="[a-zA-Z]+", message = "LastName must only contain characters.")
    private String lastName;
    @NotBlank(message = "Email must have a value.")
    @Email(message = "Email address must be entered in the correct format, i.e with name@company.com")
    private String email;
    @NotBlank(message = "Password must have a value.")
    @Length(min = 6, max = 15, message = "Password must have a minimum of 6 and a maximum of 15 in length.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
