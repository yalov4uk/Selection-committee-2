package com.yalov4uk.configurations;

import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/23/17.
 */
@Service
public class FakeUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.yalov4uk.beans.User user = userService.findByLogin(login);
        if (user != null) {
            return new User(user.getLogin(), user.getPassword(), true,
                    true, true, true,
                    AuthorityUtils.createAuthorityList(user.getRole().getName()));
        } else {
            throw new UsernameNotFoundException("could not find the user '" + login + "'");
        }
    }
}
