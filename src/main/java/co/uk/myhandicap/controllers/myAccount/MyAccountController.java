package main.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * My Account Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class MyAccountController {

    @RequestMapping(value="/personalInformation")
    public String displayPersonalInformation(Model model) {

        model.addAttribute(new User());

        return "myAccount/personal";
    }

}
