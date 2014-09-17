package test.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.average.AverageScoreByCourse;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
public class AverageScoreByCourseTest extends AverageScoreTest {

    @InjectMocks
    private AverageScoreByCourse averageScoreByCourse = new AverageScoreByCourse();

    @Mock
    private User user;

    @Mock
    private ScoreCardDao scoreCardDao;

    @Test
    public void executeWithTwoGolfRoundsOnTheSameCourseReturnValidAverageValue() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveScoreCardAverageByGolfCourse(user, "Course Name Here")).thenReturn(list);

        String average = averageScoreByCourse.execute(user, "Course Name Here");
        assertThat(average, notNullValue());
    }

    @Test
    public void executeWithNullUserObjectDefaultReturnValueOf0Returned() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveScoreCardAverageByGolfCourse(null, null)).thenReturn(null);

        String average = averageScoreByCourse.execute(user, "Course Name Here");
        assertThat(average, equalTo("0"));
    }

    @Test
    public void executeWithOneGolfRoundsWithAnInvalidCourseNameReturnValidAverageValue() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveScoreCardAverageByGolfCourse(user, "$%*$$%aaf^$*")).thenReturn(null);

        String average = averageScoreByCourse.execute(user, "$%*$$%aaf^$*");
        assertThat(average, equalTo("0"));
    }

}
