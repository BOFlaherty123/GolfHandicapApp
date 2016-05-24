package main.java.co.uk.myhandicap.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/07/14
 * @project MyHandicapApp
 */
public interface AppFormController<T> {

    ModelAndView submitFormRequest(ModelAndView mav, T object,  BindingResult errors);

}
