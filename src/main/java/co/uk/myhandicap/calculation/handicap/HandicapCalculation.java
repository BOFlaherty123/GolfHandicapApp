package main.java.co.uk.myhandicap.calculation.handicap;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.Handicap;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardService;
import main.java.co.uk.myhandicap.service.UserService;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    private static final XLogger logger = XLoggerFactory.getXLogger(HandicapCalculation.class
            .getName());


    private HandicapCalculation() {}

    /**
     * calculate a players handicap
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

        // retrieve all rounds of golf played by a user
        List<Round> roundsOfGolf = extractRoundsOfGolfFromScoreCard(scoreCardList);

        // calculate the users handicap for this Round of golf
        Handicap playerHandicap = calculateHandicapForRoundOfGolf(score, roundsOfGolf);

        if(playerHandicap == null) {
            throw new RuntimeException("We are unable to provide a handicap calculation for userId[" + userId + "]");
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
        Handicap playerHandicap = new Handicap().setupDefaultHandicap();

        if(!roundsOfGolf.isEmpty()) {

            List<BigDecimal> adjustedScores = new ArrayList<>();

            // loop through each round of golf on the players scorecard
            adjustedScores = golfRound.processRoundOfGolf(score, roundsOfGolf, adjustedScores);

            // total all adjusted scores for each round of golf played by the user
            BigDecimal adjustedTotal = score.getAdjustmentTotal();

            for(BigDecimal adjustScore : adjustedScores) {
                adjustedTotal = score.addToAdjustmentScore(adjustedTotal, adjustScore);
            }

            String handicap = score.calculateHandicap(roundsOfGolf.size(), adjustedTotal);

            // add calculations to the handicap object
            playerHandicap.setHandicapScore(handicap);
            playerHandicap.setNumberOfRounds(String.valueOf(roundsOfGolf.size()));

            logger.info("playerHandicap=[ " + handicap + "] roundsOfGolf=[ " + roundsOfGolf.size() + "]");
        }

        logger.exit();

        return playerHandicap;
    }

    /**
     * Retrieve the user from the database
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    private User getUser(Long userId) throws UserNotFoundException {
        logger.entry(userId);

        User user = userService.retrieveUserById(userId);

        if(user == null) {
            throw new UserNotFoundException("User not found for Id=[" + userId +"]");
        }

        logger.exit(user.toString());

        return user;
    }

}