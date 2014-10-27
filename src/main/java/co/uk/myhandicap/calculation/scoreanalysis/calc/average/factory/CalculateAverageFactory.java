package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.factory;

import main.java.co.uk.myhandicap.model.user.User;

/**
 * A factory class to produce an average calculation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project MyHandicapApp
 */
public abstract class CalculateAverageFactory {

    public String requestAverage(User user,String userReq, String averageReq) {
        return  calculateAverage(user, userReq, averageReq);
    }

    protected abstract String calculateAverage(User user, String userReq, String averageReq);

}
