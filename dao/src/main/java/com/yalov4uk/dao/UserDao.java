package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.DaoUncheckedException;
import com.yalov4uk.interfaces.IUserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

    public User findByLogin(String login) {
        try {
            User user = (User) entityManager.createQuery("from User user where user.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
            logger.info("found by login");
            logger.debug(user);
            return user;
        } catch (NoResultException e) {
            return null;
        }  catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }

    protected Class<User> getBeanClass(){
        return User.class;
    }
}
