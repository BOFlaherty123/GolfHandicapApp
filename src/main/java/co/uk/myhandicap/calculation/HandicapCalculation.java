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

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    // Pass in the user
    // extract Rounds Of Golf from Scorecard
    // Locally Store Course Par and SSI
    // loop through each hole of the round and...

        // Calculate the users total score (Number of Strokes)
        // Gross Score = Number of Strokes - Scratch Score (SSS)

        // Calculate the rounds Adjusted Score (Gross Score - any adjustments under CONGU *)

        // * CONGU allows double bogey (+2) maximum on any hole. i.e if a player takes 8 shots on a par 5
        // * the maximum score allowed for that hole is 7 (5+2).


    // Divide the number of Adjusted Gross Scores by the number of rounds played.

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

        // Store coursePar & courseSSS for calculation
        int coursePar; int courseSSS; int playerScore = 0;

        for(Round round : roundsOfGolf) {

            coursePar = Integer.valueOf(round.getCoursePar());
            // TODO - add SSS (standard scratch score) to Round class

            for(Hole hole : round.getHoles()) {
                playerScore += Integer.valueOf(hole.getHoleScore());
            }

            System.out.println(playerScore);

        }

        return playerHandicap;

    }

    /**
     * creates a default starting handicap object for a user
     *
     * @return
     */
    private Handicap setupDefaultHandicap() {
        Handicap playerHandicap = new Handicap();

        playerHandicap.setHandicapScore(String.valueOf(DEFAULT_HANDICAP));
        playerHandicap.setCalculatedOn("17/07/2014");

        return playerHandicap;
    }

}
