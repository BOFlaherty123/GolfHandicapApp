package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.model.user.User;

/**
 * User Dao Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public interface UserDao extends GenericDao<User, Long> {

    public User retrieveUserById(Long userId);

    User retrieveUserFromSecurityContext();
}
