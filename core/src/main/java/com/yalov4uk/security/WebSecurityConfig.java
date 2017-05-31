package com.yalov4uk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
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

                /*.antMatchers(HttpMethod.GET, "/client/**").permitAll()
                .antMatchers(HttpMethod.POST, "/client/**").permitAll()

                .antMatchers(HttpMethod.GET, "/faculties").hasAnyRole("enrollee", "admin")
                .antMatchers(HttpMethod.POST, "/subjects").hasAnyRole("enrollee",  "admin")

                .antMatchers(HttpMethod.GET, "/enrollee/**").hasRole("enrollee")*/

                .anyRequest().hasRole("admin")

                .and()
                .formLogin()
                .usernameParameter("login")

                .and()
                .logout()

                .and()
                .httpBasic()

                .and()
                .csrf()
                .disable();
    }
}
