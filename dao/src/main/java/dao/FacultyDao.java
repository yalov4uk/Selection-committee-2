package dao;

import abstracts.BaseDao;
import beans.Faculty;
import interfaces.IFacultyDao;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by valera on 5/1/17.
 */
public class FacultyDao extends BaseDao<Faculty> implements IFacultyDao {

    public FacultyDao(Class<Faculty> persistentClass) {
        super(persistentClass);
    }

    public Faculty findByName(String name) {
        try {
            Session session = hibernateUtil.getSession();
            Query query = session.createQuery("from Faculty faculty where faculty.name = :name");
            query.setParameter("name", name);
            return (Faculty) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
