package main.java.co.uk.myhandicap.controllers;

import main.java.co.uk.myhandicap.form.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/08/2014
 * @project MyHandicapApp
 */
@Controller
public class UserRegistration {

    @RequestMapping(value="/register")
    public ModelAndView displayUserRegistration(ModelAndView mav)  {

        mav.setViewName("registerUser");
        mav.addObject(new UserRegistrationDto());

        return mav;
    }

}
