package main.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.calculation.HandicapCalculation;
import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.Handicap;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardService;
import main.java.co.uk.myhandicap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * My Handicap Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myHandicap")
public class MyHandicapController implements AppController {

    @Autowired
    private HandicapCalculation handicapCalculation;

    @Autowired
    private ScoreCardService scoreCardService;

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(value="/history")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myHandicap/history");

        System.out.println("scoreCardService " + scoreCardService);
        System.out.println("userService " + userService);

        try {
            Handicap handicap = handicapCalculation.calculateUserHandicapScore(21L);

            User user = userService.retrieveUserById(21L);

            List<ScoreCard> scoreCardList = scoreCardService.retrieveUserScoredCardsById(user);

            mav.addObject("playerHandicap", handicap);
            mav.addObject("playerScoreCards", scoreCardList);

        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return mav;
    }

}
