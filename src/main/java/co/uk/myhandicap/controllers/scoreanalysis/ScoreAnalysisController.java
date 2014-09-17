package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 06/08/2014
 * @project MyHandicapApp
 */
@Controller
public class ScoreAnalysisController implements AppController {

    // average score (par 3, 4 & 5) - course, par, score(s), average, user/player
    // average score by length of hole - course, hole yardage, score(s), average, user/player
    // average score by golf course - course, course total(s), average, user/player
    // best/worst holes on courses played more than once.

    @Autowired
    private UserService userService;
    @Autowired
    private CalculateRequestedAverage calculateRequestedAverage;

    @Override
    @RequestMapping(value="/scoreAnalysis")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");

        User user = null;
        String average = null;
        try {
            user = userService.findUserByUsername(principal.getName());

            average = calculateRequestedAverage.process("avgByCourse", user, "Rivenhall Oaks");

        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        mav.addObject("avgByCourseName", average);

        return mav;
    }

    @RequestMapping(value="/scoreAnalysis/averagePar")
    public ModelAndView averagePar(ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");

        User user = null;
        String average = null;
        try {
            user = userService.findUserByUsername(principal.getName());

            average = calculateRequestedAverage.process("avgByHolePar", user, "3");

        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        mav.addObject("avgByHolePar", average);

        return mav;
    }

}
