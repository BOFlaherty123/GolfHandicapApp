package main.java.co.uk.myhandicap.actions;

import main.java.co.uk.myhandicap.encryption.EncryptUserPassword;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Valid User Account Rest Action
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/10/2014
 * @project MyHandicapApp
 */
public class ValidUserAccountReset implements Action {

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptUserPassword encryptUserPassword;

    @Override
    public Event execute(RequestContext requestContext) throws Exception {

        // retrieve the username from the request scope
        String username = requestContext.getRequestScope().getString("username");

        // generate a new password for the user
        String password = encryptPassword();

        // retrieve the user
        User user = retrieveUser(username);

        // add values to the flowScope
        addParameterToFlowScope(requestContext, "username", username);
        addParameterToFlowScope(requestContext, "password", password);

        // encrypt new password and update the user object
        updateUser(user, password);

        return (user != null) ? new Event(this, "yes") : new Event(this, "no");
    }


    /**
     * retrieve the user object from the database
     *
     * @param username
     * @return
     */
    private User retrieveUser(String username) {
        User user = null;

        try {
            user = userService.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * encrypt the password String
     *
     * @return
     */
    private String encryptPassword() {
        return RandomStringUtils.randomAlphanumeric(20);
    }

    /**
     * add a parameter to the flowScope (username & password)
     *
     * @param requestContext
     * @param key
     * @param value
     */
    private void addParameterToFlowScope(RequestContext requestContext, String key, String value) {
        requestContext.getFlowScope().put(key, value);
    }

    /**
     * update the user object containing the newly encrypted password
     *
     * @param user
     * @param password
     */
    private void updateUser(User user, String password) {
        user.setPassword(encryptUserPassword.encryptPassword(password));
        userService.update(user);
    }

}
