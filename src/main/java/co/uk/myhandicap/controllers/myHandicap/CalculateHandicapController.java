package main.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.dto.ScoreCardDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView displayMyHandicapHistory(ModelAndView mav) {
        mav.setViewName("myHandicap/calculate");
        mav.addObject(new ScoreCardDto());

        return mav;
    }

    // POST method to accept submitted form data
    // Validate the form data entered by the user
    // Output errors (if any) to the view
    // Call the Service Method to save the data
    @RequestMapping(value="/submitRound", method = RequestMethod.POST)
    public void submitRoundOfGolf(@Valid ScoreCardDto scoreCard, Errors errors) {

        if(errors.hasErrors()) {
            System.out.println("has errors");
        }

        System.out.println(scoreCard.toString());

    }

}
