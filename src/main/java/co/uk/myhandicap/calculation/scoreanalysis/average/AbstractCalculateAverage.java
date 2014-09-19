package main.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Common behavior for AverageCalculation classes.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/09/2014
 * @project MyHandicapApp
 */
public abstract class AbstractCalculateAverage {

    protected String calculate(BigDecimal total, int size) {
        // divide total of score by number of times played @ averageRequested
        return String.valueOf(total.divide(new BigDecimal(String.valueOf(size)), 2, RoundingMode.HALF_UP));
    }

}
