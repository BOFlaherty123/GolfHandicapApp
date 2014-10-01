package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.controllers.IAppController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Score Analysis for Overall Player Statistics
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/10/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/scoreAnalysis")
public class ScoreAnalysisOverallController extends AbstractScoreAnalysisController implements IAppController {

    private static final String VIEW_NAME = "analysis/overallAnalysis";

    @Override
    @RequestMapping(value="/overall")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);

        return mav;
    }

    @RequestMapping(value="averagePar/{userInput}")
    public ModelAndView averagePar(@PathVariable("userInput") String userInput, ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);
        // calculate and add returned average to model
        mav.addObject("avgByHolePar", calculateAverage("avgByHolePar", userInput, principal));

        return mav;
    }

    @RequestMapping(value="averageYardage/{userInput}")
    public ModelAndView averageYardage(@PathVariable("userInput") String userInput, ModelAndView mav, Principal principal) {

        // set view name
        mav = new ModelAndView(VIEW_NAME);
        // calculate and add returned average to model
        mav.addObject("avgByHoleYardage", calculateAverage("avgByHoleYardage", userInput, principal));

        return mav;
    }

}
