package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.controllers.AbstractController;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

/**
 * Abstract Score Analysis Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/10/2014
 * @project MyHandicapApp
 */
public abstract class AbstractScoreAnalysisController extends AbstractController {

    @Autowired
    protected UserService userService;
    @Autowired
    protected CalculateRequestedAverage calculateRequestedAverage;

    /**
     * calculate average based on user type of average selection and value input.
     *
     * @param typeOfAvg
     * @param userInput
     * @param principal
     * @return
     */
    protected String calculateAverage(String typeOfAvg, String userInput, Principal principal) {
        return calculateRequestedAverage.processRequestedAverage(typeOfAvg, retrieveUser(principal.getName()), userInput);
    }

}
