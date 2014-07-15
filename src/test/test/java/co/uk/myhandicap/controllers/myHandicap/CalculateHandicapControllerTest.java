package test.java.co.uk.myhandicap.controllers.myHandicap;

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
    private HoleValidator validator;  //my custom validator, bean defined in the app context

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void myHandicapControllerCalculateHandicapRequestMapping() throws Exception {

        mockMvc.perform(get("/myHandicap/calculate"))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/calculate"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/calculate.jsp"));

    }

    /**
     * Scenario: ScoreCardDto, RoundDto and HoleDto are all valid.
     * Result:
     *      .hasNoErrors()
     * View:
     *      "myHandicap/history"
     *
     * @throws Exception
     */
    @Test
    public void myHandicapControllerSubmitRoundPassValidation() throws Exception {

        mockMvc.perform(post("/myHandicap/submitRound")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("playerId", "1")
                .param("submittedDate", "15/07/2014")
                .param("golfRounds[0].playDate", "15/07/2014")
                .param("golfRounds[0].courseName", "Testing Course Name")
                .param("golfRounds[0].coursePar", "72")
        )
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("myHandicap/history"));
    }

    /**
     * Scenario: ScoreCardDto is missing playerId.
     * Result:
     *      .hasErrors()
     *      .errorCount(1)
     * View:
     *      "myHandicap/calculate"
     *
     * @throws Exception
     */
    @Test
    public void myHandicapControllerSubmitRoundMissingPlayerIdFailValidation() throws Exception {

        mockMvc.perform(post("/myHandicap/submitRound")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("submittedDate", "15/07/2014")
                .param("golfRounds[0].playDate", "15/07/2014")
                .param("golfRounds[0].courseName", "Testing Course Name")
                .param("golfRounds[0].coursePar", "72")
        )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(view().name("myHandicap/calculate"));

    }

}