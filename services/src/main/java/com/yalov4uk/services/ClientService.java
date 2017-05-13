package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by valera on 5/3/17.
 */
@Service
public class ClientService extends BaseService implements IClientService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    public User register(String name, String login, String password) {
        if (userDao.findByLogin(login) != null) {
            return null;
        }
        User user = new User(name, login, password);
        Role role = roleDao.find(1);
        user.setRole(role);
        userDao.persist(user);
        return user;
    }

    public User login(String login, String password) {
        User user = userDao.findByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }
}
