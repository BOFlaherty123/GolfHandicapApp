package test.java.co.uk.myhandicap.calculation.scorestatistics;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DetermineTypeOfScore;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DisplayScoreStatisticsByCourse;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
@RunWith(MockitoJUnitRunner.class)
public class DisplayScoreStatisticsByCourseTest {

    @InjectMocks
    private DisplayScoreStatisticsByCourse displayStatistics = new DisplayScoreStatisticsByCourse();

    @Mock
    private ScoreCardDao scoreCardDao;

    @Mock
    private User user;

    @Mock
    private DetermineTypeOfScore determineTypeOfScore;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void scoreCardsAreRetrievedByCourseNameProvidedByUser() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveScoreCardsByCourseName(Matchers.<User>any(), Matchers.<String>any())).thenReturn(list);

        displayStatistics.execute("courseName", user);

        // retrieve all score cards by course name
        verify(scoreCardDao, times(1)).retrieveScoreCardsByCourseName(Matchers.<User>any(), Matchers.<String>any());

    }

    // 1. retrieve score cards for course xxxxxxx
    // 2. retrieve hole data
    // 3. compare hole par against user score. Calculate what type (birdie, par, bogey etc) of score the player
    // has achieved and keep running totals create object to store data? (18 x statisticHole objects in a list,
    // and keep a running total of the number of pars, birdies, bogeys etc)
    // 4. return list<object> containing the data to the controller for output in the model


    // TODO - tidy up this code to use the same code as within AverageScoreTest (rename this class and extend)
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

        List<Hole> holeScores = new ArrayList<>();

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
