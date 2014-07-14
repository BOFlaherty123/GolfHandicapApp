package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.model.user.User;

/**
 * User Service Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public interface UserService extends GenericService<User, Long> {

    User retrieveUserById(Long id);

}
