package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/3/17.
 */
@Service
public class ClientService extends BaseService implements IClientService {

    private final IUserDao userDao;
    private final IRoleDao roleDao;

    @Autowired
    public ClientService(IUserDao userDao, IRoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public User register(User user) {
        try {
            if (userDao.findByLogin(user.getLogin()) != null) {
                return null;
            }
            Role role = roleDao.find(1);
            user.setRole(role);
            userDao.persist(user);
            logger.info("register client");
            logger.debug(user);
            return user;
        } catch (Exception e) {
            logger.error("not register client");
            throw new ServiceUncheckedException(e);
        }
    }

    public User login(String login, String password) {
        try {
            User user = userDao.findByLogin(login);
            if (user == null || !user.getPassword().equals(password)) {
                return null;
            }
            logger.info("login");
            logger.debug(user);
            return user;
        }  catch (Exception e) {
            logger.error("not login");
            throw new ServiceUncheckedException(e);
        }
    }
}
