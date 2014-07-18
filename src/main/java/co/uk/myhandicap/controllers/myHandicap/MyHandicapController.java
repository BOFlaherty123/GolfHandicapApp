package main.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.calculation.HandicapCalculation;
import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.Handicap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * My Handicap Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myHandicap")
public class MyHandicapController implements AppController {

    @Autowired
    private HandicapCalculation handicapCalculation;

    @Override
    @RequestMapping(value="/history")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myHandicap/history");

        try {
            Handicap handicap = handicapCalculation.calculateUserHandicapScore(21L);
            System.out.println(handicap);

            mav.addObject("playerHandicap", handicap);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return mav;
    }

}
