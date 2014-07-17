package main.java.co.uk.myhandicap.model.handicap;

/**
 * Handicap Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/07/14
 * @project MyHandicapApp
 */
public class Handicap {

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

    @Override
    public String toString() {
        return "Handicap{" +
                "calculatedOn='" + calculatedOn + '\'' +
                ", handicapScore='" + handicapScore + '\'' +
                ", numberOfRounds='" + numberOfRounds + '\'' +
                '}';
    }
}
