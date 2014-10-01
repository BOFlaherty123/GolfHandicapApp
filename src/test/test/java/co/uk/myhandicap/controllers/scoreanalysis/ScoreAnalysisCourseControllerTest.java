package test.java.co.uk.myhandicap.controllers.scoreanalysis;

import main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.average.CalculateRequestedAverage;
import main.java.co.uk.myhandicap.dao.ScoreCardDao;
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
import java.util.ArrayList;
import java.util.List;

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
        "classpath:/test/config/MyHandicapApp-servlet-test.xml"})
@WebAppConfiguration
public class ScoreAnalysisCourseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CalculateRequestedAverage calculateRequestedAverage;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreCardDao scoreCardDao;

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

        when(userService.findUserByUsername(principal.getName())).thenReturn(createMockUser());

        mockMvc.perform(get("/scoreAnalysis/courseName").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/courseAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/courseAnalysis.jsp"));

    }

    @Test
    public void scoreAnalysisControllerAverageByCourseNameRequestMapping() throws Exception {

        User user = createMockUser();

        when(userService.findUserByUsername(principal.getName())).thenReturn(user);
        when(calculateRequestedAverage.process("avgByCourse", user, "Rivenhall Oaks")).thenReturn("5");
        when(scoreCardDao.retrieveAllGolfCourseNamesForUserByScoreCard(user)).thenReturn(mockCourseNameList());

        mockMvc.perform(get("/scoreAnalysis/averageCourseName/Rivenhall Oaks")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("analysis/courseAnalysis"))
                .andExpect(forwardedUrl("/WEB-INF/views/analysis/courseAnalysis.jsp"))
        .andExpect(model().attributeExists("avgByCourseName"));

    }

    @Test
    public void scoreAnalysisControllerAverageByParRequestMapping() throws Exception {

        User user = createMockUser();

        when(userService.findUserByUsername(principal.getName())).thenReturn(user);
        when(calculateRequestedAverage.process("avgByHolePar", user, "4")).thenReturn("4.25");
        when(scoreCardDao.retrieveAllGolfCourseNamesForUserByScoreCard(user)).thenReturn(mockCourseNameList());

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
        when(scoreCardDao.retrieveAllGolfCourseNamesForUserByScoreCard(user)).thenReturn(mockCourseNameList());

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

    private List<String> mockCourseNameList() {
        List<String> courseNames = new ArrayList();
        courseNames.add("Course Name 1");
        courseNames.add("Course Name 2");

        return courseNames;
    }

}
