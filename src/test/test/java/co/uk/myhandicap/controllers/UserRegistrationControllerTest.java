package test.java.co.uk.myhandicap.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
 * User Registration Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/08/2014
 * @project MyHandicapApp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/testContext.xml",
        "classpath:/main/webapp/WEB-INF/MyHandicapApp-servlet.xml"})
@WebAppConfiguration
public class UserRegistrationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void userRegistrationDefaultRequestMapping() throws Exception {

        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(forwardedUrl("/WEB-INF/views/registerUser.jsp"));

    }

    @Test
    public void isUserRegistrationDtoStoredInModel() throws Exception {

        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(forwardedUrl("/WEB-INF/views/registerUser.jsp"))
                .andExpect(model().attributeExists("userRegistrationDto"));

    }

    @Test
    public void userRegistrationFormSubmissionWithValidData() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("username", "BOFlaherty")
                .param("firstName", "Benjamin")
                .param("lastName", "OFlaherty")
                .param("email", "test@test.com")
                .param("password", "qwerty12")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(forwardedUrl("/WEB-INF/views/registerUser.jsp"))
                .andExpect(model().attributeExists("success"));

    }

    @Test
    public void userRegistrationFormSubmissionThrowsErrorsWithInvalidData() throws Exception {

        mockMvc.perform(post("/registerUser")
                        .param("username", "BOF")
                        .param("firstName", "B3NJ4MIN")
                        .param("lastName", "123424")
                        .param("email", "EmailFail")
                        .param("password", "")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(forwardedUrl("/WEB-INF/views/registerUser.jsp"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(6))
                .andExpect(model().attributeExists("failure"));

    }


}
