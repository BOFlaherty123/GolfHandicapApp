package main.java.co.uk.myhandicap.calculation.scoreanalysis.average.scorestatistics;

import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Display user score statistics by golf course.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
@Component
public class DisplayScoreStatisticsByCourse {

    private static final int MAX_HOLES_OF_GOLF = 18;

    @Autowired
    private ScoreCardDao scoreCardDao;

    @Autowired
    private DetermineTypeOfScore determineTypeOfScore;

    // for a particular course, display the users score by hole using the breakdown:
    // - number of birdies
    // - number of pars
    // - number of  bogeys
    // - number of double bogeys
    // - number of triple bogeys

    // additional variables
    // - number of times played the course

    /*
            eagle | birdie | par | bogey | double bogey | triple bogey |

        1 |   0  |    1    |  1   |  4   |      0       |      2       |
        2 |   0  |    0    |  1   |  5   |      1       |      2       |

       cont.
     */

    // 1. retrieve score cards for course xxxxx
    // 2. retrieve hole data
    // 3. compare hole par against user score. Calculate what type (birdie, par, bogey etc) of score the player
    // has achieved and keep running totals create object to store data? (18 x statisticHole objects in a list,
    // and keep a running total of the number of pars, birdies, bogeys etc)
    // 4. return list<object> containing the data to the controller for output in the model

    /**
     * execute method to process user score statistics for a particular golf course.
     *
     * @param courseName
     * @param user
     */
    public void execute(String courseName, User user) {

        // 1. retrieve score cards for course xxxxx
        List<ScoreCard> scoreCardList = scoreCardDao.retrieveScoreCardsByCourseName(user, courseName);

        // 2. retrieve hole data
        for(ScoreCard scoreCard : scoreCardList) {
            processScoreCardData(scoreCard);
        }

    }

    /**
     * loop through each ScoreCard and process the statistical data for each hole played.
     *
     * @param scoreCard
     */
    private void processScoreCardData(ScoreCard scoreCard) {

        List<HoleScoreType> holeScoreTypeList = buildScoreTypeList();

        int holeNo = 0;
        // extract and process each golf round within a scoreCard
        for(Round golfRound : scoreCard.getGolfRounds()) {
            for(Hole hole : golfRound.getHoles()) {
                // determine the users score type for each hole played
                buildStatisticalDataPerHole(holeNo, holeScoreTypeList, hole);
                holeNo++;
            }
        }

    }

    /**
     * build initial list of HoleScoreType objects (18).
     *
     * @return
     */
    private List<HoleScoreType> buildScoreTypeList() {

        List<HoleScoreType> holeScoreTypeList = new ArrayList();

        // create 18 new holeScoreType objects
        for(int i = 1; i <= MAX_HOLES_OF_GOLF; i++) {
          holeScoreTypeList.add(new HoleScoreType.HoleScoreTypeBuilder(i).build());
        }

        return holeScoreTypeList;
    }

    /**
     * update number of score type per hole based on the number of times the course has been played.
     *
     * @param holeNo
     * @param hole
     */
    private void buildStatisticalDataPerHole(int holeNo, List<HoleScoreType> holeScoreTypeList, Hole hole) {

        // retrieve HoleScoreType object for corresponding hole object
        if(holeNo < MAX_HOLES_OF_GOLF) {

            // retrieve holeScoreType object for a given hole
            HoleScoreType holeScoreType = holeScoreTypeList.get(holeNo);

            // create a new object/helper class to determine what type of score the user achieved
            determineTypeOfScore.execute(holeScoreType, hole.getHolePar(), hole.getHoleScore());

        }

    }

}
