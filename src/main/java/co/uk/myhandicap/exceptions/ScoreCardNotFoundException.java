package main.java.co.uk.myhandicap.exceptions;

/**
 * ScoreCard Not Found Exception
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public class ScoreCardNotFoundException extends Exception {

    public ScoreCardNotFoundException(String message) {
        super(message);
    }

}
