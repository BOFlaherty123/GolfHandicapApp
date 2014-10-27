package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.factory.CalculateUserAverages;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Calculate a user's requested average
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/09/2014
 * @project MyHandicapApp
 */
@Component
public class CalculateRequestedAverage {

    @Autowired
    private CalculateUserAverages factory;

    /**
     * determine which calculation class to use based on user selection
     *
     * @param userRequest
     * @param user
     * @param averageRequested
     * @return
     */
    public String processRequestedAverage(String userRequest, User user, String averageRequested) {
        return factory.requestAverage(user, userRequest, averageRequested);
    }

}
