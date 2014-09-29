package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.model.handicap.Hole;
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
 * Hole Dao Implementation.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/09/2014
 * @project MyHandicapApp
 */
@Component
public class HoleDaoImpl implements HoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Hole saveObj) {}

    @Override
    public void update(Hole updateObj) {}

    @Override
    public void delete(Hole deleteObj) {}

    @Override
    public List<Hole> retrieveHoleAverageByHolePar(User user, String holePar) {

        Session session = sessionFactory.openSession();

        List<Hole> golfHoles = new ArrayList<>();

        try {
            session.beginTransaction();

            Query query = session.createQuery("select distinct holes from ScoreCard as scoreCard " +
                    "inner join scoreCard.golfRounds as round " +
                    "inner join round.holes as holes where scoreCard.playerId = :playerId and holes.holePar = :holePar");
            query.setParameter("playerId", user.getId());
            query.setParameter("holePar", holePar);

            golfHoles = query.list();

            session.getTransaction().commit();
        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            session.close();
        }

        return golfHoles;
    }

    @Override
    public List<Hole> retrieveHoleAverageByHoleYardage(User user, String averageRequested) {

        Session session = sessionFactory.openSession();

        List<Hole> golfHoles = new ArrayList<>();

        try {
            session.beginTransaction();

            Query query = session.createQuery("select distinct holes from ScoreCard as scoreCard " +
                    "inner join scoreCard.golfRounds as round " +
                    "inner join round.holes as holes where scoreCard.playerId = :playerId and holes.holeYards > :averageRequested");
            query.setParameter("playerId", user.getId());
            query.setParameter("averageRequested", averageRequested);

            golfHoles = query.list();

            session.getTransaction().commit();
        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        } finally {
            session.close();
        }

        return golfHoles;
    }

}
