package com.yalov4uk.security.handlers;

import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final IUserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User user = userService.findByLogin(authentication.getPrincipal().toString());
        if (user == null || !user.getPassword().equals(authentication.getCredentials().toString()))
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad credentials");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
