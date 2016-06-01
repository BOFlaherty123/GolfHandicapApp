package test.test.java.co.uk.myhandicap.calculation.scorestatistics;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DetermineTypeOfScore;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DisplayOverallScoreStatistics;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.DisplayScoreStatisticsHelper;
import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics.HoleScoreType;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Display Overall Score Statistics Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 06/10/2014
 * @project MyHandicapApp
 */
public class DisplayOverallScoreStatisticsTest {

    @InjectMocks
    private DisplayOverallScoreStatistics displayOverallScoreStatistics = new DisplayOverallScoreStatistics();

    @Mock
    private ScoreCardDao dao;

    @Mock
    private User user;

    @Mock
    private DetermineTypeOfScore determineTypeOfScore;

    @Mock
    private UserService userService;

    @Mock
    private DisplayScoreStatisticsHelper helper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testScoreCardDaoIsCalled() throws UserNotFoundException {

        displayOverallScoreStatistics.execute(user);

        verify(dao, times(1)).retrieveUserScoreCardById(Matchers.<User>any());
    }

    @Test
    public void testDetermineTypeOfScoreIsCalled() {

        displayOverallScoreStatistics.execute(user);

        verify(determineTypeOfScore, times(1)).execute(Matchers.<HoleScoreType>any(), Matchers.<String>any(), Matchers.<String>any());

    }

    @Test
    public void testInitialSetupOfScoreTypeListIsValid() {

        displayOverallScoreStatistics.execute(user);
        verify(helper, times(1)).buildScoreTypeList();

    }

}
