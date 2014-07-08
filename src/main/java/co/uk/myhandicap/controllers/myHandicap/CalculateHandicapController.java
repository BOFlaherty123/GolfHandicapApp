package main.java.co.uk.myhandicap.controllers.myHandicap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Calculate Handicap Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myHandicap")
public class CalculateHandicapController {

    @RequestMapping(value="/calculate")
    public String displayMyHandicapHistory() {
        return "myHandicap/calculate";
    }

}
