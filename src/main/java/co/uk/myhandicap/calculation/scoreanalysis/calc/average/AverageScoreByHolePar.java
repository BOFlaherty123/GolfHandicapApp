package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average;

import main.java.co.uk.myhandicap.dao.HoleDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Average Score By Hole Par
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/09/2014
 * @project MyHandicapApp
 */
@Component
public class AverageScoreByHolePar extends AbstractCalculateAverage implements IAverageScore {

    @Autowired
    private HoleDao holeDao = null;

    public String execute(User user, String averageRequested) {

        // retrieve a scoreCard(s) for the user;
        List<Hole> holeParList = holeDao.retrieveHoleAverageByHolePar(user, averageRequested);

        return calculateAverage(holeParList);
    }

    public String calculateAverage(List holeParList) {
        // averageRequested equals holePar parameter requested by the user, valid values 3, 4 or 5
        return iterateOverAndProcessQueryResults(holeParList);
    }

    private String iterateOverAndProcessQueryResults(List<Hole> holeParList) {

        // Default average value
        BigDecimal total = new BigDecimal(ZERO);
        total = total.add(calculateTotalHoleScoreForGivenPar(total, holeParList));

        // if total is zero return, else calculate the user's avg score by par
        return (total.signum() == 0) ?
                ZERO : calculate(total, numberOfHoles(holeParList), 2);
    }

    /**
     * add the score of any given hole that matches the users par criteria to the overall total
     *
     * @param total
     * @param holeParList
     * @return
     */
    private BigDecimal calculateTotalHoleScoreForGivenPar(BigDecimal total, List<Hole> holeParList) {

        for(Hole hole : holeParList) {
            BigDecimal score = new BigDecimal(hole.getHoleScore());
            total = total.add(score);
        }

        return total;
    }

}