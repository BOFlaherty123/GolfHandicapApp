package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.dto.user.User;

/**
 * User Dao Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public interface UserDao extends GenericDAO<User, Long> {

    public User retrieveUserById(Long userId);

}
