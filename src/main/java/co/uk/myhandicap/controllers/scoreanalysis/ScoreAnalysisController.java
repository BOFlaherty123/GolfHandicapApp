package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Score Analysis Controller
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
        return new ModelAndView("analysis/scoreAnalysis");
    }

    @RequestMapping(value="scoreAnalysis/averageCourseName")
    public ModelAndView averageByCourseName(ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");
        // calculate and add returned average to model
        mav.addObject("avgByCourseName", calculateAverage("avgByCourse", "Rivenhall Oaks", principal));

        return mav;
    }

    @RequestMapping(value="/scoreAnalysis/averagePar/{userInput}")
    public ModelAndView averagePar(@PathVariable("userInput") String userInput, ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");
        // calculate and add returned average to model
        mav.addObject("avgByHolePar", calculateAverage("avgByHolePar", userInput, principal));

        return mav;
    }

    @RequestMapping(value="/scoreAnalysis/averageYardage/{userInput}")
    public ModelAndView averageYardage(@PathVariable("userInput") String userInput, ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");
        // calculate and add returned average to model
        mav.addObject("avgByHoleYardage", calculateAverage("avgByHoleYardage", userInput, principal));

        return mav;
    }

    /**
     * calculate average based on user type of average selection and value input.
     *
     * @param typeOfAvg
     * @param userInput
     * @param principal
     * @return
     */
    private String calculateAverage(String typeOfAvg, String userInput, Principal principal) {

        User user = null; String average = null;

        try {
            user = userService.findUserByUsername(principal.getName());
            // determine which average processor to use
            average = calculateRequestedAverage.process(typeOfAvg, user, userInput);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return average;
    }

}
