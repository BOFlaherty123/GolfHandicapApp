package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.controllers.IAppFormController;
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
public class ChangePasswordController implements IAppController, IAppFormController<ChangePasswordDto> {

    private final XLogger logger = XLoggerFactory.getXLogger(ChangePasswordController.class);

    @Value("${logging.info}")
    private String logInfoMsg;

    @Value("${exception.userNotFound}")
    private String userNotFoundException;

    @Value("${myAccount.changePassword.failure}")
    private String failureMessage;

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptUserPassword encryptUserPassword;

    /**
     * handleRequest for MyAccount/ChangePassword (GET).
     *
     * @param mav
     * @param principal
     * @return
     */
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

    /**
     * submitFormRequest for MyAccount/ChangePassword (POST).
     *
     * @param mav
     * @param changePassword
     * @param errors
     * @return
     */
    @Override
    @RequestMapping(value="/changeAccountPassword/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid ChangePasswordDto changePassword,
                                          BindingResult errors) {

        if(errors.hasErrors()) {
            mav.addObject("status", failureMessage);
            logger.info(format(logInfoMsg, this.getClass().getName(), ".submitFormRequest()", format("%s errors triggered", errors.getErrorCount())));

        } else {

            User user = userService.retrieveUserFromSecurityContext();

            // Encrypt the users password
            String password = encryptUserPassword.encryptPassword(changePassword.getPassword());

            user.setPassword(password);

            userService.update(user);
        }

        mav.setViewName("myAccount/changePassword");

        return mav;
    }

}
