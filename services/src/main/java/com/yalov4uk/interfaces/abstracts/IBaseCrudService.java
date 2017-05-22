package com.yalov4uk.interfaces.abstracts;

import com.yalov4uk.abstracts.Bean;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public interface IBaseCrudService<T extends Bean> extends IBaseService {

    void create(T object);

    T read(Integer key);

    T update(T object);

    void delete(Integer key);

    List<T> getAll();
}
