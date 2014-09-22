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

    /**
     * calculate an average score, to a set number of decimal places
     *
     * @param total
     * @param size
     * @param decimalPlaces
     * @return
     */
    protected String calculate(BigDecimal total, int size, int decimalPlaces) {
        // divide total of score by number of times played @ averageRequested
        return String.valueOf(total.divide(new BigDecimal(String.valueOf(size)), decimalPlaces, RoundingMode.HALF_UP));
    }

}
