package test.java.co.uk.myhandicap.calculation;

import main.java.co.uk.myhandicap.calculation.handicap.GolfRound;
import main.java.co.uk.myhandicap.calculation.handicap.Handicap;
import main.java.co.uk.myhandicap.calculation.handicap.HandicapCalculation;
import main.java.co.uk.myhandicap.calculation.handicap.helper.HandicapCalculationHelper;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardService;
import main.java.co.uk.myhandicap.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Handicap Calculation Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 16/07/14
 * @project MyHandicapApp
 */
@RunWith(MockitoJUnitRunner.class)
public class HandicapCalculationTest {

    private final DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");

    @InjectMocks
    private HandicapCalculation processor = new HandicapCalculation();

    @Mock
    private HandicapCalculationHelper handicapCalculationHelper;

    @Mock
    private UserService userService;

    @Mock
    private ScoreCardService scoreCardService;

    @Mock
    private GolfRound golfRound;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void exceptionThrowForAnInvalidUserId() throws UserNotFoundException {
//
//        when(userService.retrieveUserById(-1L)).thenReturn(null);
//
//        expectedException.expect(UserNotFoundException.class);
//
//        processor.calculateUserHandicapScore(-1L);
//
//    }

    @Test
    public void defaultHandicapSetIfTheUserHasNotSubmittedAnyRoundsOfGolf() throws UserNotFoundException {

        User user = buildMockUser(1L);

        List<ScoreCard> scoreCardList = new ArrayList();

        // setup mock scoreCard
        ScoreCard scoreCard = setupScoreCard(1L, 2L, "10/10/1985");

        // setup mock list of golf rounds
        List<Round> listOfRounds = new ArrayList<Round>();
        listOfRounds.add(setupGolfRound(scoreCard, 1L, "24/09/2014", "72", "72"));

        // add mocked rounds of golf to scoreCard
        scoreCard.setGolfRounds(listOfRounds);

        // add mocked scoreCard to list
        scoreCardList.add(scoreCard);

        when(userService.retrieveUserById(anyLong())).thenReturn(user);
        when(scoreCardService.retrieveUserScoredCardsById(user)).thenReturn(scoreCardList);
        when(handicapCalculationHelper.extractRoundsOfGolfFromScoreCard(scoreCardList)).thenReturn(listOfRounds);

        Handicap handicapMock = mockPlayerHandicap("28", listOfRounds);

        when(handicapCalculationHelper.setupDefaultHandicap()).thenReturn(handicapMock);
        when(handicapCalculationHelper.calculateHandicap(anyInt(), (BigDecimal) any())).thenReturn(handicapMock.getHandicapScore());

        Handicap playerHandicap = processor.calculateUserHandicapScore(1L);

        verify(scoreCardService, times(1)).retrieveUserScoredCardsById(Matchers.<User>any());
        assertEquals(fmt.print(new DateTime()), playerHandicap.getCalculatedOn());
        assertEquals("28", playerHandicap.getHandicapScore());

    }


    private ScoreCard setupScoreCard(long id, long playerId, String submissionDate) {

        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setId(id);
        scoreCard.setPlayerId(playerId);
        scoreCard.setSubmittedDate(submissionDate);

        return scoreCard;
    }

    private Round setupGolfRound(ScoreCard scoreCard, long roundId, String dateOfPlay, String coursePar, String courseSSS) {

        Round golfRound = new Round();
        golfRound.setId(2L);
        golfRound.setPlayDate("24/09/20140");
        golfRound.setCoursePar("72");
        golfRound.setCourseSSS("72");

        return golfRound;

    }

    private Handicap mockPlayerHandicap(String handicap, List<Round> listOfRounds) {
        return new Handicap.HandicapBuilder(fmt.print(new DateTime()), handicap)
                .withNumberOfRounds(String.valueOf(listOfRounds.size())).build();
    }


    private User buildMockUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

}
