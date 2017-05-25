package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.IUserDao;
import dto.UserDto;
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

    public UserDto register(UserDto userDto) {
        try {
            if (userDao.findByLogin(userDto.getLogin()) != null) {
                throw new NotFoundException();
            }
            User user = modelMapper.map(userDto, User.class);
            Role role = roleDao.find(1);
            user.setRole(role);
            userDao.persist(user);
            return modelMapper.map(user, UserDto.class);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public UserDto login(String login, String password) {
        try {
            User user = userDao.findByLogin(login);
            if (user == null || !user.getPassword().equals(password)) {
                throw new NotFoundException();
            }
            return modelMapper.map(user, UserDto.class);
        }  catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }
}
