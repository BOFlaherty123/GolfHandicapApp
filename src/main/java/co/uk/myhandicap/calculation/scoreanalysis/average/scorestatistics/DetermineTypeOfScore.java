package main.java.co.uk.myhandicap.calculation.scoreanalysis.average.scorestatistics;

import org.springframework.stereotype.Component;

import static main.java.co.uk.myhandicap.calculation.scoreanalysis.average.scorestatistics.TypeOfScore.*;

/**
 * Determine which type of score total to update for a hole object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
@Component
public class DetermineTypeOfScore {

    public HoleScoreType execute(HoleScoreType hole, String holePar, String holeScore) {

        int value = (Integer.valueOf(holeScore) - Integer.valueOf(holePar));

        if(value == EAGLE.getValue()) {
            hole.setEagle(calculateTotal(hole.getEagle()));

        } else if (value == BIRDIE.getValue()) {
            hole.setBirdie(calculateTotal(hole.getBirdie()));

        } else if (value == PAR.getValue()) {
            hole.setPar(calculateTotal(hole.getPar()));

        } else if (value == BOGEY.getValue()) {
            hole.setBogey(calculateTotal(hole.getBogey()));

        } else if (value == DOUBLE_BOGEY.getValue()) {
            hole.setDoubleBogey(calculateTotal(hole.getDoubleBogey()));

        } else {
            hole.setOther(calculateTotal(hole.getOther()));
        }

        return hole;
    }

    private int calculateTotal(int currentValue) {
        return (currentValue + 1);
    }

}
