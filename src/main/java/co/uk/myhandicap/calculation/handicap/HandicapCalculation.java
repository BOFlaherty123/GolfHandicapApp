package main.java.co.uk.myhandicap.calculation.handicap;

import main.java.co.uk.myhandicap.calculation.handicap.helper.HandicapCalculationHelper;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardService;
import main.java.co.uk.myhandicap.service.UserService;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Calculate a users golf handicap
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/07/14
 * @project MyHandicapApp
 */
@SuppressWarnings("unchecked")
@Component
public class HandicapCalculation {

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreCardService scoreCardService;

    @Autowired
    private GolfRound golfRound;

    @Value("${handicapCalculation.runTimeException}")
    private String handicapException;

    @Value("${exception.userNotFound}")
    private String userNotFoundException;

    private static final XLogger logger = XLoggerFactory.getXLogger(HandicapCalculation.class.getName());

    /**
     * calculate a players handicap.
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    public Handicap calculateUserHandicapScore(Long userId) throws UserNotFoundException {
        logger.entry(userId);

        List<ScoreCard> scoreCardList = scoreCardService.retrieveUserScoredCardsById(getUser(userId));
        List<Round> roundsOfGolf = HandicapCalculationHelper.extractRoundsOfGolfFromScoreCard(scoreCardList);

        Handicap playerHandicap = calculateHandicapForRoundOfGolf(roundsOfGolf);

        if(playerHandicap == null) {
            throw new RuntimeException(format(handicapException, userId));
        }

        logger.info("playerHandicap=[ " + playerHandicap + "]");

        logger.exit();

        return playerHandicap;
    }

    /**
     * calculate a users handicap based on their submitted roundOFGolf data
     *
     * @param roundsOfGolf
     * @return
     */
    private Handicap calculateHandicapForRoundOfGolf(List<Round> roundsOfGolf) {
        logger.entry(roundsOfGolf);

        Handicap playerHandicap = HandicapCalculationHelper.setupDefaultHandicap();

        if(!roundsOfGolf.isEmpty()) {

            BigDecimal adjustedTotal = calculateAdjustedScoreTotal(roundsOfGolf);
            String handicap = HandicapCalculationHelper.calculateHandicap(roundsOfGolf.size(), adjustedTotal);

            playerHandicap.setHandicapScore(handicap);
            playerHandicap.setNumberOfRounds(String.valueOf(roundsOfGolf.size()));

            logger.info("playerHandicap=[ " + handicap + "] roundsOfGolf=[ " + roundsOfGolf.size() + "]");
        }

        logger.exit();

        return playerHandicap;
    }

    /**
     * calculate the players adjusted score total
     *
     * @param roundsOfGolf
     * @return
     */
    private BigDecimal calculateAdjustedScoreTotal(List<Round> roundsOfGolf) {

        List<BigDecimal> adjustedScores = golfRound.processRoundOfGolf(roundsOfGolf, new ArrayList<BigDecimal>());
        BigDecimal adjustedTotal = HandicapCalculationHelper.createBigDecimalDefault();

        for(BigDecimal adjustScore : adjustedScores) {
            adjustedTotal = HandicapCalculationHelper.addValueToTotal(adjustedTotal, adjustScore);
        }

        return adjustedTotal;
    }

    /**
     * Retrieve the user from the database.
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    private User getUser(Long userId) throws UserNotFoundException {
        logger.entry(userId);

        User user = userService.retrieveUserById(userId);

        if(user == null) {
            throw new UserNotFoundException(format(userNotFoundException, userId));
        }

        logger.exit(user.toString());

        return user;
    }

}