package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AbstractController;
import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.controllers.IAppFormController;
import main.java.co.uk.myhandicap.form.DisableUserDto;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Disable a User Account
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class DisableAccountController extends AbstractController
        implements IAppController, IAppFormController<DisableUserDto> {

    @Autowired
    private UserService userService;

    private static final String VIEW_NAME = "myAccount/deleteAccount";

    /**
     * handleRequest for MyAccount/DeleteUserAccount (GET).
     *
     * @param mav
     * @param principal
     * @return
     */
    @Override
    @RequestMapping(value="/disableUserAccount")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {

        // retrieve the user
        User user = retrieveUser(principal.getName());

        // add default model objects
        mav.addObject(new DisableUserDto());
        mav.addObject(user);

        // set view name to myAccount/deleteAccount
        mav.setViewName(VIEW_NAME);

        return mav;
    }

    /**
     * submitFormRequest for MyAccount/DeleteUserAccount (POST).
     *
     * @param mav
     * @param form
     * @param errors
     * @return
     */
    @Override
    @RequestMapping(value="/disableUserAccount/update", method = RequestMethod.POST)
    public ModelAndView submitFormRequest(ModelAndView mav, DisableUserDto form, BindingResult errors) {

        // if the user has opted to disable the user, update database table
        if(form.getDisableUser().equals("Y")) {
            userService.disableUserAccount(form.getUsername());
        }

        // set view name to myAccount/deleteAccount
        mav.setViewName(VIEW_NAME);

        return mav;
    }

}
