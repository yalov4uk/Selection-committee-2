package com.yalov4uk.interfaces;

import com.yalov4uk.beans.User;

/**
 * Created by valera on 5/1/17.
 */
public interface IUserDao extends IBaseDao<User> {

    User findByLogin(String login);
}
