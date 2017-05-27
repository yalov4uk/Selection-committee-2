package com.yalov4uk.dto;


import com.yalov4uk.abstracts.Dto;

public class RoleDto extends Dto {

    private String name;

    public RoleDto() {
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public RoleDto(Integer id, String name) {
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
