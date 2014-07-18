package test.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.calculation.HandicapCalculation;
import main.java.co.uk.myhandicap.model.handicap.Handicap;
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

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void myHandicapControllerHistoryRequestMapping() throws Exception {

        mockMvc.perform(get("/myHandicap/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/history"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/history.jsp"));

    }

    @Test
    public void testHandicapIsReturnedFromHandicapCalculationProcessor() throws Exception {

        String currentDate = fmt.print(new DateTime());

        Handicap handicap = new Handicap();
        handicap.setCalculatedOn(currentDate);
        handicap.setHandicapScore("22");
        handicap.setNumberOfRounds("3");

        when(handicapCalculationMock.calculateUserHandicapScore(21L)).thenReturn(handicap);

        mockMvc.perform(get("/myHandicap/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("myHandicap/history"))
                .andExpect(forwardedUrl("/WEB-INF/views/myHandicap/history.jsp")
            )
                .andExpect(model().attribute("playerHandicap", handicap));
    }

}
