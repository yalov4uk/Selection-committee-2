package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public abstract class BaseCrudService<T extends Bean> extends BaseService implements IBaseCrudService<T> {

    protected abstract IBaseDao<T> getDao();

    public void create(T object) {
        try {
            IBaseDao<T> dao = getDao();
            dao.persist(object);
            logger.info("persisted");
        } catch (Exception e) {
            logger.error("not persisted");
            throw new ServiceUncheckedException(e);
        }
    }

    public T read(Integer key) {
        try {
            IBaseDao<T> dao = getDao();
            T object = dao.find(key);
            logger.info("read");
            logger.debug(object);
            return object;
        } catch (Exception e) {
            logger.error("not read");
            throw new ServiceUncheckedException(e);
        }
    }

    public T update(T object) {
        try {
            IBaseDao<T> dao = getDao();
            T bean = dao.update(object);
            logger.info("updated");
            logger.debug(bean);
            return bean;
        } catch (Exception e) {
            logger.error("not updated");
            throw new ServiceUncheckedException(e);
        }
    }

    public void delete(T object) {
        try {
            IBaseDao<T> dao = getDao();
            dao.delete(object.getId());
            logger.info("deleted");
        } catch (Exception e) {
            logger.error("not deleted");
            throw new ServiceUncheckedException(e);
        }
    }

    public List<T> getAll() {
        try {
            IBaseDao<T> dao = getDao();
            List<T> objects = dao.getAll();
            logger.info("got all");
            return objects;
        } catch (Exception e) {
            logger.error("not got all");
            throw new ServiceUncheckedException(e);
        }
    }
}
