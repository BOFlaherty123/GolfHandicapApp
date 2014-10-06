package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Display Overall Score Statistics for a User
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
@Component
public class DisplayOverallScoreStatistics {

    @Autowired
    private ScoreCardDao scoreCardDao;

    @Autowired
    private DisplayScoreStatisticsHelper displayOverallHelper;

    public List<HoleScoreType> execute(User user) {

        // retrieve all ScoreCard objects for the user
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveUserScoreCardById(user);

        List<HoleScoreType> holeScoreTypeList = displayOverallHelper.buildScoreTypeList();

        for(ScoreCard scoreCard : scoreCardList) {
            displayOverallHelper.processScoreCardData(holeScoreTypeList, scoreCard);
        }

        return holeScoreTypeList;
    }

}
