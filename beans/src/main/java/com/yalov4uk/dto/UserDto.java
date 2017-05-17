package com.yalov4uk.dto;

import com.yalov4uk.abstracts.Dto;

/**
 * Created by valera on 5/17/17.
 */
public class UserDto extends Dto {

    private String name;
    private String login;
    private String password;

    private RoleDto role;

    public UserDto() {
    }

    public UserDto(String name, String login, String password, RoleDto role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserDto(Integer id, String name, String login, String password, RoleDto role) {
        super(id);
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
