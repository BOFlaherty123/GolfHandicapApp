package main.java.co.uk.myhandicap.exceptions;

/**
 * Invalid Tag Parameter Exception
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/07/14
 * @project MyHandicapApp
 */
public class InvalidTagParameterException extends Exception {

    public InvalidTagParameterException(String message) {
        super(message);
    }
}
