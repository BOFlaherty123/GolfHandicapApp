package main.java.co.uk.myhandicap.controllers;

import main.java.co.uk.myhandicap.encryption.EncryptUserPassword;
import main.java.co.uk.myhandicap.form.UserRegistrationDto;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

import static java.lang.String.format;

/**
 * User Registration Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/08/2014
 * @project MyHandicapApp
 */
@Controller
public class UserRegistrationController implements AppController, AppFormController<UserRegistrationDto> {

    private final XLogger logger = XLoggerFactory.getXLogger(UserRegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptUserPassword encryptUserPassword;

    @Autowired
    private Mapper mapper;

    @Value("${registerUser.success}")
    private String successMessage;

    @Value("${registerUser.failure}")
    private String failureMessage;

    @Override
    @RequestMapping(value="/register")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {
        mav.setViewName("registerUser");
        mav.addObject(new UserRegistrationDto());

        return mav;
    }

    @Override
    @RequestMapping(value="/registerUser", method = RequestMethod.POST)
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid UserRegistrationDto user, BindingResult errors) {
        logger.entry(mav, user, errors);

        if(errors.hasErrors()) {
            mav.addObject("failure", failureMessage);
            logger.info(format("class=[ " + this.getClass().getName() + "] method=[ .submitFormRequest() ] message=[ hasErrors() - %s triggered. ]",
                    errors.getErrorCount()));

        } else {

            // Map an instance of UserRegistrationDto to the User domain user
            User registerUser = mapper.map(user, User.class);
            registerUser.setCreatedDate(new Date());

            // Encrypt the users password
            String password = encryptUserPassword.encryptPassword(user.getPassword());
            registerUser.setPassword(password);

            // Save User to database
            userService.save(registerUser);


            mav.addObject("success", successMessage);
        }

        logger.exit(mav);

        return mav;
    }

}