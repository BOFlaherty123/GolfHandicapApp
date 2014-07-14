package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.dao.ScoreCardDaoImpl;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ScoreCard Service Implementation
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
    public ScoreCard update(ScoreCard scoreCard) {
        return null;
    }

    @Override
    public void delete(ScoreCard scoreCard) {

    }

}
