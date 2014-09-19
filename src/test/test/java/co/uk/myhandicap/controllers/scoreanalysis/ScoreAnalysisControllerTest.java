package test.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Score Card Analysis Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/09/2014
 * @project MyHandicapApp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/testContext.xml",
        "classpath:/main/webapp/WEB-INF/MyHandicapApp-servlet.xml"})
@WebAppConfiguration
public class ScoreAnalysisControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CalculateRequestedAverage calculateRequestedAverage;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Mock
    private User user;

    private final String USER = "TEST_PRINCIPAL";

    Principal principal = new Principal() {
        @Override
        public String getName() {
            return USER;
        }};

    @Before
    public void setup() {
        Mockito.reset(calculateRequestedAverage);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
         public void scoreAnalysisControllerDefaultRequestMapping() throws Exception {

        mockMvc.perform(get("/scoreAnalysis"))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/scoreAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/scoreAnalysis.jsp"));

    }

    @Test
    public void scoreAnalysisControllerAverageByCourseNameRequestMapping() throws Exception {

        when(calculateRequestedAverage.process("avgByCourse", user, "Rivenhall Oaks")).thenReturn("5");

        mockMvc.perform(get("/scoreAnalysis/averageCourseName")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/scoreAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/scoreAnalysis.jsp"))
        .andExpect(model().attributeExists("avgByCourseName"));

    }

    @Test
    public void scoreAnalysisControllerAverageByParRequestMapping() throws Exception {

        when(calculateRequestedAverage.process("avgByHolePar", user, "4")).thenReturn("4.25");

        mockMvc.perform(get("/scoreAnalysis/averagePar/4")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/scoreAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/scoreAnalysis.jsp"))
                .andExpect(model().attributeExists("avgByHolePar"));

    }

    @Test
    public void scoreAnalysisControllerAverageByHoleYardageRequestMapping() throws Exception {

        when(calculateRequestedAverage.process("avgByHoleYardage", user, "250")).thenReturn("4.8");

        mockMvc.perform(get("/scoreAnalysis/averageYardage/250")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/scoreAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/scoreAnalysis.jsp"))
                .andExpect(model().attributeExists("avgByHoleYardage"));

    }


    @Test
    public void scoreAnalysisControllerFailureAsUserNotFoundExceptionThrown() throws Exception {
        when(userService.findUserByUsername(principal.getName())).thenThrow(new UserNotFoundException());

        mockMvc.perform(get("/scoreAnalysis/averageYardage/250")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/scoreAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/scoreAnalysis.jsp"));

    }

}
