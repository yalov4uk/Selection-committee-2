package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.abstracts.Bean;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ICRUDService;
import com.yalov4uk.interfaces.factories.IDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class CRUDService extends BaseService implements ICRUDService {

    private final IDaoFactory daoFactory;

    @Autowired
    public CRUDService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public <T extends Bean> void create(T object) {
        IBaseDao<T> dao = daoFactory.getDao(object.getClass());
        dao.persist(object);
    }

    public <T extends Bean> T read(Integer key, Class<T> name) {
        IBaseDao<T> dao = daoFactory.getDao(name);
        return dao.find(key);
    }

    public <T extends Bean> T update(T object) {
        IBaseDao<T> dao = daoFactory.getDao(object.getClass());
        return dao.update(object);
    }

    public <T extends Bean> void delete(T object) {
        IBaseDao<T> dao = daoFactory.getDao(object.getClass());
        dao.delete(object.getId());
    }

    public <T extends Bean> List<T> getAll(Class<T> name) {
        IBaseDao<T> dao = daoFactory.getDao(name);
        return dao.getAll();
    }
}
