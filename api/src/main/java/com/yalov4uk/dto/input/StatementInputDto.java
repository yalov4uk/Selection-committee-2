package com.yalov4uk.dto.input;

import com.yalov4uk.abstracts.Dto;

public class StatementInputDto extends Dto {

    private Integer userId;
    private Integer facultyId;

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
