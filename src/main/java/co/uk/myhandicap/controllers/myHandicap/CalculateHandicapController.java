package main.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.controllers.AbstractController;
import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.controllers.IAppFormController;
import main.java.co.uk.myhandicap.form.ScoreCardDto;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class CalculateHandicapController extends AbstractController
        implements IAppController, IAppFormController<ScoreCardDto> {

    private static final XLogger logger = XLoggerFactory.getXLogger(CalculateHandicapController.class);

    @Value("${logging.info}")
    private String logInfoMsg;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    private static final String VIEW_NAME = "myHandicap/calculate";

    /**
     * handleRequest for myHandicap/calculate (GET).
     *
     * @param mav
     * @param principal
     * @return
     */
    @Override
    @RequestMapping(value="/calculate")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {
        logger.entry(mav);

        mav.setViewName(VIEW_NAME);

        // retrieve the user
        User user = retrieveUser(principal.getName());

        // setup ScoreCard object
        ScoreCardDto scoreCardDto = setupScoreCard(user);
        mav.addObject(scoreCardDto);

        logger.exit(mav);

        return mav;
    }

    /**
     * submitFormRequest for myHandicap/submitRound (POST).
     *
     * @param mav
     * @param scoreCard
     * @param errors
     * @return
     */
    @Override
    @RequestMapping(value="/submitRound", method = RequestMethod.POST)
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid ScoreCardDto scoreCard, BindingResult errors) {

        logger.entry(mav, scoreCard, errors);

        if(errors.hasErrors()) {
            mav.setViewName(VIEW_NAME);
            logger.info(format(logInfoMsg, this.getClass().getName(), SUBMIT_FORM_METHOD_NAME, format("%s errors triggered", errors.getErrorCount())));

        } else {
            mav.setViewName("myHandicap/history");

            // model object mapping
            logger.info(format(logInfoMsg, this.getClass().getName(), SUBMIT_FORM_METHOD_NAME, "run model mapping for ScoreCard ..."));
            ScoreCard sc = modelMapper.map(scoreCard, ScoreCard.class);

            scoreCardService.save(sc);
            logger.info(format(logInfoMsg, this.getClass().getName(), SUBMIT_FORM_METHOD_NAME, format("%s saved successfully ...", sc.toString())));
        }

        logger.exit(mav);

        return mav;
    }

    /**
     * setup the a new scoreCard dto object using the User object
     *
     * @param user
     * @return
     */
    private ScoreCardDto setupScoreCard(User user) {

        ScoreCardDto scoreCardDto = new ScoreCardDto();
        scoreCardDto.setPlayerId(user.getId());
        scoreCardDto.setSubmittedDate(new Date().toString());

        return scoreCardDto;
    }

}