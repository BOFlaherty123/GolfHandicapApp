package test.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.AverageScoreByHoleYardage;
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
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

/**
 * Average Scoe By Hole Yardage Test case
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/09/2014
 * @project MyHandicapApp
 */
@RunWith(MockitoJUnitRunner.class)
public class AverageScoreByHoleYardageTest extends AverageScoreTest {

    @InjectMocks
    private AverageScoreByHoleYardage averageScoreByHoleYardage = new AverageScoreByHoleYardage();

    @Mock
    private User user;

    @Mock
    private HoleDao holeDao;

    @Test
    public void executeWith25HolesOfYardageBetween150And200ReturnValidAverageValue() {

        List<Hole> list = new ArrayList<Hole>();
        setupHoles(list, 25, 150, 200);

        when(holeDao.retrieveHoleAverageByHoleYardage(user, "155")).thenReturn(list);

        String average = averageScoreByHoleYardage.execute(user, "155");
        assertThat(average, notNullValue());

    }

    private void setupHoles(List<Hole> list, int numberOfHoles, int minYards, int maxYards) {
        for(int i = 1; i <= numberOfHoles; i++) {

            Hole hole = new Hole();
            hole.setHoleScore(generateRandomScore(3, 8));
            hole.setHoleYards(generateRandomScore(minYards, maxYards));

            list.add(hole);
        }
    }


}
