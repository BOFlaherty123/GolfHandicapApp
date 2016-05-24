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

        String username = requestContext.getRequestScope().getString("username");
        String password = encryptPassword();

        User user = retrieveUser(username);

        addParameterToFlowScope(requestContext, "username", username);
        addParameterToFlowScope(requestContext, "password", password);

        encryptPasswordAndUpdateUser(user, password);

        return (user != null) ? new Event(this, "yes") : new Event(this, "no");
    }

    private User retrieveUser(String username) {
        User user = null;

        try {
            user = userService.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    private String encryptPassword() {
        return RandomStringUtils.randomAlphanumeric(20);
    }

    private void addParameterToFlowScope(RequestContext requestContext, String key, String value) {
        requestContext.getFlowScope().put(key, value);
    }

    private void encryptPasswordAndUpdateUser(User user, String password) {
        user.setPassword(encryptUserPassword.encryptPassword(password));
        userService.update(user);
    }

}
