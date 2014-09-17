package main.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
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
public class AverageScoreByHolePar extends AbstractCalculateAverage implements AverageScore{

    @Autowired
    private ScoreCardDao scoreCardDao = null;

    public String execute(User user, String averageRequested) {
        // retrieve a scoreCard(s) for the user;
        List<Hole> holeParList = scoreCardDao.retrieveScoreCardAverageByHolePar(user, averageRequested);

        return calculateAverage(holeParList);
    }

    public String calculateAverage(List holeParList) {
        // averageRequested equals holePar parameter requested by the user, valid values 3, 4 or 5
        return iterateOverRoundsOfGolf(holeParList);
    }

    private String iterateOverRoundsOfGolf(List<Hole> holeParList) {

        // Default average value
        BigInteger total = new BigInteger(ZERO);
        total = total.add(calculateTotalHoleScoreForGivenPar(total, holeParList));

        // if total is zero return, else calculate the user's avg score by par
        return (total.signum() == 0) ?
                ZERO : calculate(total, numberOfHoles(holeParList));

    }

    private BigInteger calculateTotalHoleScoreForGivenPar(BigInteger total, List<Hole> holes) {

        for(Hole hole : holes) {
            BigInteger score = new BigInteger(hole.getHoleScore());
            total = total.add(score);
        }

        return total;
    }

    /**
     * determines the number of holes with a registered score that match the averageRequested parameter
     *
     * @return
     */
    private int numberOfHoles(List<Hole> holeParList) {
        return holeParList.size();
    }

}
