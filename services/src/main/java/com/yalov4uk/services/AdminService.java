package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.IStatementDao;
import com.yalov4uk.interfaces.IUserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService extends BaseService implements IAdminService {

    private final IStatementDao statementDao;
    private final IUserDao userDao;
    private final IFacultyDao facultyDao;

    @Autowired
    public AdminService(ModelMapper modelMapper, IStatementDao statementDao, IUserDao userDao, IFacultyDao facultyDao) {
        super(modelMapper);
        this.statementDao = statementDao;
        this.userDao = userDao;
        this.facultyDao = facultyDao;
    }

    public StatementDto registerStatement(Integer userId, Integer facultyId) {
        User user = userDao.find(userId);
        Faculty faculty = facultyDao.find(facultyId);
        if (user == null || faculty == null || !faculty.getRegisteredUsers().contains(user)) {
            throw new ServiceUncheckedException("Wrong input or user not registered to faculty");
        }

        Statement statement = new Statement();
        statement.setFaculty(faculty);
        statement.setUser(user);
        statementDao.persist(statement);
        faculty.getRegisteredUsers().remove(user);
        return modelMapper.map(statement, StatementDto.class);
    }

    public List<StatementDto> calculateEntrants(Integer facultyId) {
        Faculty faculty = facultyDao.find(facultyId);
        if (faculty == null) {
            throw new ServiceUncheckedException("faculty not found");
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
    }
}
