package main.java.co.uk.myhandicap.calculation.handicap;

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
import static main.java.co.uk.myhandicap.calculation.handicap.Score.addToAdjustmentScore;
import static main.java.co.uk.myhandicap.calculation.handicap.Score.calculateHandicap;
import static main.java.co.uk.myhandicap.calculation.handicap.Handicap.setupDefaultHandicap;

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

    private static final XLogger logger = XLoggerFactory.getXLogger(HandicapCalculation.class
            .getName());

    private HandicapCalculation() {}

    /**
     * calculate a players handicap.
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    public Handicap calculateUserHandicapScore(Long userId) throws UserNotFoundException {
        logger.entry(userId);

        // creates a new score object for the calculation
        final Score score = new Score();

        // retrieve all score cards for the current user
        List<ScoreCard> scoreCardList = scoreCardService.retrieveUserScoredCardsById(getUser(userId));
        System.out.println(scoreCardList.size());

        // retrieve all rounds of golf played by a user
        List<Round> roundsOfGolf = extractRoundsOfGolfFromScoreCard(scoreCardList);
        System.out.println(roundsOfGolf.size());

        // calculate the users handicap for this Round of golf
        Handicap playerHandicap = calculateHandicapForRoundOfGolf(score, roundsOfGolf);

        if(playerHandicap == null) {
            throw new RuntimeException(format(handicapException, userId));
        }

        logger.info("playerHandicap=[ " + playerHandicap + "]");

        logger.exit();

        return playerHandicap;
    }


    /**
     * extract all rounds of golf for the given user.
     *
     * @param scoreCardList
     * @return
     */
    private List<Round> extractRoundsOfGolfFromScoreCard(List<ScoreCard> scoreCardList) {
        logger.entry(scoreCardList);

        List<Round> roundsOfGolf = new ArrayList<>();

        for(ScoreCard scoreCard : scoreCardList) {
            for(Round round : scoreCard.getGolfRounds()) {
                roundsOfGolf.add(round);
            }
        }

        logger.exit(roundsOfGolf);

        return roundsOfGolf;
    }

    /**
     * calculate a users handicap based on their submitted roundOFGolf data
     *
     * @param roundsOfGolf
     * @return
     */
    private Handicap calculateHandicapForRoundOfGolf(Score score, List<Round> roundsOfGolf) {
        logger.entry(score, roundsOfGolf);

        // Setup a handicap object with default values
        Handicap playerHandicap = setupDefaultHandicap();
        System.out.println("default player handicap: " + playerHandicap);

        if(!roundsOfGolf.isEmpty()) {

            List<BigDecimal> adjustedScores = new ArrayList<>();

            // loop through each round of golf on the players scorecard
            adjustedScores = golfRound.processRoundOfGolf(score, roundsOfGolf, adjustedScores);
            System.out.println("adjustedScores size: " + adjustedScores.size());

            // total all adjusted scores for each round of golf played by the user
            BigDecimal adjustedTotal = score.getAdjustmentTotal();

            for(BigDecimal adjustScore : adjustedScores) {
                adjustedTotal = addToAdjustmentScore(adjustedTotal, adjustScore);
            }

            String handicap = calculateHandicap(roundsOfGolf.size(), adjustedTotal);

            // add calculations to the handicap object
            playerHandicap.setHandicapScore(handicap);
            playerHandicap.setNumberOfRounds(String.valueOf(roundsOfGolf.size()));

            logger.info("playerHandicap=[ " + handicap + "] roundsOfGolf=[ " + roundsOfGolf.size() + "]");
        }

        logger.exit();

        return playerHandicap;
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