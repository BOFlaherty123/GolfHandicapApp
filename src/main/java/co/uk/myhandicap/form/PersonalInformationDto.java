package main.java.co.uk.myhandicap.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Personal Information DTO
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 29/07/2014
 * @project MyHandicapApp
 */
public class PersonalInformationDto {

    private long id;
    @NotBlank(message = "FirstName must have a value.")
    @Pattern(regexp="[a-zA-Z]+", message = "FirstName must only contain characters.")
    @Length(min=2, message = "FirstName must be at least two characters in length.")
    private String firstName;
    @NotBlank(message = "LastName must have a value.")
    @Pattern(regexp="[a-zA-Z]+", message = "lastName must only contain characters.")
    private String lastName;
    @NotBlank(message = "Email must have a value.")
    @Email(message = "Email address must be entered in the correct format, i.e with name@company.com")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PersonalInformationDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
