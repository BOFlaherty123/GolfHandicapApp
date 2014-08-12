package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AbstractController;
import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.encryption.EncryptUserPassword;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.form.ChangePasswordDto;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
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
 * User Account Change Password
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class ChangePasswordController extends AbstractController implements AppController, AppFormController<ChangePasswordDto> {

    private final XLogger logger = initiateXLoggerInstance(ChangePasswordController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptUserPassword encryptUserPassword;

    @Value("${exception.userNotFound}")
    private String userNotFoundException;

    @Value("${myAccount.changePassword.failure}")
    private String failureMessage;

    @Override
    protected XLogger initiateXLoggerInstance(String className) {
        return XLoggerFactory.getXLogger(className);
    }

    @Override
    @RequestMapping(value="/changeAccountPassword")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {
        mav.setViewName("myAccount/changePassword");

        // retrieve the user
        User user = null;
        try {
            user = userService.findUserByUsername(principal.getName());
        } catch (UserNotFoundException e) {
           logger.error(format(userNotFoundException, principal.getName()));
        }

        mav.addObject(user);
        mav.addObject(new ChangePasswordDto());

        return mav;
    }

    @Override
    @RequestMapping(value="/changeAccountPassword/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid ChangePasswordDto changePassword,
                                          BindingResult errors) {

        if(errors.hasErrors()) {
            mav.addObject("status", failureMessage);
        } else {
            User user = retrieveUserFromSecurityContext();

            // Encrypt the users password
            String password = encryptUserPassword.encryptPassword(changePassword.getPassword());
            user.setPassword(password);

            userService.update(user);
        }

        mav.setViewName("myAccount/changePassword");

        return mav;
    }

}
