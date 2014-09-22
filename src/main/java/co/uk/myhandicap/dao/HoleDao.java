package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.user.User;

import java.util.List;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/09/2014
 * @project MyHandicapApp
 */
public interface HoleDao extends GenericDao<Hole, Long> {

    List<Hole> retrieveHoleAverageByHolePar(User user, String holePar);

    List<Hole> retrieveHoleAverageByHoleYardage(User user, String averageRequested);
}
