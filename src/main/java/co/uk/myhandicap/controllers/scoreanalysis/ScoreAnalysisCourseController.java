package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DisplayScoreStatisticsByCourse;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.HoleScoreType;
import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Score Analysis for Course Specific Player Statistics
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 06/08/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/scoreAnalysis")
public class ScoreAnalysisCourseController extends AbstractScoreAnalysisController implements IAppController {

    // best/worst holes on courses played more than once.
    @Autowired
    private ScoreCardDao scoreCardDao;
    @Autowired
    private DisplayScoreStatisticsByCourse statisticsByCourse;

    private static final String GOLF_COURSE_NAMES_ATTR = "golfCourseNames";
    private static final String VIEW_NAME = "analysis/courseAnalysis";

    @Override
    @RequestMapping(value="/courseName")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);

        // retrieve golf courses the user has played
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

        return mav;
    }

    @RequestMapping(value="/averageCourseName/{userInput}")
    public ModelAndView averageByCourseName(@PathVariable("userInput") String userInput,
                                            ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);

        // calculate and add returned average to model
        mav.addObject("avgByCourseName", calculateAverage("avgByCourse", userInput, principal));

        // retrieve golf courses the user has played
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

        // retrieve the user
        User user = null;
        try {
            user = userService.findUserByUsername(principal.getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        // Hole by Hole Score Analysis (total)
        List<HoleScoreType> holeScoreTypeList = statisticsByCourse.execute(userInput, user);
        // add result set to model
        mav.addObject("courseStatistics", holeScoreTypeList);

        return mav;
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

}
