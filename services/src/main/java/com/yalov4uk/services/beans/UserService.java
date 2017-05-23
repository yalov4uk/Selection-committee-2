package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.ServiceUncheckedException;
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

    public void create(User user) {
        try {
            userDao.persist(user);

            user.getRole().getUsers().add(user);

            logger.info("persisted");
        } catch (Exception e) {
            logger.error("not persisted");
            throw new ServiceUncheckedException(e);
        }
    }

    @Override
    public void delete(Integer key) {
        try {
            User user = userDao.find(key);
            userDao.delete(key);

            user.getRole().getUsers().remove(user);

            logger.info("deleted");
        } catch (Exception e) {
            logger.error("not deleted");
            throw new ServiceUncheckedException(e);
        }
    }

    public User findByLogin(String login) {
        try {
            User user = userDao.findByLogin(login);
            logger.info("found");
            logger.debug(user);
            return user;
        } catch (Exception e) {
            logger.error("not found");
            throw new ServiceUncheckedException(e);
        }
    }
}
