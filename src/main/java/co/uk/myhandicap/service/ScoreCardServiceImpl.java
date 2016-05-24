package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.dao.ScoreCardDaoImpl;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ScoreCard Service Implementation.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
@Service
public class ScoreCardServiceImpl implements ScoreCardService {

    @Autowired
    private ScoreCardDaoImpl scoreCardDao;

    @Override
    public void save(ScoreCard scoreCard) {
        scoreCardDao.save(scoreCard);
    }

    @Override
    public void update(ScoreCard scoreCard) {}

    @Override
    public void delete(ScoreCard scoreCard) {}

    @Override
    public List<ScoreCard> retrieveUserScoredCardsById(User user) {
        return scoreCardDao.retrieveUserScoreCardById(user);
    }

}