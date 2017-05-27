package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.DaoUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseDao<T extends Bean> implements IBaseDao<T> {

    protected final Logger logger = getLogger();

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getBeanClass();

    protected abstract Logger getLogger();

    @Override
    public void persist(T object) {
        try {
            entityManager.persist(object);
            logger.info("persisted");
        } catch (Exception e) {
            logger.error("not persisted");
            throw new DaoUncheckedException("Error while persist object." + e.getMessage());
        }
    }

    @Override
    public T update(T object) {
        try {
            if (find(object.getId()) == null) {
                logger.info("object not found");
                throw new DaoUncheckedException("object not found");
            }
            T bean = entityManager.merge(object);
            logger.info("updated");
            logger.debug(bean.toString());
            return bean;
        } catch (Exception e) {
            logger.error("not updated");
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public T find(Integer key) {
        try {
            T object = entityManager.find(getBeanClass(), key);
            logger.info("found");
            logger.debug(object == null ? null : object.toString());
            return object;
        } catch (Exception e) {
            logger.error("not found");
            throw new DaoUncheckedException("Error while found object." + e.getMessage());
        }
    }

    @Override
    public void delete(Integer key) {
        try {
            T entity = entityManager.find(getBeanClass(), key);
            entityManager.remove(entity);
            logger.info("deleted");
        } catch (Exception e) {
            logger.error("not deleted");
            throw new DaoUncheckedException("Error while delete object." + e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(getBeanClass());
            Root<T> variableRoot = query.from(getBeanClass());
            query.select(variableRoot);
            List<T> objects = entityManager.createQuery(query).getResultList();
            logger.info("got all");
            return objects;
        } catch (Exception e) {
            logger.error("not got all");
            throw new DaoUncheckedException("Error while get all objects." + e.getMessage());
        }
    }
}
