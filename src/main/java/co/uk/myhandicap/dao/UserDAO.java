package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.dto.user.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User Dao
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/07/14
 * @project MyHandicapApp
 */
@Component
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveUser(User user) {

        Session session = sessionFactory.openSession();

        // Create a session transaction (usually within a try block)
        session.beginTransaction();

        // Save User object
        session.save(user);

        // Commit and close the transaction
        session.getTransaction().commit();

        // Close the session (usually within a finally block)
        session.close();
    }

    public User retrieveUser(Long userId) {

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        // Retrieve a User object from the Database
        User user = null;
        try{
            user = (User) session2.get(User.class, 1L);
            System.out.println(user.toString());
        } catch (RuntimeException e){
            System.out.println("error: " + e.toString());
        }

        // Basic HQL query example, not you do not need to specify "Select * ..."
        // We use class/field names for HQL queries, instead of the database information (Table & Column name etc)
        Query allUsersQuery = session2.createQuery("from User");
        List<User> users = allUsersQuery.list();

        for(User aUser : users) {
            System.out.print("Username: " + aUser.getUsername());
        }

        session2.close();

        return user;
    }
}