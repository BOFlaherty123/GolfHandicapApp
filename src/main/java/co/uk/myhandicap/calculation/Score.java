package main.java.co.uk.myhandicap.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Keep track and calculate values for the handicap calculation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/07/14
 * @project MyHandicapApp
 */
public class Score {

    // TODO can we store the values locally in this object? If the Score object is injected into the class (new instance each time)
    // TODO behavior with the class will remain as a singleton and multiple iteractions will stay seperate
    // TODO Setter/Getter methods are required to keep track of the adjustment total, playerScore and handicap value

    private BigDecimal playerScore = new BigDecimal(0);
    private BigDecimal adjustmentTotal = new BigDecimal(0);
    private BigDecimal handicap;

    public BigDecimal getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(BigDecimal playerScore) {
        this.playerScore = playerScore;
    }

    public BigDecimal getAdjustmentTotal() {
        return adjustmentTotal;
    }

    public void setAdjustmentTotal(BigDecimal adjustmentTotal) {
        this.adjustmentTotal = adjustmentTotal;
    }

    public BigDecimal getHandicap() {
        return handicap;
    }

    public void setHandicap(BigDecimal handicap) {
        this.handicap = handicap;
    }


    public BigDecimal createScore(String value) {
        return new BigDecimal(value);
    }

    public BigDecimal createScore(Integer value) {
        return new BigDecimal(value);
    }

    // TODO - Use the global variables (playerScore) and add the value to this field.
    public BigDecimal addToPlayerScore(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public BigDecimal addToAdjustmentScore(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public BigDecimal subtractFromScore(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    public String calculateHandicap(int size, BigDecimal total) {
        return total.divide(new BigDecimal(size), RoundingMode.HALF_UP).toString();
    }

}
