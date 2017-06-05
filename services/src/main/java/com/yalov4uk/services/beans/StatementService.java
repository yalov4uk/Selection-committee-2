package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.dto.input.StatementInputDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.IStatementDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.IStatementService;
import com.yalov4uk.interfaces.validators.DtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatementService extends BaseCrudService<Statement, StatementDto> implements IStatementService {

    private final IStatementDao statementDao;
    private final IFacultyDao facultyDao;
    private final IUserDao userDao;
    private final DtoValidator<StatementDto> statementValidator;

    @Autowired
    public StatementService(ModelMapper modelMapper, IStatementDao statementDao, IFacultyDao facultyDao,
                            IUserDao userDao, DtoValidator<StatementDto> statementValidator) {
        super(modelMapper);
        this.statementDao = statementDao;
        this.facultyDao = facultyDao;
        this.userDao = userDao;
        this.statementValidator = statementValidator;
    }

    @Override
    public StatementDto create(StatementDto statementDto) {
        statementValidator.validate(statementDto);
        Statement statement = modelMapper.map(statementDto, Statement.class);
        statementDao.persist(statement);

        facultyDao.find(statement.getFaculty().getId()).getStatements().add(statement);
        userDao.find(statement.getUser().getId()).getStatements().add(statement);

        return modelMapper.map(statement, StatementDto.class);
    }

    @Override
    public void delete(Integer key) {
        Statement statement = statementDao.find(key);
        statementDao.delete(key);

        facultyDao.find(statement.getFaculty().getId()).getStatements().remove(statement);
        userDao.find(statement.getUser().getId()).getStatements().remove(statement);
    }

    @Override
    public StatementDto update(StatementInputDto statementInputDto) {
        if (statementInputDto == null || statementInputDto.getId() == null ||
                statementInputDto.getFacultyId() == null || statementInputDto.getUserId() == null) {
            throw new ServiceUncheckedException("wrong input");
        }
        Statement statement = statementDao.find(statementInputDto.getId());
        User user = userDao.find(statementInputDto.getUserId());
        Faculty faculty = facultyDao.find(statementInputDto.getFacultyId());
        if (statement == null || user == null || faculty == null) {
            throw new ServiceUncheckedException("statement or user or faculty not found");
        }
        facultyDao.find(statement.getFaculty().getId()).getStatements().remove(statement);
        userDao.find(statement.getUser().getId()).getStatements().remove(statement);

        statement.setUser(user);
        statement.setFaculty(faculty);

        user.getStatements().add(statement);
        faculty.getStatements().add(statement);
        return modelMapper.map(statementDao.update(statement), StatementDto.class);
    }

    protected IBaseDao<Statement> getDao() {
        return statementDao;
    }

    protected Class<Statement> getBeanClass() {
        return Statement.class;
    }

    protected Class<StatementDto> getDtoClass() {
        return StatementDto.class;
    }

    protected DtoValidator<StatementDto> getValidator() {
        return statementValidator;
    }
}
