package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.product;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Average Score By Course
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/09/2014
 * @project MyHandicapApp
 */
@Component
public class AverageScoreByCourse extends AbstractCalculateAverage implements IAverageScore {

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
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveScoreCardAverageByGolfCourse(user, averageRequested);
        return calculateAverage(scoreCardList);
    }

    @Override
    public String calculateAverage(List scoreCardList) {
        return iterateOverAndProcessQueryResults(scoreCardList);
    }

    /**
     * iterates over each round of golf and returns the average as a String (abstract method).
     *
     * @param scoreCardList
     */
    private String iterateOverAndProcessQueryResults(List<ScoreCard> scoreCardList) {

        BigDecimal totalScore = new BigDecimal(ZERO);
        int numberOfRounds = 0;

        if(scoreCardList != null) {
            for (ScoreCard scoreCard : scoreCardList) {
                for (Round golfRound : scoreCard.getGolfRounds()) {
                    totalScore = totalScore.add(processRoundsOfGolfByCourseName(golfRound));
                    numberOfRounds++;
                }
            }
        }

        return (totalScore.signum() == 0) ?
                ZERO : calculate(totalScore, numberOfRounds, 0);
    }

    private BigDecimal processRoundsOfGolfByCourseName(Round golfRound) {

        BigDecimal roundTotal = new BigDecimal(ZERO);

        for(Hole hole : golfRound.getHoles()) {
            BigDecimal score = new BigDecimal(hole.getHoleScore());
            roundTotal = roundTotal.add(score);
        }

        return roundTotal;
    }

}
