package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;

import java.util.List;

/**
 * ScoreCard Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public interface ScoreCardService extends GenericService<ScoreCard,Long> {

    List<ScoreCard> retrieveUserScoredCardsById(User user);


}
