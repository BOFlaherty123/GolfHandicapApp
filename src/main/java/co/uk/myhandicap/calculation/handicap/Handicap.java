package main.java.co.uk.myhandicap.calculation.handicap;

/**
 * Handicap Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/07/14
 * @project MyHandicapApp
 */
public class Handicap {

    private String calculatedOn;
    private String handicapScore;
    private String numberOfRounds;

    private Handicap(HandicapBuilder handicapBuilder) {
        calculatedOn = handicapBuilder.calculatedOn;
        handicapScore = handicapBuilder.handicapScore;
        numberOfRounds = handicapBuilder.numberOfRounds;
    }

    // builder design pattern
    public static class HandicapBuilder {

        private String calculatedOn = null;
        private String handicapScore = null;
        private String numberOfRounds = null;

        public HandicapBuilder(String calculatedOn, String handicapScore) {
            this.calculatedOn = calculatedOn;
            this.handicapScore = handicapScore;
        }

        public HandicapBuilder calculatedOn(String date) {
            this.calculatedOn = date;

            return this;
        }

        public HandicapBuilder withHandicapScore(String value) {
            this.handicapScore = value;

            return this;
        }

        public HandicapBuilder withNumberOfRounds(String value) {
            this.numberOfRounds = value;

            return this;
        }

        public Handicap build() {
            return new Handicap(this);
        }

    }

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
