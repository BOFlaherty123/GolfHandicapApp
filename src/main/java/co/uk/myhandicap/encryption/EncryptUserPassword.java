package main.java.co.uk.myhandicap.encryption;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Component;

/**
 * User Password Encryption
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 05/08/2014
 * @project MyHandicapApp
 */
@Component
public class EncryptUserPassword {

    public String encryptPassword(String password) {

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptPassword = passwordEncryptor.encryptPassword(password);

        boolean isOkay = checkUserPasswordsMatch(passwordEncryptor, password, encryptPassword);
        System.out.println("isOkay? " + isOkay);

        return passwordEncryptor.encryptPassword(password);
    }

    public boolean checkUserPasswordsMatch(StrongPasswordEncryptor passwordEncryptor,
                                           String password, String encryptPassword) {
        return passwordEncryptor.checkPassword(password, encryptPassword);
    }

}
