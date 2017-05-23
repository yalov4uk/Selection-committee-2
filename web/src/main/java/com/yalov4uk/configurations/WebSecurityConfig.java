package com.yalov4uk.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by valera on 5/23/17.
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/client/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/client/**").permitAll()
                .antMatchers(HttpMethod.GET, "/faculties").hasAnyRole("enrollee", "admin")
                .antMatchers(HttpMethod.POST, "/enrollee/**").hasRole("enrollee")
                .antMatchers(HttpMethod.POST, "/enrollee/**").hasRole("enrollee")
                .antMatchers(HttpMethod.POST, "/subjects").hasRole("enrollee")
                .anyRequest().hasRole("admin")

                .and().formLogin()
                //.usernameParameter("login")
                //.passwordParameter("password")
                .loginPage("/client/login")
                .permitAll()


                .and().logout()
                .logoutUrl("/client/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/client/logout"))
                .and().httpBasic()
                .and().csrf().disable();
    }
}
