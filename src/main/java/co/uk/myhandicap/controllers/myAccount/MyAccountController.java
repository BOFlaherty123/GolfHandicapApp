package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.form.PersonalInformationDto;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.dozer.Mapper;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

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

    private static final XLogger logger = XLoggerFactory.getXLogger(MyAccountController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Value("${myAccount.personal.success}")
    private String successMessage;

    @Value("${myAccount.personal.failure}")
    private String failureMessage;

    @Override
    @RequestMapping(value="/personalInformation")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {
        logger.entry(mav);

        mav.setViewName("myAccount/personal");

        // retrieve the user
        User user = null;
        try {
            user = userService.findUserByUsername(principal.getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        // Map a user object to personal information dto to populate the screen
        PersonalInformationDto userInfo = mapper.map(user, PersonalInformationDto.class);
        mav.addObject(userInfo);

        logger.exit(mav);

        return mav;
    }

    @Override
    @RequestMapping(value="/personalInformation/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid PersonalInformationDto form, BindingResult errors) {
        logger.entry(mav, form, errors);

        mav.setViewName("myAccount/personal");

        if(errors.hasErrors()) {
            mav.addObject("failure", failureMessage);
            logger.info(format("class=[ " + this.getClass().getName() + "] method=[ .submitFormRequest() ] message=[ hasErrors() - %s triggered. ]", errors.getErrorCount()));
        } else {

            User user = userService.retrieveUserById(form.getId());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setEmail(form.getEmail());

            // update user
            userService.update(user);

            mav.addObject("success", successMessage);
        }

        logger.exit(mav);

        return mav;
    }

}