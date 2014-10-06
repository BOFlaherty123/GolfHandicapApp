package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DisplayOverallScoreStatistics;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.HoleScoreType;
import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
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
 * Score Analysis for Overall Player Statistics
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/10/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/scoreAnalysis")
@SessionAttributes({"parValue", "yardageValue"})
public class ScoreAnalysisOverallController extends AbstractScoreAnalysisController implements IAppController {

    private static final String VIEW_NAME = "analysis/overallAnalysis";

    @Autowired
    private DisplayOverallScoreStatistics displayOverallScoreStatistics;

    @Override
    @RequestMapping(value="/overall")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);

        User user = null;

        try {
            user = userService.findUserByUsername(principal.getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        // Overall Hole by Hole Score Analysis for given User
        List<HoleScoreType> holeScoreTypeList = displayOverallScoreStatistics.execute(user);
        // add result set to model
        mav.addObject("courseStatistics", holeScoreTypeList);

        return mav;
    }

    @RequestMapping(value="averagePar/{parValue}")
    public ModelAndView averagePar(@PathVariable("parValue") String parValue, ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);
        // calculate and add returned average to model
        mav.addObject("avgByHolePar", calculateAverage("avgByHolePar", parValue, principal));

        return mav;
    }

    @RequestMapping(value="averageYardage/{yardageValue}")
    public ModelAndView averageYardage(@PathVariable("yardageValue") String yardageValue, ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);
        // calculate and add returned average to model
        mav.addObject("avgByHoleYardage", calculateAverage("avgByHoleYardage", yardageValue, principal));

        return mav;
    }

}
