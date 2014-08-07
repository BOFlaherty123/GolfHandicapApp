package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.controllers.AbstractController;
import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.form.ChangePasswordDto;
import main.java.co.uk.myhandicap.model.user.User;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
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
public class ChangePasswordController extends AbstractController implements AppController, AppFormController<ChangePasswordDto> {

    private final XLogger logger = initiateXLoggerInstance(ChangePasswordController.class.getName());

    @Override
    protected XLogger initiateXLoggerInstance(String className) {
        return XLoggerFactory.getXLogger(className);
    }

    @Override
    @RequestMapping(value="/changeAccountPassword")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myAccount/changePassword");

        // retrieve the user
        User user = retrieveUserFromSecurityContext();
        mav.addObject(user);

        // TODO Map User to ChangePasswordDTO
        mav.addObject(new ChangePasswordDto());

        return mav;
    }

    @Override
    @RequestMapping(value="/changeAccountPassword/update")
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid ChangePasswordDto changePassword,
                                          BindingResult errors) {

        if(errors.hasErrors()) {
            mav.addObject("status", "Please correct the error(s) and resubmit the form.");
        } else {
            // TODO - save password using the userService
        }

        mav.setViewName("myAccount/changePassword");

        return mav;
    }

}
