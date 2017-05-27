package com.yalov4uk.interfaces;

import com.yalov4uk.beans.User;

public interface IUserDao extends IBaseDao<User> {

    User findByLogin(String login);
}
