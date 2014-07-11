package main.java.co.uk.myhandicap.exceptions;

/**
 * User Not Found Exception
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

}
