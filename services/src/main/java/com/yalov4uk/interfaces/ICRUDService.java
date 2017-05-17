package com.yalov4uk.interfaces;

import com.yalov4uk.abstracts.Bean;
import com.yalov4uk.interfaces.abstracts.IBaseService;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public interface ICRUDService extends IBaseService {

    <T extends Bean> void create(T object);

    <T extends Bean> T read(Integer key, Class<T> name);

    <T extends Bean> T update(T object);

    <T extends Bean> void delete(T object);

    <T extends Bean> List<T> getAll(Class<T> name);
}
