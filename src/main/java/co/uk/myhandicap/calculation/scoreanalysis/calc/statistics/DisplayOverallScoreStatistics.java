package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Display Overall Score Statistics for a User
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
@Component
public class DisplayOverallScoreStatistics {

    @Autowired
    private ScoreCardDao scoreCardDao;

    @Autowired
    private DisplayScoreStatisticsHelper displayOverallHelper;

    public PlayerScoreType execute(User user) {

        List<ScoreCard> scoreCardList = scoreCardDao.retrieveUserScoreCardById(user);
        List<HoleScoreType> holeScoreTypeList = displayOverallHelper.buildScoreTypeList();

        for(ScoreCard scoreCard : scoreCardList) {
            holeScoreTypeList = displayOverallHelper.processScoreCardData(holeScoreTypeList, scoreCard);
        }

        return buildTotals(holeScoreTypeList);
    }

    public PlayerScoreType buildTotals(List<HoleScoreType> holeScoreTypeList) {

        int eagle = 0;
        int birdie = 0;
        int par = 0;
        int bogey = 0;
        int double_bogey = 0;
        int triple_bogey = 0;
        int other = 0;

        for(HoleScoreType hS : holeScoreTypeList) {
            eagle += hS.getEagle();
            birdie += hS.getBirdie();
            par += hS.getPar();
            bogey += hS.getBogey();
            double_bogey += hS.getDoubleBogey();
            triple_bogey += hS.getTripleBogey();
            other += hS.getOther();
        }

        return new PlayerScoreType(eagle, birdie, par, bogey, double_bogey, triple_bogey, other);
    }

}
