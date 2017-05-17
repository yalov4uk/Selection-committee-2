package com.yalov4uk.dto;

import com.yalov4uk.abstracts.Dto;

/**
 * Created by valera on 5/17/17.
 */
public class SubjectNameDto extends Dto {

    private String name;

    public SubjectNameDto() {
    }

    public SubjectNameDto(String name) {
        this.name = name;
    }

    public SubjectNameDto(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
