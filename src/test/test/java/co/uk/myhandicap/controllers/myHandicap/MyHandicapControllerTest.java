package test.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.calculation.handicap.HandicapCalculation;
import main.java.co.uk.myhandicap.model.handicap.Handicap;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardService;
import main.java.co.uk.myhandicap.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * My Handicap Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/testContext.xml",
        "classpath:/main/webapp/WEB-INF/MyHandicapApp-servlet.xml"})
@WebAppConfiguration
public class MyHandicapControllerTest {

    private final DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");

    private MockMvc mockMvc;

    @Qualifier("handicapCalculation")
    @Autowired
    private HandicapCalculation handicapCalculationMock;

    Principal principal = new Principal() {
        @Override
        public String getName() {
            return USER;
        }};

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreCardService scoreCardService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String USER = "TEST_PRINCIPAL";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void myHandicapControllerHistoryRequestMapping() throws Exception {

        mockMvc.perform(get("/myHandicap/history").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/history"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/history.jsp"));

    }

    @Test
    public void handicapValueIsReturnedFromHandicapCalculationProcessor() throws Exception {

        String currentDate = fmt.print(new DateTime());

        User user = buildMockUser(21L);

        Handicap handicap = new Handicap();
        handicap.setCalculatedOn(currentDate);
        handicap.setHandicapScore("22");
        handicap.setNumberOfRounds("3");

        when(userService.findUserByUsername(USER)).thenReturn(user);
        when(handicapCalculationMock.calculateUserHandicapScore(21L)).thenReturn(handicap);

        mockMvc.perform(get("/myHandicap/history").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/history"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/history.jsp")
            )
                .andExpect(model().attribute("playerHandicap", handicap));
    }

    @Test
    public void noPlayerScoreCardsRetrieved() throws Exception {

        User user = buildMockUser(21L);

        List<ScoreCard> scoreCardList = new ArrayList<>();

        when(userService.findUserByUsername(USER)).thenReturn(user);
        when(handicapCalculationMock.calculateUserHandicapScore(21L)).thenReturn(new Handicap());
        when(scoreCardService.retrieveUserScoredCardsById(user)).thenReturn(scoreCardList);

        mockMvc.perform(get("/myHandicap/history").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/history"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/history.jsp")
                )
                .andExpect(model().attribute("playerScoreCards", hasSize(0)))
                .andExpect(model().attribute("noPlayerScoreCards", "No Player ScoreCards Found."));

    }

    @Test
    public void playerScoreCardsSuccessfullyRetrieved() throws Exception {

        User user = buildMockUser(21L);

        List<ScoreCard> scoreCardList = new ArrayList<ScoreCard>();

        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setId(1L);
        scoreCard.setPlayerId(21L);

        scoreCardList.add(scoreCard);

        when(userService.findUserByUsername(USER)).thenReturn(user);
        when(scoreCardService.retrieveUserScoredCardsById(user)).thenReturn(scoreCardList);
        when(handicapCalculationMock.calculateUserHandicapScore(21L)).thenReturn(new Handicap());

        mockMvc.perform(get("/myHandicap/history").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/history"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/history.jsp")
            )
                .andExpect(model().attribute("playerScoreCards", hasSize(1)));
    }

    private User buildMockUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername(USER);
        return user;
    }

}
