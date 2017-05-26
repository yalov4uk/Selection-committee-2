package com.yalov4uk.abstracts;

import abstracts.Dto;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/17/17.
 */
public abstract class BaseCrudService<T extends Bean, D extends Dto> extends BaseService implements IBaseCrudService<D> {

    protected abstract IBaseDao<T> getDao();

    protected abstract Class<T> getBeanClass();

    protected abstract Class<D> getDtoClass();

    public D create(D dto) {
        T object = modelMapper.map(dto, getBeanClass());
        IBaseDao<T> dao = getDao();
        dao.persist(object);
        return modelMapper.map(object, getDtoClass());
    }

    public D read(Integer key) {
        IBaseDao<T> dao = getDao();
        return modelMapper.map(dao.find(key), getDtoClass());
    }

    public D update(D dto) {
        T object = modelMapper.map(dto, getBeanClass());
        IBaseDao<T> dao = getDao();
        return modelMapper.map(dao.update(object), getDtoClass());
    }

    public void delete(Integer key) {
        IBaseDao<T> dao = getDao();
        dao.delete(key);
    }

    public List<D> getAll() {
        IBaseDao<T> dao = getDao();
        return dao.getAll()
                .stream()
                .map(object -> modelMapper.map(object, getDtoClass()))
                .collect(Collectors.toList());
    }
}
