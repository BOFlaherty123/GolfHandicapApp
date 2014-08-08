package main.java.co.uk.myhandicap.controllers;

import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/07/14
 * @project MyHandicapApp
 */
public interface AppController {

    ModelAndView handleRequest(ModelAndView mav, Principal principal);

}
