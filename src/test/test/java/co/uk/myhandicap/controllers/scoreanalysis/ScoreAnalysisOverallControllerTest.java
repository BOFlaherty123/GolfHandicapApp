package test.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Score Analysis Overall Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/10/2014
 * @project MyHandicapApp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/testContext.xml",
        "classpath:/test/config/MyHandicapApp-servlet-test.xml"})
@WebAppConfiguration
public class ScoreAnalysisOverallControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CalculateRequestedAverage calculateRequestedAverage;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

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
    public void scoreAnalysisOverallControllerDefaultRequestMapping() throws Exception {

        when(userService.findUserByUsername(principal.getName())).thenReturn(createMockUser());

        mockMvc.perform(get("/scoreAnalysis/overall").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/overallAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/overallAnalysis.jsp"));

    }

    @Test
    public void scoreAnalysisControllerAverageByParRequestMapping() throws Exception {

        User user = createMockUser();

        when(userService.findUserByUsername(principal.getName())).thenReturn(user);
        when(calculateRequestedAverage.process("avgByHolePar", user, "4")).thenReturn("4.25");

        mockMvc.perform(get("/scoreAnalysis/averagePar/4")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/overallAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/overallAnalysis.jsp"))
                .andExpect(model().attributeExists("avgByHolePar"));

    }

    @Test
    public void scoreAnalysisControllerAverageByHoleYardageRequestMapping() throws Exception {

        User user = createMockUser();

        when(userService.findUserByUsername(principal.getName())).thenReturn(user);
        when(calculateRequestedAverage.process("avgByHoleYardage", user, "250")).thenReturn("4.8");

        mockMvc.perform(get("/scoreAnalysis/averageYardage/250")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/overallAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/overallAnalysis.jsp"))
                .andExpect(model().attributeExists("avgByHoleYardage"));

    }

    @Test
    public void scoreAnalysisControllerFailureAsUserNotFoundExceptionThrown() throws Exception {

        when(userService.findUserByUsername(principal.getName())).thenThrow(new UserNotFoundException());

        mockMvc.perform(get("/scoreAnalysis/averageYardage/250")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/overallAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/overallAnalysis.jsp"));

    }

    private User createMockUser() {

        User user = new User();
        user.setId(1L);
        user.setUsername("TEST_PRINCIPAL");

        return user;
    }

}
