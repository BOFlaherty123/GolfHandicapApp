package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

import org.springframework.stereotype.Component;

import static main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.TypeOfScore.*;

/**
 * Determine which type of score total to update for a hole object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
@Component
public class DetermineTypeOfScore {

    /**
     * determine which score property on the hole object to update
     *
     * @param hole
     * @param holePar
     * @param holeScore
     */
    public void execute(HoleScoreType hole, String holePar, String holeScore) {

        int typeOfScore = (Integer.valueOf(holeScore) - Integer.valueOf(holePar));

        // based on the value of typeOfScore, determine which property to update on the hole object
        if(typeOfScore == EAGLE.getValue()) {
            hole.setEagle(calculateTotal(hole.getEagle()));

        } else if (typeOfScore == BIRDIE.getValue()) {
            hole.setBirdie(calculateTotal(hole.getBirdie()));

        } else if (typeOfScore == PAR.getValue()) {
            hole.setPar(calculateTotal(hole.getPar()));

        } else if (typeOfScore == BOGEY.getValue()) {
            hole.setBogey(calculateTotal(hole.getBogey()));

        } else if (typeOfScore == DOUBLE_BOGEY.getValue()) {
            hole.setDoubleBogey(calculateTotal(hole.getDoubleBogey()));

        } else {
            hole.setOther(calculateTotal(hole.getOther()));
        }

    }

    private int calculateTotal(int currentValue) {
        return (currentValue + 1);
    }

}
