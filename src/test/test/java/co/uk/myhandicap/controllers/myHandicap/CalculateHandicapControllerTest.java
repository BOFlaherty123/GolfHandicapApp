package test.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import main.java.co.uk.myhandicap.validation.validator.HoleValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Calculate Handicap Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/testContext.xml",
        "classpath:/main/webapp/WEB-INF/MyHandicapApp-servlet.xml"})
@WebAppConfiguration
public class CalculateHandicapControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private HoleValidator holeValidator;  //my custom validator, bean defined in the app context

    @Autowired
    UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private final String USER = "TEST_PRINCIPAL";

    Principal principal = new Principal() {
        @Override
        public String getName() {
            return USER;
        }};

    @Test
    public void myHandicapControllerCalculateHandicapRequestMapping() throws Exception {

        when(userService.findUserByUsername(principal.getName())).thenReturn(new User());

        mockMvc.perform(get("/myHandicap/calculate").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/calculate"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/calculate.jsp"));

    }

    /**
     * Case: ScoreCardDto, RoundDto and HoleDto are all valid.
     * Result:
     *      .hasNoErrors()
     * View:
     *      "myHandicap/history"
     *
     * @throws Exception
     */
    @Test
    public void myHandicapControllerSubmitRoundShouldPassValidation() throws Exception {

        mockMvc.perform(post("/myHandicap/submitRound")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("playerId", "1")
                .param("submittedDate", "15/07/2014")
                .param("golfRounds[0].playDate", "15/07/2014")
                .param("golfRounds[0].courseName", "Testing Course Name")
                .param("golfRounds[0].coursePar", "72")
                .param("golfRounds[0].courseSSS", "72")
        )
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("myHandicap/history"));
    }

    /**
     * Case: ScoreCardDto is missing playerId.
     * Result:
     *      .hasErrors()
     *      .errorCount(1)
     * View:
     *      "myHandicap/calculate"
     *
     * @throws Exception
     */
    @Test
    public void myHandicapControllerSubmitRoundMissingPlayerIdShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myHandicap/submitRound")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("submittedDate", "15/07/2014")
                .param("golfRounds[0].playDate", "15/07/2014")
                .param("golfRounds[0].courseName", "Testing Course Name")
                .param("golfRounds[0].coursePar", "72")
                .param("golfRounds[0].courseSSS", "72")
        )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(view().name("myHandicap/calculate"));

    }


    /**
     * Case: ScoreCardDto is missing submittedDate & playerId.
     * Result:
     *      .hasErrors()
     *      .errorCount(2)
     *
     * View:
     *      "myHandicap/calculate"
     *
     */
    @Test
    public void  myHandicapControllerSubmitRoundScoreCardDTOMissingFieldsShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myHandicap/submitRound")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("golfRounds[0].playDate", "15/07/2014")
                .param("golfRounds[0].courseName", "Testing Course Name")
                .param("golfRounds[0].coursePar", "72")
        )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(3))
                .andExpect(view().name("myHandicap/calculate"));

    }

    /**
     * Case: ScoreCard Submitted with only the holePar submitted by the user.
     * Result:
     *      .hasErrors()
     *      .errorCount(1)
     * View:
     *      "myHandicap/calculate"
     *
     */
    @Test
    public void myHandicapControllerSubmitRoundHoleDTOMissingFieldsShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myHandicap/submitRound")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("playerId", "1")
                .param("submittedDate", "15/07/2014")
                .param("golfRounds[0].playDate", "15/07/2014")
                .param("golfRounds[0].courseName", "Testing Course Name")
                .param("golfRounds[0].coursePar", "72")
                .param("golfRounds[0].holes[0].holePar", "3")
                .param("golfRounds[0].holes[0].holeScore", "5")
                .param("golfRounds[0].holes[0].holeSSI", "3")
                .param("golfRounds[0].holes[0].holeYards", "250")
        )

                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(view().name("myHandicap/calculate"));

    }

}