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
        return runEncryption().encryptPassword(password);
    }

    private StrongPasswordEncryptor runEncryption() {
        return new StrongPasswordEncryptor();
    }

}
