package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.form.PersonalInformationDto;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static java.lang.String.format;

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

    public final XLogger logger = XLoggerFactory.getXLogger(MyAccountController.class
            .getName());

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(value="/personalInformation")
    public ModelAndView handleRequest(ModelAndView mav) {
        logger.entry(mav);

        mav.setViewName("myAccount/personal");

        // TODO - retrieve logged in user object
        mav.addObject(new User());
        mav.addObject(new PersonalInformationDto());

        logger.exit(mav);

        return mav;
    }

    @Override
    @RequestMapping(value="/personalInformation/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid PersonalInformationDto form, BindingResult errors) {
        logger.entry(mav, form, errors);

        mav.setViewName("myAccount/personal");

        if(errors.hasErrors()) {
            mav.addObject("status", "Personal Information Update Failed, correct errors and try again.");
            logger.info(format("method=[ .submitFormRequest() ] message=[ hasErrors() - %s triggered. ]", errors.getErrorCount()));
        } else {
            // TODO - get the current user
            // TODO - save the users form submission to database via the user service
            User user = new User();
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setEmail(form.getEmail());

            // update user
            userService.update(user);

            mav.addObject("status", "Personal Information Successfully Updated.");
        }

        logger.exit(mav);

        return mav;
    }

}