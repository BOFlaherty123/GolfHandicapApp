package main.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
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
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveUserScoreCardById(user);

        // calculate average score
        return calculateAverage(scoreCardList, averageRequested);
    }

    @Override
    public String calculateAverage(List<ScoreCard> scoreCardList, String averageRequested) {
        // return average as String
        return iterateOverRoundsOfGolf(scoreCardList, averageRequested);
    }

    /**
     * iterates over each round of golf and returns the average as a String (abstract method).
     *
     * @param scoreCardList
     * @param averageRequested
     */
    protected String iterateOverRoundsOfGolf(List<ScoreCard> scoreCardList, String averageRequested) {

        // Default average value
        BigInteger total = new BigInteger("0");
        int numberOfRounds = 0;

        for(ScoreCard scoreCard : scoreCardList) {

            for(Round golfRound : scoreCard.getGolfRounds()) {
                total = processRoundsOfGolfByCourseName(averageRequested, total, golfRound);
                numberOfRounds++;
            }
        }

        return calculate(total, numberOfRounds);
    }

    /**
     *
     * @param averageRequested
     * @param totalScore
     * @param golfRound
     * @return
     */
    private BigInteger processRoundsOfGolfByCourseName(String averageRequested, BigInteger totalScore, Round golfRound) {

        List<Round> courseNameRounds = new ArrayList<>();

        if(golfRound.getCourseName().equals(averageRequested)) {

            courseNameRounds.add(golfRound);

            for(Hole holeScore : golfRound.getHoles()) {
                BigInteger score = new BigInteger(holeScore.getHoleScore());
                totalScore = totalScore.add(score);
            }

        }

        return totalScore;
    }

}