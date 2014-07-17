package main.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.controllers.AppController;
import main.java.co.uk.myhandicap.controllers.AppFormController;
import main.java.co.uk.myhandicap.form.ScoreCardDto;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.service.ScoreCardServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
public class CalculateHandicapController implements AppController, AppFormController<ScoreCardDto> {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    @Override
    @RequestMapping(value="/calculate")
    public ModelAndView handleRequest(ModelAndView mav) {
        mav.setViewName("myHandicap/calculate");

        // Mock Initial ScoreCard values (User) TODO - Remove once redundant
        ScoreCardDto scoreCardDto = new ScoreCardDto();
        scoreCardDto.setPlayerId(1L);
        scoreCardDto.setSubmittedDate("15/07/2014");

        mav.addObject(scoreCardDto);

        return mav;
    }

    @Override
    @RequestMapping(value="/submitRound", method = RequestMethod.POST)
    public ModelAndView submitFormRequest(ModelAndView mav, @Valid ScoreCardDto scoreCard, BindingResult errors) {

        if(errors.hasErrors()) {
            mav.setViewName("myHandicap/calculate");

            for(ObjectError error : errors.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

        } else {

            mav.setViewName("myHandicap/history");

            // Dozer object mapping
            ScoreCard sc = mapper.map(scoreCard, ScoreCard.class);

            scoreCardService.save(sc);

        }

        return mav;
    }

}