package com.yalov4uk.abstracts;

public abstract class Dto {

    private Integer id;

    public Dto() {
    }

    public Dto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
