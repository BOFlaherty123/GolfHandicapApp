package main.java.co.uk.myhandicap.calculation.handicap.helper;

import main.java.co.uk.myhandicap.calculation.handicap.Handicap;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for the Handicap Calculation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 24/09/2014
 * @project MyHandicapApp
 */
@Component
public class HandicapCalculationHelper {

    private final static int ZERO_VALUE = 0;
    private final static int DEFAULT_HANDICAP = 28;

    /**
     *  setup the default handicap value (28)
     *
     * @return
     */
    public Handicap setupDefaultHandicap() {
        Handicap playerHandicap = new Handicap();

        playerHandicap.setHandicapScore(String.valueOf(DEFAULT_HANDICAP));

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        playerHandicap.setCalculatedOn(fmt.print(new DateTime()));

        return playerHandicap;
    }

    /**
     * create a default BigDecimal value
     *
     * @return
     */
    public BigDecimal createBigDecimalDefault() {
        return new BigDecimal(ZERO_VALUE);
    }

    /**
     * create a new BigDecimal with the entered method value
     *
     * @param value
     * @return
     */
    public BigDecimal createScore(String value) {
        return new BigDecimal(value);
    }

    /**
     * set maximum stroke count allowed for a players score
     *
     * @param value
     * @return
     */
    public BigDecimal setMaximumStrokeCountPerHole(Integer value) {
        return new BigDecimal(value);
    }

    /**
     * add value to total
     *
     * @param value1
     * @param value2
     * @return
     */
    public BigDecimal addValueToTotal(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    /**
     * subtract value from Score
     *
     * @param value1
     * @param value2
     * @return
     */
    public BigDecimal subtractFromScore(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    /**
     *
     * @param size
     * @param total
     * @return
     */
    public String calculateHandicap(int size, BigDecimal total) {
        // TODO - no not allow for a handicap above 28 (max 28)
        return String.valueOf(total.divide(new BigDecimal(size), RoundingMode.HALF_UP));
    }

    /**
     * extract all rounds of golf for the given user.
     *
     * @param scoreCardList
     * @return
     */
    public List<Round> extractRoundsOfGolfFromScoreCard(List<ScoreCard> scoreCardList) {
        List<Round> roundsOfGolf = new ArrayList<>();

        for(ScoreCard scoreCard : scoreCardList) {
            for(Round round : scoreCard.getGolfRounds()) {
                roundsOfGolf.add(round);
            }
        }

        return roundsOfGolf;
    }

}
