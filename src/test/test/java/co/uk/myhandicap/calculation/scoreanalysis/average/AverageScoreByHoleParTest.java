package test.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.average.AverageScoreByHolePar;
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
public class AverageScoreByHoleParTest extends AverageScoreTest {

    @InjectMocks
    private AverageScoreByHolePar averageScoreByHolePar = new AverageScoreByHolePar();

    @Mock
    private User user;

    @Mock
    private ScoreCardDao scoreCardDao;

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnPar3ReturnValidAverageValue() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveUserScoreCardById(user)).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "3");
        assertThat(average, notNullValue());

    }

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnPar4ReturnValidAverageValue() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveUserScoreCardById(user)).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "4");
        assertThat(average, notNullValue());

    }

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnPar5ReturnValidAverageValue() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveUserScoreCardById(user)).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "5");
        assertThat(average, notNullValue());

    }

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnPar2ReturnValidAverageValue() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 2));

        when(scoreCardDao.retrieveUserScoreCardById(user)).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "2");
        assertThat(average, equalTo("0"));

    }

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnInvalidParReturnValidAverageValue() {

        List<ScoreCard> list = new ArrayList<>();
        list.add(addScoreCard(1L, new ArrayList<Round>(), 1));

        when(scoreCardDao.retrieveUserScoreCardById(user)).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "-");
        assertThat(average, equalTo("0"));

    }

}
