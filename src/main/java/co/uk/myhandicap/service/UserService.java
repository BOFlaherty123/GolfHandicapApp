package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.dao.UserDAO;
import main.java.co.uk.myhandicap.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/07/2014
 * @project MyHandicapApp
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public User retrieveUser(Long userId) {
        return userDAO.retrieveUser(userId);
    }

}