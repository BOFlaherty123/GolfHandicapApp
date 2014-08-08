package main.java.co.uk.myhandicap.calculation.handicap;

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

    @Autowired
    private GolfHole golfHole;

    private static final XLogger logger = XLoggerFactory.getXLogger(GolfRound.class
            .getName());

    private GolfRound() {}

    /**
     * Loop through each Round on the players scorecard and process data
     *
     * @param score
     * @param roundsOfGolf
     * @param adjustedScores
     */
    public List<BigDecimal> processRoundOfGolf(Score score, List<Round> roundsOfGolf, List<BigDecimal> adjustedScores) {
        logger.entry(score, roundsOfGolf, adjustedScores);

        for(Round round : roundsOfGolf) {

            BigDecimal playerScore = score.getPlayerScore();

            score.setCourseSSS(score.createScore(round.getCourseSSS()));

            // loop through each hole of the round and adjust the players score
            playerScore = golfHole.processHoleData(score, round, playerScore);

            // calculate the players adjusted score for the round
            BigDecimal adjustedScore = score.subtractFromScore(playerScore, score.getCourseSSS());
            adjustedScores.add(adjustedScore);
            logger.info(".processRoundOfGolf() - add adjustedScore=[ " + adjustedScore + " ]");
        }

        logger.exit(adjustedScores);

        return adjustedScores;
    }

}
