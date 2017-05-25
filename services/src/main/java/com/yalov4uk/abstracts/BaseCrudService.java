package com.yalov4uk.abstracts;

import abstracts.Dto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
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
        try {
            T object = modelMapper.map(dto, getBeanClass());
            IBaseDao<T> dao = getDao();
            dao.persist(object);
            return modelMapper.map(object, getDtoClass());
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public D read(Integer key) {
        try {
            IBaseDao<T> dao = getDao();
            return modelMapper.map(dao.find(key), getDtoClass());
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public D update(D dto) {
        try {
            T object = modelMapper.map(dto, getBeanClass());
            IBaseDao<T> dao = getDao();
            return modelMapper.map(dao.update(object), getDtoClass());
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public D delete(Integer key) {
        try {
            IBaseDao<T> dao = getDao();
            dao.delete(key);
            return null;
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public List<D> getAll() {
        try {
            IBaseDao<T> dao = getDao();
            return dao.getAll()
                    .stream()
                    .map(object -> modelMapper.map(object, getDtoClass()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }
}
