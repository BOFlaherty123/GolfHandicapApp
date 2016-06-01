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
 * UserDAO
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/07/14
 * @project MyHandicapApp
 */
@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${exception.userNotFound}")
    private String userNotFoundException;

    @Override
    public void save(User user) {
        GenericDAO.logger.entry(user);

        Session session = openSession();

        try {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
            session.close();

        } catch(GenericJDBCException ex) {
            GenericDAO.logger.error("class=[" + this.getClass().getName() + "] method=[.save()]", ex);
        }

        GenericDAO.logger.exit();
    }

    @Override
    public void update(User updateObj) {
        GenericDAO.logger.entry(updateObj);

        Session session = openSession();
        session.beginTransaction();

        GenericDAO.logger.info("update User=[ " + updateObj.toString() + " ]");
        session.update(updateObj);
        session.flush();

        session.getTransaction().commit();
        session.close();

        GenericDAO.logger.exit(updateObj);
    }

    // TODO - code delete user method
    @Override
    public void delete(User deleteObj) {

    }

    /**
     * retrieve a user object by the user's id.
     *
     * @param userId
     * @return
     */
    public User retrieveUserById(Long userId) {
        GenericDAO.logger.entry(userId);

        Session session = openSession();

        User user = null;
        try {

            session.beginTransaction();

            try{
                user = (User) session.get(User.class, userId);

                if(user == null) {
                    throw new UserNotFoundException(format(userNotFoundException, userId));
                }

            } catch (UserNotFoundException e){
                GenericDAO.logger.error("class=[" + this.getClass().getName() + "] method=[.retrieveUserById()]", e.getMessage());
            }

        } catch(GenericJDBCException ex) {
            GenericDAO.logger.error("class=[" + this.getClass().getName() + "] method=[.retrieveUserById()]", ex);
        }

        session.close();

        GenericDAO.logger.exit();

        return user;
    }

    /**
     * retrieve the user object from the security context (username).
     *
     * @return
     */
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

    /**
     * disable a user's account by their username
     *
     * @param username
     */
    @Override
    public void disableUserAccount(String username) {
        GenericDAO.logger.entry(username);

        Session session = openSession();

        session.beginTransaction();

        // retrieve user query
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);

        User user = (User) query.uniqueResult();
        user.setActive("N");

        session.update(user);

        session.getTransaction().commit();
        session.close();

        GenericDAO.logger.exit();
    }

    /**
     * find a user object by username (String).
     *
     * @param username
     * @return
     * @throws UserNotFoundException
     */
    public User findUserByUsername(String username) throws UserNotFoundException {
        GenericDAO.logger.entry(username);

        Session session = openSession();

        User user = null;
        try {

            session.beginTransaction();

            Query query = session.createQuery("from User where username = :username");
            query.setParameter("username", username);

            user = (User) query.uniqueResult();

            if(user == null) {
                throw new UserNotFoundException(format(userNotFoundException, username));
            }

        } catch(GenericJDBCException ex) {
            GenericDAO.logger.error("class=[" + this.getClass().getName() + "] method=[.findUserByUsername()]", ex);
        }

        session.close();

        GenericDAO.logger.exit();

        return user;
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