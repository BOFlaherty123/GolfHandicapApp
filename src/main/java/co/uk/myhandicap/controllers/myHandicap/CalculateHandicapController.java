package main.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.form.ScoreCardDto;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardServiceImpl;
import main.java.co.uk.myhandicap.service.UserService;
import org.dozer.Mapper;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

import static java.lang.String.format;

/**
 * Calculate Handicap Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myHandicap")
public class CalculateHandicapController implements AppController, AppFormController<ScoreCardDto> {

    private static final XLogger logger = XLoggerFactory.getXLogger(CalculateHandicapController.class
            .getName());

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    @Override
    @RequestMapping(value="/calculate")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {
        logger.entry(mav);

        mav.setViewName("myHandicap/calculate");

        User user = null;
        ScoreCardDto scoreCardDto = new ScoreCardDto();

        try {
            user = userService.findUserByUsername(principal.getName());

            // if the user is found, setup the a new scoreCard dto object
            scoreCardDto.setPlayerId(user.getId());
            scoreCardDto.setSubmittedDate(new Date().toString());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        mav.addObject(scoreCardDto);

        logger.exit(mav);

        return mav;
    }

    @Override
    @RequestMapping(value="/submitRound", method = RequestMethod.POST)
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid ScoreCardDto scoreCard, BindingResult errors) {
        logger.entry(mav, scoreCard, errors);

        if(errors.hasErrors()) {
            mav.setViewName("myHandicap/calculate");
            logger.info(format("method=[ .submitFormRequest() ] message=[ hasErrors() - %s triggered. ]", errors.getErrorCount()));

        } else {

            mav.setViewName("myHandicap/history");

            // Dozer object mapping
            ScoreCard sc = mapper.map(scoreCard, ScoreCard.class);

            scoreCardService.save(sc);
        }

        logger.exit(mav);

        return mav;
    }

}