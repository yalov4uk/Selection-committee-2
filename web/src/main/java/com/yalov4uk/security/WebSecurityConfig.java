package com.yalov4uk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by valera on 5/23/17.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /*.antMatchers(HttpMethod.GET, "/client/**").permitAll()
                .antMatchers(HttpMethod.POST, "/client/**").permitAll()
                .antMatchers(HttpMethod.GET, "/faculties").hasAnyRole("enrollee", "admin")
                .antMatchers(HttpMethod.POST, "/enrollee/**").hasRole("enrollee")
                .antMatchers(HttpMethod.POST, "/enrollee/**").hasRole("enrollee")
                .antMatchers(HttpMethod.POST, "/subjects").hasRole("enrollee")
                .anyRequest().hasRole("admin")

                .and().formLogin()
                .usernameParameter("login")
                .loginPage("/client/login")
                .permitAll()

                .and().logout()
                .logoutUrl("/client/logout")*/
                .anyRequest().permitAll()
                .and().httpBasic()
                .and().csrf().disable();
    }
}
