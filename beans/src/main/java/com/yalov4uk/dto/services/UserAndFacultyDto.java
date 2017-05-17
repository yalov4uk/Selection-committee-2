package com.yalov4uk.dto.services;

import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.dto.UserDto;

/**
 * Created by valera on 5/17/17.
 */
public class UserAndFacultyDto {

    private UserDto user;
    private FacultyDto faculty;

    public UserAndFacultyDto() {
    }

    public UserAndFacultyDto(UserDto user, FacultyDto faculty) {
        this.user = user;
        this.faculty = faculty;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public FacultyDto getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDto faculty) {
        this.faculty = faculty;
    }
}
