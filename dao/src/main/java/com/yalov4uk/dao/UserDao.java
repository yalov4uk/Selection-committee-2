package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.DaoUncheckedException;
import com.yalov4uk.interfaces.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

    @Override
    public User findByLogin(String login) {
        try {
            User user = (User) entityManager.createQuery("from User user where user.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
            logger.info("found by login");
            logger.debug("user " + user.toString() + " was found");
            return user;
        } catch (NoResultException e) {
            logger.error("user not found");
            return null;
        } catch (Exception e) {
            logger.error("find by login error cause: " + e.getMessage());
            throw new DaoUncheckedException("Error while find user by login");
        }
    }

    protected Class<User> getBeanClass() {
        return User.class;
    }

    protected Logger getLogger(){
        return LoggerFactory.getLogger(this.getClass());
    }
}
