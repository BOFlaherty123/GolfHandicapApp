package main.java.co.uk.myhandicap.actions;

import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
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

    @Override
    public Event execute(RequestContext requestContext) throws Exception {

        String username = requestContext.getRequestScope().getString("username");

        User user = null;
        try {
            user = userService.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (user != null) ? new Event(this, "yes") : new Event(this, "no");
    }
}
