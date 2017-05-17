package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class UserService extends BaseCrudService<User> implements IUserService {

    private final IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    protected IBaseDao<User> getDao() {
        return userDao;
    }
}
