package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.abstracts.Bean;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ICRUDService;
import com.yalov4uk.interfaces.factories.IDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
@Service
public class CRUDService extends BaseService implements ICRUDService {

    @Autowired
    private IDaoFactory daoFactory;

    public <T extends Bean> T create(T object) {
        IBaseDao<T> dao = daoFactory.getDao(object.getClass());
        dao.persist(object);
        return object;
    }

    public <T extends Bean> T read(Integer key, Class<T> name) {
        IBaseDao<T> dao = daoFactory.getDao(name);
        return dao.find(key);
    }

    public <T extends Bean> T update(T object) {
        IBaseDao<T> dao = daoFactory.getDao(object.getClass());
        dao.update(object);
        return object;
    }

    public <T extends Bean> T delete(T object) {
        IBaseDao<T> dao = daoFactory.getDao(object.getClass());
        dao.delete(object.getId());
        return object;
    }

    public <T extends Bean> List<T> getAll(Class<T> name) {
        IBaseDao<T> dao = daoFactory.getDao(name);
        return dao.getAll();
    }
}
