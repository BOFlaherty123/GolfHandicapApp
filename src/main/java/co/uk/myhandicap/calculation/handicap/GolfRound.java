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

            BigDecimal playerScore = HandicapCalculationHelper.createBigDecimalDefault();
            playerScore = golfHole.processHoleData(round, playerScore);

            BigDecimal adjustedScore = HandicapCalculationHelper.subtractFromScore(playerScore,
                    HandicapCalculationHelper.createScore(round.getCourseSSS()));

            adjustedScores.add(adjustedScore);
            logger.info(".processRoundOfGolf() - add adjustedScore=[ " + adjustedScore + " ]");
        }

        logger.exit(adjustedScores);

        return adjustedScores;
    }

}
