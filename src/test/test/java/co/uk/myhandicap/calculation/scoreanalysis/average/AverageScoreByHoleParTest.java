package test.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.AverageScoreByHolePar;
import main.java.co.uk.myhandicap.dao.HoleDao;
import main.java.co.uk.myhandicap.model.handicap.Hole;
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
    private HoleDao holeDao;

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnPar3ReturnValidAverageValue() {

        List<Hole> list = new ArrayList<Hole>();
        setupHoles(list, 18, "3");

        when(holeDao.retrieveHoleAverageByHolePar(user, "3")).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "3");
        assertThat(average, notNullValue());

    }

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnPar4ReturnValidAverageValue() {

        List list = new ArrayList();
        setupHoles(list, 36, "4");

        when(holeDao.retrieveHoleAverageByHolePar(user, "4")).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "4");
        assertThat(average, notNullValue());

    }

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnPar5ReturnValidAverageValue() {

        List list = new ArrayList();
        setupHoles(list, 12, "5");

        when(holeDao.retrieveHoleAverageByHolePar(user, "5")).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "5");
        assertThat(average, notNullValue());

    }

    @Test
    public void executeWithTwoGolfRoundsForAverageScoreOnInvalidParReturnValidAverageValue() {

        List list = new ArrayList();
        setupHoles(list, 0, "-");

        when(holeDao.retrieveHoleAverageByHolePar(user, "-")).thenReturn(list);

        String average = averageScoreByHolePar.execute(user, "-");
        assertThat(average, equalTo("0"));

    }

    private void setupHoles(List<Hole> list, int numberOfHoles, String par) {
        for(int i = 1; i <= numberOfHoles; i++) {

            Hole hole = new Hole();
            hole.setHolePar(par);
            hole.setHoleScore(generateRandomScore(3, 8));

            list.add(hole);
        }
    }

}
