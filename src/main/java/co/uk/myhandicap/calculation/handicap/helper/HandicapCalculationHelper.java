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
    private final static BigDecimal MAX_HANDICAP = new BigDecimal("28");

    /**
     *  setup the default handicap value (28).
     *
     * @return
     */
    public Handicap setupDefaultHandicap() {

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");

        return new Handicap.HandicapBuilder(fmt.print(new DateTime()), String.valueOf(DEFAULT_HANDICAP)).build();
    }

    /**
     * create a default BigDecimal value.
     *
     * @return
     */
    public BigDecimal createBigDecimalDefault() {
        return new BigDecimal(ZERO_VALUE);
    }

    /**
     * create a new BigDecimal with the entered method value.
     *
     * @param value
     * @return
     */
    public BigDecimal createScore(String value) {
        return new BigDecimal(value);
    }

    /**
     * set maximum stroke count allowed for a players score.
     *
     * @param value
     * @return
     */
    public BigDecimal setMaximumStrokeCountPerHole(Integer value) {
        return new BigDecimal(value);
    }

    /**
     * add value to total.
     *
     * @param value1
     * @param value2
     * @return
     */
    public BigDecimal addValueToTotal(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    /**
     * subtract value from Score.
     *
     * @param value1
     * @param value2
     * @return
     */
    public BigDecimal subtractFromScore(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    /**
     * calculate the players handicap, if the value is greater than 28, use the default maximum on 28.
     *
     * @param size
     * @param total
     * @return
     */
    public String calculateHandicap(int size, BigDecimal total) {

        total = (total.divide(new BigDecimal(size), RoundingMode.HALF_UP).signum() > 28)
                ? total : MAX_HANDICAP;

        return String.valueOf(total);
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
