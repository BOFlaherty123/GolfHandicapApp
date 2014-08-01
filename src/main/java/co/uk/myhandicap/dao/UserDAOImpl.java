package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
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

        System.out.println("update: " + updateObj.toString());

        // Basic HQL query example, not you do not need to specify "Select * ..."
        // We use class/field names for HQL queries, instead of the database information (Table & Column name etc)
//        Query allUsersQuery = session.createQuery("from User");
//        List<User> users = allUsersQuery.list();
//
//        for(User aUser : users) {
//            System.out.print("Username: " + aUser.getUsername());
//        }

        logger.exit(updateObj);
    }

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

                System.out.println("retrieveUserById " + userId);

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

}