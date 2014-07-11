package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.dto.user.User;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
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
            System.out.println("Database Exception! Add Logging");
        }

    }

    @Override
    public void update(User updateObj) {

        // Basic HQL query example, not you do not need to specify "Select * ..."
        // We use class/field names for HQL queries, instead of the database information (Table & Column name etc)
//        Query allUsersQuery = session.createQuery("from User");
//        List<User> users = allUsersQuery.list();
//
//        for(User aUser : users) {
//            System.out.print("Username: " + aUser.getUsername());
//        }

    }

    @Override
    public void delete(User deleteObj) {

    }

    public User retrieveUserById(Long userId) {

        Session session = sessionFactory.openSession();

        User user = null;
        try {

            session.beginTransaction();

            // Retrieve a User object from the Database
            try{
                user = (User) session.get(User.class, userId);

                if(user == null) {
                    throw new UserNotFoundException("user[id=" + userId + "] not found.");
                }

            } catch (UserNotFoundException e){
                System.out.println("error: " + e.getMessage());
            }

            session.close();

        } catch(GenericJDBCException ex) {
            System.out.println("Database Exception! Add Logging");
        }

        return user;
    }

}