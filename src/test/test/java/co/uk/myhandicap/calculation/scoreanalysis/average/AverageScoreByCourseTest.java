package test.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.average.AverageScoreByCourse;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/09/2014
 * @project MyHandicapApp
 */
@RunWith(MockitoJUnitRunner.class)
public class AverageScoreByCourseTest {

    @InjectMocks
    private AverageScoreByCourse averageScoreByCourse = new AverageScoreByCourse();

    @Mock
    private User user;

    @Mock
    private ScoreCardDao scoreCardDao;

    @Test
    public void test() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveUserScoreCardById(user)).thenReturn(list);

        String average = averageScoreByCourse.execute(user, "Course Name Here");
        assertThat(average, notNullValue());
    }

    @Test
    public void executeWithNullUserObjectDefaultReturnValueOf0Returned() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveUserScoreCardById(null)).thenReturn(null);

        String average = averageScoreByCourse.execute(user, "Course Name Here");
        assertThat(average, equalTo("0"));
    }

    public ScoreCard addScoreCard(Long playerId, List<Round> golfRounds, int timesPlayed) {

        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setPlayerId(playerId);
        scoreCard.setSubmittedDate(new Date().toString());

        scoreCard.setGolfRounds(addGolfRounds(golfRounds, timesPlayed));

        return scoreCard;
    }

    private List<Round> addGolfRounds(List<Round> golfRounds, int timesPlayed) {

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

    private List<Hole> addHoleScores() {

        List<Hole> holeScores = new ArrayList<>();

        for(int i = 1; i <= 18; i++) {

            Hole hole = new Hole();
            hole.setHoleScore(generateRandomScore());

            holeScores.add(hole);
        }

        return holeScores;

    }

    private String generateRandomScore() {
        int min = 3; int max = 9;

        Random r = new Random();

        return String.valueOf(r.nextInt(max - min + 1) + min);
    }

}
