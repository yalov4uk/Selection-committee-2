package com.yalov4uk.interfaces;

import com.yalov4uk.abstracts.Bean;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface ICRUDService extends IBaseService {

    <T extends Bean> T create(T object);

    <T extends Bean> T read(Integer key, Class<T> name);

    <T extends Bean> T update(T object);

    <T extends Bean> T delete(T object);

    <T extends Bean> List<T> getAll(Class<T> name);
}
