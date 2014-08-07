package main.java.co.uk.myhandicap.controllers;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.slf4j.ext.XLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Abstract Controller to implement common behavior
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/08/2014
 * @project MyHandicapApp
 */
public abstract class AbstractController {

    @Autowired
    private UserService userService;

    public User retrieveUserFromSecurityContext() {

        // retrieve the user from the security context
        SecurityContext ctx = SecurityContextHolder.getContext();

        User user = null;

        try {
            user = userService.findUserByUsername(ctx.getAuthentication().getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    protected abstract XLogger initiateXLoggerInstance(String className);

}
