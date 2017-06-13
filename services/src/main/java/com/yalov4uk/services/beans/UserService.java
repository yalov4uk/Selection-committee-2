package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.dto.input.UserInputDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.IUserService;
import com.yalov4uk.interfaces.validators.DtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseCrudService<User, UserDto> implements IUserService {

    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final ISubjectDao subjectDao;
    private final DtoValidator<UserDto> userValidator;

    @Autowired
    public UserService(ModelMapper modelMapper, IUserDao userDao, IRoleDao roleDao, ISubjectDao subjectDao,
                       DtoValidator<UserDto> userValidator) {
        super(modelMapper);
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.subjectDao = subjectDao;
        this.userValidator = userValidator;
    }

    protected IBaseDao<User> getDao() {
        return userDao;
    }

    @Override
    public UserDto create(UserDto userDto) {
        userValidator.validate(userDto);
        User user = modelMapper.map(userDto, User.class);
        userDao.persist(user);

        roleDao.find(user.getRole().getId()).getUsers().add(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void delete(Integer key) {
        User user = userDao.find(key);
        userDao.delete(key);

        roleDao.find(user.getRole().getId()).getUsers().remove(user);
    }

    @Override
    public UserDto update(UserInputDto userInputDto) {
        if (userInputDto == null || userInputDto.getLogin() == null || userInputDto.getName() == null
                || userInputDto.getPassword() == null || userInputDto.getRoleId() == null) {
            throw new ServiceUncheckedException("wrong input");
        }

        User user = userDao.find(userInputDto.getId());
        Role role = roleDao.find(userInputDto.getRoleId());
        if (role == null || user == null) {
            throw new ServiceUncheckedException("role or user not found");
        }

        roleDao.find(user.getRole().getId()).getUsers().remove(user);

        user.setRole(role);
        userDao.persist(user);

        role.getUsers().add(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userDao.findByLogin(login);
        if (user != null) return modelMapper.map(user, UserDto.class);
        return null;
    }

    @Override
    public void addSubject(Integer userId, Integer subjectId) {
        User user = userDao.find(userId);
        Subject subject = subjectDao.find(subjectId);
        if (user == null || subject == null || user.getSubjects().contains(subject)) {
            throw new ServiceUncheckedException("wrong input or user already contains this subject");
        }

        user.getSubjects().add(subject);
    }

    @Override
    public void deleteSubject(Integer userId, Integer subjectId) {
        User user = userDao.find(userId);
        Subject subject = subjectDao.find(subjectId);
        if (user == null || subject == null || !user.getSubjects().contains(subject)) {
            throw new ServiceUncheckedException("wrong input or user hasn't this subject");
        }

        subjectDao.delete(subjectId);
        user.getSubjects().remove(subject);
    }

    @Override
    public List<SubjectDto> getSubjects(Integer userId) {
        User user = userDao.find(userId);
        if (user == null) {
            throw new ServiceUncheckedException("user not found");
        }

        return user.getSubjects()
                .stream()
                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    protected Class<User> getBeanClass() {
        return User.class;
    }

    protected Class<UserDto> getDtoClass() {
        return UserDto.class;
    }

    protected DtoValidator<UserDto> getValidator() {
        return userValidator;
    }
}
