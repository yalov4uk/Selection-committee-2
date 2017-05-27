package com.yalov4uk.interfaces;

import com.yalov4uk.abstracts.Bean;

import java.util.List;

public interface IBaseDao<T extends Bean> {

    void persist(T object);

    T find(Integer key);

    T update(T object);

    void delete(Integer key);

    List<T> getAll();
}
