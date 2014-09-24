package main.java.co.uk.myhandicap.calculation.handicap;

import main.java.co.uk.myhandicap.calculation.handicap.helper.HandicapCalculationHelper;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Process Data Per Golf Hole
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 31/07/2014
 * @project MyHandicapApp
 */
@Component
public class GolfHole {

    private static final XLogger logger = XLoggerFactory.getXLogger(GolfHole.class
            .getName());

    @Autowired
    private HandicapCalculationHelper handicapCalculationHelper;

    private GolfHole() {}

    /**
     * loop through each hole and make the necessary adjustments to the players overal score.
     *
     * @param round
     * @param playerScore
     * @return
     */
    public BigDecimal processHoleData(Round round, BigDecimal playerScore) {
        logger.entry(round, playerScore);

        for(Hole hole : round.getHoles()) {

            // Process CONGU adjustment
            processCONGUAdjustment(hole);

            // add players score for the hole to the round total
            playerScore = handicapCalculationHelper.addValueToTotal(playerScore,
                    handicapCalculationHelper.createScore(hole.getHoleScore()));

        }

        logger.exit(playerScore);

        return playerScore;
    }

    /**
     * CONGU adjustment (max +2 shot penalty per hole 'double bogey).
     *
     * @param hole
     */
    private void processCONGUAdjustment(Hole hole) {
        logger.entry(hole);

        BigDecimal maxStroke = handicapCalculationHelper.setMaximumStrokeCountPerHole(determineMaximumScoreAllowed(hole.getHolePar()));
        BigDecimal playerScore = handicapCalculationHelper.createScore(hole.getHoleScore());

        if(playerScore.compareTo(maxStroke) > 0) {
            playerScore = maxStroke;

            hole.setHoleScore(String.valueOf(playerScore));
        }

        logger.exit(hole.toString());
    }

    private int determineMaximumScoreAllowed(String holePar) {
        return Integer.valueOf(holePar) + 2;
    }

}
