package com.yalov4uk.abstracts;

import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public abstract class BaseCrudService<T extends Bean> extends BaseService implements IBaseCrudService<T> {

    protected abstract IBaseDao<T> getDao();

    public void create(T object) {
        IBaseDao<T> dao = getDao();
        dao.persist(object);
    }

    public T read(Integer key) {
        IBaseDao<T> dao = getDao();
        return dao.find(key);
    }

    public T update(T object) {
        IBaseDao<T> dao = getDao();
        return dao.update(object);
    }

    public void delete(T object) {
        IBaseDao<T> dao = getDao();
        dao.delete(object.getId());
    }

    public List<T> getAll() {
        IBaseDao<T> dao = getDao();
        return dao.getAll();
    }
}
