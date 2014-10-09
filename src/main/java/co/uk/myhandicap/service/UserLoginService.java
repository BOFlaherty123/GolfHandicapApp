package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Spring Security User Login Service.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 05/08/2014
 * @project MyHandicapApp
 */
@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String input) {

        User user;
        String username = null;
        String password = null;
        try {
            user = userService.findUserByUsername(input);

            // if the user is found, store their current username and password
            username = user.getUsername();
            password = user.getPassword();

        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        // Assign user the role, ROLE_USER on a successful login
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

        return new org.springframework.security.core.userdetails.User(username,
                password, authorities);
    }
}
