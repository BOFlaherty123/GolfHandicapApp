package main.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.model.handicap.ScoreCard;

import java.math.BigInteger;
import java.util.List;

/**
 * Common behavior for AverageCalculation classes
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/09/2014
 * @project MyHandicapApp
 */
public abstract class AbstractCalculateAverage {

    abstract String iterateOverRoundsOfGolf(List<ScoreCard> scoreCardList, String averageRequested);

    protected String calculate(BigInteger total, int size) {
        // divide total of score by number of times played @ averageRequested
        return String.valueOf(total.divide(new BigInteger(String.valueOf(size))));
    }

}
