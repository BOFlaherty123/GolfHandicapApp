package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for Display Score Statistics
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 06/10/2014
 * @project MyHandicapApp
 */
@Component
public class DisplayScoreStatisticsHelper {

    // TODO - convert to an enum? as per 'effective Java' book
    private static final int MAX_HOLES_OF_GOLF = 18;

    @Autowired
    private DetermineTypeOfScore determineTypeOfScore;

    private DisplayScoreStatisticsHelper() {}

    /**
     * loop through each ScoreCard and process the statistical data for each hole played.
     *
     * @param scoreCard
     */
    public List<HoleScoreType> processScoreCardData(List<HoleScoreType> holeScoreTypeList, ScoreCard scoreCard) {

        int holeNo = 0;
        // extract and process each golf round within a scoreCard
        for(Round golfRound : scoreCard.getGolfRounds()) {
            for(Hole hole : golfRound.getHoles()) {
                // determine the users score type for each hole played
                buildStatisticalDataPerHole(holeNo, holeScoreTypeList, hole);
                holeNo++;
            }
        }

        return holeScoreTypeList;
    }

    /**
     * update number of score type per hole based on the number of times the course has been played.
     *
     * @param holeNo
     * @param hole
     */
    public void buildStatisticalDataPerHole(int holeNo, List<HoleScoreType> holeScoreTypeList, Hole hole) {

        // retrieve HoleScoreType object for corresponding hole object
        if(holeNo < MAX_HOLES_OF_GOLF) {

            // retrieve holeScoreType object for a given hole
            HoleScoreType holeScoreType = holeScoreTypeList.get(holeNo);

            // create a new object/helper class to determine what type of score the user achieved
            determineTypeOfScore.execute(holeScoreType, hole.getHolePar(), hole.getHoleScore());

        }

    }

    /**
     * build initial list of HoleScoreType objects (18).
     *
     * @return
     */
    public List<HoleScoreType> buildScoreTypeList() {

        List<HoleScoreType> holeScoreTypeList = new ArrayList();

        // create 18 new holeScoreType objects
        for(int i = 1; i <= MAX_HOLES_OF_GOLF; i++) {
            holeScoreTypeList.add(new HoleScoreType.HoleScoreTypeBuilder(i).build());
        }

        return holeScoreTypeList;
    }

}
