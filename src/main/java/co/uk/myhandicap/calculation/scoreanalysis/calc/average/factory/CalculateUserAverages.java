package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.factory;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.product.AverageScoreByCourse;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.product.AverageScoreByHolePar;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.product.AverageScoreByHoleYardage;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Average By Score Factory
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project MyHandicapApp
 */
@Component
public class CalculateUserAverages extends CalculateAverageFactory {

    @Autowired
    private AverageScoreByHolePar averageScoreByHolePar;
    @Autowired
    private AverageScoreByCourse averageScoreByCourse;
    @Autowired
    private AverageScoreByHoleYardage averageScoreByHoleYardage;

    @Override
    public String calculateAverage(User user, String userReq, String averageReq) {
        return (userReq.equals("avgByHolePar")) ? averageScoreByHolePar.execute(user, averageReq) :
                (userReq.equals("avgByCourse")) ?  averageScoreByCourse.execute(user, averageReq) :
                        averageScoreByHoleYardage.execute(user, averageReq);
    }

}
