package com.yalov4uk.dto;

import com.yalov4uk.abstracts.Dto;

import java.util.Date;

/**
 * Created by valera on 5/17/17.
 */
public class StatementDto extends Dto{

    private Date date;

    private UserDto user;
    private FacultyDto faculty;

    public StatementDto() {
    }

    public StatementDto(Date date, UserDto user, FacultyDto faculty) {
        this.date = date;
        this.user = user;
        this.faculty = faculty;
    }

    public StatementDto(Integer id, Date date, UserDto user, FacultyDto faculty) {
        super(id);
        this.date = date;
        this.user = user;
        this.faculty = faculty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
