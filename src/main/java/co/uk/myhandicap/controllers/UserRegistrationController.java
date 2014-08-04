package main.java.co.uk.myhandicap.controllers;

import main.java.co.uk.myhandicap.form.UserRegistrationDto;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

/**
 * User Registration Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/08/2014
 * @project MyHandicapApp
 */
@Controller
public class UserRegistrationController implements AppFormController<UserRegistrationDto> {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register")
    public ModelAndView displayUserRegistration(ModelAndView mav)  {

        mav.setViewName("registerUser");
        mav.addObject(new UserRegistrationDto());

        return mav;
    }

    @Override
    @RequestMapping(value="/registerUser", method = RequestMethod.POST)
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid UserRegistrationDto object, BindingResult errors) {

        if(errors.hasErrors()) {
            mav.addObject("failure", "failure message here");
        } else {

            User registerUser = new User();
            registerUser.setUsername(object.getUsername());
            registerUser.setFirstName(object.getFirstName());
            registerUser.setLastName(object.getLastName());
            registerUser.setEmail(object.getEmail());
            registerUser.setCreatedDate(new Date());
            registerUser.setPassword(object.getPassword());

            userService.save(registerUser);

            mav.addObject("success", "success message here");
        }

        return mav;
    }
}
