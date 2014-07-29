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
 * My Account Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class MyAccountController implements AppController, AppFormController<User> {

    @Override
    @RequestMapping(value="/personalInformation")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myAccount/personal");

        mav.addObject(new User());

        return mav;
    }

    @Override
    @RequestMapping(value="/personalInformation/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid User user, BindingResult errors) {

        mav.setViewName("myAccount/personal");

        if(errors.hasErrors()) {
            mav.addObject("failure", "Personal Information Update Failed, correct errors and try again.");
        } else {
            mav.addObject("success", "Personal Information Successfully Updated.");
        }

        return mav;
    }

}
