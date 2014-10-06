package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Display user score statistics by golf course.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
@Component
public class DisplayScoreStatisticsByCourse {

    @Autowired
    private ScoreCardDao scoreCardDao;

    @Autowired
    private DetermineTypeOfScore determineTypeOfScore;

    @Autowired
    private DisplayScoreStatisticsHelper displayCourseHelper;

    /**
     * execute method to process user score statistics for a particular golf course.
     *
     * @param courseName
     * @param user
     */
    public List<HoleScoreType> execute(String courseName, User user) {

        // retrieve all matching scoreCards for a given courseName
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveScoreCardsByCourseName(user, courseName);

        // build the initial HoleScoreType list (default construction)
        List<HoleScoreType> holeScoreTypeList = displayCourseHelper.buildScoreTypeList();

        // loop through each matching scoreCard and process the hole data
        for(ScoreCard scoreCard : scoreCardList) {
            displayCourseHelper.processScoreCardData(holeScoreTypeList, scoreCard);
        }

        return holeScoreTypeList;

    }

}
