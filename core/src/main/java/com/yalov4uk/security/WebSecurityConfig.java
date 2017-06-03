package com.yalov4uk.security;

import com.yalov4uk.security.details.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService userDetailsService,
                             AuthenticationEntryPoint authenticationEntryPoint,
                             LogoutSuccessHandler logoutSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers(HttpMethod.GET, "/faculties/*").hasAnyRole("enrollee", "admin")
                .antMatchers(HttpMethod.GET, "/faculties").hasAnyRole("enrollee", "admin")
                .antMatchers(HttpMethod.GET, "/faculties/*/subjects").hasAnyRole("enrollee", "admin")

                .antMatchers(HttpMethod.POST, "/faculties/*/users").hasRole("enrollee")

                .antMatchers(HttpMethod.POST, "/subjects").hasAnyRole("enrollee", "admin")

                .antMatchers(HttpMethod.POST, "/users").anonymous()
                .antMatchers(HttpMethod.POST, "/login").anonymous()
                .antMatchers(HttpMethod.GET, "/logout").authenticated()

                .antMatchers(HttpMethod.GET, "/users/myAccount").hasAnyRole("enrollee", "admin")
                .antMatchers(HttpMethod.GET, "/users/myAccount/subjects").hasRole("enrollee")

                .antMatchers(HttpMethod.POST, "/auth/*").permitAll()

                .anyRequest().hasRole("admin")

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .csrf()
                .disable();
    }
}
