package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.dao.UserDaoImpl;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service Implementation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/07/2014
 * @project MyHandicapApp
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDAO;

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User retrieveUserById(Long id) {
        return userDAO.retrieveUserById(id);
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        return userDAO.findUserByUsername(username);
    }
}