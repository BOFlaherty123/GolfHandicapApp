package main.java.co.uk.myhandicap.service;

import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Spring Security User Login Service
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);

        try {
            throw new UserNotFoundException("User=[ " + user + " ] not found");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

        // TODO - Fix Encryption Error "org.jasypt.exceptions.EncryptionOperationNotPossibleException"

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
}
