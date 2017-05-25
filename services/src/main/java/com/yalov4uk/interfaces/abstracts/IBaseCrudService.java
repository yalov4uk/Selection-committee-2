package com.yalov4uk.interfaces.abstracts;

import abstracts.Dto;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public interface IBaseCrudService<D extends Dto> extends IBaseService {

    D create(D dto);

    D read(Integer key);

    D update(D dto);

    D delete(Integer key);

    List<D> getAll();
}
