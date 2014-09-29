package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

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

    @Value("${exception.userNotFound}")
    private String userNotFoundException;

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
                    throw new UserNotFoundException(format(userNotFoundException, userId));
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

    @Override
    public User retrieveUserFromSecurityContext() {

        SecurityContext ctx = SecurityContextHolder.getContext();

        User user = null;
        try {
            user = findUserByUsername(ctx.getAuthentication().getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void disableUserAccount(String username) {
        logger.entry(username);

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // retrieve user query
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);

        // update active field on user object
        User user = (User) query.list().get(0);
        user.setActive("N");

        session.update(user);

        // Commit and close the transaction
        session.getTransaction().commit();

        session.close();

        logger.exit();
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        logger.entry(username);

        Session session = sessionFactory.openSession();

        User user = null;
        try {

            session.beginTransaction();

            // Retrieve a User object from the Database
            Query query = session.createQuery("from User where username = :username");
            query.setParameter("username", username);

            // user is equal to the first user object found
            user = (User) query.list().get(0);

            if(user == null) {
                throw new UserNotFoundException(format(userNotFoundException, username));
            }

        } catch(GenericJDBCException ex) {
            logger.error("class=[" + this.getClass().getName() + "] method=[.findUserByUsername()]", ex);
        }

        session.close();

        logger.exit();

        return user;
    }

}