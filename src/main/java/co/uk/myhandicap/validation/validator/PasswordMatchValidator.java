package main.java.co.uk.myhandicap.validation.validator;

import main.java.co.uk.myhandicap.validation.annotation.PasswordMatch;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * Password Match Validator
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/07/2014
 * @project MyHandicapApp
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordMatch passwordMatch) {
        password = passwordMatch.password();
        confirmPassword = passwordMatch.confirmPassword();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        String pwd = null;
        String confirmPwd = null;

        try {
            pwd = BeanUtils.getProperty(o, password).trim();
            confirmPwd = BeanUtils.getProperty(o, confirmPassword).trim();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        // if the two password inputs do not match, validation should fail
        if(!pwd.equals(confirmPwd)) {
            return false;
        }

        System.out.println("passed validation");
        return true;
    }
}
