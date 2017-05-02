package abstracts;

import exceptions.DaoUncheckedException;
import interfaces.IBaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
public abstract class BaseDao<T extends Bean> implements IBaseDao<T> {

    protected HibernateUtil hibernateUtil;
    private Class<T> persistentClass;

    @Override
    public Serializable save(T object) {
        Serializable id;
        try {
            Session session = hibernateUtil.getSession();
            id = session.save(object);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
        return id;
    }

    @Override
    public void persist(T object) {
        try {
            Session session = hibernateUtil.getSession();
            session.persist(object);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public void update(T object) {
        try {
            Session session = hibernateUtil.getSession();
            session.update(object);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public T find(Integer key) {
        T entity;
        try {
            Session session = hibernateUtil.getSession();
            entity = session.get(persistentClass, key);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
        return entity;
    }

    @Override
    public void delete(Integer key) {
        try {
            Session session = hibernateUtil.getSession();
            T entity = session.get(persistentClass, key);
            session.delete(entity);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }

    @Override
    public List<T> getAll() {
        List<T> results;
        try {
            Session session = hibernateUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(persistentClass);
            criteria.select(criteria.from(persistentClass));
            Query q = session.createQuery(criteria);
            results = q.list();
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
        return results;
    }

    public BaseDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        hibernateUtil = HibernateUtil.getInstance();
    }
}
