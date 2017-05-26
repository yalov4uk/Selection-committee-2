package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.IUserService;
import dto.SubjectDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class UserService extends BaseCrudService<User, UserDto> implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private ISubjectDao subjectDao;

    protected IBaseDao<User> getDao() {
        return userDao;
    }

    public UserDto create(UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            userDao.persist(user);

            roleDao.find(user.getRole().getId()).getUsers().add(user);

            return modelMapper.map(user, UserDto.class);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    @Override
    public UserDto delete(Integer key) {
        try {
            User user = userDao.find(key);
            userDao.delete(key);

            roleDao.find(user.getRole().getId()).getUsers().remove(user);
            return null;
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            return userDao.findByLogin(login);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public void addSubject(UserDto userDto, SubjectDto subjectDto) {
        try {
            User user = userDao.find(userDto.getId());
            Subject subject = subjectDao.find(subjectDto.getId());
            if (user == null || subject == null || user.getSubjects().contains(subject)) {
                throw new NotFoundException();
            }

            user.getSubjects().add(subject);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public void deleteSubject(UserDto userDto, SubjectDto subjectDto) {
        try {
            User user = userDao.find(userDto.getId());
            Subject subject = subjectDao.find(subjectDto.getId());
            if (user == null || subject == null || user.getSubjects().contains(subject)) {
                throw new NotFoundException();
            }

            user.getSubjects().remove(subject);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    protected Class<User> getBeanClass() {
        return User.class;
    }

    protected Class<UserDto> getDtoClass() {
        return UserDto.class;
    }
}
