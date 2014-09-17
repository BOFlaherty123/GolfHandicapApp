package main.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * Average Score By Course
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/09/2014
 * @project MyHandicapApp
 */
@Component
public class AverageScoreByCourse extends AbstractCalculateAverage implements AverageScore {

    @Autowired
    private ScoreCardDao scoreCardDao = null;

    /**
     * averageRequested equals courseName parameter requested by the user.
     *
     * @param user
     * @param averageRequested
     * @return
     */
    @Override
    public String execute(User user, String averageRequested) {

        // retrieve a scoreCard(s) for the user;
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveScoreCardAverageByGolfCourse(user, averageRequested);

        // calculate average score
        return calculateAverage(scoreCardList);
    }

    @Override
    public String calculateAverage(List scoreCardList) {
        // return average as String
        return iterateOverRoundsOfGolf(scoreCardList);
    }

    /**
     * iterates over each round of golf and returns the average as a String (abstract method).
     *
     * @param scoreCardList
     */
    private String iterateOverRoundsOfGolf(List<ScoreCard> scoreCardList) {

        // Default average value
        BigInteger totalScore = new BigInteger(ZERO);
        int numberOfRounds = 0;

        if(scoreCardList != null) {
            for (ScoreCard scoreCard : scoreCardList) {
                for (Round golfRound : scoreCard.getGolfRounds()) {
                    totalScore = totalScore.add(processRoundsOfGolfByCourseName(golfRound));
                    numberOfRounds++;
                }
            }
        }

        // if total is zero return, else calculate the user's avg score by course
        return (totalScore.signum() == 0) ?
                ZERO : calculate(totalScore, numberOfRounds);

    }

    /**
     *
     *
     * @param golfRound
     * @return
     */
    private BigInteger processRoundsOfGolfByCourseName(Round golfRound) {

        BigInteger roundTotal = new BigInteger(ZERO);

        for(Hole hole : golfRound.getHoles()) {
            BigInteger score = new BigInteger(hole.getHoleScore());
            roundTotal = roundTotal.add(score);
        }

        return roundTotal;
    }

}