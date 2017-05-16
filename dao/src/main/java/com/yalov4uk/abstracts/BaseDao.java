package com.yalov4uk.abstracts;

import com.yalov4uk.interfaces.IBaseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao<T extends Bean> implements IBaseDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> persistentClass;

    @Override
    public void persist(T object) {
        entityManager.persist(object);
    }

    @Override
    public T update(T object) {
        return entityManager.merge(object);
    }

    @Override
    public T find(Integer key) {
        return entityManager.find(persistentClass, key);
    }

    @Override
    public void delete(Integer key) {
        T entity = entityManager.find(persistentClass, key);
        entityManager.remove(entity);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        Root<T> variableRoot = query.from(persistentClass);
        query.select(variableRoot);
        return entityManager.createQuery(query).getResultList();
    }

    public BaseDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
}
