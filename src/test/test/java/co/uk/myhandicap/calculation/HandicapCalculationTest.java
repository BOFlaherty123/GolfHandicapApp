package test.java.co.uk.myhandicap.calculation;

import main.java.co.uk.myhandicap.calculation.HandicapCalculation;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.Handicap;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.handicap.Round;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardServiceImpl;
import main.java.co.uk.myhandicap.service.UserServiceImpl;
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

    @InjectMocks
    private HandicapCalculation processor;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ScoreCardServiceImpl scoreCardService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void exceptionThrowForAnInvalidUserId() throws UserNotFoundException {

        when(userService.retrieveUserById(-1L)).thenReturn(null);

        expectedException.expect(UserNotFoundException.class);

        processor.calculateUserHandicapScore(-1L);

    }

    @Test
    public void defaultHandicapSetIfTheUserHasNotSubmittedAnyRoundsOfGolf() throws UserNotFoundException {

        User user = buildMockUser(1L);

        when(userService.retrieveUserById(user.getId())).thenReturn(user);

        List<ScoreCard> scoreCardList = new ArrayList();

        ScoreCard scoreCard = buildMockScoreCard();
        scoreCardList.add(scoreCard);

        when(scoreCardService.retrieveUserScoredCardsById(user)).thenReturn(scoreCardList);

        Handicap playerHandicap = processor.calculateUserHandicapScore(1L);

        verify(scoreCardService, times(1)).retrieveUserScoredCardsById(Matchers.<User>any());
        assertEquals("17/07/2014", playerHandicap.getCalculatedOn());
        assertEquals("28", playerHandicap.getHandicapScore());

    }

    @Test
    public void userHandicapSuccessfullyReturnedWithAHandicapAndAllParameters() throws UserNotFoundException {

        User user = buildMockUser(1L);

        when(userService.retrieveUserById(user.getId())).thenReturn(user);

        Handicap playerHandicap = processor.calculateUserHandicapScore(1L);
        assertEquals("17/07/2014", playerHandicap.getCalculatedOn());
        assertEquals("20", playerHandicap.getHandicapScore());
        assertEquals("5", playerHandicap.getNumberOfRounds());
    }

    private User buildMockUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    private ScoreCard buildMockScoreCard() {
        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setPlayerId(1L);
        scoreCard.setSubmittedDate("15/07/2014");

        List<Round> golfRounds = new ArrayList<>();
        golfRounds.add(buildMockGolfRound());

        scoreCard.setGolfRounds(golfRounds);

        return scoreCard;
    }

    private Round buildMockGolfRound() {
        Round round = new Round();
        round.setCourseName("Course Name");
        round.setCoursePar("72");
        round.setPlayDate("16/07/2014");

        List<Hole> holes = new ArrayList<>();
        holes.add(buildMockHole());

        round.setHoles(holes);

        return round;
    }

    private Hole buildMockHole() {
        Hole hole = new Hole();
        hole.setHolePar("4");
        hole.setHoleScore("3");
        hole.setHoleYards("240");
        hole.setHoleSSI("7");

        return hole;
    }

}
