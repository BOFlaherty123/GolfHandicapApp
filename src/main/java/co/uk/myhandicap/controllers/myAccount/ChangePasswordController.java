package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.form.ChangePasswordDto;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * User Account Change Password
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class ChangePasswordController implements AppController, AppFormController<ChangePasswordDto> {

    @Override
    @RequestMapping(value="/changeAccountPassword")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myAccount/changePassword");

        mav.addObject(new ChangePasswordDto());

        // TODO - retrieve logged in user object
        User user = new User();
        user.setId(1L);
        user.setFirstName("Testing");
        user.setLastName("Mctester");

        mav.addObject(user);

        return mav;
    }

    @Override
    @RequestMapping(value="/changeAccountPassword/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid ChangePasswordDto changePassword,
                                          BindingResult errors) {

        if(errors.hasErrors()) {
            mav.addObject("status", "Please correct the error(s) and resubmit the form.");
        } else {

            // save password

        }

        mav.setViewName("myAccount/changePassword");

        return mav;
    }
}
