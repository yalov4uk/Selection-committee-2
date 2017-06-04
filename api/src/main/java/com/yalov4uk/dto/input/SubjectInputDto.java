package com.yalov4uk.dto.input;

import com.yalov4uk.abstracts.Dto;

public class SubjectInputDto extends Dto {

    private Integer value;

    private Integer subjectNameId;
    private Integer userId;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSubjectNameId() {
        return subjectNameId;
    }

    public void setSubjectNameId(Integer subjectNameId) {
        this.subjectNameId = subjectNameId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
