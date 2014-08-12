package main.java.co.uk.myhandicap.calculation.handicap;

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

    private BigDecimal playerScore = new BigDecimal(0);
    private BigDecimal adjustmentTotal = new BigDecimal(0);
    private BigDecimal courseSSS = new BigDecimal(0);
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

    public BigDecimal getCourseSSS() {
        return courseSSS;
    }

    public void setCourseSSS(BigDecimal courseSSS) {
        this.courseSSS = courseSSS;
    }

    // Score BigDecimal arithmetic and initialisation
    public static final BigDecimal createScore(String value) {
        return new BigDecimal(value);
    }

    public static final BigDecimal createScore(Integer value) {
        return new BigDecimal(value);
    }

    public static final BigDecimal addToPlayerScore(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public static final BigDecimal addToAdjustmentScore(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public static final BigDecimal subtractFromScore(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    public static String calculateHandicap(int size, BigDecimal total) {
        return total.divide(new BigDecimal(size), RoundingMode.HALF_UP).toString();
    }

    @Override
    public String toString() {
        return "Score{" +
                "playerScore=" + playerScore +
                ", adjustmentTotal=" + adjustmentTotal +
                ", courseSSS=" + courseSSS +
                ", handicap=" + handicap +
                '}';
    }
}
