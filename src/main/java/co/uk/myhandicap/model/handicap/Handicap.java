package main.java.co.uk.myhandicap.model.handicap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Handicap Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/07/14
 * @project MyHandicapApp
 */
public class Handicap {

    private final static int DEFAULT_HANDICAP = 28;

    private String calculatedOn;
    private String handicapScore;
    private String numberOfRounds;

    public String getCalculatedOn() {
        return calculatedOn;
    }

    public void setCalculatedOn(String calculatedOn) {
        this.calculatedOn = calculatedOn;
    }

    public String getHandicapScore() {
        return handicapScore;
    }

    public void setHandicapScore(String handicapScore) {
        this.handicapScore = handicapScore;
    }

    public String getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(String numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public Handicap setupDefaultHandicap() {
        Handicap playerHandicap = new Handicap();

        playerHandicap.setHandicapScore(String.valueOf(DEFAULT_HANDICAP));

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        playerHandicap.setCalculatedOn(fmt.print(new DateTime()));

        return playerHandicap;
    }

    @Override
    public String toString() {
        return "Handicap{" +
                "calculatedOn='" + calculatedOn + '\'' +
                ", handicapScore='" + handicapScore + '\'' +
                ", numberOfRounds='" + numberOfRounds + '\'' +
                '}';
    }
}
