package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.IStatementDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.IStatementService;
import dto.StatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class StatementService extends BaseCrudService<Statement, StatementDto> implements IStatementService {

    @Autowired
    private IStatementDao statementDao;
    @Autowired
    private IFacultyDao facultyDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public StatementDto create(StatementDto statementDto) {
        try {
            Statement statement = modelMapper.map(statementDto, Statement.class);
            statementDao.persist(statement);

            facultyDao.find(statement.getFaculty().getId()).getStatements().add(statement);
            userDao.find(statement.getUser().getId()).getStatements().add(statement);

            return modelMapper.map(statement, StatementDto.class);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    @Override
    public StatementDto delete(Integer key) {
        try {
            Statement statement = statementDao.find(key);
            statementDao.delete(key);

            facultyDao.find(statement.getFaculty().getId()).getStatements().remove(statement);
            userDao.find(statement.getUser().getId()).getStatements().remove(statement);
            return null;
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
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
