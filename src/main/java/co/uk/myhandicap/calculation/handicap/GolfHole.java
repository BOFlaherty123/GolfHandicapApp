package main.java.co.uk.myhandicap.calculation.handicap;

import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static main.java.co.uk.myhandicap.calculation.handicap.Score.addToPlayerScore;
import static main.java.co.uk.myhandicap.calculation.handicap.Score.createScore;

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

    private GolfHole() {}

    /**
     * loop through each hole and make the necessary adjustments to the players overal score
     *
     * @param score
     * @param round
     * @param playerScore
     * @return
     */
    public BigDecimal processHoleData(Score score, Round round, BigDecimal playerScore) {
        logger.entry(score, round, playerScore);

        for(Hole hole : round.getHoles()) {

            // Process CONGU adjustment
            processCONGUAdjustment(hole, score);

            // add players score for the hole to the round total
            BigDecimal holeScore = createScore(hole.getHoleScore());
            playerScore = addToPlayerScore(playerScore, holeScore);

        }

        logger.exit(playerScore);

        return playerScore;
    }

    /**
     * CONGU adjustment (max +2 shot penalty per hole 'double bogey)
     *
     * @param hole
     */
    private void processCONGUAdjustment(Hole hole, Score score) {
        logger.entry(hole, score);

        BigDecimal maxStroke = createScore(Integer.valueOf(hole.getHolePar()) + 2);
        BigDecimal playerScore = createScore(hole.getHoleScore());

        if(playerScore.compareTo(maxStroke) > 0) {
            playerScore = maxStroke;

            hole.setHoleScore(String.valueOf(playerScore));
        }

        logger.exit(hole.toString());
    }

}
