package test.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Disable Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/10/2014
 * @project MyHandicapApp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/testContext.xml",
        "classpath:/test/config/MyHandicapApp-servlet-test.xml"})
@WebAppConfiguration
public class DisableAccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Mock
    private SecurityContext ctx;

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
    public void disableAccountControllerDefaultRequestMapping() throws Exception {

        when(userService.findUserByUsername(USER)).thenReturn(new User());

        mockMvc.perform(get("/myAccount/disableUserAccount").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/deleteAccount"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/deleteAccount.jsp"))
                .andExpect(model().attributeExists("user"))
            .andExpect(model().attributeExists("disableUserDto"))
            .andExpect(model().attributeExists("user"));

        Mockito.verify(userService, times(1)).findUserByUsername(anyString());
    }

    @Test
    public void disableAccountControllerSubmit() throws Exception {

        when(userService.findUserByUsername(USER)).thenReturn(new User());

        mockMvc.perform(post("/myAccount/disableUserAccount/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("disableUser", "Y")
            ).andExpect(status().isOk())
                .andExpect(view().name("myAccount/deleteAccount"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/deleteAccount.jsp"));

        verify(userService, times(1)).disableUserAccount(anyString());
    }

}
