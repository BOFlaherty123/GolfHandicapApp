package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.dto.handicap.ScoreCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        Session session = sessionFactory.openSession();

        try {
            // Create a session transaction (usually within a try block)
            session.beginTransaction();

            // Save User object
            session.save(scoreCard);

            // Commit and close the transaction
            session.getTransaction().commit();

            // Close the session (usually within a finally block)
            session.close();

        } catch(GenericJDBCException ex) {
            System.out.println("Database Exception! Add Logging");
        }

    }

    @Override
    public void update(ScoreCard updateObj) {

    }

    @Override
    public void delete(ScoreCard deleteObj) {

    }


}
