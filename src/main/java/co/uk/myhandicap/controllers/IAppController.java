package main.java.co.uk.myhandicap.controllers;

import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Interface for common method for all controllers
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/07/14
 * @project MyHandicapApp
 */
public interface IAppController {

    ModelAndView handleRequest(ModelAndView mav, Principal principal);

}
