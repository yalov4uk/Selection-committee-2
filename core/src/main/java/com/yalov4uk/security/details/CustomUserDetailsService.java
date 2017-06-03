package com.yalov4uk.security.details;

import com.yalov4uk.dto.UserDto;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/23/17.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    @Autowired
    public CustomUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDto user = userService.findByLogin(login);
        if (user != null) {
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("could not find the user '" + login + "'");
        }
    }
}
