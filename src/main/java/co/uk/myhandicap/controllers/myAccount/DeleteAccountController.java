package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Delete User Account
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class DeleteAccountController implements AppController, AppFormController<User> {

    @Override
    @RequestMapping(value="/deleteUserAccount")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myAccount/deleteAccount");
        return mav;
    }

    @Override
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid User user, BindingResult errors) {
        return null;

    }
}
