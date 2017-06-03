package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.validators.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BaseService implements IClientService {

    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final UserValidator userValidator;

    @Autowired
    public ClientService(ModelMapper modelMapper, IUserDao userDao, IRoleDao roleDao,
                         UserValidator userValidator) {
        super(modelMapper);
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.userValidator = userValidator;
    }

    public UserDto register(UserDto userDto) {
        userValidator.validate(userDto);
        if (userDao.findByLogin(userDto.getLogin()) != null) {
            throw new ServiceUncheckedException("login already used");
        }
        User user = modelMapper.map(userDto, User.class);
        Role role = roleDao.find(1);
        user.setRole(role);
        userDao.persist(user);
        roleDao.find(user.getRole().getId()).getUsers().add(user);
        return modelMapper.map(user, UserDto.class);
    }
}
