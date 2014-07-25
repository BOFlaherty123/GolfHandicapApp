package main.java.co.uk.myhandicap.controllers;

import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.model.user.UserRole;
import main.java.co.uk.myhandicap.model.user.address.HomeAddress;
import main.java.co.uk.myhandicap.model.user.address.WorkAddress;
import main.java.co.uk.myhandicap.service.ScoreCardServiceImpl;
import main.java.co.uk.myhandicap.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Welcome Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/07/14
 * @project MyHandicapApp
 */
@Controller
public class WelcomeController implements AppController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    /**
     * Display the 'welcome' page to the application
     *
     * @return
     */
    @Override
    @RequestMapping(value="/")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("welcome");
        return mav;
    }

    /**
     * Save a new user to the database
     *
     * @return
     */
    @RequestMapping(value="/saveUser")
    public ModelAndView saveUser() {

        // Mockup and Save a User

        User user = new User();
        user.setCreatedDate(new Date());
        user.setUsername("BOFlaherty");
        user.setEmail("benjamin@oflaherty.co.uk");
        user.setFirstName("Benjamin");
        user.setLastName("OFlaherty");
        user.setPassword("qwerty12");

        // addToScore UserRoles collection (Set<UserRole)
        UserRole role1 = new UserRole();
        role1.setAssignedDate(new Date());
        role1.setRole("ROLE_USER");

        UserRole role2 = new UserRole();
        role2.setAssignedDate(new Date());
        role2.setRole("ROLE_ADMIN");

        user.getUserRoles().add(role1);
        user.getUserRoles().add(role2);

        HomeAddress homeAddress = new HomeAddress();
        homeAddress.setAddress1("131 The Lock, Building 72, London");
        homeAddress.setPostCode("E15 2QG");
        homeAddress.setHomeTelephone("02085550707");

        WorkAddress workAddress = new WorkAddress();
        workAddress.setAddress1("28 Dingwall Road, Croydon");
        workAddress.setPostCode("CR0 2NH");
        workAddress.setWorkTelephone("02082564881");
        workAddress.setWorkFaxNumber("00000000000");

        user.getAddress().add(homeAddress);
        user.getAddress().add(workAddress);

        userService.save(user);

        return new ModelAndView("welcome");
    }

    /**
     * Retrieve a user from the database
     *
     * @param mav
     * @return
     */
    @RequestMapping(value="/retrieveUser")
    public ModelAndView getUser(ModelAndView mav) {

        User user = userService.retrieveUserById(1L);

        mav.setViewName("retrieveUser");

        if(user != null) {
            mav.addObject(user);
        }

        return mav;
    }

}