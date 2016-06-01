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
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveScoreCardsByCourseName(user, courseName);
        List<HoleScoreType> holeScoreTypeList = displayCourseHelper.buildScoreTypeList();

        for(ScoreCard scoreCard : scoreCardList) {
            displayCourseHelper.processScoreCardData(holeScoreTypeList, scoreCard);
        }

        return holeScoreTypeList;

    }

}
