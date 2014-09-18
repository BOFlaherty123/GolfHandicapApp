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

        Session session = sessionFactory.openSession();

        try {
            // Create a session transaction (usually within a try block)
            session.beginTransaction();

            // Save User object
            session.save(scoreCard);

            // Commit and close the transaction
            session.getTransaction().commit();

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            // Close the session (usually within a finally block)
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

    @Override
    public List<ScoreCard> retrieveUserScoreCardById(User user) {
        logger.entry(user);

        Session session = sessionFactory.openSession();

        List<ScoreCard> scoreCardList = new ArrayList<>();

        try {
            // Create a session transaction (usually within a try block)
            session.beginTransaction();

            Query query = session.createQuery("from ScoreCard where playerId = :playerId ");
            query.setParameter("playerId", user.getId());
            logger.info("class=[" + this.getClass().getName() + "] method=[.save()] query=[" + query.toString() + "]");

            scoreCardList = query.list();

            // Commit and close the transaction
            session.getTransaction().commit();

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            // Close the session (usually within a finally block)
            session.close();
        }

        logger.exit();

        return scoreCardList;
    }

    public List<ScoreCard> retrieveScoreCardAverageByGolfCourse(User user, String courseName) {

        Session session = sessionFactory.openSession();

        List<ScoreCard> scoreCardList = new ArrayList<>();

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

}
