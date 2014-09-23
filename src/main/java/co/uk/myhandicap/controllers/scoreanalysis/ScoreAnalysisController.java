package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Score Analysis Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 06/08/2014
 * @project MyHandicapApp
 */
@Controller
public class ScoreAnalysisController implements IAppController {

    // best/worst holes on courses played more than once.
    @Autowired
    private UserService userService;
    @Autowired
    private CalculateRequestedAverage calculateRequestedAverage;
    @Autowired
    private ScoreCardDao scoreCardDao;

    private static final String GOLF_COURSE_NAMES_ATTR = "golfCourseNames";

    @Override
    @RequestMapping(value="/scoreAnalysis")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");

        // retrieve golf courses the user has played
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

        return mav;
    }

    @RequestMapping(value="/scoreAnalysis/averageCourseName/{userInput}")
    public ModelAndView averageByCourseName(@PathVariable("userInput") String userInput,
                                            ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");

        // calculate and add returned average to model
        mav.addObject("avgByCourseName", calculateAverage("avgByCourse", userInput, principal));

        // retrieve golf courses the user has played
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

        return mav;
    }

    @RequestMapping(value="/scoreAnalysis/averagePar/{userInput}")
    public ModelAndView averagePar(@PathVariable("userInput") String userInput, ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");
        // calculate and add returned average to model
        mav.addObject("avgByHolePar", calculateAverage("avgByHolePar", userInput, principal));

        // retrieve golf courses the user has played
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

        return mav;
    }

    @RequestMapping(value="/scoreAnalysis/averageYardage/{userInput}")
    public ModelAndView averageYardage(@PathVariable("userInput") String userInput, ModelAndView mav, Principal principal) {

        mav = new ModelAndView("analysis/scoreAnalysis");
        // calculate and add returned average to model
        mav.addObject("avgByHoleYardage", calculateAverage("avgByHoleYardage", userInput, principal));

        // retrieve golf courses the user has played
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

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
        return calculateRequestedAverage.process(typeOfAvg, retrieveUserByPrinciple(principal), userInput);
    }


    /**
     * retrieve all distinct golf course names that a user has submitted.
     *
     * @param principal
     * @return
     */
    private List<String> retrieveGolfCourseNamesForUser(Principal principal) {
        return scoreCardDao.retrieveAllGolfCourseNamesForUserByScoreCard(retrieveUserByPrinciple(principal));
    }

    /**
     * retrieve the user object by principle.
     *
     * @param principal
     * @return
     */
    private User retrieveUserByPrinciple(Principal principal) {

        User user = null;

        try {
            user = userService.findUserByUsername(principal.getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

}
