package main.java.co.uk.myhandicap.controllers;

import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 09/10/2014
 * @project MyHandicapApp
 */
public abstract class AbstractController {

    @Autowired
    private UserService userService;

    /**
     * retrieve the user object from the database
     *
     * @param username
     * @return
     */
    protected User retrieveUser(String username) {
        User user = null;

        try {
            user = userService.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}
