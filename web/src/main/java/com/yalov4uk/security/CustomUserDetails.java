package com.yalov4uk.security;

import com.yalov4uk.beans.User;
import dto.RoleDto;
import dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by valera on 5/26/17.
 */
public class CustomUserDetails extends UserDto implements UserDetails {

    private final String ROLE_PREFIX = "ROLE_";

    public CustomUserDetails(User user) {
        super(user.getId(), user.getName(), user.getLogin(), user.getPassword(),
                new RoleDto(user.getRole().getId(), user.getRole().getName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + super.getRole().getName()));
        return list;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
