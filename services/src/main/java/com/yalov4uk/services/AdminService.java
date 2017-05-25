package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.IStatementDao;
import com.yalov4uk.interfaces.IUserDao;
import dto.FacultyDto;
import dto.StatementDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/3/17.
 */
@Service
public class AdminService extends BaseService implements IAdminService {

    @Autowired
    private IStatementDao statementDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IFacultyDao facultyDao;

    public StatementDto registerStatement(UserDto userDto, FacultyDto facultyDto) {
        try {
            User user = userDao.find(userDto.getId());
            Faculty faculty = facultyDao.find(facultyDto.getId());
            if (user == null || faculty == null || !faculty.getRegisteredUsers().contains(user)) {
                throw new NotFoundException();
            }

            Statement statement = new Statement();
            statement.setFaculty(faculty);
            statement.setUser(user);
            statementDao.persist(statement);
            faculty.getRegisteredUsers().remove(user);
            return modelMapper.map(statement, StatementDto.class);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public List<StatementDto> calculateEntrants(Integer facultyId) {
        try {
            Faculty faculty = facultyDao.find(facultyId);
            if (faculty == null) {
                throw new NotFoundException();
            }
            return statementDao.getAll()
                    .stream()
                    .filter(statement -> statement.getFaculty().equals(faculty))
                    .sorted((a, b) -> {
                        int x = a.getUser().getAverageScore(a.getFaculty());
                        int y = b.getUser().getAverageScore(b.getFaculty());
                        return y - x;
                    })
                    .limit(faculty.getMaxSize())
                    .map(statement -> modelMapper.map(statement, StatementDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }
}
