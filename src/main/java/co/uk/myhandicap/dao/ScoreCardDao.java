package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;

import java.util.List;

/**
 * ScoreCard Dao Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public interface ScoreCardDao extends GenericDao<ScoreCard, Long> {

    List<ScoreCard> retrieveUserScoreCardById(User user);

    List<ScoreCard> retrieveScoreCardAverageByGolfCourse(User user, String courseName);

    List<String> retrieveAllGolfCourseNamesForUserByScoreCard(User user);

    List<ScoreCard> retrieveScoreCardsByCourseName(User user, String courseName);

}
