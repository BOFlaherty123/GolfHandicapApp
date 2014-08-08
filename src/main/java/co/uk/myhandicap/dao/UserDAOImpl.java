package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User Dao
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/07/14
 * @project MyHandicapApp
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        logger.entry(user);

        Session session = sessionFactory.openSession();

        try {
            // Create a session transaction (usually within a try block)
            session.beginTransaction();

            // Save User object
            session.save(user);

            // Commit and close the transaction
            session.getTransaction().commit();

            // Close the session (usually within a finally block)
            session.close();

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        }

        logger.exit();
    }

    @Override
    public void update(User updateObj) {
        logger.entry(updateObj);

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // Update User object
        logger.info("update User=[ " + updateObj.toString() + " ]");
        session.update(updateObj);
        session.flush();

        // Commit and close the transaction
        session.getTransaction().commit();

        session.close();

        logger.exit(updateObj);
    }

    // TODO - code delete user method
    @Override
    public void delete(User deleteObj) {

    }

    public User retrieveUserById(Long userId) {
        logger.entry(userId);

        Session session = sessionFactory.openSession();

        User user = null;
        try {

            session.beginTransaction();

            // Retrieve a User object from the Database
            try{
                user = (User) session.get(User.class, userId);

                if(user == null) {
                    throw new UserNotFoundException("user=[id=" + userId + "] not found.");
                }

            } catch (UserNotFoundException e){
                logger.error("class=[" + this.getClass().getName() + "] method=[.retrieveUserById()]", e.getMessage());
            }

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.retrieveUserById()]", ex);
        }

        session.close();

        logger.exit();

        return user;
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        logger.entry(username);

        Session session = sessionFactory.openSession();

        User user = null;
        try {

            session.beginTransaction();

            // Retrieve a User object from the Database
            Query query = session.createQuery("from User where username = :username ");
            query.setParameter("username", username);

            // user is equal to the first user object found
            user = (User) query.list().get(0);

            if(user == null) {
                throw new UserNotFoundException("user=[username=" + username + "] not found.");
            }

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.findUserByUsername()]", ex);
        }

        session.close();

        logger.exit();

        return user;
    }

}