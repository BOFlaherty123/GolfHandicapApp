package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.dao.UserDAOImpl;
import main.java.co.uk.myhandicap.dto.user.User;
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
    private UserDAOImpl userDAO;

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User retrieveUserById(Long id) {
        return userDAO.retrieveUserById(id);
    }
}