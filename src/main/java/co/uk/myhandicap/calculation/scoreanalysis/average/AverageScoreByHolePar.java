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
 * Average Score By Hole Par
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/09/2014
 * @project MyHandicapApp
 */
@Component
public class AverageScoreByHolePar extends AbstractCalculateAverage implements AverageScore {

    @Autowired
    private ScoreCardDao scoreCardDao = null;

    @Override
    public String execute(User user, String averageRequested) {
        // retrieve a scoreCard(s) for the user;
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveUserScoreCardById(user);

        return calculateAverage(scoreCardList, averageRequested);
    }

    @Override
    public String calculateAverage(List<ScoreCard> scoreCardList, String averageRequested) {
        // averageRequested equals holePar parameter requested by the user, valid values 3, 4 or 5
        return iterateOverRoundsOfGolf(scoreCardList, averageRequested);
    }

    @Override
    protected String iterateOverRoundsOfGolf(List<ScoreCard> scoreCardList, String averageRequested) {

        // Default average value
        BigInteger total = new BigInteger("0");

        for(ScoreCard scoreCard : scoreCardList) {

            for(Round golfRound : scoreCard.getGolfRounds()) {
                total = total.add(processRoundsOfGolfByHolePar(averageRequested, total, golfRound));
            }
        }

        // if total is zero return, else calculate the user's avg score by par
        return (total.signum() == 0 ) ?
                ZERO : calculate(total, numberOfHoles(scoreCardList, averageRequested));

    }

    private BigInteger processRoundsOfGolfByHolePar(String averageRequested, BigInteger total, Round golfRound) {

        for(Hole hole : golfRound.getHoles()) {

            if(hole.getHolePar().equals(averageRequested)) {
                BigInteger score = new BigInteger(hole.getHoleScore());
                total = total.add(score);
            }

        }

        return total;
    }

    /**
     * determines the number of holes with a registered score that match the averageRequested parameter
     *
     * @param scoreCardList
     * @param averageRequested
     * @return
     */
    private int numberOfHoles(List<ScoreCard> scoreCardList, String averageRequested) {

        int noHoles = 0;

        for(ScoreCard scoreCard : scoreCardList) {
            for(Round golfRound : scoreCard.getGolfRounds()) {
                for(Hole hole : golfRound.getHoles()) {
                    if(hole.getHolePar().equals(averageRequested)) {
                        noHoles++;
                    }
                }
            }
        }

        return noHoles;
    }
}
