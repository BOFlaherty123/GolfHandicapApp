package main.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;

import java.util.List;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/09/2014
 * @project MyHandicapApp
 */
public interface AverageScore {

    static final String ZERO = "0";

    String execute(User user, String averageRequested);

    String calculateAverage(List<ScoreCard> scoreCardList, String averageRequested);

}
