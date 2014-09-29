package main.java.co.uk.myhandicap.form;

import main.java.co.uk.myhandicap.validation.annotation.PasswordMatch;
import org.hibernate.validator.constraints.Length;

/**
 * Change Password Screen Dto.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/07/2014
 * @project MyHandicapApp
 */
@PasswordMatch(password = "password", confirmPassword = "confirmPassword",
        message = "Please ensure that your passwords match.")
public class ChangePasswordDto {

    @Length(min=4, max=15, message = "Password must be a minimum of 4 characters and a maximum of 15.")
    private String password;
    @Length(min=4, max=15, message = "ConfirmPassword must be a minimum of 4 characters and a maximum of 15.")
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
