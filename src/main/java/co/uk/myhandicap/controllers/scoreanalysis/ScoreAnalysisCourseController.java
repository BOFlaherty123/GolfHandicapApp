package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DisplayScoreStatisticsByCourse;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.HoleScoreType;
import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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
@SessionAttributes("courseName")
public class ScoreAnalysisCourseController extends AbstractScoreAnalysisController implements AppController {

    @Autowired
    private ScoreCardDao scoreCardDao;
    @Autowired
    private DisplayScoreStatisticsByCourse statisticsByCourse;

    private static final String GOLF_COURSE_NAMES_ATTR = "golfCourseNames";
    private static final String VIEW_NAME = "analysis/courseAnalysis";

    @Override
    @RequestMapping(value="/courseName")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {

        mav.setViewName(VIEW_NAME);
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

        return mav;
    }

    @RequestMapping(value="/averageCourseName/{courseName}")
    public ModelAndView averageByCourseName(@PathVariable("courseName") String courseName,
                                            ModelAndView mav, Principal principal) {

        mav.setViewName(VIEW_NAME);;
        mav.addObject("avgByCourseName", calculateAverage("avgByCourse", courseName, principal));
        mav.addObject(GOLF_COURSE_NAMES_ATTR, retrieveGolfCourseNamesForUser(principal));

        User user = retrieveUser(principal.getName());

        List<HoleScoreType> holeScoreTypeList = statisticsByCourse.execute(courseName, user);
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
        return scoreCardDao.retrieveAllGolfCourseNamesForUserByScoreCard(retrieveUser(principal.getName()));
    }

}
