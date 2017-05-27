package com.yalov4uk.dto;

public class UserAndFacultyDto {

    private Integer userId;
    private Integer facultyId;

    public UserAndFacultyDto() {
    }

    public UserAndFacultyDto(Integer userId, Integer facultyId) {
        this.userId = userId;
        this.facultyId = facultyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
}
