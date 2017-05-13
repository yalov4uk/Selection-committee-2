package com.yalov4uk.interfaces;

import com.yalov4uk.abstracts.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
public interface IBaseDao<T extends Bean> {

    void persist(T object);

    T find(Integer key);

    void update(T object);

    void delete(Integer key);

    List<T> getAll();
}
