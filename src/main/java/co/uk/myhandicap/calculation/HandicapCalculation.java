package main.java.co.uk.myhandicap.calculation;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.Handicap;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardServiceImpl;
import main.java.co.uk.myhandicap.service.UserServiceImpl;
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

    private final static int DEFAULT_HANDICAP = 28;

    private final Score score = new Score();

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    /**
     * calculate a players handicap
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    public Handicap calculateUserHandicapScore(Long userId) throws UserNotFoundException {

        // retrieve user object
        User user = getUser(userId);

        // retrieve all score cards for a user
        List<ScoreCard> scoreCardList = scoreCardService.retrieveUserScoredCardsById(user);

        // retrieve all rounds of golf played by a user
        List<Round> roundsOfGolf = extractRoundsOfGolfFromScoreCard(scoreCardList);

        // calculate the users handicap for this Round of golf
        Handicap playerHandicap = calculateHandicapForRoundOfGolf(roundsOfGolf);

        if(playerHandicap == null) {
            throw new RuntimeException("We are unable to provide a handicap calculation for userId[" + userId + "]");
        }

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
        User user = userService.retrieveUserById(userId);

        if(user == null) {
            throw new UserNotFoundException("User not found for Id[" + userId +"]");
        }

        return user;
    }

    /**
     * extract all rounds of golf for the given user.
     *
     * @param scoreCardList
     * @return
     */
    private List<Round> extractRoundsOfGolfFromScoreCard(List<ScoreCard> scoreCardList) {

        List<Round> roundsOfGolf = new ArrayList<>();

        for(ScoreCard scoreCard : scoreCardList) {
            for(Round round : scoreCard.getGolfRounds()) {
                roundsOfGolf.add(round);
            }
        }

        return roundsOfGolf;
    }

    /**
     * calculate a users handicap based on their submitted roundOFGolf data
     *
     * @param roundsOfGolf
     * @return
     */
    private Handicap calculateHandicapForRoundOfGolf(List<Round> roundsOfGolf) {

        // Setup a handicap object with default values
        Handicap playerHandicap = setupDefaultHandicap();

        if(!roundsOfGolf.isEmpty()) {

            List<BigDecimal> adjustedScores = new ArrayList<>();

            // TODO - addToScore SSS (standard scratch score) to Round class
            // Store coursePar & courseSSS for calculation
            BigDecimal coursePar; int courseSSS;

            for(Round round : roundsOfGolf) {

                BigDecimal playerScore = score.getPlayerScore();

                coursePar = score.createScore(round.getCoursePar());

                for(Hole hole : round.getHoles()) {

                    // Process CONGU adjustment
                    processCONGUAdjustment(hole);

                    // add players score for the hole to the round total
                    BigDecimal holeScore = score.createScore(hole.getHoleScore());
                    playerScore = score.addToPlayerScore(playerScore, holeScore);

                }

                // calculate the players adjusted score for the round
                BigDecimal adjustedScore = score.subtractFromScore(playerScore, coursePar);
                adjustedScores.add(adjustedScore);

            }

            // total all adjusted scores for each round of golf played by the user
            BigDecimal adjustedTotal = score.getAdjustmentTotal();

            for(BigDecimal adjustScore : adjustedScores) {
                adjustedTotal = score.addToAdjustmentScore(adjustedTotal, adjustScore);
            }

            String handicap = score.calculateHandicap(roundsOfGolf.size(), adjustedTotal);

            // add calculations to the handicap object
            playerHandicap.setHandicapScore(handicap);
            playerHandicap.setNumberOfRounds(String.valueOf(roundsOfGolf.size()));
        }

        return playerHandicap;
    }

    /**
     * CONGU adjustment (max +2 shot penalty per hole 'double bogey)
     *
     * @param hole
     */
    private void processCONGUAdjustment(Hole hole) {

        BigDecimal maxStroke = score.createScore(Integer.valueOf(hole.getHolePar()) + 2);
        BigDecimal playerScore = score.createScore(hole.getHoleScore());

        if(playerScore.compareTo(maxStroke) > 0) {
            playerScore = maxStroke;

            hole.setHoleScore(String.valueOf(playerScore));
        }

    }

    /**
     * creates a default starting handicap object for a user
     *
     * @return
     */
    private Handicap setupDefaultHandicap() {
        Handicap playerHandicap = new Handicap();

        playerHandicap.setHandicapScore(String.valueOf(DEFAULT_HANDICAP));
        // TODO - use JodaTime to obtain todays date in String format
        playerHandicap.setCalculatedOn("17/07/2014");

        return playerHandicap;
    }

}