package main.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
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
public abstract class AbstractScoreAnalysisController {

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
        return calculateRequestedAverage.process(typeOfAvg, retrieveUserByPrinciple(principal), userInput);
    }

    /**
     * retrieve the user object by principle.
     *
     * @param principal
     * @return
     */
    protected User retrieveUserByPrinciple(Principal principal) {

        User user = null;

        try {
            user = userService.findUserByUsername(principal.getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException np) {
            np.printStackTrace();
        }

        return user;
    }

}
