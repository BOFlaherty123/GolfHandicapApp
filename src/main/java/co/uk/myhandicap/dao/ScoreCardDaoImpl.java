package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ScoreCard Dao Implementation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Component
public class ScoreCardDaoImpl implements ScoreCardDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ScoreCard scoreCard) {
        logger.entry(scoreCard);

        Session session = openSession();

        try {
            session.beginTransaction();
            session.save(scoreCard);
            session.getTransaction().commit();
        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            session.close();
        }

        logger.exit();
    }

    @Override
    public void update(ScoreCard updateObj) {

    }

    @Override
    public void delete(ScoreCard deleteObj) {

    }

    /**
     * retrieve score cards for a user by id.
     *
     * @param user
     * @return
     */
    @Override
    public List<ScoreCard> retrieveUserScoreCardById(User user) {
        logger.entry(user);

        Session session = openSession();

        List<ScoreCard> scoreCardList = new ArrayList<ScoreCard>();

        try {
            session.beginTransaction();

            Query query = session.createQuery("from ScoreCard where playerId = :playerId ");
            query.setParameter("playerId", user.getId());
            logger.info("class=[" + this.getClass().getName() + "] method=[.save()] query=[" + query.toString() + "]");

            scoreCardList = query.list();
            session.getTransaction().commit();

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            session.close();
        }

        logger.exit();

        return scoreCardList;
    }

    /**
     * retrieve the average score for the user on a particular golf course.
     *
     * @param user
     * @param courseName
     * @return
     */
    public List<ScoreCard> retrieveScoreCardAverageByGolfCourse(User user, String courseName) {

        Session session = openSession();

        List<ScoreCard> scoreCardList = new ArrayList<ScoreCard>();

        try {

            session.beginTransaction();

            Query query = session.createQuery("select distinct scoreCard from ScoreCard as scoreCard " +
                    "inner join scoreCard.golfRounds as round where scoreCard.playerId = :playerId and round.courseName = :courseName");
            query.setParameter("playerId", user.getId());
            query.setParameter("courseName", courseName);

            scoreCardList = query.list();

            session.getTransaction().commit();

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            session.close();
        }

        return scoreCardList;
    }

    /**
     * retrieve all golf course names (String) that a user has played on.
     *
     * @param user
     * @return
     */
    @Override
    public List<String> retrieveAllGolfCourseNamesForUserByScoreCard(User user) {

        Session session = openSession();

        List<String> golfCourseNames = new ArrayList<String>();

        try {

            session.beginTransaction();

            Query query = session.createQuery("select distinct round.courseName from ScoreCard as scoreCard " +
                    "inner join scoreCard.golfRounds as round where scoreCard.playerId = :playerId");
            query.setParameter("playerId", user.getId());

            golfCourseNames = query.list();

            session.getTransaction().commit();

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            session.close();
        }

        return golfCourseNames;
    }

    /**
     * retrieve score cards for a user for a given course.
     *
     * @param user
     * @param courseName
     * @return
     */
    @Override
    public List<ScoreCard> retrieveScoreCardsByCourseName(User user, String courseName) {

        Session session = openSession();

        List<ScoreCard> scoreCardList = new ArrayList<ScoreCard>();

        try {

            session.beginTransaction();

            Query query = session.createQuery("from ScoreCard as scoreCard " +
                    "inner join scoreCard.golfRounds as round where scoreCard.playerId = :playerId and round.courseName = :courseName");
            query.setParameter("playerId", user.getId());
            query.setParameter("courseName", courseName);

            List<Object[]> scoreCardsList = query.list();

            for(Object[] object : scoreCardsList) {
                ScoreCard sc = (ScoreCard) object[0];
                scoreCardList.add(sc);
            }

            session.getTransaction().commit();

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            session.close();
        }

        return scoreCardList;
    }

    /**
     * Open a new session
     *
     * @return
     */
    private Session openSession() {
        return sessionFactory.openSession();
    }

}
