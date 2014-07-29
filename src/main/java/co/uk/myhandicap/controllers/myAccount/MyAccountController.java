package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.form.PersonalInformationDto;
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
public class MyAccountController implements AppController, AppFormController<PersonalInformationDto> {

    @Override
    @RequestMapping(value="/personalInformation")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myAccount/personal");

        mav.addObject(new User());
        mav.addObject(new PersonalInformationDto());

        return mav;
    }

    @Override
    @RequestMapping(value="/personalInformation/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid PersonalInformationDto form, BindingResult errors) {

        mav.setViewName("myAccount/personal");

        if(errors.hasErrors()) {
            mav.addObject("status", "Personal Information Update Failed, correct errors and try again.");
        } else         {

            // save the users form submission to database via the user service

            mav.addObject("status", "Personal Information Successfully Updated.");
        }

        return mav;

    }

}
