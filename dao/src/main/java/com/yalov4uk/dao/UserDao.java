package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.IUserDao;
import org.springframework.stereotype.Repository;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

    public UserDao() {
        super(User.class);
    }

    public User findByLogin(String login) {
        return (User) entityManager.createQuery("from User user where user.login = :login")
                .setParameter("login", login)
                .getSingleResult();
    }
}
