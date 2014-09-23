package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.controllers.IAppFormController;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Delete User Account
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class DeleteAccountController implements IAppController, IAppFormController<User> {

    /**
     * handleRequest for MyAccount/DeleteUserAccount (GET).
     *
     * @param mav
     * @param principal
     * @return
     */
    @Override
    @RequestMapping(value="/deleteUserAccount")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {
        mav.setViewName("myAccount/deleteAccount");
        return mav;
    }

    /**
     * submitFormRequest for MyAccount/DeleteUserAccount (POST).
     *
     * @param mav
     * @param user
     * @param errors
     * @return
     */
    @Override
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid User user, BindingResult errors) {
        return null;
    }

}
