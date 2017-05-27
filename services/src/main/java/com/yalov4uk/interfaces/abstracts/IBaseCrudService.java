package com.yalov4uk.interfaces.abstracts;

import com.yalov4uk.abstracts.Dto;

import java.util.List;

public interface IBaseCrudService<D extends Dto> extends IBaseService {

    D create(D dto);

    D read(Integer key);

    D update(D dto);

    void delete(Integer key);

    List<D> getAll();
}
