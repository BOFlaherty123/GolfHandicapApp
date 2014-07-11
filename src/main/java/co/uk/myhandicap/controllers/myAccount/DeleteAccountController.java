package main.java.co.uk.myhandicap.controllers.myAccount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Delete User Account
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myAccount")
public class DeleteAccountController {

    @RequestMapping(value="/deleteUserAccount")
    public String displayDeleteUserAccountPage() {
        return "myAccount/deleteAccount";
    }

}
