package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.IStatementDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.IStatementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatementService extends BaseCrudService<Statement, StatementDto> implements IStatementService {

    private final IStatementDao statementDao;
    private final IFacultyDao facultyDao;
    private final IUserDao userDao;

    @Autowired
    public StatementService(ModelMapper modelMapper, IStatementDao statementDao, IFacultyDao facultyDao, IUserDao userDao) {
        super(modelMapper);
        this.statementDao = statementDao;
        this.facultyDao = facultyDao;
        this.userDao = userDao;
    }

    @Override
    public StatementDto create(StatementDto statementDto) {
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

    protected IBaseDao<Statement> getDao() {
        return statementDao;
    }

    protected Class<Statement> getBeanClass() {
        return Statement.class;
    }

    protected Class<StatementDto> getDtoClass() {
        return StatementDto.class;
    }
}
