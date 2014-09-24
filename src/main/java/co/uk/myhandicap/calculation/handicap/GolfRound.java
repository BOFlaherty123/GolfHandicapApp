package main.java.co.uk.myhandicap.calculation.handicap;

import main.java.co.uk.myhandicap.calculation.handicap.helper.HandicapCalculationHelper;
import main.java.co.uk.myhandicap.model.handicap.Round;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Process Data Per Golf Round
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 31/07/2014
 * @project MyHandicapApp
 */
@Component
public class GolfRound {

    private static final XLogger logger = XLoggerFactory.getXLogger(GolfRound.class
            .getName());

    @Autowired
    private GolfHole golfHole;

    @Autowired
    private HandicapCalculationHelper handicapCalculationHelper;

    private GolfRound() {}

    /**
     * Loop through each Round on the players scorecard and process data.
     *
     * @param roundsOfGolf
     * @param adjustedScores
     */
    public List<BigDecimal> processRoundOfGolf(List<Round> roundsOfGolf, List<BigDecimal> adjustedScores) {
        logger.entry(roundsOfGolf, adjustedScores);

        for(Round round : roundsOfGolf) {

            // create a default value for player score
            BigDecimal playerScore = handicapCalculationHelper.createBigDecimalDefault();

            // loop through each hole of the round and adjust the players score
            playerScore = golfHole.processHoleData(round, playerScore);

            // calculate the players adjusted score for the round
            BigDecimal adjustedScore = handicapCalculationHelper.subtractFromScore(playerScore,
                    handicapCalculationHelper.createScore(round.getCourseSSS()));

            adjustedScores.add(adjustedScore);
            logger.info(".processRoundOfGolf() - add adjustedScore=[ " + adjustedScore + " ]");
        }

        logger.exit(adjustedScores);

        return adjustedScores;
    }

}
