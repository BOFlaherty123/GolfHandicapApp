package test.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/09/2014
 * @project MyHandicapApp
 */
public class AverageScoreTest {

    protected ScoreCard addScoreCard(Long playerId, List<Round> golfRounds, int timesPlayed) {

        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setPlayerId(playerId);
        scoreCard.setSubmittedDate(new Date().toString());

        scoreCard.setGolfRounds(addGolfRounds(golfRounds, timesPlayed));

        return scoreCard;
    }

    protected List<Round> addGolfRounds(List<Round> golfRounds, int timesPlayed) {

        for(int i = 1; i <= timesPlayed; i++) {

            Round golfRound = new Round();
            golfRound.setCourseName("Course Name Here");
            golfRound.setPlayDate("10/10/1985");
            golfRound.setCourseSSS("SSS");
            golfRound.setCoursePar("72");
            golfRound.setHoles(addHoleScores());

            golfRounds.add(golfRound);

        }

        return golfRounds;
    }

    protected List<Hole> addHoleScores() {

        List<Hole> holeScores = new ArrayList<Hole>();

        for(int i = 1; i <= 18; i++) {

            Hole hole = new Hole();
            hole.setHoleScore(generateRandomScore(3, 8));
            hole.setHolePar(generateRandomScore(3, 5));

            holeScores.add(hole);
        }

        return holeScores;

    }

    protected String generateRandomScore(int min, int max) {
        Random r = new Random();

        return String.valueOf(r.nextInt(max - min + 1) + min);
    }

}
