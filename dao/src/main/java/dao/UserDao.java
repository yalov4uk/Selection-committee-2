package dao;

import abstracts.BaseDao;
import beans.User;
import exceptions.DaoUncheckedException;
import interfaces.IUserDao;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by valera on 5/1/17.
 */
public class UserDao extends BaseDao<User> implements IUserDao {

    public UserDao(Class<User> persistentClass) {
        super(persistentClass);
    }

    public User findByLogin(String login) {
        try {
            Session session = hibernateUtil.getSession();
            Query query = session.createQuery("from User user where user.login = :login");
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
