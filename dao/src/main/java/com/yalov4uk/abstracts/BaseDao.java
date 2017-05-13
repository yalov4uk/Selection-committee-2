package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.DaoUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao<T extends Bean> implements IBaseDao<T> {

    @PersistenceContext
    protected EntityManager em;
    private Class<T> persistentClass;

    @Override
    public void persist(T object) {
        em.persist(object);
    }

    @Override
    public void update(T object) {
        em.merge(object);
    }

    @Override
    public T find(Integer key) {
        return em.find(persistentClass, key);
    }

    @Override
    public void delete(Integer key) {
        T entity = em.find(persistentClass, key);
        em.remove(entity);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        Root<T> variableRoot = query.from(persistentClass);
        query.select(variableRoot);
        return em.createQuery(query).getResultList();
    }

    public BaseDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
}
