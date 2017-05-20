package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.DaoUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
public abstract class BaseDao<T extends Bean> implements IBaseDao<T> {

    protected final static Logger logger = Logger.getLogger(Bean.class);

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> persistentClass;

    @Override
    public void persist(T object) {
        try {
            entityManager.persist(object);
            logger.info("persisted");
        } catch (Exception e) {
            logger.error("not persisted");
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public T update(T object) {
        try {
            if (find(object.getId()) == null) {
                logger.info("object not found");
                return null;
            }
            T bean = entityManager.merge(object);
            logger.info("updated");
            logger.debug(bean);
            return bean;
        } catch (Exception e) {
            logger.error("not updated");
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public T find(Integer key) {
        try {
            T object = entityManager.find(persistentClass, key);
            logger.info("found");
            logger.debug(object);
            return object;
        } catch (Exception e) {
            logger.error("not found");
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public void delete(Integer key) {
        try {
            T entity = entityManager.find(persistentClass, key);
            entityManager.remove(entity);
            logger.info("deleted");
        } catch (Exception e) {
            logger.error("not deleted");
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public List<T> getAll() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(persistentClass);
            Root<T> variableRoot = query.from(persistentClass);
            query.select(variableRoot);
            List<T> objects = entityManager.createQuery(query).getResultList();
            logger.info("got all");
            return objects;
        } catch (Exception e) {
            logger.error("not got all");
            throw new DaoUncheckedException(e);
        }
    }

    public BaseDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
}
