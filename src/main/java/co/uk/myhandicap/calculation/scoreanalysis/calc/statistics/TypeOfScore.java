package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

/**
 * Enum for Type of Score
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
public enum TypeOfScore {

    EAGLE(-2),
    BIRDIE(-1),
    PAR(0),
    BOGEY(1),
    DOUBLE_BOGEY(2),
    TRIPPE_BOGEY(3);

    private int typeOfScore;

    private TypeOfScore(int i) {
        typeOfScore = i;
    }

    public int getValue() {
        return typeOfScore;
    }

}
