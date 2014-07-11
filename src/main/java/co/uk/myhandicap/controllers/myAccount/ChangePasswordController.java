package main.java.co.uk.myhandicap.controllers.myAccount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User Account Change Password
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class ChangePasswordController {

    @RequestMapping(value="/changeAccountPassword")
    public String displayChangePasswordPage() {
        return "myAccount/changePassword";
    }

}
