package main.java.co.uk.myhandicap.controllers;

import main.java.co.uk.myhandicap.dto.Address;
import main.java.co.uk.myhandicap.dto.User;
import main.java.co.uk.myhandicap.dto.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Welcome Page
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/07/14
 * @project MyHandicapApp
 */
@Controller
public class WelcomeController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value="/")
    public String welcomePage() {

        User user = new User();
        user.setCreatedDate(new Date());
        user.setUsername("BOFlaherty");
        user.setEmail("benjamin@oflaherty.co.uk");
        user.setFirstName("Benjamin");
        user.setLastName("OFlaherty");
        user.setPassword("qwerty12");

        // add UserRoles collection (Set<UserRole)
        UserRole role1 = new UserRole();
        role1.setAssignedDate(new Date());
        role1.setRole("ROLE_USER");

        UserRole role2 = new UserRole();
        role2.setAssignedDate(new Date());
        role2.setRole("ROLE_ADMIN");

        user.getUserRoles().add(role1);
        user.getUserRoles().add(role2);

        Address address = new Address();
        address.setAddress1("131 The Lock, Building 72, London");
        address.setPostCode("E15 2QG");

        user.setAddress(address);

        Session session = sessionFactory.openSession();

        // Create a session transaction (usually within a try block)
        session.beginTransaction();

        // Save User object
        session.save(user);

        // Commit and close the transaction
        session.getTransaction().commit();

        // Close the session (usually within a finally block)
        session.close();

        user = null;

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

//        // Retrieve a User object from the Database
//        try{
//            user = (User) session2.get(User.class, 1L);
//            System.out.println(user.toString());
//
//        } catch (RuntimeException e){
//            System.out.println(e.toString());
//        }
//
        return "welcome";
    }

}